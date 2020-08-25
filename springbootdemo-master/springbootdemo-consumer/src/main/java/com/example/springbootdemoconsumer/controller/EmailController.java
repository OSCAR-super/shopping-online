package com.example.springbootdemoconsumer.controller;

import com.example.springbootdemoconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RestController
public class EmailController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/SendEmail")
    public String sendEmail(HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        userService.sendEamil(request,response,"411487840@qq.com");
        return "OK";
    }
}
