package com.enosnery84.twittermock.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@SequenceGenerator(name="tweet_seq", initialValue=0, allocationSize=100)
@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweet_seq")
    private Long id;
    @NotNull
    @Size(max = 140)
    private String tweet;

    @NotNull
    private java.util.Date dataRegister = new Date();

    public Tweet() {
    }

    public Tweet(@NotNull @Size(max = 140) String tweet, Date dataRegister) {
        this.tweet = tweet;
        this.dataRegister = dataRegister;
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

    public Date getDataRegister() {
        return dataRegister;
    }

    public void setDataRegister(Date dataRegister) {
        this.dataRegister = dataRegister;
    }
}
