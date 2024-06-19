package com.gym.helper.service;

import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import com.gym.helper.helper.MapEntity;
import com.gym.helper.model.TraineeDto;
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
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;

    @Autowired
    public TraineeServiceImpl(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }
    private final TrainerRepository trainerRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "trainees", allEntries = true),
            @CacheEvict(value = "trainee", key = "#trainee.id") })
    public Trainee assignTrainer(Long traineeId, Long trainerId) {
        Trainee trainee = traineeRepository.findById(traineeId)
                .orElseThrow(() -> new IllegalArgumentException("Trainee not found"));
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new IllegalArgumentException("Trainer not found"));

        trainee.setTrainer(trainer);
        return traineeRepository.save(trainee);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "trainees", allEntries = true),
            @CacheEvict(value = "trainee", key = "#trainee.id") })
    public TraineeDto addTrainee(Trainee trainee) {
        traineeRepository.save(trainee);
        TraineeDto traineeDto = new TraineeDto();
        MapEntity.mapTraineeToTraineeDto(trainee, traineeDto);
        return traineeDto;
    }

    @Override
    @Cacheable("trainee")
    public List<TraineeDto> getTrainees() {
        return traineeRepository.findAll().stream().map(trainee -> {
            TraineeDto traineeDto = new TraineeDto();
            MapEntity.mapTraineeToTraineeDto(trainee, traineeDto);
            return traineeDto;
        }).toList();
    }

    @Override
    @Cacheable("trainee")
    public TraineeDto findById(Long id) {
        Optional <Trainee> foundTrainee = traineeRepository.findById(id);
        if (foundTrainee.isPresent()){
            TraineeDto traineeDto = new TraineeDto();
            MapEntity.mapTraineeToTraineeDto(foundTrainee.get(), traineeDto);
            return traineeDto;
        }
        return null;
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = "trainees", allEntries = true),
            @CacheEvict(value = "trainee", key = "#id") })
    public TraineeDto updateTrainee(Long id, Trainee trainee) {
        Optional<Trainee> traineeFound = traineeRepository.findById(id);
        if(traineeFound.isPresent()){
            Trainee traineeToUpdate = traineeFound.get();
            MapEntity.mapTraineeProperties(traineeToUpdate, trainee);
            traineeRepository.save(traineeToUpdate);

            TraineeDto traineeDto = new TraineeDto();
            MapEntity.mapTraineeToTraineeDto(traineeToUpdate, traineeDto);

            return traineeDto;
        }
        return null;
    }

    @Override
    @Cacheable("trainee")
    public List<Trainee> findAllByTrainerId(Long trainerId) {
        return traineeRepository.findAllByTrainerId(trainerId);
    }

    @Override
    public Trainee assingTrainer(Long id, Long trainerId) {
        return null;
    }


}
