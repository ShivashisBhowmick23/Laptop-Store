package com.boot.laptop.service;

import com.boot.laptop.model.Laptop;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LaptopService {

    void addLaptop(Laptop laptop);

    List<Laptop> getAllLaptop();

    Optional<Laptop> getLaptopById(int laptop_id);

    void deleteLaptopByLaptopID(int laptop_id);

    List<Laptop> findLaptopByIdWithUsers(int laptopId);

    List<Laptop> getLaptopsByLaptopName(String laptopName);


}
