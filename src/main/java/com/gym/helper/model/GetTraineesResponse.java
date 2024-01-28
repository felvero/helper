package com.gym.helper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTraineesResponse {
    private List<TraineeDto> trainees;
    private int count;
}
