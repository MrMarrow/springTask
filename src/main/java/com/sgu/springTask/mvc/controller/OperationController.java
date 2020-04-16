package com.sgu.springTask.mvc.controller;

import com.sgu.springTask.mvc.model.Operation;
import com.sgu.springTask.mvc.model.User;
import com.sgu.springTask.service.OperationService;
import com.sgu.springTask.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.sgu.springTask.constant.UrlConstant.GET_ALL_OPERATIONS_BY_USER;

@Controller
public class OperationController {

    private UserService userService;
    private OperationService operationService;

    public OperationController(UserService userService, OperationService operationService) {
        this.userService = userService;
        this.operationService = operationService;
    }

    @GetMapping(GET_ALL_OPERATIONS_BY_USER)
    public @ResponseBody
    List<Operation> getAllByUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getByLogin(username);

        return operationService.getAllOperations(user.getId());
    }
}
