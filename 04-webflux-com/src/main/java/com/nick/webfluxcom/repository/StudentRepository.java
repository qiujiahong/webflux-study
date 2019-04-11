package com.nick.webfluxcom.repository;

import com.nick.webfluxcom.bean.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

//第一个泛型是repository操作的对象
//第二个泛型是操作对应的主键类型
public interface StudentRepository extends ReactiveMongoRepository<Student,String> {
}
