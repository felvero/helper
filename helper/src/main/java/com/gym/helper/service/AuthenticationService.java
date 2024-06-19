package com.gym.helper.service;

import com.gym.helper.model.AuthenticationResponse;
import com.gym.helper.model.LoginRequest;
import com.gym.helper.model.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticationResponse registerTrainer (RegisterRequest request);

    AuthenticationResponse registerTrainee (RegisterRequest request);

    AuthenticationResponse authenticateTrainer (LoginRequest request);

    AuthenticationResponse authenticateTrainee (LoginRequest request);
}
