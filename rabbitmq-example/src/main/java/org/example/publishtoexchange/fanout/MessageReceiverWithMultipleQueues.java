package org.example.publishtoexchange.fanout;

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

    private static final String EXCHANGE_NAME = "my_fanout_exchange";


    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

//        String queueName = channel.queueDeclare().getQueue();
        /* Each queue with one binding key. However, we can associate multiple keys with each queues. */
        String queueName1 = channel.queueDeclare("fanout_myqueue1", false, false, false, null).getQueue(); // better to give name to the queue, else default name will be given to the binded queue and you will not be able to see messages in it on the rabbitmq UI.
        channel.queueBind(queueName1, EXCHANGE_NAME, "");

        String queueName2 = channel.queueDeclare("fanout_myqueue2", false, false, false, null).getQueue(); // better to give name to the queue, else default name will be given to the binded queue and you will not be able to see messages in it on the rabbitmq UI.
        channel.queueBind(queueName2, EXCHANGE_NAME, "");
        DeliverCallback deliverCallback = (consumerTag, message) -> {

            System.out.println("The consumerTag is ::: " + consumerTag);
            System.out.println("The received message is ::: " + new String(message.getBody(), StandardCharsets.UTF_8));

        };
        channel.basicConsume(queueName1, true, deliverCallback, consumerTag -> {});
        channel.basicConsume(queueName2, true, deliverCallback, consumerTag -> {});

    }
}
