package org.example.publishtotopic.durablesubs;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author Piyush Kumar.
 * @since 02/06/21.
 */
public class MessageReceiverSync {

    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL; // Default url is : failover://tcp://localhost:61616
    private static final String TOPIC_NAME = "my_topic";

    public static void main(String[] args) throws JMSException {
        System.out.println("The message broker url is ::: " + BROKER_URL);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("123456");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer1 = session.createDurableSubscriber(topic, "consumer1");
        MessageConsumer consumer2 = session.createDurableSubscriber(topic, "consumer2");

//        while (true) {
            Message receivedMsg1 = consumer1.receive();

            System.out.println("The received msg JMS type ::: " + receivedMsg1.getJMSType());

            if (receivedMsg1 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) receivedMsg1;
                System.out.println("The text message is ::: " + textMessage.getText()); // If message is json sent by sender, then we will receive it as String, which we can deserialize to object.
            }

            Message receivedMsg2 = consumer2.receive();

            System.out.println("The received msg JMS type ::: " + receivedMsg2.getJMSType());

            if (receivedMsg2 instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) receivedMsg2;
                System.out.println("The text message is ::: " + textMessage.getText()); // If message is json sent by sender, then we will receive it as String, which we can deserialize to object.
            }
//        }

//        connection.close();
    }
}
