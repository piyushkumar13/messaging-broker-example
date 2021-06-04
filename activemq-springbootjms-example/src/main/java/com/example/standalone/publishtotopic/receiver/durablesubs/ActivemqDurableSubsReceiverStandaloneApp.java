package com.example.standalone.publishtotopic.receiver.durablesubs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */
@SpringBootApplication
@EnableJms
public class ActivemqDurableSubsReceiverStandaloneApp {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(ActivemqDurableSubsReceiverStandaloneApp.class);
        springApplication.setAdditionalProfiles("standalone"); // Programmatically activating standalone profile.

        springApplication.run(args);
    }
}
