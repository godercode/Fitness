package com.example.template;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.template.Database.FitnessDatabase;

public class ReportActivity extends AppCompatActivity {

    private TextView tvExerciseCount, tvTotalTime, tvCaloriesBurned;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_report);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvExerciseCount = findViewById(R.id.tv_exercise_count);
        tvTotalTime = findViewById(R.id.tv_total_time_rp);
        tvCaloriesBurned = findViewById(R.id.tv_calories_burned);
        btnBack = findViewById(R.id.btn_back_rp);

        btnBack.setOnClickListener(v -> finish());

        loadReportData();
    }
    private void loadReportData() {
        new Thread(() -> {
            FitnessDatabase db = FitnessDatabase.getInstance(this);
            int exerciseCount = db.workoutSessionDao().getCompletedExerciseCount();
            long totalTime = db.workoutSessionDao().getTotalTimeAllExercises();
            double caloriesBurned = (totalTime / 60000.0) * 5;
            runOnUiThread(() -> {
                tvExerciseCount.setText(String.valueOf(exerciseCount));
                tvTotalTime.setText(String.format("%d s", totalTime / 1000));
                tvCaloriesBurned.setText(String.format("%.2f calo", caloriesBurned));
            });
        }).start();
    }
}
