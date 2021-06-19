package com.medium.devcave.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    public static final String FIRST_QUEUE_BASIC = "FIRST-QUEUE-BASIC";
    public static final String SECOND_QUEUE_BASIC = "SECOND-QUEUE-BASIC";

    @Bean
    public Queue firstQueue() {
        return QueueBuilder
                .durable(FIRST_QUEUE_BASIC)
                .build();
    }

    @Bean
    public Queue secondQueue() {
        return QueueBuilder
                .durable(SECOND_QUEUE_BASIC)
                .build();
    }
}
