package com.medium.devcave.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medium.devcave.domain.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class JsonConsumer {

    public static final String JSON_QUEUE_BASIC = "JSON-QUEUE-BASIC";

    private final ObjectMapper objectMapper;

    public JsonConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = JSON_QUEUE_BASIC)
    public void receiveMessageFromJsonQueue(Message message) throws IOException {
        log.info("receive message from queue {}", message.getMessageProperties().getConsumerQueue());
        byte[] bodyAsJson = message.getBody();
        log.info("body {}", objectMapper.readValue(bodyAsJson, Person.class));
    }

}
