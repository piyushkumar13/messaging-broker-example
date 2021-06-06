# messaging-broker-example

Before using this project you need to install and start activemq and rabbitmq on your local. 

* For activemq, you can follow [this](https://medium.com/@gauravingalkar/getting-started-with-activemq-installing-and-getting-web-console-running-e9aca136a60e) guide to do that.
* For rabbitmq, you can follow [this](https://medium.com/macoclock/setup-rabbitmq-on-your-macos-in-3-mins-f27d3ce25f55) guide to do that.


This project comprises of module : 

1. activemq-jms-example
2. activemq-springbootjms-example
3. rabbitmq-example
4. rabbitmq-springboot-example

## activemq-jms-example
* This module comprises of a example which makes use of activemq as a messaging broker with plain java.
* It comprises examples of publishing messages to queue and receiving messages in synchronous and asynchronous ways.
* It also comprises of examples of publishing messages to topic and receiving messages from topic in synchronous and asynchronous ways with non-durable and durable subscribers.


# activemq-springbootjms-example
* This module contains example of activemq with JMS and Spring boot. 
* Activemq is used in standalone mode as well as in embedded mode(activemq comes with spring boot library in embedded form).
* It also comprises of examples of publishing messages to topic and receiving messages from topic in synchronous and asynchronous ways with non-durable and durable subscribers.

**Note** : standalone package is having all the examples with non-durable and durable subscription to topics, publishing to queues, however, embedded package has only publishing to queues but all the other things are just same as like standalone activemq examples.

# rabbitmq-example
* This module contains example of rabbitmq with plain java.
* It contains examples of publishing messages to queue directly.
* It contains examples of publishing messages to exchanges like fanout, topic and direct.

# rabbitmq-springboot-example
* This module contains example of rabbitmq with spring-boot.
* It contains examples of publishing messages to queue directly.
* It contains examples of publishing messages to exchanges like fanout, topic and direct.