package com.example.demo.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2018/6/11.
 */
@ApiModel(value = "用户注册入参")
public class User {
    @ApiModelProperty(value = "用户Id")
    private Integer sid;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private Integer age;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
