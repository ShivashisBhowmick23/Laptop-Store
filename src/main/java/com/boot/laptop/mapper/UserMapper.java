package com.boot.laptop.mapper;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.model.User;
import com.boot.laptop.repository.LaptopRepository;
import com.boot.laptop.request.UserRequest;
import com.boot.laptop.response.LaptopResponse;
import com.boot.laptop.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    @Autowired
    LaptopRepository laptopRepository;

    public User mapUserRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setUser_id(userRequest.getUser_id());
        user.setUser_name(userRequest.getUser_name());
        user.setUser_email(userRequest.getUser_email());

        // Retrieve the laptop details
        Optional<Laptop> optionalLaptop = laptopRepository.findById(userRequest.getLaptop_id());
        if (optionalLaptop.isPresent()) {
            Laptop laptop = optionalLaptop.get();
            // Update laptop details
            laptop.setLaptop_price(userRequest.getLaptop_price());
            laptop.setLaptop_name(userRequest.getLaptop_name());
            // Add the laptop to the user's set of laptops
            user.getLaptops().add(laptop);
        }

        return user;
    }


    public UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUser_name(user.getUser_name());
        userResponse.setUser_email(user.getUser_email());
        userResponse.setUser_id(user.getUser_id());
        if (!user.getLaptops().isEmpty()) {
            Laptop laptop = user.getLaptops().iterator().next();
            userResponse.setLaptop_id(laptop.getLaptop_id());
            userResponse.setLaptop_name(laptop.getLaptop_name());
            userResponse.setLaptop_price(laptop.getLaptop_price());
        }

        return userResponse;
    }
}
