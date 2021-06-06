package com.example.rabbitmqspringbootexample.topic.receiver;

import com.example.rabbitmqspringbootexample.domain.Student;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Piyush Kumar.
 * @since 06/06/21.
 */

@SpringBootApplication
public class MessageReceiver {

    private static final String QUEUE_NAME = "myboot_queue";


//    @RabbitListener(queues = QUEUE_NAME)
//    public void consumeMsg(Student student){
//
//        System.out.println("Listener : Received msg is ::: " + student);
//    }

    /** Following method will work for any type of object being sent by sender.
     * Either it of type Student(which gets deserialized to JSON while sending) or Student as JSON String. */
    @RabbitListener(queues = QUEUE_NAME)
    public void consumeMsg(Message message){

        System.out.println("Listener1 : Received msg is ::: " + new String(message.getBody()));
    }

//    @RabbitListener(queues = QUEUE_NAME)
//    public void consumeMsg2(String msg){
//
//        System.out.println("Listener 2 : Received msg is ::: " + msg);
//    }

    public static void main(String[] args) {
        SpringApplication.run(MessageReceiver.class, args);
    }
}
