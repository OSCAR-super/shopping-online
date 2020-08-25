package com.example.springbootdemothread.execption;


import java.util.concurrent.ThreadPoolExecutor;


public class RejectedExecutionHandler implements java.util.concurrent.RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//            executor.setRejectedExecutionHandler();
    }
}
