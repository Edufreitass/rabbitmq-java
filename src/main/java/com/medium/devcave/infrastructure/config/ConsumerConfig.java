package com.medium.devcave.infrastructure.config;

import com.medium.devcave.domain.service.BasicListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    public static final String SECOND_QUEUE_BASIC = "SECOND-QUEUE-BASIC";

    private final ConnectionFactory connectionFactory;
    private final BasicListener basicListener;
    private final SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory;

    public ConsumerConfig(ConnectionFactory connectionFactory, BasicListener basicListener, SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory) {
        this.connectionFactory = connectionFactory;
        this.basicListener = basicListener;
        this.simpleRabbitListenerContainerFactory = simpleRabbitListenerContainerFactory;
    }

    @Bean
    public MessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SECOND_QUEUE_BASIC);
        container.setMessageListener(basicListener);
        simpleRabbitListenerContainerFactory.getAdviceChain();
        return container;
    }

}
