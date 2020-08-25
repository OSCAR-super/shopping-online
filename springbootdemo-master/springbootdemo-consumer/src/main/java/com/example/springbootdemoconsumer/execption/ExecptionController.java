package com.example.springbootdemoconsumer.execption;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExecptionController {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> excptionhandler(Exception ex) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("errorCode", "500");
        map.put("errorMsg", "系统内部错误");
        map.put("errorInfo", ex.getMessage());

        return map;
    }
}
