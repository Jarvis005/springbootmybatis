package com.example.demo.dao;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */
@Repository
/*@Mapper*/
/*@Repository也可以不用添加，但是不添加之后使用@Autowired注入接口是IDEA会提示红线，但是不影响编译运行*/
/*@Mapper如果配置了自动扫描，这个注解可以不添加，也推荐使用自动扫描*/
public interface UserDao {
    /*xml方式*/
    User findByName(String name);
    /*注解方式*/
    @Select("select * from users")
    List<User> findAll();

    Integer add(User user);
}
