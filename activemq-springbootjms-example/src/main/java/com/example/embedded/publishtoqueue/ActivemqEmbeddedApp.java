package com.example.embedded.publishtoqueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */

/*
* TODO: Uncomment activemq-broker dependency before running this class.
* */
@SpringBootApplication
public class ActivemqEmbeddedApp implements CommandLineRunner {

    @Autowired
    private MessageSender messageSender;

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(ActivemqEmbeddedApp.class);
        springApplication.setAdditionalProfiles("embedded"); // Programmatically activating standalone profile.

        ConfigurableApplicationContext ctx = springApplication.run(args);
        ctx.close();
    }

    @Override
    public void run(String... args) throws Exception {

//        messageSender.sendMsgAsText();
        messageSender.sendMsgAsJson();

    }
}
