package com.example.template.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.template.object.Nutrition;

import java.util.List;

@Dao
public interface NutritionDao {
    @Insert
    void insertNutrition(Nutrition nutrition);
    @Query("SELECT * FROM nutrition")
    List<Nutrition> selectAllNutrition();
    @Query("SELECT * FROM nutrition WHERE planId= :planID ")
    List<Nutrition> selectNutritionByID(int planID);
}
