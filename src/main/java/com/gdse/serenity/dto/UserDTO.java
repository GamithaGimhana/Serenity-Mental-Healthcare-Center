package com.gdse.serenity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String  userId;
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String role;
}
