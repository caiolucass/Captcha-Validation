package com.example.captchavalidation.service;

import com.example.captchavalidation.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    void createUser(User user);
    List<User> getAllUsers();
    Optional<User> getOneUser(Integer id);
}
