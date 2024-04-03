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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(URLConstant.USER)
public class UserController {

    Logger log = LoggerFactory.getLogger(LaptopController.class);

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
        log.debug("Mapping user request to user");
        User request = userMapper.mapUserRequestToUser(userRequest);
        log.debug("Adding user to the database");
        userService.addUser(request);
        log.debug("Mapping user to user response");
        UserResponse response = userMapper.mapUserToUserResponse(request);
        log.debug("Returning response with status OK");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(URLConstant.GET_ALL_USERS)
    @Operation(summary = "Get all Users", description = " getAllUserList is a method where it will retrieve all the users from the database", method = "GET")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "404", description = "Resource not found"), @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<List<UserResponse>> getAllUserList() {
        long userCount = 0;
        log.debug("Fetching all users from the database");
        List<User> userList = userService.retrieveAllUser();
        userCount = userList.size();
        log.debug("Mapping User list to user response list");
        List<UserResponse> responseList = userMapper.mapUserListToUserResponseList(userList);
        log.debug("Returning User response list");
        log.info("User count is {}", userCount);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);

    }
}
