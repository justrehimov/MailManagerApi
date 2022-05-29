package com.mailmanager.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AdvancedMail extends BaseEntity{
    private String sender;
    private String to;
    private String subject;
    private String text;
    private MultipartFile file;
}
