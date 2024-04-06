package com.boot.laptop.util;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.repository.LaptopRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LaptopUtil {
    private final LaptopRepository laptopRepository;

    public LaptopUtil(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public boolean isLaptopExists(Laptop laptop) {
        // Check if a laptop with the same name already exists in the database
        Optional<Laptop> existingLaptop = laptopRepository.findById(laptop.getLaptop_id());
        // Return true if the laptop already exists, false otherwise
        return existingLaptop.isPresent();
    }

    public boolean isLaptopIdExists(int laptop_id) {
        // Check if a laptop with the same name already exists in the database
        Optional<Laptop> existingLaptop = laptopRepository.findById(laptop_id);
        // Return true if the laptop already exists, false otherwise
        return existingLaptop.isPresent();
    }
}
