package com.boot.laptop.service;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.repository.LaptopRepository;
import com.boot.laptop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    LaptopRepository laptopRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void addLaptop(Laptop laptop) {
         laptopRepository.saveAndFlush(laptop);
    }

    @Override
    public List<Laptop> getAllLaptop() {
        return laptopRepository.findAll();
    }

    @Override
    public Optional<Laptop> getLaptopById(int laptop_id) {
        return laptopRepository.findById(laptop_id);
    }
}
