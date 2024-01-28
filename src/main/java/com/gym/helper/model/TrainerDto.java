package com.gym.helper.model;

import com.gym.helper.entity.Exercise;
import com.gym.helper.entity.Trainee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrainerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Integer yearOfBirth;
    private String actualGym;
    private Instant date;
    private List<Trainee> trainees;
    private List<Exercise> exercises;
}
