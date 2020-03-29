package com.hyp.service;

import com.hyp.repository.UserRepository;
import com.hyp.model.User;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public User getUser(String userId) {
        return userRepository.getUser(userId);
    }
}
