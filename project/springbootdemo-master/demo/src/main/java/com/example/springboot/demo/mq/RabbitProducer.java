package com.example.springboot.demo.mq;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.springboot.demo.service.UserService;
import com.example.springbootdemoentity.entity.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitProducer {
    @Autowired
    private RabbitProducer rabbitProducer;
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private UserService userService;
    public void sendDemoQueue() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        System.out.println("[demoQueue] send msg: " + dateString);  
        // 第一个参数为刚刚定义的队列名称
        this.rabbitTemplate.convertAndSend("demoQueue", dateString);
    }
    
    public void sendFanout(String aq) {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        System.out.println("[fanout] send msg:" + dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey 我们不用管空着就可以，第三个是你要发送的消息
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", aq);
    }
    
    public void sendTopicTopicAB() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.msg] send msg:" + dateString;
        System.out.println(dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey topic.msg，第三个是你要发送的消息
        // 这条信息将会被 topic.a  topic.b接收
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.msg", dateString);
    }

    public String sendTopicTopicB(String orderId,String method,String aq) {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.good.msg] send msg:" + dateString;
        System.out.println(dateString);

        if (method.equals("orderId")){

            Order order=userService.getOrderId(orderId);
            if (order==null){
                return "1";
            }else {return "0";}
        }else {
        aq = aq.replace("\"", "");
        System.out.println(aq);
        rabbitProducer.sendFanout(aq);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.good.msg", orderId+"："+method);
        }
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey ，第三个是你要发送的消息
        // 这条信息将会被topic.b接收

        return "OK";
    }

    public void sendTopicTopicBC() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(date);
        dateString = "[topic.m.z] send msg:" + dateString;
        System.out.println(dateString);
        // 注意 第一个参数是我们交换机的名称 ，第二个参数是routerKey ，第三个是你要发送的消息
        // 这条信息将会被topic.b、topic.c接收
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.m.z", dateString);
    }
}