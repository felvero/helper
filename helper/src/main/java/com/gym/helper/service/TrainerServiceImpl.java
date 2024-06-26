package com.gym.helper.service;

import com.gym.helper.entity.Exercise;
import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import com.gym.helper.helper.MapEntity;
import com.gym.helper.model.ExerciseDto;
import com.gym.helper.model.TrainerDto;
import com.gym.helper.repository.ExerciseRepository;
import com.gym.helper.repository.TraineeRepository;
import com.gym.helper.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository, TraineeRepository traineeRepository) {
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "trainers", allEntries = true),
            @CacheEvict(value = "trainer", key = "#trainer.id")
    })
    public TrainerDto addTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
        TrainerDto trainerDto = new TrainerDto();
        MapEntity.mapTrainerToTrainerDto(trainer, trainerDto);
        return trainerDto;
    }

    @Override
    @Cacheable("trainer")
    public List<TrainerDto> getTrainers() {
        return trainerRepository.findAll().stream().map(trainer -> {
            TrainerDto trainerDto = new TrainerDto();
            MapEntity.mapTrainerToTrainerDto(trainer, trainerDto);
            return trainerDto;
        }).toList();
    }

    @Override
    @Cacheable("trainer")
    public TrainerDto findById(Long id) {
        Optional<Trainer> foundTrainer = trainerRepository.findById(id);
        if (foundTrainer.isPresent()) {
            TrainerDto trainerDto = new TrainerDto();
            MapEntity.mapTrainerToTrainerDto(foundTrainer.get(), trainerDto);
            return trainerDto;
        }
        return null;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "trainers", allEntries = true),
            @CacheEvict(value = "trainer", key = "#id")
    })
    public TrainerDto updateTrainer(Long id, Trainer trainer) {
        Optional<Trainer> trainerFound = trainerRepository.findById(id);
        if (trainerFound.isPresent()) {
            Trainer trainerToUpdate = trainerFound.get();
            MapEntity.mapTrainerProperties(trainerToUpdate, trainer);
            trainerRepository.save(trainerToUpdate);

            TrainerDto trainerDto = new TrainerDto();
            MapEntity.mapTrainerToTrainerDto(trainerToUpdate, trainerDto);

            return trainerDto;
        }

        return null;
    }

    @Override
    public String getTrainerNameByTraineeId(Long traineeId) {
        Trainee trainee = traineeRepository.findById(traineeId)
                .orElseThrow(() -> new IllegalArgumentException("Trainee not found"));
        if (trainee.getTrainer() != null) {
            return trainee.getTrainer().getFirstName() + " " + trainee.getTrainer().getLastName();
        } else {
            throw new IllegalArgumentException("Trainer not found for the given trainee");
        }
    }
}
