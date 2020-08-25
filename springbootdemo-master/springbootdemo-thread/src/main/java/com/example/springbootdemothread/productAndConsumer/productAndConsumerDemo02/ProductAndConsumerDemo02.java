package com.example.springbootdemothread.productAndConsumer.productAndConsumerDemo02;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;


public class ProductAndConsumerDemo02 {
    /**
     * 定义一个容器，容器中有
     */

    //AtomicInteger count = new AtomicInteger(0);



    public static void main(String[] args) {
        ArrayBlockingQueue<Product> productArrayBlockingQueue = new ArrayBlockingQueue<>(10);
        AtomicInteger count = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for(int i = 0;i<25;i++){
            Produce produce = new Produce(productArrayBlockingQueue,count);
            executorService.submit(produce);
        }
    for (int i =0;i<20;i++){
        Consumer consumer = new Consumer(productArrayBlockingQueue,count);
        executorService.submit(consumer);
    }


    }

}
