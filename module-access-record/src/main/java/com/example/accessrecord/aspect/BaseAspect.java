package com.example.accessrecord.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by AMe on 2020-07-11 14:57.
 */
public class BaseAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void handlerClass() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)"
        + " || @annotation(org.springframework.web.bind.annotation.GetMapping)"
        + " || @annotation(org.springframework.web.bind.annotation.PostMapping)"
        + " || @annotation(org.springframework.web.bind.annotation.PutMapping)"
        + " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void handlerMethod() {
    }

    @Pointcut("@within(com.example.accessrecord.annotation.IgnoreAccessRecord)")
    public void ignoreClass() {
    }

    @Pointcut("@annotation(com.example.accessrecord.annotation.IgnoreAccessRecord)")
    public void ignoreMethod() {
    }

}
