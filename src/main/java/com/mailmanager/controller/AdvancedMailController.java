package com.mailmanager.controller;

import com.mailmanager.service.AdvancedMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/advanced-mail")
@RequiredArgsConstructor
public class AdvancedMailController {

    private final AdvancedMailService advancedMailService;

    @PostMapping(value = "/send",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void send(@RequestPart("sender") String sender, @RequestPart("to") String to, @RequestPart("subject") String subject,
                     @RequestPart("text") String text, @RequestPart("file")MultipartFile file){
        advancedMailService.send(sender,to,subject,text,file);
    }

}
