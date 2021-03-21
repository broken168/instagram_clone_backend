package com.brabos.bahia.instagram.test.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingResource {

    @GetMapping
    public String hello(){
        return "Hello, World!";
    }

}
