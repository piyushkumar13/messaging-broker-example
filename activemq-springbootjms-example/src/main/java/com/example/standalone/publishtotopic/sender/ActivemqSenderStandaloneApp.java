package com.example.standalone.publishtotopic.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */
@SpringBootApplication
public class ActivemqSenderStandaloneApp implements CommandLineRunner {

    @Autowired
    private MessageSender messageSender;

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(ActivemqSenderStandaloneApp.class);
        springApplication.setAdditionalProfiles("standalone"); // Programmatically activating standalone profile.

        ConfigurableApplicationContext ctx = springApplication.run(args);
        ctx.close();
    }

    @Override
    public void run(String... args) throws Exception {

//        messageSender.sendMsgAsText();
        messageSender.sendMsgAsJson();

    }
}
