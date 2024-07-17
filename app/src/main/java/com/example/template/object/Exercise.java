package com.example.template.object;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "exercise", foreignKeys = {
        @ForeignKey(entity = Muscle.class,
                parentColumns = "muscleId",
                childColumns = "muscleId",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Plan.class,
                parentColumns = "planId",
                childColumns = "planId",
                onDelete = ForeignKey.CASCADE)
})
public class Exercise implements Serializable {
    @PrimaryKey
    private int exerciseId;
    private int muscleId;
    private String exerciseName;
    private int exerciseImage;
    private int exerciseVideo;
    private String demo;
    private int planId;

    public Exercise(int exerciseId, int muscleId, String exerciseName, int exerciseImage, int exerciseVideo, String demo, int planId) {
        this.exerciseId = exerciseId;
        this.muscleId = muscleId;
        this.exerciseName = exerciseName;
        this.exerciseImage = exerciseImage;
        this.exerciseVideo = exerciseVideo;
        this.demo = demo;
        this.planId = planId;
    }
    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getMuscleId() {
        return muscleId;
    }

    public void setMuscleId(int muscleId) {
        this.muscleId = muscleId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getExerciseImage() {
        return exerciseImage;
    }

    public void setExerciseImage(int exerciseImage) {
        this.exerciseImage = exerciseImage;
    }

    public int getExerciseVideo() {
        return exerciseVideo;
    }

    public void setExerciseVideo(int exerciseVideo) {
        this.exerciseVideo = exerciseVideo;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
