package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.config.redis.RedisService;
import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;
    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<User> findAll() {
        List<User> users = userDao.findAll();
        if(users.size() <= 0 || users == null){
            return new ArrayList<>();
        }
        try {
            logger.info("开始缓存======>");
            for(int i = 0; i< users.size(); i++){
                redisService.cacheHashValue("jidaneTest", users.get(i).getName(), JSON.toJSONString(users.get(i)), 60);
            }
        } catch (Exception e) {
            logger.error("缓存失败===>"+ JSON.toJSONString(e));
        }
        logger.info("缓存完毕<====");
        return users;
    }

    @Override
    public Integer add(User user) {
        return userDao.add(user);
    }
}
