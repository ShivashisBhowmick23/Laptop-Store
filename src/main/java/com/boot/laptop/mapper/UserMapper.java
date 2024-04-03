package com.boot.laptop.mapper;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.model.User;
import com.boot.laptop.repository.LaptopRepository;
import com.boot.laptop.request.UserRequest;
import com.boot.laptop.response.LaptopResponse;
import com.boot.laptop.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {

    Logger logger = LoggerFactory.getLogger(UserMapper.class);
    @Autowired
    LaptopRepository laptopRepository;

    /**
     * Map the user request to a user, including laptop details if available.
     *
     * @param userRequest the user request object to be mapped
     * @return the user object mapped from the user request
     */
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
        } else {
            System.out.println("Laptop details not found for user: " + userRequest.getUser_id());
        }
        System.out.println("User mapped successfully from user request: " + userRequest.getUser_id());
        return user;
    }

    /**
     * Maps a User object to a UserResponse object.
     *
     * @param user the User object to be mapped
     * @return the mapped UserResponse object
     */
    public UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        logger.debug("Mapping user to user response");

        userResponse.setUser_name(user.getUser_name());
        userResponse.setUser_email(user.getUser_email());
        userResponse.setUser_id(user.getUser_id());

        user.getLaptops().stream().findFirst().ifPresent(laptop -> {
            userResponse.setLaptop_id(laptop.getLaptop_id());
            userResponse.setLaptop_name(laptop.getLaptop_name());
            userResponse.setLaptop_price(laptop.getLaptop_price());
            logger.debug("Setting laptop details in user response");
        });

        return userResponse;
    }

    public List<UserResponse> mapUserListToUserResponseList(List<User> users) {
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = new UserResponse();
            userResponse.setUser_name(user.getUser_name());
            userResponse.setUser_email(user.getUser_email());
            userResponse.setUser_id(user.getUser_id());
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }
}


