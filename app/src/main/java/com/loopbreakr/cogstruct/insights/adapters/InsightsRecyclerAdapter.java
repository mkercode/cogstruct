package com.loopbreakr.cogstruct.insights.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.loopbreakr.cogstruct.R;

import java.util.List;

public class InsightsRecyclerAdapter extends RecyclerView.Adapter<InsightsRecyclerAdapter.ViewHolder> {

    final List<String> insightsList;
    InsightsRecyclerAdapter.OnItemClickListener rowListener;

    public InsightsRecyclerAdapter(List<String> insightsList) {
        this.insightsList = insightsList;
    }

    public void setOnItemClickListener(InsightsRecyclerAdapter.OnItemClickListener listener) {
        rowListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.insights_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InsightsRecyclerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(insightsList.get(position));
    }

    @Override
    public int getItemCount() {
        return insightsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.insights_textview);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    rowListener.clickLog(position);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void clickLog(int position);
    }
}

