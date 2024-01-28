package com.gym.helper.controller;

import com.gym.helper.helper.ExceptionHelper;
import com.gym.helper.model.AuthenticationResponse;
import com.gym.helper.model.LoginRequest;
import com.gym.helper.model.RegisterRequest;
import com.gym.helper.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/trainer-register")
    public ResponseEntity<?> registerTrainer(@RequestBody RegisterRequest registerRequest) {
        try {
            return ResponseEntity.ok(authenticationService.registerTrainer(registerRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(ExceptionHelper.extractException(e.getCause().toString()), HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/trainee-register")
    public ResponseEntity<?> registerTrainee(@RequestBody RegisterRequest registerRequest) {
        try {
            return ResponseEntity.ok(authenticationService.registerTrainee(registerRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(ExceptionHelper.extractException(e.getCause().toString()), HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/trainer-login")
    public ResponseEntity<AuthenticationResponse> loginTrainer(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.authenticateTrainer(loginRequest));
    }

    @PostMapping("/trainee-login")
    public ResponseEntity<AuthenticationResponse> loginTrainee(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.authenticateTrainee(loginRequest));
    }

}
