package com.boot.laptop.util;

import com.boot.laptop.model.Laptop;
import com.boot.laptop.repository.LaptopRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LaptopUtil {
    private final LaptopRepository laptopRepository;
    @PersistenceContext
    private EntityManager entityManager;

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


    public boolean isLaptopReferencedInUserLaptop(int laptop_id) {
        // Create a native SQL query to check if laptop_id is referenced in user_laptop table
        String sqlQuery = "SELECT COUNT(*) FROM user_laptop WHERE laptop_id = :laptopId";

        // Create the query
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("laptopId", laptop_id);

        // Execute the query and get the result
        int count = ((Number) query.getSingleResult()).intValue();
        System.out.println(count);

        // Return true if count > 0, indicating laptop_id is referenced, otherwise return false
        return count > 0;
    }
}

