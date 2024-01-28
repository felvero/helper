package com.gym.helper.controller;

import com.gym.helper.entity.Exercise;
import com.gym.helper.entity.Trainee;
import com.gym.helper.model.ExerciseDto;
import com.gym.helper.model.GetExercisesResponse;
import com.gym.helper.service.ExerciseService;
import com.gym.helper.service.TraineeService;
import com.gym.helper.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/exercises")
public class ExerciseController {
    private final Logger logger = LoggerFactory.getLogger(ExerciseController.class);

    private final ExerciseService exerciseService;
    private final TrainerService trainerService;
    private final TraineeService traineeService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, TrainerService trainerService, TraineeService traineeService) {
        this.exerciseService = exerciseService;
        this.trainerService = trainerService;
        this.traineeService = traineeService;
    }

    @PostMapping
    public ResponseEntity<?> addExercise (@RequestBody @Validated Exercise exercise){
        try{
            ExerciseDto exerciseDto = exerciseService.addExercise(exercise);
            return ResponseEntity.ok(exerciseDto);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping
    public ResponseEntity<GetExercisesResponse> getExercises(){
        List<ExerciseDto> exercises = exerciseService.getExercises();
        int count = exercises.size();
        GetExercisesResponse exercisesResponse = new GetExercisesResponse(exercises, count);
        return ResponseEntity.ok(exercisesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        ExerciseDto exerciseDto = exerciseService.findById(id);
        if (exerciseDto != null){
            return ResponseEntity.ok(exerciseDto);
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }


    @GetMapping("/exByTrainerId")
    public ResponseEntity<List<ExerciseDto>> findExercisesByTrainerId (@RequestParam("trainerId") Long trainerId){
        if (trainerId != null){
            return ResponseEntity.ok(exerciseService.findExercisesByTrainerId(trainerId));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @GetMapping("/exByTraineeId")
    public ResponseEntity<List<ExerciseDto>> findExercisesByTraineeId (@RequestParam("traineeId") Long traineeId){
        if (traineeId != null){
            return ResponseEntity.ok(exerciseService.findExercisesByTraineeId(traineeId));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @GetMapping("/exByTrainerIdLast30Days")
    public ResponseEntity<List<ExerciseDto>> findTop75ExercisesByTrainerIdOrderByDateDesc (@RequestParam("trainerId") Long trainerId){
        if (trainerId != null){
            return ResponseEntity.ok(exerciseService.findTop75ExercisesByTrainerIdOrderByDateDesc(trainerId));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @GetMapping("/exByTraineeIdLast30Days")
    public ResponseEntity<List<ExerciseDto>> findTop75ExercisesByTraineeIdOrderByDateDesc (@RequestParam("traineeId") Long traineeId){
        if (traineeId != null){
            return ResponseEntity.ok(exerciseService.findTop75ExercisesByTraineeIdOrderByDateDesc(traineeId));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NO_CONTENT.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExercise (@PathVariable("id") Long id, @RequestBody Exercise exercise){
        try {
            ExerciseDto exerciseDto = exerciseService.updateExercise(id, exercise);
            if (exerciseDto != null){
                return ResponseEntity.ok(exerciseDto);
            } else {
                return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok("OK");
    }
}


