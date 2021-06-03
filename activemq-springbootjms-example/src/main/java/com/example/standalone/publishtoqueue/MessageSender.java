package com.example.standalone.publishtoqueue;

import com.example.standalone.domain.Student;
import java.util.UUID;
import javax.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */

@Component
public class MessageSender {

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMsgAsText() {
        System.out.println("Sending message as Text");

        UUID uuid = UUID.randomUUID();
        System.out.println("ID is ::: " + uuid);

        String msg = "Simple text message with id ::: " + uuid;
        jmsTemplate.convertAndSend(queue, msg);

        System.out.println("Message sent ::: " + msg);
    }

    public void sendMsgAsJson() {
        System.out.println("Sending message as JSON");

        UUID uuid = UUID.randomUUID();
        System.out.println("ID is ::: " + uuid);

        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTargetType(MessageType.TEXT);
        messageConverter.setTypeIdPropertyName("_type");

        /* Just for the sake of having separate method for json, I have set the json message converter here.
        However, better way is to create a bean of message converter in configuration. Please check commented section in MessageConfig class. */
        jmsTemplate.setMessageConverter(messageConverter);
        jmsTemplate.convertAndSend(queue, new Student(uuid.toString(), "Piyush", "Computer Science"));

        System.out.println("Message sent as JSON");

    }
}