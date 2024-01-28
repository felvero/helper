package com.gym.helper.service;

import com.gym.helper.entity.Exercise;
import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import com.gym.helper.helper.MapEntity;
import com.gym.helper.model.TraineeDto;
import com.gym.helper.repository.ExerciseRepository;
import com.gym.helper.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public TraineeServiceImpl(TraineeRepository traineeRepository, ExerciseRepository exerciseRepository) {
        this.traineeRepository = traineeRepository;
        this.exerciseRepository = exerciseRepository;
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
    public TraineeDto addExerciseToTrainee(Long id, Long exerciseId) {
        Optional<Trainee> trainee = traineeRepository.findById(id);
        Optional<Exercise> exercise = exerciseRepository.findById(exerciseId);

        if (trainee.isPresent() && exercise.isPresent()){
            Trainee foundTrainee = trainee.get();
            Exercise foundExercise = exercise.get();
            List<Exercise> exercises = foundTrainee.getExercises();
            exercises.add(foundExercise);
            foundTrainee.setExercises(exercises);
            traineeRepository.save(foundTrainee);

            TraineeDto traineeDto = new TraineeDto();
            MapEntity.mapTraineeToTraineeDto(foundTrainee, traineeDto);

            return traineeDto;
        }
        return null;
    }

    @Override
    public List<TraineeDto> findTraineeByTrainerId(Long trainerId) {
        return traineeRepository.findTraineesByTrainerId(trainerId)
                .stream()
                .map(trainee -> {
                    TraineeDto traineeDto = new TraineeDto();
                    MapEntity.mapTraineeToTraineeDto(trainee, traineeDto);
                    return traineeDto;
                }).toList();
    }

    @Override
    public Trainer findTrainerByTraineeId(Long traineeId) {
        return traineeRepository.findTrainerByTraineeId(traineeId);
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

}
