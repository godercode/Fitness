package com.example.template;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.template.Database.FitnessDatabase;
import com.example.template.object.Exercise;
import com.example.template.object.WorkoutSession;

import java.util.List;

public class ExerciseDetail extends AppCompatActivity {
    TextView demoText;
    TextView tvName;
    ProgressBar progressBar;
    VideoView videoView;
    ImageButton btnPlay, btnSkip, btnBack;
    Chronometer chronometer;
    Exercise exercise;
    List<Exercise> listExercise;
    boolean isResume;
    boolean isChronometerRunning;
    int currentPos;
    long totalTime;
    private Handler handler = new Handler();
    private Runnable updateTotalTimeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvName = findViewById(R.id.tv_name);
        btnBack = findViewById(R.id.btn_back);
        demoText = findViewById(R.id.editTextTextMultiLine);
        btnSkip = findViewById(R.id.btn_skip);
        chronometer = findViewById(R.id.chronometer);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setMax(90);
        btnBack.setOnClickListener(v -> finish());

        getDataFromIntent();
        setVideo(exercise);
        setButton();
        skipClick();

        updateTotalTimeRunnable = new Runnable() {
            @Override
            public void run() {
                loadTotalTimeForExercise(exercise.getExerciseId());
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(updateTotalTimeRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateTotalTimeRunnable);
    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        exercise = (Exercise) bundle.getSerializable("exercise");
        listExercise = (List<Exercise>) bundle.getSerializable("listExercise");
        if (listExercise != null && exercise != null) {
            int position = listExercise.indexOf(exercise);
            if (position != -1) {
                Log.d("ExerciseDetail", "Position: " + position);
                currentPos = position;
            } else {
                Log.d("ExerciseDetail", "Exercise not found in the list");
            }
        }
    }

    private void setVideo(Exercise exercise) {
        videoView = findViewById(R.id.vdv_exercise);
        if (exercise != null && exercise.getExerciseVideo() != 0) {
            String videoPath = "android.resource://" + getPackageName() + "/" + exercise.getExerciseVideo();
            videoView.setVideoPath(videoPath);
            videoView.setOnPreparedListener(mp -> mp.seekTo(1000));
            videoView.setOnCompletionListener(mp -> videoView.start());
            videoView.pause();
            tvName.setText(exercise.getExerciseName());
            demoText.setText(exercise.getDemo());
            loadTotalTimeForExercise(exercise.getExerciseId());
        }
    }

    private void skipClick() {
        btnSkip.setOnClickListener(v -> {
            if (currentPos == listExercise.size() - 1) {
                currentPos = 0;
            } else {
                currentPos = currentPos + 1;
            }
            Exercise nextExercise = listExercise.get(currentPos);
            exercise = nextExercise;
            setVideo(nextExercise);
        });
    }

    public void setButton() {
        btnPlay = findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(v -> {
            if (!isResume) {
                isResume = true;
                btnPlay.setImageResource(R.drawable.pause);
                videoView.start();
                startChronometer();
            } else {
                isResume = false;
                btnPlay.setImageResource(R.drawable.play_arrow);
                videoView.pause();
                stopChronometer();
            }
        });
    }

    private void startChronometer() {
        if (!isChronometerRunning) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            isChronometerRunning = true;
        }
    }

    private void stopChronometer() {
        if (isChronometerRunning) {
            long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            isChronometerRunning = false;
            saveWorkoutSession(elapsedMillis);
        }
    }

    private void saveWorkoutSession(long elapsedMillis) {
        long endTime = System.currentTimeMillis();
        long startTime = endTime - elapsedMillis;
        int exerciseId = exercise.getExerciseId();
        WorkoutSession session = new WorkoutSession(exerciseId, startTime, endTime);
        new Thread(() -> {
            FitnessDatabase.getInstance(this).workoutSessionDao().insertWorkoutSession(session);
            loadTotalTimeForExercise(exerciseId);
        }).start();
    }

    private void loadTotalTimeForExercise(int exerciseId) {
        new Thread(() -> {
            Long loadedTotalTime = FitnessDatabase.getInstance(this).workoutSessionDao().getTotalTimeForExercise(exerciseId);
            runOnUiThread(() -> {
                if (loadedTotalTime != null) {
                    totalTime = loadedTotalTime;
                    progressBar.setProgress((int) (totalTime / 1000));
                } else {
                    totalTime = 0;
                    progressBar.setProgress(0);
                }
                if (totalTime >= 30000) {
                    btnSkip.setVisibility(View.VISIBLE);
                } else {
                    btnSkip.setVisibility(View.GONE);
                }
            });
        }).start();
    }
}
