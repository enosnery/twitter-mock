package com.enosnery84.twittermock.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class TweetUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String profileName;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @OneToMany
    private List<TweetUser> followers;

    @OneToMany
    private List<TweetUser> following;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tweetUser")
    private List<Tweet> tweets;

    public TweetUser() {
    }

    public TweetUser(@NotNull String profileName, @NotNull String userName, @NotNull String password, List<TweetUser> followers, List<TweetUser> following, List<Tweet> tweets) {
        this.profileName = profileName;
        this.userName = userName;
        this.password = password;
        this.followers = followers;
        this.following = following;
        this.tweets = tweets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TweetUser> getFollowers() {
        return followers;
    }

    public void setFollowers(List<TweetUser> followers) {
        this.followers = followers;
    }

    public List<TweetUser> getFollowing() {
        return following;
    }

    public void setFollowing(List<TweetUser> following) {
        this.following = following;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
