package com.example.template.object;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "plan", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "userId",
        childColumns = "userId",
        onDelete = ForeignKey.CASCADE))
public class Plan {
    @PrimaryKey(autoGenerate = true)
    private int planId;
    private String planName;
    private int userId;

    public Plan(int planId, String planName, int userId) {
        this.planId = planId;
        this.planName = planName;
        this.userId = userId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
