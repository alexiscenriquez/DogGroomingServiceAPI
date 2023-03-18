package com.myprojects.dogs.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {
    public static enum Role{
        ROLE_USER,ROLE_ADMIN
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    @NotBlank
    private String username;
    @Column(nullable = false)
    @NotBlank
    private String password;

    @Column(columnDefinition = "boolean default true")
    private boolean enabled;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
