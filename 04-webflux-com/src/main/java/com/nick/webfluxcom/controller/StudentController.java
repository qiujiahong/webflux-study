package com.nick.webfluxcom.controller;


import com.nick.webfluxcom.bean.Student;
import com.nick.webfluxcom.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    //    一次性返回
    @GetMapping("/all")
    public Flux<Student> getAll(){
        return repository.findAll();
    }
    //curl -N --http2 -H "Accept:text/event-stream" https://localhost:8080/student/sse/all
    //sse方式实时返回
    //text/event-stream
    @GetMapping(value = "/sse/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getSseAll(){
        return repository.findAll();
    }

    //添加说明
    @PostMapping("/save")
    public Mono<Student> saveStudent(Student student) {
        return repository.save(student);
    }
}
