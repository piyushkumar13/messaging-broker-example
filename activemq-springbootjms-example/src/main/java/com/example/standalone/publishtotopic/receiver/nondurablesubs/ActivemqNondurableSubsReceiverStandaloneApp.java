package com.example.standalone.publishtotopic.receiver.nondurablesubs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */
@SpringBootApplication
@EnableJms
public class ActivemqNondurableSubsReceiverStandaloneApp {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(ActivemqNondurableSubsReceiverStandaloneApp.class);
        springApplication.setAdditionalProfiles("standalone"); // Programmatically activating standalone profile.

        springApplication.run(args);
    }
}
