package com.enosnery84.twittermock.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Size(max = 140)
    private String tweet;

    @NotNull
    private java.util.Date dateRegister = new Date();

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private TweetUser tweetUser;

    public Tweet() {
    }

    public Tweet(@NotNull @Size(max = 140) String tweet, Date dateRegister) {
        this.tweet = tweet;
        this.dateRegister = dateRegister;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dataRegister) {
        this.dateRegister = dataRegister;
    }

    public TweetUser getTweetUser() {
        return tweetUser;
    }

    public void setTweetUser(TweetUser tweetUser) {
        this.tweetUser = tweetUser;
    }
}
