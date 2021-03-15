package com.loopbreakr.cogstruct.proscons;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.loopbreakr.cogstruct.R;

import java.util.List;

public class PCRecyclerAdapter extends RecyclerView.Adapter<PCRecyclerAdapter.ViewHolder> {

    List<String> prosConsList;
    OnItemClickListener pcListener;

    public PCRecyclerAdapter(List<String> prosConsList) {
        this.prosConsList = prosConsList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        pcListener= listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pc_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pcTextView.setText(prosConsList.get(position));
    }

    @Override
    public int getItemCount() {
        return prosConsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView pcTextView;
        ImageView pcDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pcTextView = itemView.findViewById(R.id.pro_con_textview);
            pcDelete= itemView.findViewById(R.id.pc_delete);

            pcDelete.setOnClickListener(v ->{
                if (pcListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        pcListener.clickDeletePC(position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        //handle numTimes increments when clicking +/-
        void clickDeletePC(int position);
    }
}
