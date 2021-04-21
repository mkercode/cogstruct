package com.loopbreakr.cogstruct.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loopbreakr.cogstruct.R;

import java.util.List;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    List<String> simpleList;
    OnItemClickListener rowListener;

    public SimpleRecyclerAdapter(List<String> simpleList) {
        this.simpleList = simpleList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        rowListener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.simple_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(simpleList.get(position));
    }

    @Override
    public int getItemCount() {
        return simpleList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView rowDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.simple_textview);
            rowDelete = itemView.findViewById(R.id.row_delete);

            rowDelete.setOnClickListener(v ->{
                if (rowListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        rowListener.clickDeleteRow(position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        //handle numTimes increments when clicking +/-
        void clickDeleteRow(int position);
    }
}
