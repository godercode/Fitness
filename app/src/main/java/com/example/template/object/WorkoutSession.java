package com.example.template.object;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "workout_session", foreignKeys = {
        @ForeignKey(entity = Exercise.class,
                parentColumns = "exerciseId",
                childColumns = "exerciseId",
                onDelete = ForeignKey.CASCADE)
})
public class WorkoutSession implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int sessionId;
    private int exerciseId;
    private long startTime;
    private long endTime;

    public WorkoutSession(int exerciseId, long startTime, long endTime) {
        this.exerciseId = exerciseId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
