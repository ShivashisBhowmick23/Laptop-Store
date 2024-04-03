package com.boot.laptop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(name = "user_id")
    private int user_id;
    @NotEmpty
    @Column(name = "user_name")
    private String user_name;
    @NotEmpty
    @Column(name = "user_email")
    private String user_email;

    //Multiple Users Can Buy Multiple Laptops
    // ManyToMany Relationship
    @ManyToMany
    @JoinTable(name = "user_laptop",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "laptop_id")
    )
    private Set<Laptop> laptops = new HashSet<>();


}
