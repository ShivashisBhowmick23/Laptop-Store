package com.boot.laptop.controller;
import com.boot.laptop.model.User;
import com.boot.laptop.request.UserRequest;
import com.boot.laptop.response.UserResponse;
import com.boot.laptop.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void testAddUser() throws Exception {
        // Testing successful user addition
        UserRequest userRequest = new UserRequest();
        User user = new User();
        user.setUser_name("Superman");
        user.setUser_id(1);
        user.setUser_email("super.man@example.com");
        UserResponse expectedResponse = new UserResponse();

        when(userService.addUser(any(User.class))).thenReturn(String.valueOf(user));

        ResponseEntity<UserResponse> response = userController.addUser(userRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

        // Testing resource not found
        when(userService.addUser(any(User.class))).thenThrow(new Exception("User not found"));

        assertThrows(Exception.class, () -> userController.addUser(userRequest));

        // Testing internal server error
        when(userService.addUser(any(User.class))).thenThrow(new Exception("Internal Server Error"));

        assertThrows(Exception.class, () -> userController.addUser(userRequest));
    }
}
