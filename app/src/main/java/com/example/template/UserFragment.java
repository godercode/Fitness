package com.example.template;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.template.Database.FitnessDatabase;
import com.example.template.Database.InsertData;
import com.example.template.object.User;

public class UserFragment extends Fragment {
    RadioButton rbtNam, rbtNu, rbtEasy, rbtNormal, rbtHard, rbtWeightGain, rbtWeightLoss, rbtMuscleGain;
    EditText etName, etAge, etWeight, etHeight;
    Button btnSave;
    TextView tvHoSo;
    Context mContext;
    String name, level = "", goal = "";
    float weight, height;
    int age;
    boolean sex;
    MainActivity mainActivity; // Thêm biến mainActivity

    public UserFragment(MainActivity mainActivity) { // Truyền MainActivity vào UserFragment
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initViews(view);
        User user = FitnessDatabase.getInstance(mContext).userDao().getUser();
        if(user != null){
            getData(user);
        }else{
            tvHoSo.setText("Tạo mới");
            btnSave.setText("Đăng ký");
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user == null){
                    insertUser();
                    Toast.makeText(mContext, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    updateUser();
                    Toast.makeText(mContext, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context mContext) {
        super.onAttach(mContext);
        this.mContext = mContext;
    }

    private void initViews(View view) {
        tvHoSo = view.findViewById(R.id.tv_ho_so);
        rbtNam = view.findViewById(R.id.rbt_nam);
        rbtNu = view.findViewById(R.id.rbt_nu);
        rbtEasy = view.findViewById(R.id.rbt_easy);
        rbtNormal = view.findViewById(R.id.rbt_normal);
        rbtHard = view.findViewById(R.id.rbt_hard);
        rbtWeightGain = view.findViewById(R.id.rbt_weight_gain);
        rbtWeightLoss = view.findViewById(R.id.rbt_weight_loss);
        rbtMuscleGain = view.findViewById(R.id.rbt_muscle_gain);
        etName = view.findViewById(R.id.et_ho_ten);
        etAge = view.findViewById(R.id.et_tuoi);
        etWeight = view.findViewById(R.id.et_can_nang);
        etHeight = view.findViewById(R.id.et_chieu_cao);
        btnSave = view.findViewById(R.id.btn_save_in4);
    }

    private void updateUser() {
        User newUser = getNewUser();
        FitnessDatabase.getInstance(mContext).userDao().updateUser(newUser);
    }

    private void insertUser() {
        User newUser = getNewUser();
        FitnessDatabase.getInstance(mContext).userDao().insertUser(newUser);
        firstInsert();
        navigateToPlanFragment();
    }

    private void navigateToPlanFragment() {
        PlanFragment planFragment = new PlanFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frl_main, planFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        mainActivity.setVisibility(); // Sử dụng mainActivity để gọi setVisibility
    }

    private void getData(User user) {
        name = user.getUserName();
        level = user.getLevel();
        goal = user.getGoal();
        age = user.getAge();
        sex = user.isSex();
        height = user.getHeight();
        weight = user.getWeight();

        etName.setText(name);
        etAge.setText(String.valueOf(age));
        etHeight.setText(String.valueOf(height));
        etWeight.setText(String.valueOf(weight));

        if (sex) {
            rbtNam.setChecked(true);
        } else {
            rbtNu.setChecked(true);
        }

        if (level.equals("easy")) {
            rbtEasy.setChecked(true);
        } else if (level.equals("normal")) {
            rbtNormal.setChecked(true);
        } else if (level.equals("hard")) {
            rbtHard.setChecked(true);
        }

        if (goal.equals("weight gain")) {
            rbtWeightGain.setChecked(true);
        } else if (goal.equals("weight loss")) {
            rbtWeightLoss.setChecked(true);
        } else if (goal.equals("muscle gain")) {
            rbtMuscleGain.setChecked(true);
        }
    }

    private User getNewUser() {
        name = etName.getText().toString().trim();
        if (name.isEmpty()) {
            etName.setError("Tên không được để trống");
        }
        try {
            height = Float.parseFloat(etHeight.getText().toString().trim());
        } catch (NumberFormatException e) {
            etHeight.setError("Chiều cao không hợp lệ");
        }
        try {
            weight = Float.parseFloat(etWeight.getText().toString().trim());
        } catch (NumberFormatException e) {
            etWeight.setError("Cân nặng không hợp lệ");
        }
        try {
            age = Integer.parseInt(etAge.getText().toString().trim());
        } catch (NumberFormatException e) {
            etAge.setError("Tuổi không hợp lệ");
        }
        if (rbtNam.isChecked()) {
            sex = true;
        } else if (rbtNu.isChecked()) {
            sex = false;
        } else {
            Toast.makeText(getContext(), "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
        }
        if (rbtEasy.isChecked()) {
            level = "easy";
        } else if (rbtNormal.isChecked()) {
            level = "normal";
        } else if (rbtHard.isChecked()) {
            level = "hard";
        } else {
            Toast.makeText(getContext(), "Vui lòng chọn cấp độ", Toast.LENGTH_SHORT).show();
        }

        if (rbtWeightGain.isChecked()) {
            goal = "weight gain";
        } else if (rbtWeightLoss.isChecked()) {
            goal = "weight loss";
        } else if (rbtMuscleGain.isChecked()) {
            goal = "muscle gain";
        }
        return new User(1, name, weight, height, age, sex, level, goal);
    }

    private void firstInsert() {
        InsertData insertData = new InsertData(mContext);
        insertData.insertPlan();
        insertData.insertMuscle();
        insertData.insertExercise();
    }
}
