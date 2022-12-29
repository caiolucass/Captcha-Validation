package com.example.captchavalidation.repository;

import com.example.captchavalidation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
