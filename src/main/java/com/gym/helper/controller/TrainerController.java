package com.gym.helper.controller;

import com.gym.helper.entity.Trainer;
import com.gym.helper.helper.ExceptionHelper;
import com.gym.helper.model.GetTrainersResponse;
import com.gym.helper.model.TraineeDto;
import com.gym.helper.model.TrainerDto;
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
@RequestMapping("/api/trainers")
public class TrainerController {
    private final Logger logger = LoggerFactory.getLogger(TrainerController.class);

    private final TrainerService trainerService;

    @Autowired

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<?> addTrainer(@RequestBody @Validated Trainer trainer) {
        try {
            TrainerDto trainerDto = trainerService.addTrainer(trainer);
            return ResponseEntity.ok(trainerDto);
        } catch (Exception e) {
            return new ResponseEntity<>(ExceptionHelper.extractException(e.getCause().toString()),
                    HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping
    public ResponseEntity<GetTrainersResponse> getTrainers() {
        List<TrainerDto> trainers = trainerService.getTrainers();
        int count = trainers.size();
        GetTrainersResponse trainersResponse = new GetTrainersResponse(trainers, count);
        return ResponseEntity.ok(trainersResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrainer (@PathVariable Long id, @RequestBody Trainer trainer) throws Exception{
        try{
            TrainerDto trainerDto = trainerService.updateTrainer(id, trainer);
            if (trainerDto != null){
                return ResponseEntity.ok(trainerDto);
            } else {
                return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(ExceptionHelper.extractException(e.getCause().toString()),
                    HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }


    @PostMapping("{trainerId}/add-trainee/{traineeId}")
    public ResponseEntity<?> addTraineesToTrainer(@PathVariable Long trainerId, @PathVariable Long traineeId){
        TrainerDto trainerDto = trainerService.addTraineesToTrainer(trainerId, traineeId);
        if (trainerId != null){
            return ResponseEntity.ok(trainerDto);
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping("{trainerId}/add-exercise/{exerciseId}")
    public ResponseEntity<?> addExerciseToTrainer(@PathVariable Long trainerId, @PathVariable Long exerciseId){
        TrainerDto trainerDto = trainerService.addExerciseToTrainer(trainerId, exerciseId);
        if (trainerId != null){
            return ResponseEntity.ok(trainerDto);
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        TrainerDto trainerDto = trainerService.findById(id);
        if (trainerDto != null){
            return ResponseEntity.ok(trainerDto);
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }


}
