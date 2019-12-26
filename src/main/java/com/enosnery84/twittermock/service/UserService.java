package com.enosnery84.twittermock.service;

import com.enosnery84.twittermock.constants.Constants;
import com.enosnery84.twittermock.models.Tweet;
import com.enosnery84.twittermock.models.TweetUser;
import com.enosnery84.twittermock.repository.TweetRepository;
import com.enosnery84.twittermock.repository.UserRepository;
import com.enosnery84.twittermock.requests.FollowRequest;
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

    public TweetUser findByUserNameAndPassword(String username, String password){
        return userRepository.findByUserNameAndPassword(username, password);
    }

    public Boolean validateUser(Long userId){
        return userRepository.findById(userId).isPresent();
    }

    public List<Tweet> getTweets(Long userId) {
        if (validateUser(userId)) {
            TweetUser temp = userRepository.findById(userId).get();
            List<Long> userIds = new ArrayList<>();
            userIds.add(temp.getId());
            for(TweetUser following : temp.getFollowing()){
                userIds.add(following.getId());
            }
            System.out.println(userIds);
            return tweetRepository.findAllTweetsByUserAndFollowers(userIds);
        } else {
            return null;
        }
    }

    public String follow(FollowRequest request){
        if(validateUser(request.userId)){
            if(validateUser(request.followingId)){
                TweetUser user = userRepository.findById(request.userId).get();
                TweetUser following = userRepository.findById(request.followingId).get();
                user.getFollowing().add(following);
                userRepository.save(user);
                return "Você agora está seguindo " + following.getProfileName();
            }else{
                return Constants.ERROR_FOLLOWING;
            }
        }else{
            return Constants.ERROR_USER;
        }
    }
}
