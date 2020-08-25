package com.example.springbootdemotoken.webController;


import com.example.springbootdemotoken.tokens.JsonWebToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class WebController {

    @RequestMapping(value = "/getStr")
    public String getStr(String str){
        Map<String,String> map = new HashMap<>();
        map.put("name","123");
        map.put("userId","123221");
       String token =  JsonWebToken.createToken(map);
        return token+"----";
    }

    @RequestMapping(value = "/crossToken")
    public String crossToken(){
        return "token验证成功";
    }
}
