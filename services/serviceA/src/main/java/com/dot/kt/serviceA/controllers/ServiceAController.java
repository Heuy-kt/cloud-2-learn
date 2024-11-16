package com.dot.kt.serviceA.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("cloud")
public class ServiceAController {

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name){
        return "Hello " + name;
    }

    @Value("${message: hello world}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }
}
