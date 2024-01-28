package com.gym.helper.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity(name = "Exercise")
@Table(name = "_exercise")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "exercise_name", nullable = false, columnDefinition = "TEXT")
    private String exerciseName;

    @Column(name = "series1")
    private Integer series1;

    @Column(name = "series2")
    private Integer series2;

    @Column(name = "series3")
    private Integer series3;

    @Column(name = "series4")
    private Integer series4;

    @Column(name = "series5")
    private Integer series5;

    @Column(name = "series6")
    private Integer series6;

    @Column(name = "series7")
    private Integer series7;

    @Column(name = "series8")
    private Integer series8;

    @Column(name = "series9")
    private Integer series9;

    @Column(name = "series10")
    private Integer series10;

    @Column(name = "maximum_weight")
    private Integer maximumWeight;

    @Column(name = "date", nullable = false)
    private Date date = new Date();

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;
}
