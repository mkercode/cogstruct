package com.loopbreakr.cogstruct.proscons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.loopbreakr.cogstruct.R;

import java.util.List;

public class PCRecyclerAdapter extends RecyclerView.Adapter<PCRecyclerAdapter.ViewHolder> {

    List<String> prosConsList;

    public PCRecyclerAdapter(List<String> prosConsList) {
        this.prosConsList = prosConsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pc_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pcTextView.setText(prosConsList.get(position));

    }

    @Override
    public int getItemCount() {
        return prosConsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pcTextView;
        Button deleteRowButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pcTextView = itemView.findViewById(R.id.pro_con_textview);
            deleteRowButton = itemView.findViewById(R.id.delete_pc_button);

            itemView.setOnClickListener(this);
            deleteRowButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
