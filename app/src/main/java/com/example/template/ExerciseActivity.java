package com.example.template;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.Adapter.PlanAdapter;
import com.example.template.Database.FitnessDatabase;
import com.example.template.Interface.IClickExerciseItemListener;
import com.example.template.object.Exercise;
import com.example.template.object.Muscle;

import java.util.List;

public class ExerciseActivity extends AppCompatActivity {
    TextView tvMuscle, tvAddToPlan;
    ImageButton btnBack;
    RecyclerView rcv;
    PlanAdapter planAdapter;
    List<Exercise> mListExercise;
    Muscle muscle;
    private boolean isAddButtonVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        getDataFromIntent();
        setLayout();
        setAdapter();
        tvMuscle.setText(muscle.getMuscleName());
        tvAddToPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAddButtonVisible = !isAddButtonVisible;
                planAdapter.setAddButtonVisible(isAddButtonVisible);
            }
        });
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
    }

    private void initViews() {
        tvMuscle = findViewById(R.id.tv_name);
        rcv = findViewById(R.id.rcv_muscle_ex);
        tvAddToPlan = findViewById(R.id.add_to_plan);
    }
    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        muscle = (Muscle) bundle.getSerializable("muscle");
    }
    private void setLayout(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
    }
    private void setAdapter(){
        planAdapter = new PlanAdapter(new IClickExerciseItemListener() {
            @Override
            public void onClickExerciseItemListener(Exercise exercise) {
                clickExerciseItem(exercise);
            }
        }, this);
        mListExercise = getListExercise();
        planAdapter.setData(mListExercise);
        rcv.setAdapter(planAdapter);
    }
    private List<Exercise> getListExercise() {
        return FitnessDatabase.getInstance(this).exerciseDao().getExercisesByMuscleId(muscle.getMuscleId());
    }

    private void clickExerciseItem(Exercise exercise) {
        Intent intent = new Intent(this, ExerciseDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("exercise", exercise);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}