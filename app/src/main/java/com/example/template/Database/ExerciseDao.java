package com.example.template.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.template.object.Exercise;

import java.util.List;
@Dao
public interface ExerciseDao {
    @Insert
    void insertExercise(Exercise exercise);
    @Update
    void updateExercise(Exercise exercise);
    @Query("SELECT * FROM exercise")
    List<Exercise> selectAllExercise();
    @Query("SELECT * FROM exercise WHERE exerciseName LIKE '%' || :name || '%'")
    List<Exercise> searchExercise(String name);

    @Query("SELECT * FROM exercise WHERE muscleId = :muscleId")
    List<Exercise> getExercisesByMuscleId(int muscleId);

    @Query("SELECT * FROM exercise WHERE planId = :planId")
    List<Exercise> getExercisesByPlanId(int planId);
}
