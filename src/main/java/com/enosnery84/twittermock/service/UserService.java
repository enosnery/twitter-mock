package com.enosnery84.twittermock.service;

import com.enosnery84.twittermock.models.User;
import com.enosnery84.twittermock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private User findByUserNameAndPassword(String username, String password){
        return userRepository.findByUserNameAndPassword(username, password);
    }
}
