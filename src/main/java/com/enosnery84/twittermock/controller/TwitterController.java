package com.enosnery84.twittermock.controller;

import com.enosnery84.twittermock.constants.Constants;
import com.enosnery84.twittermock.models.Tweet;
import com.enosnery84.twittermock.requests.FollowRequest;
import com.enosnery84.twittermock.requests.TweetRequest;
import com.enosnery84.twittermock.service.TweetService;
import com.enosnery84.twittermock.service.UserService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class TwitterController {

    @Autowired
    UserService userService;

    @Autowired
    TweetService tweetService;

    private HashMap<String, Object> response;

    /**
     * @param userId - Id do usuário que solicita o feed
    * Método que retorna os tweets do feed de um usuário. Se o usuário não existir, o método irá retornar a mensagem correspondente;
    * se o usuário existir, o método irá retornar uma lista com os tweets do seu feed, ordenados por sua data de inclusão e ID.
     */
    @GetMapping("/feed")
    public HashMap<String, Object> getNewsFeed(@RequestParam Long userId){
        response = new HashMap<>();
        List<Tweet> tweets = userService.getTweets(userId);
        if(tweets == null){
            response.put("response", "Este usuário não existe");
        }else {
            response.put("response", tweets);
        }
        return response;
    }

    /**
    * @param request - corpo da requisição ( JSON contendo o Id do usuário que irá postar e o conteúdo do tweet)
    * Método para postar um tweet. O método irá validar se o usuário existe, e também irá retornar o status do que foi feito,
    * se o tweet foi incluído ou não.
     */
    @PostMapping("/tweet")
    public HashMap<String, Object> postTweet(@RequestBody TweetRequest request){
        response = new HashMap<>();
        if(userService.validateUser(request.userId)){
            if(tweetService.postTweet(request)){
                response.put(Constants.RESPONSE, "Tweet Postado!");
            }else{
                response.put(Constants.RESPONSE, "Não foi possível postar o Tweet");
            }
        }
        return response;
    }

    /**
     * @param request - corpo da requisição (JSON contendo o Id do usuário solicitante e o Id do usuário a ser seguido.)
    * Método para seguir outro usuário.
     */
    @PostMapping("/follow")
    public HashMap<String, Object> follow(@RequestBody FollowRequest request){
        response = new HashMap<>();
        String message = userService.follow(request);
        response.put(Constants.RESPONSE, message);
        return response;
    }

    /**
     * @param request - corpo da requisição (JSON contendo o Id do usuário solicitante e o Id do usuário que não será mais seguido.)
     * Método para deixar de seguir outro usuário.
     */
    @PostMapping("/unfollow")
    public HashMap<String, Object> unfollow(@RequestBody FollowRequest request){
        response = new HashMap<>();
        String message = userService.unfollow(request);
        response.put(Constants.RESPONSE, message);
        return response;
    }
}
