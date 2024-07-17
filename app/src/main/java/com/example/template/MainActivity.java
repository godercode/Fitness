package com.example.template;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.template.Database.FitnessDatabase;
import com.example.template.Database.InsertData;
import com.example.template.object.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bottomNavigationView = findViewById(R.id.bottom_nav);
        User user = FitnessDatabase.getInstance(this).userDao().getUser();
        if(user == null){
            replaceFragment(new UserFragment(this)); // Truyền MainActivity vào UserFragment
            bottomNavigationView.setVisibility(View.GONE);
        }
        else{
            replaceFragment(new PlanFragment());
            bottomNavigationView.setSelectedItemId(R.id.action_plan);
        }
        bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.action_plan){
                replaceFragment(new PlanFragment());
            }
            if(menuItem.getItemId() == R.id.action_muscle){
                replaceFragment(new MuscleFragment());
            }
            if(menuItem.getItemId() == R.id.action_nutrition){
                replaceFragment(new NutritionFragment());
            }
            if(menuItem.getItemId() == R.id.action_user){
                replaceFragment(new UserFragment(this)); // Truyền MainActivity vào UserFragment
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frl_main, fragment);
        fragmentTransaction.commit();
    }
    public void setVisibility(){
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}
