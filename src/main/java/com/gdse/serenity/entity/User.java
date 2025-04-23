package com.gdse.serenity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User implements SuperEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String role;
}