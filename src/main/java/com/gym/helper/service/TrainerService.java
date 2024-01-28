package com.gym.helper.service;

import com.gym.helper.entity.Trainer;
import com.gym.helper.model.TrainerDto;

import java.util.List;

public interface TrainerService {
    TrainerDto addTrainer(Trainer trainer);

    List<TrainerDto> getTrainers();

    TrainerDto findById(Long id);

    TrainerDto updateTrainer (Long id,Trainer trainer);

    TrainerDto addTraineesToTrainer(Long id, Long traineeId);

    TrainerDto addExerciseToTrainer(Long id, Long traineeId);

}
