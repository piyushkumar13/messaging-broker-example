package com.example.rabbitmqspringbootexample.deadletterq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
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
    public static final String DLQ_EXCHANGE_NAME = "myboot_direct_dlx";

    private static final String QUEUE_NAME_1 = "myboot_direct_queue1";
    private static final String QUEUE_NAME_2 = "myboot_direct_queue2";
    private static final String QUEUE_NAME_3 = "myboot_direct_queue3";

    private static final String DLQ_NAME_1 = "myboot_direct_dlq1";
    private static final String DLQ_NAME_2 = "myboot_direct_dlq2";
    private static final String DLQ_NAME_3 = "myboot_direct_dlq3";

    private static final String DLQ_RK_1 = "myboot.direct.dlq1";
    private static final String DLQ_RK_2 = "myboot.direct.dlq2";
    private static final String DLQ_RK_3 = "myboot.direct.dlq3";

    /* In the below queues, I have configured them to point to the same routing key i.e to the same dead-letter-queue. However, we can make each queue to have its own DLQ.
    *  Also, we can use any kind of exchange for dead letter exchange. */
    @Bean
    public Queue queue1() {

        return QueueBuilder
                .durable(QUEUE_NAME_1)
                .withArgument("x-dead-letter-exchange", DLQ_EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", DLQ_RK_1).build();
    }

    @Bean
    public Queue queue2() {
        return QueueBuilder
                .durable(QUEUE_NAME_2)
                .withArgument("x-dead-letter-exchange", DLQ_EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", DLQ_RK_1).build();
    }

    @Bean
    public Queue queue3() {
        return QueueBuilder
                .durable(QUEUE_NAME_3)
                .withArgument("x-dead-letter-exchange", DLQ_EXCHANGE_NAME) //  here instead of giving/creating separate direct exchange, we can just put "" which will consider the base direct exchange which is used for normal queues configured here.
                .withArgument("x-dead-letter-routing-key", DLQ_RK_1).build();
    }

    @Bean
    public Queue dlq1() {
        return QueueBuilder
                .durable(DLQ_NAME_1).build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public DirectExchange dlqExchange() {
        return new DirectExchange(DLQ_EXCHANGE_NAME);
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
    public Binding bindingdlq1(Queue dlq1, DirectExchange dlqExchange) {
        return BindingBuilder.bind(dlq1).to(dlqExchange).with("myboot.direct.dlq1");
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
