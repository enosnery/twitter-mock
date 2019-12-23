package com.enosnery84.twittermock.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Size(max = 140)
    private String tweet;

    @NotNull
    private java.util.Date dataRegister = new Date();

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
