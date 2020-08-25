package com.example.springboot.demo.mq;

import com.example.springboot.demo.service.UserService;
import com.example.springbootdemoentity.entity.Product;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.a")
public class FanoutAConsumer {
    @Autowired
    private UserService userService;
    /**
     * 消息消费
     * @RabbitHandler 代表此方法为接受到消息后的处理方法
     */
    @RabbitHandler
    public void recieved(String msg) {
        System.out.println("[fanout.a] recieved message: " + msg);
        String[] a=msg.split("-");
        for (int i=0;i<a.length;i+=3){
            Product product=userService.getP(a[i]);
            userService.consume(a[i], String.valueOf(Integer.parseInt(product.getProductSurplus())-Integer.parseInt(a[i+1])),a[i+2]);
        }
    }
}