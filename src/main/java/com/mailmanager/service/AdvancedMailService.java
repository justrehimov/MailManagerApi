package com.mailmanager.service;

import org.springframework.web.multipart.MultipartFile;

public interface AdvancedMailService {
    void send(String sender, String to, String subject, String text, MultipartFile file);
}
