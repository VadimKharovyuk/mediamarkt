package com.example.mediamarkt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPage {
    @GetMapping("/")
    public String HomePage(){
        return "homePage";
    }
}
