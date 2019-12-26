package com.enosnery84.twittermock.repository;

import com.enosnery84.twittermock.models.TweetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TweetUser, Long> {

    TweetUser findByUserNameAndPassword(String username, String password);
}
