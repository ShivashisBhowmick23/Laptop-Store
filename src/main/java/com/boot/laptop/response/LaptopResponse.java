package com.boot.laptop.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LaptopResponse {
    private int laptop_id;
    private String laptop_name;
    private String laptop_price;
}
