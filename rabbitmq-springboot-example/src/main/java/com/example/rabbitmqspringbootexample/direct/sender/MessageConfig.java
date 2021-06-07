package com.example.rabbitmqspringbootexample.direct.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
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

    public static final String EXCHANGE_NAME = "myboot_direct_exchange";

    private static final String QUEUE_NAME_1 = "myboot_direct_queue1";
    private static final String QUEUE_NAME_2 = "myboot_direct_queue2";
    private static final String QUEUE_NAME_3 = "myboot_direct_queue3";

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_NAME_1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_NAME_2);
    }

    @Bean
    public Queue queue3() {
        return new Queue(QUEUE_NAME_3);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingQueue1(Queue queue1, DirectExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with("myboot.direct.queue.1");
    }

    @Bean
    public Binding bindingQueue2(Queue queue2, DirectExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with("myboot.direct.queue.2");
    }

    @Bean
    public Binding bindingQueue3(Queue queue3, DirectExchange exchange) {
        return BindingBuilder.bind(queue3).to(exchange).with("myboot.direct.queue.3");
    }

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
}
