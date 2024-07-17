package com.example.template;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.template.Adapter.PlanAdapter;
import com.example.template.Database.FitnessDatabase;
import com.example.template.Interface.IClickExerciseItemListener;
import com.example.template.object.Exercise;
import com.example.template.object.Plan;
import com.example.template.object.User;

import java.io.Serializable;
import java.util.List;

public class PlanFragment extends Fragment {
    private RecyclerView rcvPlan;
    private ImageButton btnReport, btnEdit;
    private TextView tvPlanName;
    private Context mContext;
    PlanAdapter planAdapter;
    List<Exercise> mListExercise;
    private boolean isDeleteButtonVisible = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        initViews(view);
        setLayout();
        setAdapter();
        setBtnEditClick();
        setBtnReportClick();
        return view;
    }
    @Override
    public void onAttach(@NonNull Context mContext) {
        super.onAttach(mContext);
        this.mContext = mContext;
    }
    private void initViews(View view) {
        rcvPlan = view.findViewById(R.id.rcv_plan);
        btnReport = view.findViewById(R.id.btn_report);
        btnEdit = view.findViewById(R.id.btn_edit);
        tvPlanName = view.findViewById(R.id.tv_plan_name);
    }
    private void setBtnReportClick(){
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReportActivity.class);
                startActivity(intent);
            }
        });
    }
    private void setBtnEditClick(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDeleteButtonVisible = !isDeleteButtonVisible;
                planAdapter.setDeleteButtonVisible(isDeleteButtonVisible);
            }
        });
    }
    private void setLayout(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvPlan.setLayoutManager(linearLayoutManager);

    }
    private void setAdapter(){
        planAdapter= new PlanAdapter(new IClickExerciseItemListener() {
            @Override
            public void onClickExerciseItemListener(Exercise exercise) {
                clickExerciseItem(exercise);
            }
        }, mContext);
        mListExercise = getListExercise();
        planAdapter.setData(mListExercise);
        rcvPlan.setAdapter(planAdapter);
    }
    private List<Exercise> getListExercise() {
        List<Exercise> list;
        User user = FitnessDatabase.getInstance(mContext).userDao().getUser();
        if(user != null){
            Plan plan = FitnessDatabase.getInstance(mContext).planDao().getPlansByName(user.getGoal());
            tvPlanName.setText(plan.getPlanName());
            list = FitnessDatabase.getInstance(mContext).exerciseDao().getExercisesByPlanId(plan.getPlanId());
        }
        else{
            list = null; }
        return list;
    }
    private void clickExerciseItem(Exercise exercise) {
        Intent intent = new Intent(mContext, ExerciseDetail.class);
        Bundle bundle = new Bundle();
        List<Exercise> list = getListExercise();
        bundle.putSerializable("listExercise", (Serializable) list);
        bundle.putSerializable("exercise", exercise);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}