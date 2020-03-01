package com.rmp.rmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmp.rmp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
