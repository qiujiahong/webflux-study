package com.nick.webfluxcom.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "t_student")
public class Student {
    @Id
    private String id;//主键一般是string

    private String name;
    private int age;

}
