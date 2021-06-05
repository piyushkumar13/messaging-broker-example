package com.example.embedded.publishtoqueue;

import javax.jms.Queue;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */

@Configuration
public class MessageConfig {

    @Bean
    Queue queue(){
        return new ActiveMQQueue("my_queue");
    }

}
