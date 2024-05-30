package com.springboot.blog.data.repository;

import com.springboot.blog.data.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
