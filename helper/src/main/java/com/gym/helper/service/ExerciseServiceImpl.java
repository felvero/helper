package com.gym.helper.service;

import com.gym.helper.entity.Exercise;
import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import com.gym.helper.helper.MapEntity;
import com.gym.helper.model.ExerciseDto;
import com.gym.helper.repository.ExerciseRepository;
import com.gym.helper.repository.TraineeRepository;
import com.gym.helper.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    public final ExerciseRepository exerciseRepository;
    public final TraineeRepository traineeRepository;
    public final TrainerRepository trainerRepository;

    private final MapEntity mapEntity;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, TraineeRepository traineeRepository, TrainerRepository trainerRepository, MapEntity mapEntity) {
        this.exerciseRepository = exerciseRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.mapEntity = mapEntity;
    }

    @Override
    public ExerciseDto addExerciseToTrainer(ExerciseDto exerciseDto) {
        Exercise exercise = MapEntity.mapExerciseDtoToExercise(exerciseDto);

        Optional<Trainer> trainerOptional = trainerRepository.findById(exerciseDto.getTrainer().getId());

        if (trainerOptional.isPresent()) {
            exercise.setTrainer(trainerOptional.get());

            Exercise exerciseSaved = exerciseRepository.save(exercise);
            return MapEntity.mapExerciseToExerciseDto(exerciseSaved, exerciseDto);
        } else {
            throw new RuntimeException("One or more entities was not found!");
        }
    }

    @Override
    public ExerciseDto addExerciseToTrainee(ExerciseDto exerciseDto) {
        Exercise exercise = MapEntity.mapExerciseDtoToExercise(exerciseDto);

        Optional<Trainee> traineeOptional = traineeRepository.findById(exerciseDto.getTrainee().getId());

        if (traineeOptional.isPresent()) {
            exercise.setTrainee(traineeOptional.get());

            Exercise exerciseSaved = exerciseRepository.save(exercise);
            return MapEntity.mapExerciseToExerciseDto(exerciseSaved, exerciseDto);
        } else {
            throw new RuntimeException("One or more entities was not found!");
        }
    }

    @Override
    public ExerciseDto updateExercise(Long id, Exercise exercise) {
        Optional<Exercise> exerciseFound = exerciseRepository.findById(id);
        if (exerciseFound.isPresent()) {
            Exercise exerciseToUpdate = exerciseFound.get();
            MapEntity.mapExerciseProperties(exerciseToUpdate, exercise);
            exerciseToUpdate.setDate(new Date());
            exerciseRepository.save(exerciseToUpdate);

            ExerciseDto exerciseDto = new ExerciseDto();
            MapEntity.mapExerciseToExerciseDto(exerciseToUpdate, exerciseDto);


            return exerciseDto;
        }

        return null;
    }

    @Override
    @Cacheable("exercise")
    public List<ExerciseDto> getExercises() {
        return exerciseRepository.findAll().stream().map(exercise -> {
            ExerciseDto exerciseDto = new ExerciseDto();
            MapEntity.mapExerciseToExerciseDto(exercise, exerciseDto);
            return exerciseDto;
        }).toList();
    }


    @Override
    @Cacheable("exercise")
    public ExerciseDto findById(Long id) {
        Optional<Exercise> foundExercise = exerciseRepository.findById(id);
        if (foundExercise.isPresent()) {
            ExerciseDto exerciseDto = new ExerciseDto();
            MapEntity.mapExerciseToExerciseDto(foundExercise.get(), exerciseDto);
            return exerciseDto;
        }
        return null;
    }

    @Override
    public void deleteExercise(Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {
            exerciseRepository.deleteById(id);
        }
    }

    @Override
    @Cacheable("exercise")
    public List<Exercise> findAllByTrainerId(Long trainerId) {
        return exerciseRepository.findAllByTrainerId(trainerId);
    }

    @Override
    @Cacheable("exercise")
    public List<Exercise> findAllByTraineeId(Long traineeId) {
        return exerciseRepository.findAllByTraineeId(traineeId);
    }
}
