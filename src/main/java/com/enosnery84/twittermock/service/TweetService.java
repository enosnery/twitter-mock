package com.enosnery84.twittermock.service;

import com.enosnery84.twittermock.models.Tweet;
import com.enosnery84.twittermock.repository.TweetRepository;
import com.enosnery84.twittermock.repository.UserRepository;
import com.enosnery84.twittermock.requests.TweetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TweetService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TweetRepository tweetRepository;

    public Boolean postTweet(TweetRequest request){
        try{
            Tweet temp = new Tweet();
            temp.setTweetUser(userRepository.findById(request.userId).get());
            temp.setTweet(request.tweet);
            temp.setDataRegister(new Date());
            tweetRepository.save(temp);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

}
