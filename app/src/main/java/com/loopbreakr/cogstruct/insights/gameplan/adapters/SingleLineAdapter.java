package com.loopbreakr.cogstruct.insights.gameplan.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.loopbreakr.cogstruct.R;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class SingleLineAdapter extends RecyclerView.Adapter<SingleLineAdapter.ViewHolder>{
    final List<String> changeFactorList;

    public SingleLineAdapter(List<String> changeFactorList) {
        this.changeFactorList = changeFactorList;
    }

    @NotNull
    @Override
    public SingleLineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_single_line, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleLineAdapter.ViewHolder holder, int position) {
        holder.textView.setText(changeFactorList.get(position));
    }

    @Override
    public int getItemCount() {
        return changeFactorList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.single_textview);
        }
    }

}
