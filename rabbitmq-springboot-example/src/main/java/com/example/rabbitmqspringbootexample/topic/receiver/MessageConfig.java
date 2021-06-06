package com.example.rabbitmqspringbootexample.topic.receiver;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Piyush Kumar.
 * @since 06/06/21.
 */

@Configuration
public class MessageConfig {
    private static final String QUEUE_NAME = "myboot_queue";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

//  Below config is only required for MessageListener2 class i.e when you implement MessageListener interface instead of using @RabbitListener annotation
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME);
//    }
//
//    @Bean
//    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, Queue queue){
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//        simpleMessageListenerContainer.setQueues(queue);
//        simpleMessageListenerContainer.setMessageListener(new MessageListener2());
//        return simpleMessageListenerContainer;
//    }

}
