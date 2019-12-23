package com.enosnery84.twittermock.controller;

import com.enosnery84.twittermock.models.User;
import com.enosnery84.twittermock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TwitterController {

    @Autowired
    UserService userService;

    @GetMapping("/feed")
    public HashMap<String, Object> getNewsFeed(@RequestParam int userId){
        HashMap<String, Object> response = new HashMap<>();
//        User temp = userService.fi

        return response;
    }
}
