package com.gym.helper.controller;

import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import com.gym.helper.helper.ExceptionHelper;
import com.gym.helper.model.GetTraineesResponse;
import com.gym.helper.model.TraineeDto;
import com.gym.helper.model.TrainerDto;
import com.gym.helper.service.TraineeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {
    private final Logger logger = LoggerFactory.getLogger(TraineeController.class);

    private final TraineeService traineeService;

    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }


    @GetMapping
    public ResponseEntity<GetTraineesResponse> getTrainees(){
        List<TraineeDto> trainees = traineeService.getTrainees();
        int count = trainees.size();
        GetTraineesResponse traineesResponse = new GetTraineesResponse(trainees, count);
        return ResponseEntity.ok(traineesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById (@PathVariable Long id){
        TraineeDto traineeDto = traineeService.findById(id);
        if (traineeDto !=null) {
            return ResponseEntity.ok(traineeDto);
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }

    @PostMapping
    public ResponseEntity<?> addTrainee(@RequestBody @Validated Trainee trainee) {
        try {
            TraineeDto traineeDto = traineeService.addTrainee(trainee);
            return ResponseEntity.ok(traineeDto);
        } catch (Exception e) {
            return new ResponseEntity<>(ExceptionHelper.extractException(e.getCause().toString()),
                    HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrainee (@PathVariable Long id, @RequestBody Trainee trainee) throws Exception{
        try{
            TraineeDto traineeDto = traineeService.updateTrainee(id, trainee);
            if (traineeDto != null){
                return ResponseEntity.ok(traineeDto);
            } else {
                return new ResponseEntity<>(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
            }
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(ExceptionHelper.extractException(e.getCause().toString()),
                    HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/trainee/{trainerId}")
    public List<Trainee> findAllByTrainerId(@PathVariable Long trainerId) {
        return traineeService.findAllByTrainerId(trainerId);
    }


    @PostMapping("/trainee/{traineeId}/assign-trainer/{trainerId}")
    public ResponseEntity<Trainee> assignTrainerToTrainee(@PathVariable Long traineeId, @PathVariable Long trainerId) {
        Trainee updatedTrainee = traineeService.assignTrainer(traineeId, trainerId);
        return ResponseEntity.ok(updatedTrainee);
    }

}
