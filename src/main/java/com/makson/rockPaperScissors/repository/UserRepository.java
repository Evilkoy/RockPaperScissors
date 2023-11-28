package com.makson.rockPaperScissors.repository;

import com.makson.rockPaperScissors.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
