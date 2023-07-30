package com.demo.rabbitMQ.publisher;

import com.demo.rabbitMQ.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitmq.queue.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue.json.routing.key}")
    private String JsonRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);


    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }

    public void sendMessage(User user){

        LOGGER.info(String.format("Message sent -> %s",user.toString()));
        rabbitTemplate.convertAndSend(exchange,JsonRoutingKey,user);
    }
}
