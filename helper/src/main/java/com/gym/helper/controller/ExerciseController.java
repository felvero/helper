package com.gym.helper.controller;

import com.gym.helper.entity.Exercise;
import com.gym.helper.model.ExerciseDto;
import com.gym.helper.model.GetExercisesResponse;
import com.gym.helper.service.ExerciseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/exercises")
public class ExerciseController {
    private final Logger logger = LoggerFactory.getLogger(ExerciseController.class);
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping("/addToTrainer")
    public ResponseEntity<?> addExerciseToTrainer(@RequestBody ExerciseDto exerciseDto) {
        try {
            ExerciseDto exerciseAdded = exerciseService.addExerciseToTrainer(exerciseDto);
            return new ResponseEntity<>(exerciseAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/addToTrainee")
    public ResponseEntity<?> addExerciseToTrainee(@RequestBody ExerciseDto exerciseDto) {
        try {
            ExerciseDto exerciseAdded = exerciseService.addExerciseToTrainee(exerciseDto);
            return new ResponseEntity<>(exerciseAdded, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping
    public ResponseEntity<GetExercisesResponse> getExercises() {
        List<ExerciseDto> exercises = exerciseService.getExercises();
        int count = exercises.size();
        GetExercisesResponse exercisesResponse = new GetExercisesResponse(exercises, count);
        return ResponseEntity.ok(exercisesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ExerciseDto exerciseDto = exerciseService.findById(id);
        if (exerciseDto != null) {
            return ResponseEntity.ok(exerciseDto);
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise(@PathVariable("id") Long id, @RequestBody Exercise exercise) {
        try {
            ExerciseDto exerciseDto = exerciseService.updateExercise(id, exercise);
            if (exerciseDto != null) {
                return ResponseEntity.ok(exerciseDto);
            } else {
                return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/trainer/{trainerId}")
    public List<Exercise> findAllByTrainerId(@PathVariable Long trainerId) {
        return exerciseService.findAllByTrainerId(trainerId);
    }

    @GetMapping("/trainee/{traineeId}")
    public List<Exercise> findAllByTraineeId(@PathVariable Long traineeId) {
        return exerciseService.findAllByTraineeId(traineeId);
    }
}


