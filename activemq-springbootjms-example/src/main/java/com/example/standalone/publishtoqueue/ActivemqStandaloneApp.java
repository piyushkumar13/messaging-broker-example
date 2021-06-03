package com.example.standalone.publishtoqueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.AbstractEnvironment;

/**
 * @author Piyush Kumar.
 * @since 04/06/21.
 */
@SpringBootApplication
public class ActivemqStandaloneApp implements CommandLineRunner {

    @Autowired
    private MessageSender messageSender;

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(ActivemqStandaloneApp.class);
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
