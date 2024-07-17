package com.example.template;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.template.Adapter.NutritionAdapter;
import com.example.template.Database.FitnessDatabase;
import com.example.template.Database.PlanDao;
import com.example.template.object.Nutrition;
import com.example.template.object.Plan;
import com.example.template.object.User;

import java.util.List;

public class NutritionFragment extends Fragment {
    RecyclerView rcvNutrition;
    NutritionAdapter nutritionAdapter;
    List<Nutrition> mListNutrition;
    Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_nutrition,container,false);
        initViews(view);
        setLayout();
        setAdapter();
        return view;
    }

    private void initViews(View view) {
        rcvNutrition = view.findViewById(R.id.rcv_nutitrion);
    }

    private void setLayout() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcvNutrition.setLayoutManager(staggeredGridLayoutManager);
    }

    private void setAdapter() {
        mListNutrition = getListNutrition();
        nutritionAdapter = new NutritionAdapter();
        nutritionAdapter.setData(mListNutrition);
        rcvNutrition.setAdapter(nutritionAdapter);
    }
    private List<Nutrition> getListNutrition(){
        User user = FitnessDatabase.getInstance(mContext).userDao().getUser();
        Plan plan = FitnessDatabase.getInstance(mContext).planDao().getPlansByName(user.getGoal());
        return FitnessDatabase.getInstance(mContext).nutritionDao().selectNutritionByID(plan.getPlanId());

    }


}