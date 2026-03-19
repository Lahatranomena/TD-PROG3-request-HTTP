package com.example.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class WelcomeService {

    public String getWelcomeService(@RequestParam String name){
        return "Welcome "+name;
    }
}
