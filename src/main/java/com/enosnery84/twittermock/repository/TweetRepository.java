package com.enosnery84.twittermock.repository;

import com.enosnery84.twittermock.models.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query("SELECT t FROM Tweet t WHERE t.tweetUser.id IN (:ids)")
    Page<Tweet> findTop10ByUserAndFollowers(@Param("ids")List<Long> userIds, Pageable pageable);
    
}
