package com.sgu.springTask.service;

import com.google.common.collect.Lists;
import com.sgu.springTask.mvc.model.User;
import com.sgu.springTask.repositiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }
}
