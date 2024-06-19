package com.gym.helper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity(name = "Exercise")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;
    private String exerciseName;
    private Integer series1;
    private Integer series2;
    private Integer series3;
    private Integer series4;
    private Integer series5;
    private Integer series6;
    private Integer series7;
    private Integer series8;
    private Integer series9;
    private Integer series10;
    private Integer maximumWeight;
    private Date date = new Date();
    private String comment;
}
