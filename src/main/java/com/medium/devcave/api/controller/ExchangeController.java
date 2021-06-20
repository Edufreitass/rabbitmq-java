package com.medium.devcave.api.controller;

import com.medium.devcave.domain.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    private final RabbitTemplate rabbitTemplate;

    public ExchangeController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/{exchange}/{routingKey}")
    public ResponseEntity<?> postOnExchange(@PathVariable String exchange,
                                            @PathVariable String routingKey,
                                            @RequestBody String message) {
        log.info("sending message {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/json/{exchange}/{routingKey}")
    public ResponseEntity<?> postJsonOnExchange(@PathVariable String exchange,
                                                @PathVariable String routingKey,
                                                @RequestBody Person message) {
        log.info("sending message {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return ResponseEntity.ok().build();
    }

}
