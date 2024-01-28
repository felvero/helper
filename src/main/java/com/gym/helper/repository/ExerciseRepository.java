package com.gym.helper.repository;

import com.gym.helper.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("SELECT e FROM Trainer t JOIN t.exercises e WHERE t.id = :trainerId")
    List<Exercise> findExercisesByTrainerId(@Param("trainerId") Long trainerId);

    @Query("SELECT e FROM Trainee t JOIN t.exercises e WHERE t.id = :traineeId")
    List<Exercise> findExercisesByTraineeId(@Param("traineeId") Long traineeId);

    @Query("SELECT e FROM Trainer t JOIN t.exercises e WHERE t.id = :trainerId order by e.date desc")
    List<Exercise> findTop75ExercisesByTrainerIdOrderByDateDesc(@Param("trainerId") Long trainerId);

    @Query("SELECT e FROM Trainee t JOIN t.exercises e WHERE t.id = :traineeId order by e.date desc")
    List<Exercise> findTop75ExercisesByTraineeIdOrderByDateDesc(@Param("traineeId") Long traineeId);

}
