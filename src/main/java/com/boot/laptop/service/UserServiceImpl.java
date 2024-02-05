package com.boot.laptop.service;

import com.boot.laptop.model.User;
import com.boot.laptop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository ;

    @Override
    public String addUser(User user) {
         userRepository.saveAndFlush(user);
         return  "User added successfully";
    }

    @Override
    public List<User> retrieveAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int user_id) {
        return userRepository.findById(user_id);
    }
}
