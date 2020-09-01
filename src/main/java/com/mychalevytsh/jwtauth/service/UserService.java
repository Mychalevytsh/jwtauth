package com.mychalevytsh.jwtauth.service;

import com.mychalevytsh.jwtauth.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    User findById(Long id);

    User findByUserName(String username);

    List<User> getAll();

    void delete(Long id);
}
