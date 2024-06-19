package com.gym.helper.service;

import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import com.gym.helper.model.ExerciseDto;
import com.gym.helper.model.TraineeDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TraineeService {
     List<TraineeDto> getTrainees();

    @Caching(evict = {
            @CacheEvict(value = "trainees", allEntries = true),
            @CacheEvict(value = "trainee", key = "#trainee.id") })
    Trainee assignTrainer(Long traineeId, Long trainerId);

    TraineeDto addTrainee(Trainee trainee);
     TraineeDto findById(Long id);
    TraineeDto updateTrainee (Long id, Trainee trainee);
    List<Trainee> findAllByTrainerId(Long trainerId);
//    Trainee updateTrainerId(Long traineeId, Long newTrainerId);

    Trainee assingTrainer(Long id, Long trainerId);

}
