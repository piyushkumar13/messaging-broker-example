package org.example.publishtoexchange.direct;

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
public class MessageReceiver {

    private static final String EXCHANGE_NAME = "my_direct_exchange";
    private static final String BINDING_KEY_1 = "error";
    private static final String BINDING_KEY_2 = "warning";


    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

//        String queueName = channel.queueDeclare().getQueue();
        String queueName = channel.queueDeclare("direct_myqueue", false, false, false, null).getQueue(); // better to give name to the queue, else default name will be given to the binded queue and you will not be able to see messages in it on the rabbitmq UI.

        /* Same queue with multiple binding keys(i.e routing keys)*/
        channel.queueBind(queueName, EXCHANGE_NAME, BINDING_KEY_1);
        channel.queueBind(queueName, EXCHANGE_NAME, BINDING_KEY_2);

        DeliverCallback deliverCallback = (consumerTag, message) -> {

            System.out.println("The consumerTag is ::: " + consumerTag);
            System.out.println("The received message is ::: " + new String(message.getBody(), StandardCharsets.UTF_8) + " with routing key = " + message.getEnvelope().getRoutingKey());

        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
