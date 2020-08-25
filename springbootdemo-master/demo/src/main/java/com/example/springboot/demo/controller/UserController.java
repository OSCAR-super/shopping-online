package com.example.springboot.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.demo.mq.HttpRequest;
import com.example.springboot.demo.mq.RabbitProducer;
import com.example.springboot.demo.service.UserService;
import com.example.springbootdemoentity.entity.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhenzi.sms.ZhenziSmsClient;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RabbitProducer rabbitProducer;
    @RequestMapping(value = "/sendMessage")
    public String sendMessage(String number,HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            JSONObject json = null;
            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            //发送短信
            ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com","106387", "da4c1ad5-2627-44c7-ad52-63d4004c473c");
            Map<String, Object> params = new HashMap<String, Object>();
            String[] i={verifyCode};
            params.put("templateParams",i);
            params.put("number", number);
            params.put("templateId","1119");
            String result = client.send(params);
            json = JSONObject.parseObject(result);
            if(json.getIntValue("code") != 0){//发送短信失败
                return "fail";}
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson
            HttpSession session = request.getSession();
            json = new JSONObject();
            json.put("verifyCode", verifyCode);
            json.put("createTime", System.currentTimeMillis());
            // 将认证码存入SESSION
            request.getSession().setAttribute("verify", json);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/getTag")
    public String getTag(String ProductTag,HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        List<Product> products=userService.getTag(ProductTag);
        for (Product p:products){
            List<image> img=userService.findProductImage(String.valueOf(p.getProductId()));
            for(image i:img){
                if (i.getImageState().equals("main")){
                    p.setProductState(i.getImageName());
                }
            }
        }
        JSONObject result = new JSONObject();
        result.put("这个标签的货物",products);
        return result.toString();
    }
    @RequestMapping(value = "/getProduct")
    public String getProduct(String ProductName,HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        List<Product> products=userService.getProduct(ProductName);
        for (Product p:products){
            List<image> img=userService.findProductImage(String.valueOf(p.getProductId()));
            for(image i:img){
                if (i.getImageState().equals("main")){
                    p.setProductState(i.getImageName());
                }
            }
        }
        JSONObject result = new JSONObject();
        result.put("这个名字的货物",products);
        return result.toString();
    }
    @RequestMapping(value = "/getOpenid")
    public String getOpenid(String code,HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        if (code == null || code.length() == 0) {
            return "code 不能为空";
        }
        //小程序的appid
        String appId = "wxada45d39286a57ea";
        // 小程序的secret
        String appsecret = "d6d9cc603b96d96cd1bf1e86c06d95ac";

        //向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + appId + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=authorization_code";

        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);

        JSONObject jsonObject = JSONObject.parseObject(sr);

        System.out.println(jsonObject.get("openid"));

        JSONObject result1 = new JSONObject();
        result1.put("openid",jsonObject.get("openid"));
        return result1.toString();
    }

    @RequestMapping(value = "/subOrder")
    public String subOrder(String face,String name,String time,String Id, String OrderProduct, String aq, String OrderTime, HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        int i=0;
        String orderId=null;
        do {
            orderId=getRandomString(8);
            i= Integer.parseInt(rabbitProducer.sendTopicTopicB(orderId,"orderId","0"));
        }while (i==0);

        rabbitProducer.sendTopicTopicB(orderId,OrderTime+"："+Id+"："+face+"："+name+"："+time,aq);
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        JSONObject result = new JSONObject();
        result.put("这个订单的货物",OrderProduct);
        jedis.append(orderId,result.toString());
        JSONObject result1 = new JSONObject();
        result1.put("这个订单的订单号",orderId);
        jedis.del(Id);
        aq = aq.replace("\"", "");
        jedis.append(orderId+"123",aq);
        return result1.toString();
    }
    @RequestMapping(value = "/getOrderProduct")
    public String getOrderProduct(String OrderId,HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        String i=jedis.get(OrderId);
        return i;
    }
    @RequestMapping(value = "/getOrder")
    public String getOrder(String OrderId,HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Order order=userService.getOrderId(OrderId);
        JSONObject result= new JSONObject();
        result.put("这个订单的信息",order);
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        String aq=jedis.get(OrderId+"123");
        String[] a=aq.split("-");
        int totalPrice=0;
        int totalNumber=0;
        for (int ii=0;ii<a.length;ii+=3){
            totalNumber+=Integer.parseInt(a[ii+1]);
            totalPrice+=Integer.parseInt(a[ii+2]);
        }
        result.put("totalNumber",totalNumber);
        result.put("totalPrice",totalPrice);
        return result.toString();
    }
    @RequestMapping(value = "/getCart")
    public String getCart(String Id,HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        String i=jedis.get(Id);
        return i;
    }
    @RequestMapping(value = "/sendCart")
    public String sendCart(String Id,String Products,HttpServletRequest request, HttpServletResponse response){
        System.out.println(Products);
        Products = Products.replace("\\", "").replace("\"{", "{").replace("}\"", "}");
        Product Product=JSONArray.parseObject(Products, Product.class);

        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        String i=jedis.get(Id);
        JSONObject result= new JSONObject();

        if (i==null){
            List<Product> p=new ArrayList<>();
            p.add(Product);
            result.put("购物车的商品",p);
            jedis.append(Id,result.toString());
        }else {
            i = i.replace("{\"购物车的商品\":", "").replace("}]}", "}]");
            List<Product> products = (List<Product>) JSONArray.parseArray(i, Product.class);
            System.out.println(products);
            int a=0;
            for (Product p:products){
                if (p.getProductId()==Product.getProductId()){
                    a=1;
                }
            }
            if (a==0) {
                products.add(Product);
            }
            result.put("购物车的商品",products);
            jedis.set(Id,result.toString());
        }
        return "OK";
    }
    @RequestMapping(value = "/daddCart")
    public String daddCart(String Id,String Products,HttpServletRequest request, HttpServletResponse response){
        Products = Products.replace("\\", "").replace("\"{", "{").replace("}\"", "}");
        Product Product=JSONArray.parseObject(Products, Product.class);
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        String i=jedis.get(Id);
        JSONObject result= new JSONObject();
        i = i.replace("{\"购物车的商品\":", "").replace("}]}", "}]");
        List<Product> products = (List<Product>) JSONArray.parseArray(i, Product.class);
        products.remove(Product);
        result.put("购物车的商品",products);
        jedis.set(Id,result.toString());
        return "OK";
    }
    @RequestMapping(value = "/achieveOrder")
    public String achieveOrder(String OrderId,HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        userService.achieveOrderId(OrderId);

        return "OK";
    }
    @RequestMapping(value = "/concelOrder")
    public String concelOrder(String aq,String OrderId,HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        userService.concelOrderId(OrderId);
        aq = aq.replace("\"", "");
        System.out.println("qqq"+aq);
        String[] a=aq.split("-");
        for (int i=0;i<a.length;i+=3){
            Product product=userService.getP(a[i]);
            userService.consume(a[i], String.valueOf(Integer.parseInt(product.getProductSurplus())+Integer.parseInt(a[i+1])),a[i+2]);
        }

        return "OK";
    }
    @RequestMapping(value = "/getOrderById")
    public String getOrderById(String Id,HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        List<Order> order=userService.getOrder(Id);
        JSONObject result= new JSONObject();
        result.put("这个用户的所有订单",order);
        return result.toString();
    }
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}

