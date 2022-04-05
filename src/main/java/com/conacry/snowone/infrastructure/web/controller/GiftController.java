package com.conacry.snowone.infrastructure.web.controller;

import com.conacry.snowone.application.usecase.RequestGift;
import com.conacry.snowone.infrastructure.web.dto.EmailToSanta;
import com.conacry.snowone.infrastructure.web.mapper.EmailMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/")
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
