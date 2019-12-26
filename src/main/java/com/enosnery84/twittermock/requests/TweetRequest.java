package com.enosnery84.twittermock.requests;

import javax.validation.constraints.Size;

public class TweetRequest {
    public Long userId;

    @Size(max = 140)
    public String tweet;

}
