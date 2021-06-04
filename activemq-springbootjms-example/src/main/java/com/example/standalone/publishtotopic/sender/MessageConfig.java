package com.example.standalone.publishtotopic.sender;

import javax.jms.Queue;
import javax.jms.Topic;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */

@Configuration
public class MessageConfig {

    @Bean
    Topic queue(){
        return new ActiveMQTopic("my_topic");
    }

//    /* Serialize message content to json using jms template */
//    @Bean
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }

}
