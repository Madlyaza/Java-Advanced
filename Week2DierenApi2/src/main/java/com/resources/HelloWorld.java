package com.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/helloWorld")
public class HelloWorld {

    @Value("")

    @GetMapping
    public String message()
    {
        return "this message is a thing";
    }
}
