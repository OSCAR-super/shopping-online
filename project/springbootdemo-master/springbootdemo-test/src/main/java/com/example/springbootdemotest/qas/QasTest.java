package com.example.springbootdemotest.qas;


import com.example.springbootdemotest.interfaces.LambdaSingleReturnMutiplyParameter;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class QasTest {
//    AbstractQueuedSynchronizer
//    ReentrantLock
public static void main(String[] args) {
    LambdaSingleReturnMutiplyParameter lambda1 =  (a,b)->{
        return a+b;
    };
    System.out.println(lambda1.test(1,2));
}
}
