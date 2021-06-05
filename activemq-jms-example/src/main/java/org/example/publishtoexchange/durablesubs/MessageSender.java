package org.example.publishtoexchange.durablesubs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.domain.Student;

/**
 * @author Piyush Kumar.
 * @since 02/06/21.
 */
public class MessageSender {

    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL; // Default url is : failover://tcp://localhost:61616
    private static final String TOPIC_NAME = "my_topic";

    private static TextMessage getTextMessage(Session session) throws JMSException {
        return session.createTextMessage("Hi! This is Piyush");
    }

    private static TextMessage getJsonMsgAsText(Session session) throws JsonProcessingException, JMSException {

        String jsonMsg = new ObjectMapper().writeValueAsString(new Student(18, "Piyush", "Java"));
        return session.createTextMessage(jsonMsg);
    }

    public static void main(String[] args) throws JMSException, JsonProcessingException {
        System.out.println("The message broker url is :::: " + BROKER_URL);

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME); //Or Destination destination = session.createTopic(TOPIC_NAME)
//        TextMessage textMessage = getTextMessage(session);
        TextMessage textMessage = getJsonMsgAsText(session);
        MessageProducer producer = session.createProducer(topic);

        producer.send(textMessage);

        System.out.println("Message sent ::: " + textMessage.getText());

        connection.close();
    }
}
