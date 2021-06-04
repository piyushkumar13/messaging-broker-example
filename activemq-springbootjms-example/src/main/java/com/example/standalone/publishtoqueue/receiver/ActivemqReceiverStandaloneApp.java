package com.example.standalone.publishtoqueue.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */
@SpringBootApplication
@EnableJms
public class ActivemqReceiverStandaloneApp {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(ActivemqReceiverStandaloneApp.class);
        springApplication.setAdditionalProfiles("standalone"); // Programmatically activating standalone profile.

        springApplication.run(args);
    }
}
