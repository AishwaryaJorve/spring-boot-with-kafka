package com.aish.springbootwithkafka.controllers;

import com.aish.springbootwithkafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserController {


    @Autowired
    KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC="json-example";

//    @GetMapping("/publish/{message}")
//    public String postMessage(@PathVariable("message") final String message){
//
//        kafkaTemplate.send(TOPIC,message);
//        return "publish message";
//    }

    @PostMapping("/publish")
    public User postMessage(@RequestBody User user){

        kafkaTemplate.send(TOPIC,new User(user.getName(),user.getDepartment(),user.getSalary()  ));
        return user;
    }

}
