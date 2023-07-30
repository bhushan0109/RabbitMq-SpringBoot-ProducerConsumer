package com.demo.rabbitMQ.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rabbitMQ.dto.User;
import com.demo.rabbitMQ.publisher.RabbitMQJsonProducer;
import com.demo.rabbitMQ.publisher.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    @Autowired
    private RabbitMQProducer producer ;
    @Autowired
    private RabbitMQJsonProducer JsonProducer;
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
    	LOGGER.info("message==>"+message);
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ");
    }

    @PostMapping("/publish_json")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
    	LOGGER.info("user json data==>"+user);
        JsonProducer.sendMessage(user);
        return ResponseEntity.ok("User sent to RabbitMQ");
    }
}
