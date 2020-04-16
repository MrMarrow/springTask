package com.sgu.springTask.mvc.controller;

import com.sgu.springTask.mvc.model.LoginOperation;
import com.sgu.springTask.mvc.model.User;
import com.sgu.springTask.repositiry.AccountRepository;
import com.sgu.springTask.repositiry.UserRepository;
import com.sgu.springTask.service.UserLoginService;
import com.sgu.springTask.service.UserService;
import com.sgu.springTask.web.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;
import java.util.List;

import static com.sgu.springTask.constant.UrlConstant.GET_ALL_USERS;
import static com.sgu.springTask.constant.UrlConstant.SING_IN;
import static com.sgu.springTask.constant.UrlConstant.SING_UP;

/**
 * Account controller.
 */
@RestController
public class UserController {

    private UserService service;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private UserLoginService userLoginService;
    private JwtToken jwtToken;

    @Autowired
    public UserController(UserService service, AuthenticationManager authenticationManager,
                          UserRepository userRepository, UserLoginService userLoginService,
                          JwtToken jwtToken) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userLoginService = userLoginService;
        this.jwtToken = jwtToken;
    }


    @PostMapping(value = SING_IN)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginOperation authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userLoginService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping(SING_UP)
    public Boolean create(@RequestBody User body) throws ValidationException {
        if (userRepository.existsUserByLogin(body.getLogin())) {
            throw new ValidationException("Username already existed");
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(body.getPassword());
        userRepository.save(new User(body.getLogin(), encodedPassword, body.getAddress(), body.getPhone()));
        return true;
    }

    @GetMapping(GET_ALL_USERS)
    public List<User> getAllUsers() {
        return service.getAll();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
