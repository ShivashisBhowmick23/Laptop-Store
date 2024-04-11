package com.boot.laptop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.List;

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
    @NotNull(message = "Required field")
    private int laptop_id;
    @Column(name = "laptop_name")
    @NotNull(message = "Required field")
    private String laptop_name;
    @Column(name = "laptop_price")
    @NotNull
    private String laptop_price;
    @ManyToMany(mappedBy = "laptops", cascade = CascadeType.MERGE)
    private List<User> userSet = new ArrayList<>() {
    };

}
