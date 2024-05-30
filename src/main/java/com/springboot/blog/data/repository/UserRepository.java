package com.springboot.blog.data.repository;

import com.springboot.blog.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
