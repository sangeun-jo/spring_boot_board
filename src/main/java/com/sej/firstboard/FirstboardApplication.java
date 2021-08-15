package com.sej.firstboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.*;

@Controller
@SpringBootApplication
@MapperScan(value={"com.sej.firstboard.mapper"})
public class FirstboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstboardApplication.class, args);
    }
}
