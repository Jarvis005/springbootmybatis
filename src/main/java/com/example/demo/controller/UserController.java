package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user", description = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/find")
    @ApiOperation(value = "根据用户名查询用户")
    public User findByName(@ApiParam(value = "name", required = true) @PathVariable("name") String name){
        return userService.findByName(name);
    }

    @PostMapping("/find_all")
    @ApiOperation(value = "查询用户列表")
    public List<User> findAll(){
        List<User> users =userService.findAll();

        return users;
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增用户")
    public List<User> add(@ApiParam(value = "name", required = true) @PathVariable("name") String name,
                          @ApiParam(value = "age", required = true) @PathVariable("age") int age){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        userService.add(user);
        return userService.findAll();
    }

}
