package com.example.template.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.template.object.Exercise;
import com.example.template.object.Muscle;
import com.example.template.object.Nutrition;
import com.example.template.object.Plan;
import com.example.template.object.User;
import com.example.template.object.WorkoutSession;


@Database(entities = {User.class, Muscle.class, Plan.class, Exercise.class, Nutrition.class, WorkoutSession.class}, version = 1)
public abstract class FitnessDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "fitness.db";
    private static FitnessDatabase instance;

    public static synchronized FitnessDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FitnessDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract UserDao userDao();
    public abstract MuscleDao muscleDao();
    public abstract ExerciseDao exerciseDao();
    public abstract PlanDao planDao();
    public abstract WorkoutSessionDao workoutSessionDao();
    public abstract NutritionDao nutritionDao();
}
