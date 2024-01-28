package com.gym.helper.service;

import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import com.gym.helper.model.TraineeDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TraineeService {
    public TraineeDto addTrainee(Trainee trainee);

    TraineeDto updateTrainee (Long id, Trainee trainee);

    public List<TraineeDto> getTrainees();

    public TraineeDto findById(Long id);

    public TraineeDto addExerciseToTrainee (Long id, Long traineeId);

    List<TraineeDto> findTraineeByTrainerId (Long trainerId);

    Trainer findTrainerByTraineeId (Long traineeId);

}
