package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

    //Query used to find an account by username
    Optional<Account> findByUsername(String username);

    //Query used to find an account by username and password
    Optional<Account> findByUsernameAndPassword(String username, String password);

}
