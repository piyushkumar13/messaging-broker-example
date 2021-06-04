package com.example.standalone.publishtotopic.receiver.durablesubs;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */

@Configuration
public class MessageReceiverConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        return connectionFactory;

    }

    @Bean
    DefaultJmsListenerContainerFactory customJmsListenerContainerFactory1() {
        DefaultJmsListenerContainerFactory listenerFactory = new DefaultJmsListenerContainerFactory();
        listenerFactory.setClientId("12345");
        listenerFactory.setConnectionFactory(connectionFactory());
        listenerFactory.setSubscriptionDurable(true);

        return listenerFactory;
    }

    @Bean
    DefaultJmsListenerContainerFactory customJmsListenerContainerFactory2() {
        DefaultJmsListenerContainerFactory listenerFactory = new DefaultJmsListenerContainerFactory();
        listenerFactory.setClientId("123");
        listenerFactory.setConnectionFactory(connectionFactory());
        listenerFactory.setSubscriptionDurable(true);

        return listenerFactory;
    }
}