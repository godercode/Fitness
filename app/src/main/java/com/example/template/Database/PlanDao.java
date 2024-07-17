package com.example.template.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.template.object.Plan;
import java.util.List;

@Dao
public interface PlanDao {
    @Insert
    void insertPlan(Plan plan);
    @Update
    void updatePlan(Plan plan);

    @Query("SELECT * FROM `plan`")
    Plan getPlan();
    @Query("SELECT * FROM `plan` WHERE planName = :planName")
    Plan getPlansByName(String planName);
}