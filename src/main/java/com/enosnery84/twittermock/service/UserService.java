package com.enosnery84.twittermock.service;

import com.enosnery84.twittermock.models.Tweet;
import com.enosnery84.twittermock.models.User;
import com.enosnery84.twittermock.repository.TweetRepository;
import com.enosnery84.twittermock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TweetRepository tweetRepository;

    public User findByUserNameAndPassword(String username, String password){
        return userRepository.findByUserNameAndPassword(username, password);
    }

    public List<Tweet> getTweets(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            User temp = userRepository.findById(userId).get();
            return temp.getTweets();
        } else {
            return new ArrayList<>();
        }
    }
}
