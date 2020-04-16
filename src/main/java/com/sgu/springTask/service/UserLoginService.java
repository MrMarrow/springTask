package com.sgu.springTask.service;

import com.sgu.springTask.mvc.model.User;
import com.sgu.springTask.repositiry.UserRepository;
import com.sgu.springTask.web.exceptions.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(name);

        if (user == null) {
            user = userRepository.findByPhone(name);
            if (user == null) {
                throw new NotFound("User not found with this name");
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                new ArrayList<>());
    }

}
