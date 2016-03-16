package com.gaogandeng.utils;

import com.gaogandeng.model.User;
import com.gaogandeng.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanxing on 16-3-16.
 */
@Service("authenticationService")
public class AuthenticationService {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> queryAllUsers(){
        return userService.findAllUsers();
    }

    public User queryUserByName(String name){
        return userService.findUserByName(name);
    }

    public void insertUser(User user){
        userService.insertUser(user);
    }
}
