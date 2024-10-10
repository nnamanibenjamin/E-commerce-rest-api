package com.nnamanibenjamin.E_commerce.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @java.lang.Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @java.lang.Override
    public java.lang.String getUsername() {
        return email;
    }

    @java.lang.Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @java.lang.Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @java.lang.Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @java.lang.Override
    public boolean isEnabled() {
        return true;
    }

    public enum Role{
        USER, ADMIN
    }
}
