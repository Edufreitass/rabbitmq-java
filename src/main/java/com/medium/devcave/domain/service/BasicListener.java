package com.medium.devcave.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BasicListener implements MessageListener {

    private final ObjectMapper objectMapper;

    public BasicListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        log.info("receive message from queue {}", message.getMessageProperties().getConsumerQueue());
        byte[] bodyAsString = message.getBody();
        log.info("body {}", objectMapper.readValue(bodyAsString, String.class));
    }
}
