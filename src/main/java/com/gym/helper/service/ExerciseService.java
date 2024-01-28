package com.gym.helper.service;

import com.gym.helper.entity.Exercise;
import com.gym.helper.entity.Trainer;
import com.gym.helper.model.ExerciseDto;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExerciseService {

     ExerciseDto addExercise (Exercise exercise);

     ExerciseDto updateExercise (Long id, Exercise exercise);

     List<ExerciseDto> getExercises();

     ExerciseDto findById(Long id);

     void deleteExercise (Long id);

     List<ExerciseDto> findExercisesByTrainerId(Long trainerId);

     List<ExerciseDto> findExercisesByTraineeId(Long traineeId);

     List<ExerciseDto> findTop75ExercisesByTrainerIdOrderByDateDesc(Long trainerId);

     List<ExerciseDto> findTop75ExercisesByTraineeIdOrderByDateDesc(Long traineeId);


}
