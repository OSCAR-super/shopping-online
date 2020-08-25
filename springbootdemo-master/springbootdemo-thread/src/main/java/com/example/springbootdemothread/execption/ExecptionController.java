package com.example.springbootdemothread.execption;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * @ControllerAdvice(basePackages = "com.example") 注解不添加basePackages 表示加了@Controller和@RestController都能控制
 */
@ControllerAdvice
public class ExecptionController {

    @ResponseBody
    @ExceptionHandler(value = java.util.concurrent.RejectedExecutionException.class)
    public Map<String, Object> excptionhandler(Exception ex) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("errorCode", "500");
        map.put("errorMsg", "当前并发访问量太大，请您稍后访问");
        map.put("errorInfo", ex.getMessage());

        return map;
    }
}
