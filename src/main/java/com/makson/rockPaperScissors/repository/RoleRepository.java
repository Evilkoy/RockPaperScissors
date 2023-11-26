package com.makson.rockPaperScissors.repository;

import com.makson.rockPaperScissors.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
