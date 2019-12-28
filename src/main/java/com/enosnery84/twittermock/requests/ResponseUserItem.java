package com.enosnery84.twittermock.requests;

public class ResponseUserItem {
    public Long id;
    public String name;
    public Boolean isFollowing = false;

    @Override
    public String toString() {
        return "ResponseUserItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isFollowing=" + isFollowing +
                '}';
    }
}
