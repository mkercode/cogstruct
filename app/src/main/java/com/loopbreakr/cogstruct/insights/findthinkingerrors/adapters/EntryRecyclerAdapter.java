package com.loopbreakr.cogstruct.insights.findthinkingerrors.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.objects.FTEDisplayObject;

import java.util.Arrays;
import java.util.List;

public class EntryRecyclerAdapter extends RecyclerView.Adapter<EntryRecyclerAdapter.ViewHolder>{
    EntryListener entryListener;
    final List<FTEDisplayObject> entriesList;

    public EntryRecyclerAdapter(List<FTEDisplayObject> entriesList) {
        this.entriesList = entriesList;
    }
    public void setOnItemClickListener(EntryRecyclerAdapter.EntryListener listener) {
        entryListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_fte_entry, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView dateTextView;
        final TextView errorsTextView;
        final ImageView popupButton;

        @SuppressLint("NonConstantResourceId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_created_textview);
            errorsTextView = itemView.findViewById(R.id.errors_textview);
            popupButton = itemView.findViewById(R.id.ftePopup);


            popupButton.setOnClickListener(v -> {
                PopupMenu popupMenu = new PopupMenu(itemView.getContext(), popupButton);
                // Inflating popup menu from xml file
                popupMenu.getMenuInflater().inflate(R.menu.edit_popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    int position = getAdapterPosition();
                    switch (menuItem.getItemId()){
                        case R.id.action_deletePopup:
                            if (position != RecyclerView.NO_POSITION) {
                                entryListener.clickEntry(position, "Delete");
                          }
                            return true;
                        case R.id.action_editPopup:
                            if (position != RecyclerView.NO_POSITION) {
                                entryListener.clickEntry(position, "Edit");
                            }
                            return true;
                        default:
                            return false;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dateTextView.setText(String.format("Date created: %s", entriesList.get(position).getDateCreated()));
        StringBuilder currErrorString = new StringBuilder();
        List<String> errors = Arrays.asList(entriesList.get(position).getThinkingErrors().split("\\s*,\\s*"));
        for(String error: errors){
            if(error.equals(errors.get(errors.size()-1))){
                currErrorString.append(error);
            }
            else{
                currErrorString.append(error).append("\n").append("\n");
            }
        }
        holder.errorsTextView.setText(currErrorString);
    }

    @Override
    public int getItemCount() {
        return entriesList.size();
    }


    public interface EntryListener {
        void clickEntry(int position, String action);
    }
}
