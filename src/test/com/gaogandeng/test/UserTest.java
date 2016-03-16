package com.gaogandeng.test;

import com.gaogandeng.model.User;
import com.gaogandeng.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lanxing on 16-3-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)   //相当于继承了SpringJUnit4ClassRunner
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class UserTest {
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void findAllUsers(){
        List<User> users = userService.findAllUsers();
        for (User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setUserName("张三");
        user.setPassword("123456");
        userService.insertUser(user);
    }
}
