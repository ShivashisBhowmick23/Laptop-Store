package com.boot.laptop.service;

import com.boot.laptop.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    String addUser(User user);

    List<User> retrieveAllUser();

    Optional<User> getUserById(int user_id);
}
