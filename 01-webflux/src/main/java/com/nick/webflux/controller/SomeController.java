package com.nick.webflux.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
public class SomeController {


    @GetMapping("/common")
    public String commonHandle() {
        log.info("common start..............");
        String result = doSome("common handler");
        log.info("common end..............");
        return result;
    }

    @GetMapping("/mono")
    public Mono<String> MonoHandle() {

        log.info("mono start..............");
        Mono<String> mono = Mono.fromSupplier(() -> doSome("mono handler"));
        log.info("mono end..............");
        // Mono 表示包含0或者1个元素的异步序列
        return mono.just("mono handler");
    }


    @RequestMapping("/flux")
    public Flux<String> fluxHandle(){
        //Flux表示包含0或者n个元素的异步序列
        return Flux.just("北京","shanghai","广州");
    }

    // http://localhost:8080/array?cities=广州&cities=shanghai&cities=南京
    @RequestMapping("/array")
    public Flux<String> fluxHandle2(@RequestParam String [] cities){
        //将数组转化成未Flux
        return Flux.fromArray(cities);
    }

    @RequestMapping("/list")
    public Flux<String> fluxHandle(@RequestParam List<String> cities){
        //将数组转化成未Flux
        return Flux.fromStream(cities.stream());
    }

    private String doSome(String msg) {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }


}
