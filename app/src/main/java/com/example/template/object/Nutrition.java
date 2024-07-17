package com.example.template.object;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "nutrition", foreignKeys = @ForeignKey(entity = Plan.class,
        parentColumns = "planId",
        childColumns = "planId",
        onDelete = ForeignKey.CASCADE))
public class Nutrition {
    @PrimaryKey(autoGenerate = true)
    private int nutritionId;
    private String nutritionName;
    private int nutritionImage;
    private String nutrition;
    private int planId;

    public Nutrition(int nutritionId, String nutritionName, int nutritionImage, String nutrition, int planId) {
        this.nutritionId = nutritionId;
        this.nutritionName = nutritionName;
        this.nutritionImage = nutritionImage;
        this.nutrition = nutrition;
        this.planId = planId;
    }

    public int getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(int nutritionId) {
        this.nutritionId = nutritionId;
    }

    public String getNutritionName() {
        return nutritionName;
    }

    public void setNutritionName(String nutritionName) {
        this.nutritionName = nutritionName;
    }

    public int getNutritionImage() {
        return nutritionImage;
    }

    public void setNutritionImage(int nutritionImage) {
        this.nutritionImage = nutritionImage;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
