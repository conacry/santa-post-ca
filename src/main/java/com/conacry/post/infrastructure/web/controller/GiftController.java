package com.conacry.post.infrastructure.web.controller;

import com.conacry.post.application.usecase.RequestGift;
import com.conacry.post.infrastructure.web.mapper.EmailMapper;
import com.conacry.post.infrastructure.web.dto.EmailToSanta;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/web/v1/")
public class GiftController {

    private final EmailMapper emailMapper;
    private final RequestGift requestGift;

    public GiftController(EmailMapper emailMapper, RequestGift requestGift) {
        this.emailMapper = emailMapper;
        this.requestGift = requestGift;
    }

    @PostMapping(path = "sendEmailToSanta")
    public void sendEmailToSanta(@RequestBody EmailToSanta email) {
        var data = emailMapper.fromEmailToRequest(email);
        requestGift.execute(data);
    }
}
