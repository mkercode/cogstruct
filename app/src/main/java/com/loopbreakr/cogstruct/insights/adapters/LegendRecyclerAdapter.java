package com.loopbreakr.cogstruct.insights.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.behavioralinspection.objects.Factor;

import java.util.ArrayList;

public class LegendRecyclerAdapter extends RecyclerView.Adapter<LegendRecyclerAdapter.LegendViewHolder>{
    private ArrayList<Factor> factorList;

    public LegendRecyclerAdapter(ArrayList<Factor> factorList){
        this.factorList = factorList;
    }


    @NonNull
    @Override
    public LegendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chart_legend, parent, false);
        return new LegendViewHolder(v);
    }

    public static class LegendViewHolder extends RecyclerView.ViewHolder {

        public View legendColor;
        public TextView legendFactor;
        public TextView legendNumTimes;
        public TextView legendPercentage;

        public LegendViewHolder(@NonNull View itemView) {
            super(itemView);
            //find views in row XML
            legendColor = itemView.findViewById(R.id.legend_color);
            legendFactor = itemView.findViewById(R.id.factor_textview);
            legendNumTimes = itemView.findViewById(R.id.legend_count);
            legendPercentage = itemView.findViewById(R.id.legend_percent);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull LegendRecyclerAdapter.LegendViewHolder holder, int position) {
        String currentItem = factorList.get(position).getFactor();
        int currentColor = factorList.get(position).getFactorColor();
        String curerentPecentage = factorList.get(position).getPercentage();
        String currentNum = String.valueOf(factorList.get(position).getNumTimes());


        holder.legendFactor.setText(currentItem);
        holder.legendColor.setBackgroundColor(currentColor);
        holder.legendNumTimes.setText(currentNum);
        holder.legendPercentage.setText(curerentPecentage);
    }

    @Override
    public int getItemCount() {
        return factorList.size();
    }
}
