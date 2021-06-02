package org.example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author Piyush Kumar.
 * @since 02/06/21.
 */
public class MessageSender {

    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL; // Default url is : tcp://localhost:61616
    private static final String QUEUE_NAME = "my_queue";


    public static void main(String[] args) throws JMSException {
        System.out.println("The message broker url is :::: " + BROKER_URL);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME); //Or Destination destination = session.createQueue(QUEUE_NAME)
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage("Hi! This is Piyush");

        producer.send(textMessage);

        System.out.println("Message sent ::: " + textMessage.getText());

        connection.close();
    }
}
