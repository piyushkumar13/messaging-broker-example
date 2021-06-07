package com.example.rabbitmqspringbootexample.direct.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Piyush Kumar.
 * @since 06/06/21.
 */

@SpringBootApplication
public class MessageReceiver {

    @RabbitListener(queues = MessageConfig.QUEUE_NAME_1)
    public void consumeMsg1(Message message){

        System.out.println("Listener1 : Received msg is ::: " + new String(message.getBody()));
    }

    @RabbitListener(queues = MessageConfig.QUEUE_NAME_2)
    public void consumeMsg2(Message message){

        System.out.println("Listener2 : Received msg is ::: " + new String(message.getBody()));
    }

    @RabbitListener(queues = MessageConfig.QUEUE_NAME_3)
    public void consumeMsg3(Message message){

        System.out.println("Listener3 : Received msg is ::: " + new String(message.getBody()));
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageReceiver.class, args);
    }
}
