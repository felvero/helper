package com.gym.helper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExerciseDto {

    private Long id;
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
    private Date date;
    private String comment;
}
