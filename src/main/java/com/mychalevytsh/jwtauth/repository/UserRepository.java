package com.mychalevytsh.jwtauth.repository;

import com.mychalevytsh.jwtauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName (String name);
}
