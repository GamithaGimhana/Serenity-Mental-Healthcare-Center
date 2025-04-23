package com.gdse.serenity.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTM {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String role;

    public UserTM(String userId, String name, String email, String phone, String username, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.role = role;
    }
}
