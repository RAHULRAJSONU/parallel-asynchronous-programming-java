package io.github.rahulrajsonu.service;

import java.util.concurrent.CompletableFuture;

import static io.github.rahulrajsonu.util.CommonUtil.delay;
import static io.github.rahulrajsonu.util.LoggerUtil.log;

public class HelloWorldService {

    public  String helloWorld() {
        delay(1000);
        log("inside helloWorld");
        return "hello world";
    }

    public  String hello() {
        delay(1000);
        log("inside hello");
        return "hello";
    }

    public  String world() {
        delay(1000);
        log("inside world");
        return " world!";
    }

    public CompletableFuture<String> worldFuture(String input) {
        return CompletableFuture.supplyAsync(()->{
            delay(1000);
            return input+" world!";
        });
    }

}
