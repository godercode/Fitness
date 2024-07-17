package com.example.template.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.Interface.IClickMuscleItemListener;
import com.example.template.R;
import com.example.template.object.Muscle;

import java.util.List;

public class MuscleAdapter extends RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder> {
    private List<Muscle> mListMuscle;
    private final IClickMuscleItemListener iClickMuscleItemListener;

    public MuscleAdapter(IClickMuscleItemListener iClickMuscleItemListener) {
        this.iClickMuscleItemListener = iClickMuscleItemListener;
    }
    public void setData(List<Muscle> list){
        this.mListMuscle = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MuscleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_muscle, parent, false);
        return new MuscleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MuscleViewHolder holder, int position) {
        Muscle muscle = mListMuscle.get(position);
        if(muscle == null){
            return;
        }
        holder.tvMuscle.setText(muscle.getMuscleName());
        holder.imgMuscle.setImageResource(muscle.getMuscleImage());
        holder.imgMuscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickMuscleItemListener.onClickMuscleItem(muscle);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListMuscle!=null){
            return mListMuscle.size();
        }
        return 0;
    }

    public static class MuscleViewHolder extends RecyclerView.ViewHolder{
        TextView tvMuscle;
        ImageView imgMuscle;
        CardView muscleItem;
        public MuscleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMuscle = itemView.findViewById(R.id.tv_muscle);
            imgMuscle = itemView.findViewById(R.id.img_muscle);
            muscleItem = itemView.findViewById(R.id.muscle_item);
        }
    }
}
