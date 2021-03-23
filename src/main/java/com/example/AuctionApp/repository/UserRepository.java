package com.example.AuctionApp.repository;

import com.example.AuctionApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    void deleteUserById(Long id);

    @Query(
            value = "SELECT * FROM user WHERE id = ?1 AND password = ?2",
            nativeQuery = true
    )
    Optional<User> loginUser(Long id, String Password);

    Optional<User> findUserById(Long id);
}
