package com.gym.helper.repository;

import com.gym.helper.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
//    @Query("SELECT ex FROM Exercise ex WHERE ex.trainer.id = :trainerId")
    List<Exercise> findAllByTrainerId(Long trainerId);
//    @Query("SELECT e FROM Exercise e WHERE e.trainee.id = :traineeId")
    List<Exercise> findAllByTraineeId(Long traineeId);

}
