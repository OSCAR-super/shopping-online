package com.example.springboot.demo.controller;

import com.example.springboot.demo.mq.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqController {

	@Autowired
	private RabbitProducer rabbitProducer;

	@GetMapping("/sendDemoQueue")
	public Object sendDemoQueue() {
		rabbitProducer.sendDemoQueue();
		return "success";
	}
	
	@GetMapping("/sendFanout")
	public Object sendFanout(String aq) {
		rabbitProducer.sendFanout(aq);
		return "success";
	}
	
	@GetMapping("/sendTopicTopicAB")
	public Object sendTopicTopicAB() {
		rabbitProducer.sendTopicTopicAB();
		return "success";
	}
	
	@GetMapping("/sendTopicTopicB")
	public Object sendTopicTopicB(String orderId,String method,String aq) {
		rabbitProducer.sendTopicTopicB(orderId,method,aq);
		return "success";
	}
	
	@GetMapping("/sendTopicTopicBC")
	public Object sendTopicTopicBC() {
		rabbitProducer.sendTopicTopicBC();
		return "success";
	}
}