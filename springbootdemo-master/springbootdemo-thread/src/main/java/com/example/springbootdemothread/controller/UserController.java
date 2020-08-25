package com.example.springbootdemothread.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootdemoentity.entity.User;
import com.example.springbootdemothread.BlockQueueTest;
import com.example.springbootdemothread.entity.ThreadEntity2;
import com.example.springbootdemothread.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.zhenzi.sms.ZhenziSmsClient;
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




    @RequestMapping(value = "/getUser")
    public String getUser(String productName,HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("调用getUser方法");
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        String id=getRandomString(8);
        jedis.append("h"+id,productName);
        //jedis.set("1", "start");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("h"+id));
        return "订单已上传,订单号为:"+id;
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
    @RequestMapping(value = "/getPageUser")
    public PageInfo<User> getPageUser(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        System.out.println("调用getUser方法");
        List<User> userList = userService.getPageUser();
        System.out.println(userList.toString());
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return pageInfo;
        //return user.toString();
    }

    //获取服务器CPU个数；
    static final int nThreads = Runtime.getRuntime().availableProcessors();

    //第三个参数:当线程数量大于核心线程池数量，且blockqueue已经满的时候，需要创建新的线程达到线程最大值，那么当新的
    //线程执行队列中的任务结束之后，新的线程的等待时间；
    //核心线程池数量为2，当 当前线程数量大约2时，将线程保存到线程队列中，且线程队列中最多能保存10个线程；
    //当线程队列也满了的时候，需要启动新的线程，且新的线程+原线程的数量不能大于5；
    static ExecutorService executorService = new ThreadPoolExecutor(2, 2,
            0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4));
    AtomicInteger integer = new AtomicInteger(0);

    @RequestMapping(value = "/threadTest")
    public String threadTest(int i , HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
           Thread.currentThread().setName("第"+integer+"个controller线程");
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ConcurrentHashMap<String, User> concurrentHashMap = new ConcurrentHashMap<>();
//            for (int i = 0; i < 100; i++) {
//            System.out.println(userService.hashCode());
//            executorService.submit(new ThreadEntity(i));
                executorService.submit(new ThreadEntity2(i,userService, concurrentHashMap, countDownLatch));
//            }
            String a = "";
            String b = "";
            a.equals(b);
            countDownLatch.await(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}

