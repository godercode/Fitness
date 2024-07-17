package com.example.template.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.template.object.Muscle;


import java.util.List;

@Dao
public interface MuscleDao {
    @Insert
    void insertMuscle(Muscle muscle);
    @Query("SELECT * FROM muscle")
    List<Muscle> selectAllMuscle();
}
