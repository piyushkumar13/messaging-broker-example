package com.example.rabbitmqspringbootexample.topic.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author Piyush Kumar.
 * @since 07/06/21.
 */

@Component
public class MessageListener2 implements MessageListener {
    @Override
    public void onMessage(Message message) {

        System.out.println("The message from listener 2 is :: " + message.getBody());

    }
}
