package com.example.rabbitmqspringbootexample.direct.receiver;

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
/* Todo : This configuration should come as a common configuration for MessageSender and MessageReciever. But I want to show sender and receiver as a separate application
    therefore, I have added separate MessageConfig for MessageSender and  MessageReciever. */
@Configuration
public class MessageConfig {
    public static final String QUEUE_NAME_1 = "myboot_direct_queue1";
    public static final String QUEUE_NAME_2 = "myboot_direct_queue2";
    public static final String QUEUE_NAME_3 = "myboot_direct_queue3";

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
