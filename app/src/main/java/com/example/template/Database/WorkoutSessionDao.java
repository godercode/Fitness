package com.example.template.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.template.object.WorkoutSession;

import java.util.List;

@Dao
public interface WorkoutSessionDao {
    @Insert
    void insertWorkoutSession(WorkoutSession workoutSession);

    @Query("SELECT * FROM workout_session WHERE exerciseId = :exerciseId")
    List<WorkoutSession> getWorkoutSessionsForExercise(int exerciseId);

    @Query("SELECT SUM(endTime - startTime) FROM workout_session WHERE exerciseId = :exerciseId")
    Long getTotalTimeForExercise(int exerciseId);
    @Query("SELECT * FROM workout_session")
    List<WorkoutSession> getAllWorkoutSessions();
    @Query("SELECT COUNT(DISTINCT exerciseId) FROM workout_session")
    int getCompletedExerciseCount();

    @Query("SELECT SUM(endTime - startTime) FROM workout_session")
    long getTotalTimeAllExercises();
}
