package com.enosnery84.twittermock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TwitterController {

    @GetMapping("/feed")
    public HashMap<String, Object> getNewsFeed(@RequestParam int userId){
        HashMap<String, Object> response = new HashMap<>();


        return response;
    }
}
