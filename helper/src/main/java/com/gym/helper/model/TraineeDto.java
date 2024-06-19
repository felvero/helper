package com.gym.helper.model;

import com.gym.helper.entity.Exercise;
import com.gym.helper.entity.Trainer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TraineeDto {

    private Long id;
    private Trainer trainer;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Integer yearOfBirth;
    private String actualGym;
    private Instant date;
}
