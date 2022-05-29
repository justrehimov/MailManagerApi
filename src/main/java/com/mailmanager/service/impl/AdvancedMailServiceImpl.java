package com.mailmanager.service.impl;

import com.mailmanager.entity.User;
import com.mailmanager.service.AdvancedMailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;

@RequiredArgsConstructor
@Service
public class AdvancedMailServiceImpl implements AdvancedMailService {

    @Qualifier(value = "system")
    private final JavaMailSenderImpl javaMailSender;
    private final UserServiceImpl userService;
    @Override
    @SneakyThrows
    public void send(String sender, String to, String subject, String text, MultipartFile multipartFile) {
        User user = userService.loggedUser();
        javaMailSender.setUsername(user.getEmail());
        javaMailSender.setPassword(user.getMailPassword());
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        File f = new File("src/main/resources/"+multipartFile.getOriginalFilename());
        f.createNewFile();
        byte[] arr = IOUtils.toByteArray(multipartFile.getInputStream());
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(arr);
        fos.close();
        helper.addAttachment(multipartFile.getOriginalFilename(), f);
        helper.setFrom(sender);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        javaMailSender.send(message);
        f.delete();
    }
}
