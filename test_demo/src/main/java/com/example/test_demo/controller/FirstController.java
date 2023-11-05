package com.example.test_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String nicetomeetyou(Model model){
        model.addAttribute("username","rel");
        return "greetings";
    }

    @GetMapping("/bye")
    public String goodbye(Model model){
        model.addAttribute("username","reli");
        return "bye";
    }

}
