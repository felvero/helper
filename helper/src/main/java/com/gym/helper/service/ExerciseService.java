package com.gym.helper.service;

import com.gym.helper.entity.Exercise;
import com.gym.helper.model.ExerciseDto;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ExerciseService {

     List<ExerciseDto> getExercises();
     ExerciseDto addExerciseToTrainer (ExerciseDto exerciseDto);
     ExerciseDto addExerciseToTrainee (ExerciseDto exerciseDto);
     ExerciseDto findById(Long id);
     ExerciseDto updateExercise (Long id, Exercise exercise);
     void deleteExercise (Long id);
     List<Exercise> findAllByTrainerId(Long trainerId);
     List<Exercise> findAllByTraineeId(Long traineeId);
}
