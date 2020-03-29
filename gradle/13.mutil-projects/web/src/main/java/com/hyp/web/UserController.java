package com.hyp.web;

import com.hyp.model.User;
import com.hyp.service.UserService;

public class UserController {
    private UserService userService = new UserService();

    public User getUser(String userId) {
        return userService.getUser(userId);
    }
}