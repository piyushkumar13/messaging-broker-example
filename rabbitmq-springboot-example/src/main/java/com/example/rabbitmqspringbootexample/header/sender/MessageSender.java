package com.example.rabbitmqspringbootexample.header.sender;

import com.example.rabbitmqspringbootexample.domain.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageBuilderSupport;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Piyush Kumar.
 * @since 06/06/21.
 */

@SpringBootApplication
public class MessageSender implements CommandLineRunner {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static String getTextMessage() {
        UUID uuid = UUID.randomUUID();

        return "Hi! This is Piyush with ID = " + uuid;
    }

    private static String getJsonMsgAsText() throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();

        String jsonMsg = new ObjectMapper().writeValueAsString(new Student(uuid.toString(), "Piyush", "Java"));
        return jsonMsg;
    }

    private static Student getStudentObj() throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();

        return new Student(uuid.toString(), "Piyush", "Java");
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageSender.class, args).close();

    }


    @Override
    public void run(String... args) throws Exception {
        Student student = getStudentObj();
        String msg = getJsonMsgAsText();

//        amqpTemplate.convertAndSend(MessageConfig.EXCHANGE_NAME, ROUTING_KEY, student);
//        System.out.println("sent msg is ::: " + student);

        /* When the message being sent as string(i.e as json string NOTE not as json but as json string)
           then the message will be received as string only in MessageReceiver and you dont need MessageConfig class in receiver package. */

        Message message = MessageBuilder
                .withBody(msg.getBytes())
                .setHeader("myboot.header.queue", "3")
                .build();

        amqpTemplate.convertAndSend(MessageConfig.EXCHANGE_NAME, "",message);

        System.out.println("sent msg is ::: " + new String(message.getBody()));
        System.out.println("sent Message object is ::: " + message);
    }
}
