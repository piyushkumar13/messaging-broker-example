package org.example.publishtoexchange.topic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
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

    private static final String EXCHANGE_NAME = "my_topic_exchange";
    private static final String ROUTING_KEY = "my.topic.example";

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

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

//            String msg = getTextMessage();
            String msg = getJsonMsgAsText();
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes(StandardCharsets.UTF_8));

            System.out.println("Message sent is ::: " + msg + " with routing key = " + ROUTING_KEY);
        }
    }
}
