package com.mychalevytsh.jwtauth.service.impl;

import com.mychalevytsh.jwtauth.model.Role;
import com.mychalevytsh.jwtauth.model.Status;
import com.mychalevytsh.jwtauth.model.User;
import com.mychalevytsh.jwtauth.repository.RoleRepository;
import com.mychalevytsh.jwtauth.repository.UserRepository;
import com.mychalevytsh.jwtauth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("IN register - user : {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null){
            log.warn("IN findById - no user found");
        }
        log.info("IN findById - user: {} was found by id {}", result, id);
        return result;
    }

    @Override
    public User findByUserName(String username) {
        User result = userRepository.findByName(username);
        log.info("IN findByUserName - user: {} was found by name {}", result, username);
        return result;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = userRepository.findAll();
        log.info("IN getAll - {} users found", allUsers.size());
        return allUsers;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} was successfully deleted", id);
    }
}
