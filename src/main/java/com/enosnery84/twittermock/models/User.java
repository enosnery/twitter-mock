package com.enosnery84.twittermock.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@SequenceGenerator(name="user_seq", initialValue=0, allocationSize=100)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    @NotNull
    private String profileName;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @OneToMany
    private List<User> followers;

    @OneToMany
    private List<User> following;

    @OneToMany
    private List<Tweet> tweets;

    public User() {
    }

    public User(@NotNull String profileName, @NotNull String userName, @NotNull String password, List<User> followers, List<User> following, List<Tweet> tweets) {
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

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }
}
