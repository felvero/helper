package com.gym.helper.security;

import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import com.gym.helper.repository.TraineeRepository;
import com.gym.helper.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<Trainer> trainerOptional = trainerRepository.findByEmail(username);
            if (trainerOptional.isPresent()) {
                return (UserDetails) trainerOptional.get();
            }

            Optional<Trainee> traineeOptional = traineeRepository.findByEmail(username);
            if (traineeOptional.isPresent()) {
                return (UserDetails) traineeOptional.get();
            }

            throw new UsernameNotFoundException("User not found");
        };
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
