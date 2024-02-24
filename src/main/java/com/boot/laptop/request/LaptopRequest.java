package com.boot.laptop.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LaptopRequest {
    private int laptop_id;
    private String laptop_name;
    private String laptop_price;
}
