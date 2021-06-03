package org.example.publishtoqueue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author Piyush Kumar.
 * @since 02/06/21.
 */
public class MessageReceiverAsync {

    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL; // Default url is : failover://tcp://localhost:61616
    private static final String QUEUE_NAME = "my_queue";

    public static void main(String[] args) throws JMSException {
        System.out.println("The message broker url is ::: " + BROKER_URL);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MessageListener() {
            @SneakyThrows
            @Override
            public void onMessage(Message receivedMsg) {
                System.out.println("Consumer 1 : The received msg JMS type ::: " + receivedMsg.getJMSType());

                if (receivedMsg instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) receivedMsg;
                    System.out.println("Consumer 1 : The text message is ::: " + textMessage.getText()); // If message is json sent by sender, then we will receive it as String, which we can deserialize to object.
                }
            }
        });

//        connection.close();
    }
}
