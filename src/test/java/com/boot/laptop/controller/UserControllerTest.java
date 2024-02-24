package com.boot.laptop.controller;

import com.boot.laptop.mapper.UserMapper;
import com.boot.laptop.model.User;
import com.boot.laptop.request.UserRequest;
import com.boot.laptop.response.UserResponse;
import com.boot.laptop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserService userService = mock(UserService.class);
    private UserMapper userMapper = mock(UserMapper.class);
    private UserController userController = new UserController(userService, userMapper);

    @Test
    public void testAddUser() {
        // Data setup
        UserRequest userRequest = new UserRequest(/* provide necessary parameters */);
        User user = new User(/* provide necessary parameters */);
        UserResponse userResponse = new UserResponse(/* provide necessary parameters */);

        when(userMapper.mapUserRequestToUser(userRequest)).thenReturn(user);
        when(userService.addUser(user)).thenReturn(String.valueOf(user));
        when(userMapper.mapUserToUserResponse(user)).thenReturn(userResponse);

        // Act
        ResponseEntity<UserResponse> responseEntity = userController.addUser(userRequest);

        // Assert
        verify(userMapper).mapUserRequestToUser(userRequest);
        verify(userService).addUser(user);
        verify(userMapper).mapUserToUserResponse(user);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());
    }
}
