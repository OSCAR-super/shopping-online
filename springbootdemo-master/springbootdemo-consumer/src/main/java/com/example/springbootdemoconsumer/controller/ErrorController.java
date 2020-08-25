package com.example.springbootdemoconsumer.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ErrorController {
    @RequestMapping(value = "/getErrorMsg")
    public String getErrorMsg(Integer n,Integer m){
       int i = n/m;
        System.out.println(i);
        return i+"";
    }
}
