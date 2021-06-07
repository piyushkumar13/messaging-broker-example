package com.example.rabbitmqspringbootexample.deadletterq.sender;

import com.example.rabbitmqspringbootexample.domain.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.springframework.amqp.core.AmqpTemplate;
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

    private static final String ROUTING_KEY_1 = "myboot.direct.queue.1";
    private static final String ROUTING_KEY_2 = "myboot.direct.queue.2";
    private static final String ROUTING_KEY_3 = "myboot.direct.queue.3";

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
        amqpTemplate.convertAndSend(MessageConfig.EXCHANGE_NAME, ROUTING_KEY_3, msg);
        System.out.println("sent msg is ::: " + msg);
    }
}
