package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */
public interface UserService {
    User findByName(String name);
    List<User> findAll();

    Integer add(User user);
}
