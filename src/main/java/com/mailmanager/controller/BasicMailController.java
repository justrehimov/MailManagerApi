package com.mailmanager.controller;

import com.mailmanager.entity.BasicMail;
import com.mailmanager.service.BasicMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/basic-mail")
@RequiredArgsConstructor
public class BasicMailController {

    private final BasicMailService basicMailService;

    @PostMapping("/send")
    public void send(@Valid @RequestBody BasicMail basicMail){
        basicMailService.send(basicMail);

    }
}
