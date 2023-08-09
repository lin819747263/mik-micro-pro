package com.mik.rockmq.controller;

import com.mik.rockmq.service.Sender;
import org.apache.rocketmq.client.apis.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mq")
public class RockMqTest {

    @Autowired
    Sender sender;


    @GetMapping("/send")
    public void send() throws ClientException {
        sender.send();
    }




}
