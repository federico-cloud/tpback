package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UserController {

    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/bienvenido.html");
    }

    @GetMapping("/user")
    public String user(){
        return "<h1>Welcome user </h1>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1>Welcome admin</h1>";
    }

}
