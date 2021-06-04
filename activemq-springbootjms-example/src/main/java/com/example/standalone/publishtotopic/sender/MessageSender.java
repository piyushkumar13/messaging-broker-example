package com.example.standalone.publishtotopic.sender;

import com.example.standalone.domain.Student;
import java.util.UUID;
import javax.jms.Topic;
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
    private Topic topic;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMsgAsText() {
        System.out.println("Sending message as Text");

        UUID uuid = UUID.randomUUID();
        System.out.println("ID is ::: " + uuid);

        String msg = "Simple text message with id ::: " + uuid;
        jmsTemplate.convertAndSend(topic, msg);

        System.out.println("Message sent ::: " + msg);
    }

    public void sendMsgAsJson() {
        System.out.println("Sending message as JSON");

        UUID uuid = UUID.randomUUID();
        System.out.println("ID is ::: " + uuid);

        Student student = new Student(uuid.toString(), "Piyush", "Computer Science");

        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTargetType(MessageType.TEXT);
        messageConverter.setTypeIdPropertyName("_type");

        /* Just for the sake of having separate method for json, I have set the json message converter here.
        However, better way is to create a bean of message converter in configuration. Please check commented section in MessageConfig class. */
        jmsTemplate.setMessageConverter(messageConverter);
        jmsTemplate.convertAndSend(topic, student);

        System.out.println("Message sent as JSON for object ::: " + student);

    }
}
