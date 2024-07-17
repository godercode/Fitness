package com.example.template.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.Database.FitnessDatabase;
import com.example.template.Interface.IClickExerciseItemListener;
import com.example.template.R;
import com.example.template.object.Exercise;
import com.example.template.object.Plan;
import com.example.template.object.User;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {
    private List<Exercise> mListExercise;
    private final IClickExerciseItemListener iClickExerciseItemListener;
    private boolean isDeleteButtonVisible;
    private boolean isAddButtonVisible;
    private final Context mContext;

    public PlanAdapter(IClickExerciseItemListener iClickExerciseItemListener, Context mContext) {
        this.iClickExerciseItemListener = iClickExerciseItemListener;
        this.mContext = mContext;
    }

    public void setData(List<Exercise> list) {
        this.mListExercise = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, int position) {
        Exercise exercise = mListExercise.get(position);
        if (exercise == null) {
            return;
        }
        Long totalTime = FitnessDatabase.getInstance(mContext).workoutSessionDao().getTotalTimeForExercise(exercise.getExerciseId());
        holder.progressBar.setMax(90);
        if (totalTime == null) {
            holder.progressBar.setProgress(0);
        } else {

            holder.progressBar.setProgress((int) (totalTime / 1000));
        }
        holder.tvExercise.setText(exercise.getExerciseName());
        holder.imgExercise.setImageResource(exercise.getExerciseImage());
        holder.itemExercise.setOnClickListener(v -> iClickExerciseItemListener.onClickExerciseItemListener(exercise));

        setButtonVisibility(holder.btnDelete, isDeleteButtonVisible);
        setButtonVisibility(holder.btnAdd, isAddButtonVisible);

        holder.btnDelete.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                Exercise exerciseToDelete = mListExercise.get(adapterPosition);
                exerciseToDelete.setPlanId(4);
                FitnessDatabase.getInstance(mContext).exerciseDao().updateExercise(exerciseToDelete);
                mListExercise.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                notifyItemRangeChanged(adapterPosition, mListExercise.size());
            }
        });

        holder.btnAdd.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                Exercise exerciseToAdd = mListExercise.get(adapterPosition);
                User user = FitnessDatabase.getInstance(mContext).userDao().getUserById(1);
                Plan plan = FitnessDatabase.getInstance(mContext).planDao().getPlansByName(user.getGoal());

                if (exerciseToAdd.getPlanId() == plan.getPlanId()) {
                    Toast.makeText(mContext, "Exercise already exists in Plan!", Toast.LENGTH_SHORT).show();
                } else {
                    exerciseToAdd.setPlanId(plan.getPlanId());
                    FitnessDatabase.getInstance(mContext).exerciseDao().updateExercise(exerciseToAdd);
                    Toast.makeText(mContext, "Exercise added to Plan successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (mListExercise != null) ? mListExercise.size() : 0;
    }

    public void setDeleteButtonVisible(boolean visible) {
        isDeleteButtonVisible = visible;
        notifyDataSetChanged();
    }

    public void setAddButtonVisible(boolean visible) {
        isAddButtonVisible = visible;
        notifyDataSetChanged();
    }

    private void setButtonVisibility(View button, boolean visible) {
        button.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public static class PlanViewHolder extends RecyclerView.ViewHolder {
        TextView tvExercise;
        ProgressBar progressBar;
        ImageView imgExercise;
        Button btnDelete, btnAdd;
        RelativeLayout itemExercise;
        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
            tvExercise = itemView.findViewById(R.id.tv_exercise);
            imgExercise = itemView.findViewById(R.id.img_exercise);
            itemExercise = itemView.findViewById(R.id.exercise_item);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnAdd = itemView.findViewById(R.id.btn_add_ex);
        }
    }
}
