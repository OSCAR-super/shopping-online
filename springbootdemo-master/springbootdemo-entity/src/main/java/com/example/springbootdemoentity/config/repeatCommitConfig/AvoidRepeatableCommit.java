package com.example.springbootdemoentity.config.repeatCommitConfig;


import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidRepeatableCommit {
    long timeout() default 3000;
}
