package com.loopbreakr.cogstruct.logs.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.logs.objects.LogsPreview;

public class LogsRecyclerAdapter extends FirestoreRecyclerAdapter<LogsPreview, LogsRecyclerAdapter.LogsViewHolder> {

    LogsListener logsListener;

    public LogsRecyclerAdapter(@NonNull FirestoreRecyclerOptions<LogsPreview> options, LogsListener logsListener) {
        super(options);
        this.logsListener = logsListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull LogsViewHolder holder, int position, @NonNull LogsPreview logsModel) {
        String date = String.valueOf(logsModel.getDateCreated());
        holder.formNameText.setText(logsModel.getFormName());
        holder.dateText.setText(date);
    }

    @NonNull
    @Override
    public LogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.logs_preview_item, parent, false);
        return new LogsViewHolder(view);
    }

    class LogsViewHolder extends RecyclerView.ViewHolder{
        TextView formNameText, dateText;
        public LogsViewHolder(@NonNull View itemView) {
            super(itemView);
            formNameText = itemView.findViewById(R.id.formNameTextView);
            dateText = itemView.findViewById(R.id.dateTextView);


            itemView.setOnClickListener(v -> {
                DocumentSnapshot snapshot = getSnapshots().getSnapshot(getAdapterPosition());
                logsListener.clickLog(snapshot);
            });

        }
    }
    public interface LogsListener {
        void clickLog(DocumentSnapshot snapshot);
    }
}