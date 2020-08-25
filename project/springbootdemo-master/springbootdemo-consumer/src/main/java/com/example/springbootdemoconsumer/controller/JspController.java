package com.example.springbootdemoconsumer.controller;


import com.example.springbootdemoentity.config.repeatCommitConfig.AvoidRepeatableCommit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {
    @AvoidRepeatableCommit
    @RequestMapping(value = "/getJsp")
    public String  getJsp(){
        return "index";
    }
}
