package com.boot.laptop.controller;

import com.boot.laptop.constant.URLConstant;
import com.boot.laptop.mapper.UserMapper;
import com.boot.laptop.model.User;
import com.boot.laptop.request.UserRequest;
import com.boot.laptop.response.UserResponse;
import com.boot.laptop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLConstant.USER)
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(URLConstant.ADD_USER)
    @Operation(summary = "Add user into the database", description = " addUser method will add into the database and return inserted User", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "404", description = "Resource not found"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<UserResponse> addUser(UserRequest userRequest) {
        User request = userMapper.mapUserRequestToUser(userRequest);
        userService.addUser(request);
        UserResponse response = userMapper.mapUserToUserResponse(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
