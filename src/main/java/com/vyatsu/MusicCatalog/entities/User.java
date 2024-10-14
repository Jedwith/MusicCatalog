package com.vyatsu.MusicCatalog.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.EAGER) // множество User к одному Role (одна роль может быть у нескольких пользователей)
    @JoinColumn(name = "role_id")
    private Role role;
}
