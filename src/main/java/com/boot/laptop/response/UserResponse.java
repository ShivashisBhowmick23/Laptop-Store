package com.boot.laptop.response;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserResponse {
    private int user_id;
    private String user_name;
    private String user_email;
    private String laptop_name;
    private int laptop_id;
    private String laptop_price;
}
