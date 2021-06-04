package com.example.standalone.publishtotopic.receiver.nondurablesubs;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */

@Component
public class MessageReceiver {

    @JmsListener(destination = "my_topic")
    public void receiveMessage1(Message msg) throws JMSException {

        System.out.println("Listener 1 : The received message object is ::: " + msg);

        if (msg instanceof TextMessage){
            TextMessage textMessage = (TextMessage) msg;
            System.out.println("Listener 1 : The text message is ::: " + textMessage.getText()); // If message is json sent by sender, then we will receive it as String, which we can deserialize to object.
        }
    }

    @JmsListener(destination = "my_topic")
    public void receiveMessage2(Message msg) throws JMSException {

        System.out.println("Listener 2 : The received message object is ::: " + msg);

        if (msg instanceof TextMessage){
            TextMessage textMessage = (TextMessage) msg;
            System.out.println("Listener 2 : The text message is ::: " + textMessage.getText()); // If message is json sent by sender, then we will receive it as String, which we can deserialize to object.
        }
    }
}
