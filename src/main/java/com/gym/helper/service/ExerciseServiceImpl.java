package com.gym.helper.service;

import com.gym.helper.entity.Exercise;
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

    @Autowired

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.exerciseRepository = exerciseRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public ExerciseDto addExercise(Exercise exercise) {
        exerciseRepository.save(exercise);
        ExerciseDto exerciseDto = new ExerciseDto();
        MapEntity.mapExerciseToExerciseDto(exercise, exerciseDto);
        return exerciseDto;
    }

    @Override
    public ExerciseDto updateExercise(Long id, Exercise exercise) {
        Optional<Exercise> exerciseFound = exerciseRepository.findById(id);
        if(exerciseFound.isPresent()){
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

//    @Override
//    public ProductDto deleteProduct(Long id) {
//        Optional<Product> product = productRepository.findById(id);
//        if(product.isPresent()){
//            productRepository.deleteById(id);
//            ProductDto productDto = new ProductDto();
//            MapEntity.mapProductToProductDto(product.get(), productDto);
//            return productDto;
//        }
//
//        return null;
//    }

    @Override
    @Cacheable("exercises")
    public List<ExerciseDto> findExercisesByTrainerId(Long trainerId) {
        return exerciseRepository.findExercisesByTrainerId(trainerId)
                .stream()
                .map(exercise -> {
                    ExerciseDto exerciseDto = new ExerciseDto();
                    MapEntity.mapExerciseToExerciseDto(exercise, exerciseDto);
                    return exerciseDto;
                }).toList();
    }

    @Override
    @Cacheable("exercises")
    public List<ExerciseDto> findExercisesByTraineeId(Long traineeId) {
        return exerciseRepository.findExercisesByTraineeId(traineeId)
                .stream()
                .map(exercise -> {
                    ExerciseDto exerciseDto = new ExerciseDto();
                    MapEntity.mapExerciseToExerciseDto(exercise, exerciseDto);
                    return exerciseDto;
                }).toList();
    }

    @Override
    @Cacheable("exercises")
    public List<ExerciseDto> findTop75ExercisesByTrainerIdOrderByDateDesc(Long trainerId) {
        return exerciseRepository.findTop75ExercisesByTrainerIdOrderByDateDesc(trainerId)
                .stream()
                .map(exercise -> {
                    ExerciseDto exerciseDto = new ExerciseDto();
                    MapEntity.mapExerciseToExerciseDto(exercise, exerciseDto);
                    return exerciseDto;
                }).toList();
    }

    @Override
    @Cacheable("exercises")
    public List<ExerciseDto> findTop75ExercisesByTraineeIdOrderByDateDesc(Long traineeId) {
        return exerciseRepository.findTop75ExercisesByTraineeIdOrderByDateDesc(traineeId)
                .stream()
                .map(exercise -> {
                    ExerciseDto exerciseDto = new ExerciseDto();
                    MapEntity.mapExerciseToExerciseDto(exercise, exerciseDto);
                    return exerciseDto;
                }).toList();
    }

}
