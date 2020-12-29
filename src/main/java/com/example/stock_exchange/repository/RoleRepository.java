package com.example.stock_exchange.repository;

import com.example.stock_exchange.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByRoleName(String roleName);
}
