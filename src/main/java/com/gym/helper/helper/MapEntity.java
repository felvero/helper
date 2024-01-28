package com.gym.helper.helper;

import com.gym.helper.entity.Exercise;
import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import com.gym.helper.model.ExerciseDto;
import com.gym.helper.model.TraineeDto;
import com.gym.helper.model.TrainerDto;

import java.time.Instant;

public class MapEntity {

    public static void mapTrainerProperties(Trainer initialTrainer, Trainer trainer){
        initialTrainer.setFirstName(trainer.getFirstName());
        initialTrainer.setLastName(trainer.getLastName());
        initialTrainer.setEmail(trainer.getEmail());
        initialTrainer.setPassword(trainer.getPassword());
        initialTrainer.setPhoneNumber(trainer.getPhoneNumber());
        initialTrainer.setYearOfBirth(trainer.getYearOfBirth());
        initialTrainer.setActualGym(trainer.getActualGym());
        trainer.setTrainees(trainer.getTrainees());
        trainer.setExercises(trainer.getExercises());

    }

    public static void mapTrainerToTrainerDto(Trainer trainer, TrainerDto trainerDto){
        trainerDto.setId(trainer.getId());
        trainerDto.setFirstName(trainer.getFirstName());
        trainerDto.setLastName(trainer.getLastName());
        trainerDto.setEmail(trainer.getEmail());
        trainerDto.setPassword(trainer.getPassword());
        trainerDto.setPhoneNumber(trainer.getPhoneNumber());
        trainerDto.setYearOfBirth(trainer.getYearOfBirth());
        trainerDto.setActualGym(trainer.getActualGym());
        trainerDto.setDate(trainer.getDate());
        trainerDto.setTrainees(trainer.getTrainees());
        trainerDto.setExercises(trainer.getExercises());
    }

    public static void mapTrainerDtoToTrainer(TrainerDto trainerDto, Trainer trainer){
        trainer.setId(trainerDto.getId());
        trainer.setFirstName(trainerDto.getFirstName());
        trainer.setLastName(trainerDto.getLastName());
        trainer.setEmail(trainerDto.getEmail());
        trainer.setPassword(trainerDto.getPassword());
        trainer.setPhoneNumber(trainerDto.getPhoneNumber());
        trainer.setYearOfBirth(trainerDto.getYearOfBirth());
        trainer.setActualGym(trainerDto.getActualGym());
        trainer.setDate(trainerDto.getDate());

    }


    public static void mapTraineeProperties(Trainee initialTrainee, Trainee trainee){
        initialTrainee.setFirstName(trainee.getFirstName());
        initialTrainee.setLastName(trainee.getLastName());
        initialTrainee.setEmail(trainee.getEmail());
        initialTrainee.setPassword(trainee.getPassword());
        initialTrainee.setPhoneNumber(trainee.getPhoneNumber());
        initialTrainee.setYearOfBirth(trainee.getYearOfBirth());
        initialTrainee.setActualGym(trainee.getActualGym());
        trainee.setExercises(trainee.getExercises());
    }

    public static void mapTraineeToTraineeDto(Trainee trainee, TraineeDto traineeDto){
        traineeDto.setId(trainee.getId());
        traineeDto.setFirstName(trainee.getFirstName());
        traineeDto.setLastName(trainee.getLastName());
        traineeDto.setPhoneNumber(trainee.getPhoneNumber());
        traineeDto.setYearOfBirth(trainee.getYearOfBirth());
        traineeDto.setActualGym(trainee.getActualGym());
        traineeDto.setEmail(trainee.getEmail());
        traineeDto.setPassword(trainee.getPassword());
        traineeDto.setDate(trainee.getDate());
        traineeDto.setExercises(trainee.getExercises());
    }

    public static void mapTraineeDtoToTrainee(TraineeDto traineeDto, Trainee trainee){
        trainee.setId(traineeDto.getId());
        trainee.setFirstName(traineeDto.getFirstName());
        trainee.setLastName(traineeDto.getLastName());
        trainee.setEmail(traineeDto.getEmail());
        trainee.setPassword(traineeDto.getPassword());
        trainee.setPhoneNumber(traineeDto.getPhoneNumber());
        trainee.setYearOfBirth(traineeDto.getYearOfBirth());
        trainee.setActualGym(traineeDto.getActualGym());
        trainee.setDate(traineeDto.getDate());
    }

    public static void mapExerciseProperties(Exercise initialExercise, Exercise exercise){
        initialExercise.setExerciseName(exercise.getExerciseName());
        initialExercise.setSeries1(exercise.getSeries1());
        initialExercise.setSeries2(exercise.getSeries2());
        initialExercise.setSeries3(exercise.getSeries3());
        initialExercise.setSeries4(exercise.getSeries4());
        initialExercise.setSeries5(exercise.getSeries5());
        initialExercise.setSeries6(exercise.getSeries6());
        initialExercise.setSeries7(exercise.getSeries7());
        initialExercise.setSeries8(exercise.getSeries8());
        initialExercise.setSeries9(exercise.getSeries9());
        initialExercise.setSeries10(exercise.getSeries10());
        initialExercise.setMaximumWeight(exercise.getMaximumWeight());
        exercise.setDate(exercise.getDate());
        initialExercise.setComment(exercise.getComment());
    }

    public static void mapExerciseToExerciseDto(Exercise exercise, ExerciseDto exerciseDto){
        exerciseDto.setId(exercise.getId());
        exerciseDto.setExerciseName(exercise.getExerciseName());
        exerciseDto.setSeries1(exercise.getSeries1());
        exerciseDto.setSeries2(exercise.getSeries2());
        exerciseDto.setSeries3(exercise.getSeries3());
        exerciseDto.setSeries4(exercise.getSeries4());
        exerciseDto.setSeries5(exercise.getSeries5());
        exerciseDto.setSeries6(exercise.getSeries6());
        exerciseDto.setSeries7(exercise.getSeries7());
        exerciseDto.setSeries8(exercise.getSeries8());
        exerciseDto.setSeries9(exercise.getSeries9());
        exerciseDto.setSeries10(exercise.getSeries10());
        exerciseDto.setMaximumWeight(exercise.getMaximumWeight());
        exerciseDto.setDate(exercise.getDate());
        exerciseDto.setComment(exercise.getComment());
    }

    public static void mapExerciseDtoToExercise(ExerciseDto exerciseDto, Exercise exercise){
        exercise.setId(exerciseDto.getId());
        exercise.setExerciseName(exerciseDto.getExerciseName());
        exercise.setSeries1(exerciseDto.getSeries1());
        exercise.setSeries2(exerciseDto.getSeries2());
        exercise.setSeries3(exerciseDto.getSeries3());
        exercise.setSeries4(exerciseDto.getSeries4());
        exercise.setSeries5(exerciseDto.getSeries5());
        exercise.setSeries6(exerciseDto.getSeries6());
        exercise.setSeries7(exerciseDto.getSeries7());
        exercise.setSeries8(exerciseDto.getSeries8());
        exercise.setSeries9(exerciseDto.getSeries9());
        exercise.setSeries10(exerciseDto.getSeries10());
        exercise.setMaximumWeight(exerciseDto.getMaximumWeight());

        exercise.setComment(exerciseDto.getComment());
    }
}
