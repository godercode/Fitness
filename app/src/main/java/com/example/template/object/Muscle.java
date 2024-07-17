package com.example.template.object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="muscle")
public class Muscle implements Serializable {
    @PrimaryKey
    private int muscleId;
    private String muscleName;
    private int muscleImage;

    public Muscle(int muscleId, String muscleName, int muscleImage) {
        this.muscleId = muscleId;
        this.muscleName = muscleName;
        this.muscleImage = muscleImage;
    }

    public int getMuscleId() {
        return muscleId;
    }

    public void setMuscleId(int muscleId) {
        this.muscleId = muscleId;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }

    public int getMuscleImage() {
        return muscleImage;
    }

    public void setMuscleImage(int muscleImage) {
        this.muscleImage = muscleImage;
    }
}
