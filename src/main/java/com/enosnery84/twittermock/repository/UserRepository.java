package com.enosnery84.twittermock.repository;

import com.enosnery84.twittermock.models.TweetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<TweetUser, Long> {

    TweetUser findByUserNameAndPassword(String username, String password);

    List<TweetUser> findAllByIdIsNot(Long userId);
}
