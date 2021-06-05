package org.example.publishtoexchange.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author Piyush Kumar.
 * @since 02/06/21.
 */
public class MessageReceiverWithMultipleQueues {

    private static final String EXCHANGE_NAME = "my_topic_exchange";
    private static final String BINDING_KEY_1 = "*.*.example";
    private static final String BINDING_KEY_2 = "*.example";


    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        /* Also, making exchange durable which will be there even after rabbitmq broker restarts. */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true);

//        String queueName = channel.queueDeclare().getQueue();
        /* Each queue with one binding key. However, we can associate multiple keys with each queues. */
        String queueName1 = channel.queueDeclare("myqueue1", true, false, false, null).getQueue(); // better to give name to the queue, else default name will be given to the binded queue and you will not be able to see messages in it on the rabbitmq UI. And also, queue is durable.
        channel.queueBind(queueName1, EXCHANGE_NAME, BINDING_KEY_1);

        String queueName2 = channel.queueDeclare("myqueue2", true, false, false, null).getQueue(); // better to give name to the queue, else default name will be given to the binded queue and you will not be able to see messages in it on the rabbitmq UI. And also, queue is durable.
        channel.queueBind(queueName2, EXCHANGE_NAME, BINDING_KEY_2);
        DeliverCallback deliverCallback = (consumerTag, message) -> {

            System.out.println("The consumerTag is ::: " + consumerTag);
            System.out.println("The received message is ::: " + new String(message.getBody(), StandardCharsets.UTF_8) + " with routing key = " + message.getEnvelope().getRoutingKey());

        };
        channel.basicConsume(queueName1, true, deliverCallback, consumerTag -> {});
        channel.basicConsume(queueName2, true, deliverCallback, consumerTag -> {});

    }
}
