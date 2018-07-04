package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan("com.example.demo.dao")
@Controller
public class SpringbootmybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmybatisApplication.class, args);
	}

	@RequestMapping("/index")
	public String showIndex(){
		return "user-login";
	}
}
