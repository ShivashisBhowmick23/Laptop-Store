package com.boot.laptop.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private int user_id;
    private String user_name;
    private String user_email;
    private int laptop_id;
    private String laptop_name;
    private String laptop_price;
}
