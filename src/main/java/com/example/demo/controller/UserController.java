package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */
@RestController
@RequestMapping(value = "user")
@Api(description = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("find/{name}")
    @ApiOperation(value = "根据用户名查询用户")
    public User findByName(@ApiParam(value = "用户名", required = true) @PathVariable("name") String name){
        return userService.findByName(name);
    }

    @PostMapping("find_all")
    @ApiOperation(value = "查询用户列表")
    public List<User> findAll(){

        return userService.findAll();
    }

    @PostMapping("add")
    @ApiOperation(value = "新增用户")
    public List<User> add(@ApiParam(value = "用户名", required = true) String name,
                          @ApiParam(value = "年龄", required = true) Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        userService.add(user);
        return userService.findAll();
    }

}
