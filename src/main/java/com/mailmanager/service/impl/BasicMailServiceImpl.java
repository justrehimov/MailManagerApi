package com.mailmanager.service.impl;

import com.mailmanager.entity.BasicMail;
import com.mailmanager.entity.User;
import com.mailmanager.service.BasicMailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class BasicMailServiceImpl implements BasicMailService {

    @Qualifier("user")
    private final JavaMailSenderImpl javaMailSender;
    private final UserServiceImpl userService;
    @Override
    @SneakyThrows
    public void send(BasicMail basicMail) {
        User user = userService.loggedUser();
        javaMailSender.setUsername(user.getEmail());
        javaMailSender.setPassword(user.getMailPassword());
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(basicMail.getSender());
        helper.setTo(basicMail.getTo());
        helper.setSubject(basicMail.getSubject());
        helper.setText(basicMail.getText(), basicMail.getIsHtml());
        javaMailSender.send(message);
    }

}
