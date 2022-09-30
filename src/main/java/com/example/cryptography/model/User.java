
package com.example.cryptography.model;

import com.example.cryptography.model.enumerations.Role;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "cryptography_users")
public class User implements UserDetails {


    @Id
    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String name;

    private java.lang.String surname;

    private java.lang.String email;


    private String string;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;


   @OneToMany
    private List<Tests> tests;
    @OneToMany
    private List<Ask> asks;

    public User() {
    }

    public User(java.lang.String username, java.lang.String password, java.lang.String name,
                java.lang.String surname, java.lang.String email, String string, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.string = string;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }



    @Override
    public java.lang.String toString() {
        return "User{}";
    }
}

