package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/bienvenido.html");
    }
}
