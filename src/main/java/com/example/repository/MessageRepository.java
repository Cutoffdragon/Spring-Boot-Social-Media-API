package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

    // Query to get all messages by a user
    public List<Message> findAllByPostedBy(int postedBy);

}
