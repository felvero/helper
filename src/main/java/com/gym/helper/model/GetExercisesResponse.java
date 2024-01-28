package com.gym.helper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetExercisesResponse {
    private List<ExerciseDto> exercises;
    private int count;
}
