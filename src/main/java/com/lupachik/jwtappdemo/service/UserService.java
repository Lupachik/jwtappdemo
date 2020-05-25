package com.lupachik.jwtappdemo.service;

import com.lupachik.jwtappdemo.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByLogin(String login);

    User findById(Long id);

    void delete(Long id);
}
