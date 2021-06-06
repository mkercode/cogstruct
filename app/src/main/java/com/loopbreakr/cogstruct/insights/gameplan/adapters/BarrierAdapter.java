package com.loopbreakr.cogstruct.insights.gameplan.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.gameplan.objects.Barrier;
import java.util.ArrayList;

public class BarrierAdapter extends RecyclerView.Adapter<BarrierAdapter.BarrierViewHolder>{

    private final ArrayList<Barrier> barrierList;

    public BarrierAdapter(ArrayList<Barrier> barrierList){ this.barrierList = barrierList; }

    @NonNull
    @Override
    public BarrierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_barrier, parent, false);
        return new BarrierViewHolder(v);
    }

    public static class BarrierViewHolder extends RecyclerView.ViewHolder {

        public final TextView barrier;
        public final TextView barrierType;

        public BarrierViewHolder(@NonNull View itemView) {
            super(itemView);
            //find views in row XML
            barrier = itemView.findViewById(R.id.barrier_textview);
            barrierType = itemView.findViewById(R.id.barrier_type_textview);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BarrierAdapter.BarrierViewHolder holder, int position) {
        String currentBarrier = "Barrier: " + barrierList.get(position).getBarrier();
        String currentType = "Type: " + barrierList.get(position).getBarrierType();

        holder.barrier.setText(currentBarrier);
        holder.barrierType.setText(currentType);
    }

    @Override
    public int getItemCount() { return barrierList.size(); }
}
