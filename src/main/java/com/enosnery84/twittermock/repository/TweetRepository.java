package com.enosnery84.twittermock.repository;

import com.enosnery84.twittermock.models.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query("FROM Tweet WHERE id IN (?1) ORDER BY id DESC ")
    List<Tweet> findAllTweetsByUserAndFollowers(List<Long> userIds);
    
}
