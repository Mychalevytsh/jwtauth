package com.mychalevytsh.jwtauth.repository;

import com.mychalevytsh.jwtauth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName (String name);
}
