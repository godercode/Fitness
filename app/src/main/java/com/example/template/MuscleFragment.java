package com.example.template;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.template.Adapter.MuscleAdapter;
import com.example.template.Adapter.PlanAdapter;
import com.example.template.Database.FitnessDatabase;
import com.example.template.Interface.IClickExerciseItemListener;
import com.example.template.Interface.IClickMuscleItemListener;
import com.example.template.object.Exercise;
import com.example.template.object.Muscle;

import java.util.List;

public class MuscleFragment extends Fragment {
    RecyclerView rcvMuscle;
    List<Muscle> mListMuscle;
    MuscleAdapter muscleAdapter;
    Context mContext;
    SearchView searchView;
    PlanAdapter planAdapter;
    private boolean isAddButtonVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muscle, container, false);
        initViews(view);
        setLayout();
        setAdapter();
        searchClick();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context mContext) {
        super.onAttach(mContext);
        this.mContext = mContext;
    }

    private void initViews(View view) {
        rcvMuscle = view.findViewById(R.id.rcv_muscle);
        searchView = view.findViewById(R.id.searchView);
    }

    private void setLayout(){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcvMuscle.setLayoutManager(staggeredGridLayoutManager);
    }

    private void setAdapter(){
        mListMuscle= getListMuscle();
        muscleAdapter = new MuscleAdapter(new IClickMuscleItemListener() {
            @Override
            public void onClickMuscleItem(Muscle muscle) {
                clickMuscleItem(muscle);
            }
        });
        muscleAdapter.setData(mListMuscle);
        rcvMuscle.setAdapter(muscleAdapter);
    }
    private void clickMuscleItem(Muscle muscle) {
        Intent intent = new Intent(mContext, ExerciseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("muscle", muscle);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private List<Muscle> getListMuscle() {
        return FitnessDatabase.getInstance(mContext).muscleDao().selectAllMuscle();
    }

    private void searchClick(){
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                isAddButtonVisible = !isAddButtonVisible;
                planAdapter.setAddButtonVisible(isAddButtonVisible);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchExercise(newText);
                return false;
            }
        });
    }
    private void searchExercise(String newText) {
        List<Exercise> searchList = FitnessDatabase.getInstance(mContext).exerciseDao().searchExercise(newText);
        if (searchList == null || searchList.isEmpty()) {
            Toast.makeText(mContext, "No data found", Toast.LENGTH_SHORT).show();
            setLayout();
            setAdapter();
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            rcvMuscle.setLayoutManager(linearLayoutManager);
            planAdapter = new PlanAdapter(new IClickExerciseItemListener() {
                @Override
                public void onClickExerciseItemListener(Exercise exercise) {
                    clickExerciseItem(exercise);
                }
            }, mContext);
            planAdapter.setData(searchList);
            rcvMuscle.setAdapter(planAdapter);
        }
    }

    private void clickExerciseItem(Exercise exercise) {
        Intent intent = new Intent(mContext, ExerciseDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("exercise", exercise);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
