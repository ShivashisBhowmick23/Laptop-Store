package com.boot.laptop.mapper;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.model.User;
import com.boot.laptop.request.UserRequest;
import com.boot.laptop.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapUserRequestToUser(UserRequest userRequest) {
        User user = new User();
        Laptop laptop = new Laptop();
        user.setUser_id(userRequest.getUser_id());
        user.setUser_name(userRequest.getUser_name());
        user.setUser_email(userRequest.getUser_email());
        laptop.setLaptop_id(userRequest.getLaptop_id());
        laptop.setLaptop_name(userRequest.getLaptop_name());
        laptop.setLaptop_price(userRequest.getLaptop_price());
        return user;
    }

    public UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUser_name(user.getUser_name());
        userResponse.setUser_email(user.getUser_email());
        userResponse.setUser_id(user.getUser_id());
        return userResponse;
    }
}
