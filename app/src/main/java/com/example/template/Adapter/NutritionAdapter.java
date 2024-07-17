package com.example.template.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.template.R;
import com.example.template.object.Nutrition;

import java.util.List;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.NutritionViewHolder>{
    private List<Nutrition> mListNutrition;

    public void setData(List<Nutrition> list){
        this.mListNutrition = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NutritionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nutrition,parent,false);
        return new NutritionViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull NutritionViewHolder holder, int position) {
        Nutrition nutrition = mListNutrition.get(position);
        if(nutrition==null){
            return;
        }
        holder.tvNutrition.setText(nutrition.getNutrition());
        holder.tvNutritionName.setText(nutrition.getNutritionName());
        holder.imgNutrition.setImageResource(nutrition.getNutritionImage());

    }
    @Override
    public int getItemCount() {
        if(mListNutrition!=null){
            return mListNutrition.size();
        }
        return 0;
    }


    public static class NutritionViewHolder extends RecyclerView.ViewHolder {
        TextView tvNutrition,tvNutritionName;
        ImageView imgNutrition;
        CardView nutritionItem;

        public NutritionViewHolder(@NonNull View itemView) {
            super(itemView);
            nutritionItem = itemView.findViewById(R.id.nutrition_item);
            imgNutrition = itemView.findViewById(R.id.img_nutrition);
            tvNutrition = itemView.findViewById(R.id.tv_nutrition);
            tvNutritionName = itemView.findViewById(R.id.tv_nutrition_name);
        }
    }
}
