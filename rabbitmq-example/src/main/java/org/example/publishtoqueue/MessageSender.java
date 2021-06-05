package org.example.publishtoqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import org.example.domain.Student;

/**
 * @author Piyush Kumar.
 * @since 02/06/21.
 */
public class MessageSender {

    private static final String QUEUE_NAME = "my_queue";

    private static String getTextMessage() {
        UUID uuid = UUID.randomUUID();

        return "Hi! This is Piyush with ID = " + uuid;
    }

    private static String getJsonMsgAsText() throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();

        String jsonMsg = new ObjectMapper().writeValueAsString(new Student(uuid.toString(), "Piyush", "Java"));
        return jsonMsg;
    }

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()){

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

//            String msg = getTextMessage();
            String msg = getJsonMsgAsText();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));

            System.out.println("Message sent is ::: " + msg);
        }
    }
}
