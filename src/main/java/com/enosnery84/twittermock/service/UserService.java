package com.enosnery84.twittermock.service;

import com.enosnery84.twittermock.constants.Constants;
import com.enosnery84.twittermock.models.Tweet;
import com.enosnery84.twittermock.models.TweetUser;
import com.enosnery84.twittermock.repository.TweetRepository;
import com.enosnery84.twittermock.repository.UserRepository;
import com.enosnery84.twittermock.requests.FollowRequest;
import com.enosnery84.twittermock.requests.ResponseTweetItem;
import com.enosnery84.twittermock.requests.ResponseUserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<ResponseUserItem> getUsers(){
        List<TweetUser> allUsers = userRepository.findAll();
        List<ResponseUserItem> response = new ArrayList<>();
        for (TweetUser tu : allUsers) {
            ResponseUserItem temp = new ResponseUserItem();
            temp.id = tu.getId();
            temp.name = tu.getProfileName();
            response.add(temp);
        }
        return response;
    }

    public List<ResponseUserItem> getFollowing(Long userId){
        if(validateUser(userId)) {
            TweetUser user = userRepository.findById(userId).get();
            List<TweetUser> allUsers = userRepository.findAllByIdIsNot(userId);
            List<ResponseUserItem> response = new ArrayList<>();
            for (TweetUser tu : allUsers) {
                ResponseUserItem temp = new ResponseUserItem();
                temp.id = tu.getId();
                temp.name = tu.getProfileName();
                if(user.getFollowing().contains(tu)){
                    temp.isFollowing = true;
                }
                response.add(temp);
            }
            return response;
        }else{
        return new ArrayList<>();
        }

    }

    public Boolean validateUser(Long userId){
        return userRepository.findById(userId).isPresent();
    }

    public List<ResponseTweetItem> getTweets(Long userId) {
        if (validateUser(userId)) {
            TweetUser temp = userRepository.findById(userId).get();
            List<Long> userIds = new ArrayList<>();
            List<ResponseTweetItem> response = new ArrayList<>();
            userIds.add(temp.getId());
            for(TweetUser following : temp.getFollowing()){
                userIds.add(following.getId());
            }
            Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
            for(Tweet tt : tweetRepository.findTop10ByUserAndFollowers(userIds, pageable).getContent()){
                ResponseTweetItem item = new ResponseTweetItem();
                item.id = tt.getId();
                item.tweet = tt.getTweet();
                item.dateRegister = tt.getDateRegister();
                item.tweetUser = tt.getTweetUser().getProfileName();
                response.add(item);
            }
            return response;
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

    public String unfollow(FollowRequest request){
        if(validateUser(request.userId)){
            if(validateUser(request.followingId)){
                TweetUser user = userRepository.findById(request.userId).get();
                TweetUser following = userRepository.findById(request.followingId).get();
                user.getFollowing().remove(following);
                userRepository.save(user);
                return "Você deixou de seguir " + following.getProfileName();
            }else{
                return Constants.ERROR_FOLLOWING;
            }
        }else{
            return Constants.ERROR_USER;
        }
    }
}
