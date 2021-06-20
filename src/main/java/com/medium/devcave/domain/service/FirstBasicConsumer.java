package com.medium.devcave.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class FirstBasicConsumer {

    public static final String FIRST_QUEUE_BASIC = "FIRST-QUEUE-BASIC";

    private final ObjectMapper objectMapper;

    public FirstBasicConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = FIRST_QUEUE_BASIC)
    public void receiveMessageFromFirstQueue(Message message) throws IOException {
        log.info("receive message from queue {}", message.getMessageProperties().getConsumerQueue());
        byte[] bodyAsString = message.getBody();
        log.info("body {}", objectMapper.readValue(bodyAsString, String.class));
    }

}
