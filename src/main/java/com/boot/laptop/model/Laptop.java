package com.boot.laptop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "laptop_tbl")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private int laptop_id;
    @Column(name = "laptop_name")
    private String laptop_name;
    @Column(name = "laptop_price")
    private String laptop_price;

    @ManyToMany(mappedBy = "laptops", cascade = CascadeType.MERGE)
    private Set<User> userSet = new HashSet<>() {
    };

}
