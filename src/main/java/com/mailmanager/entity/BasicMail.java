package com.mailmanager.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicMail extends BaseEntity{
    private String sender;
    private String to;
    private String subject;
    private String text;
    @Builder.Default
    private Boolean isHtml = false;
}
