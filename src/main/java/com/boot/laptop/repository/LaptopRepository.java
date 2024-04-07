package com.boot.laptop.repository;

import com.boot.laptop.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
    @Query(value = "SELECT DISTINCT l.* FROM laptop_tbl l LEFT JOIN user_laptop ul ON l.laptop_id = ul.laptop_id WHERE l.laptop_id = :laptop_id", nativeQuery = true)
    List<Laptop> findDistinctByLaptopId(@Param("laptop_id") int laptop_id);
}
