package com.gym.helper.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Data
@Entity(name="Trainer")
@Table(name = "_trainer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Trainer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false, columnDefinition = "TEXT")
    @Email
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "phone_number", nullable = false, columnDefinition = "TEXT")
    private String phoneNumber;

    @Column(name = "year_of_birth", nullable = false)
    private Integer yearOfBirth;

    @Column(name = "actual_gym", columnDefinition = "TEXT")
    private String actualGym;

    @Column(name = "date", nullable = false)
    private Instant date;

    @OneToMany(cascade = {CascadeType.ALL},  orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Trainee> trainees;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Exercise> exercises;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
