package com.medium.devcave.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    public static final String DIRECT_EXCHANGE_BASIC = "DIRECT-EXCHANGE-BASIC";
    public static final String TO_FIRST_QUEUE = "TO-FIRST-QUEUE";
    public static final String TO_SECOND_QUEUE = "TO-SECOND-QUEUE";
    public static final String TO_JSON_QUEUE = "TO-JSON-QUEUE";

    private final Queue firstQueue;
    private final Queue secondQueue;
    private final Queue jsonQueue;

    public DirectConfig(Queue firstQueue, Queue secondQueue, Queue jsonQueue) {
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
        this.jsonQueue = jsonQueue;
    }

    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder
                .directExchange(DIRECT_EXCHANGE_BASIC)
                .durable(true)
                .build();
    }

    @Bean
    public Binding firstDirectBinding() {
        return BindingBuilder
                .bind(firstQueue)
                .to(directExchange())
                .with(TO_FIRST_QUEUE)
                .noargs();
    }

    @Bean
    public Binding secondDirectBinding() {
        return BindingBuilder
                .bind(secondQueue)
                .to(directExchange())
                .with(TO_SECOND_QUEUE)
                .noargs();
    }

    @Bean
    public Binding jsonDirectBinding() {
        return BindingBuilder
                .bind(jsonQueue)
                .to(directExchange())
                .with(TO_JSON_QUEUE)
                .noargs();
    }

}
