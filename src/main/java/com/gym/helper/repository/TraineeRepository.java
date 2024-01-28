package com.gym.helper.repository;

import com.gym.helper.entity.Trainee;
import com.gym.helper.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    Optional<Trainee> findByEmail(String username);

    @Query("SELECT te FROM Trainer t JOIN t.trainees te WHERE t.id = :trainerId")
    List<Trainee> findTraineesByTrainerId(@Param("trainerId") Long trainerId);

    @Query("SELECT t FROM Trainer t JOIN t.trainees te WHERE te.id = :traineeId")
    Trainer findTrainerByTraineeId(@Param("traineeId") Long traineeId);
}
