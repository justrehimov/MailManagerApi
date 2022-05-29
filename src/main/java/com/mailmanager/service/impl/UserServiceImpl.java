package com.mailmanager.service.impl;

import com.mailmanager.dto.request.UserRequest;
import com.mailmanager.entity.ConfirmationToken;
import com.mailmanager.entity.User;
import com.mailmanager.enums.Error;
import com.mailmanager.exception.TokenException;
import com.mailmanager.exception.UserException;
import com.mailmanager.repository.ConfirmationTokenRepo;
import com.mailmanager.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final ConfirmationTokenRepo confirmationTokenRepo;
    @Qualifier("system")
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new UserException(Error.USER_NOT_FOUND));
        return user;
    }

    public void register(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepo.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(savedUser);
        ConfirmationToken savedToken = confirmationTokenRepo.save(confirmationToken);
        sendConfirmMail(savedToken);
    }

    @SneakyThrows
    private void sendConfirmMail(ConfirmationToken token){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String link = "http://localhost:8080/auth/confirm/"+token.getToken();
        helper.setFrom("Rehimov-Company");
        helper.setTo(token.getUser().getEmail());
        helper.setSubject("Confirm your mail address");
        helper.setText("Follow this " + link + " link and confirm your mail address");
        javaMailSender.send(message);
    }

    @SneakyThrows
    public void confirmUser(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepo.findConfirmationTokenByToken(token)
                .orElseThrow(()->new TokenException(Error.TOKEN_IS_INVALID));
        if(!confirmationToken.getExpiredDate().after(new Date()))
            throw new TokenException(Error.TOKEN_IS_INVALID);
        activateUser(confirmationToken.getUser().getId());
        confirmationTokenRepo.delete(confirmationToken);
    }

    private void activateUser(Long id){
       userRepo.setActive(id,true);
    }

    public User loggedUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
