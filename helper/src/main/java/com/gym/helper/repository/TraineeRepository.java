package com.gym.helper.repository;

import com.gym.helper.entity.Trainee;
import com.gym.helper.model.TraineeDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    Optional<Trainee> findByEmail(String username);

    List<Trainee> findAllByTrainerId(Long trainerId);

    Trainee findById(long id);
//    @Transactional
//    Trainee updateTrainerId(Long traineeId, Long newTrainerId);
}
