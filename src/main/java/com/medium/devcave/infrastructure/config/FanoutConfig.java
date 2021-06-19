package com.medium.devcave.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    public static final String FANOUT_EXCHANGE_BASIC = "FANOUT-EXCHANGE-BASIC";

    private final Queue firstQueue;
    private final Queue secondQueue;

    public FanoutConfig(Queue firstQueue, Queue secondQueue) {
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
    }

    @Bean
    public Exchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange(FANOUT_EXCHANGE_BASIC)
                .durable(true)
                .build();
    }

    @Bean
    public Binding firstFanoutBinding() {
        return BindingBuilder
                .bind(firstQueue)
                .to(fanoutExchange())
                .with("")
                .noargs();
    }

    @Bean
    public Binding secondFanoutBinding() {
        return BindingBuilder
                .bind(secondQueue)
                .to(fanoutExchange())
                .with("")
                .noargs();
    }

}
