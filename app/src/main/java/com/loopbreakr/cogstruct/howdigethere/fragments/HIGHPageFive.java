package com.loopbreakr.cogstruct.howdigethere.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.adapters.SimpleRecyclerAdapter;
import com.loopbreakr.cogstruct.howdigethere.models.HIGHViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HIGHPageFive extends Fragment {
    private HIGHViewModel highViewModel;
    private Button backButton, nextButton;
    private FloatingActionButton addRelief, addConsequence;
    private RecyclerView reliefRecyclerView, consequenceRecyclerView;
    private List<String> reliefsList, consequencesList;

    public HIGHPageFive() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        highViewModel = new ViewModelProvider(requireActivity()).get(HIGHViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.high_fragment_page_five, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setRecyclerViews();
        setButtons();
    }

    private void findViews(View view) {
        addRelief= view.findViewById(R.id.high_add_relief);
        addConsequence= view.findViewById(R.id.high_add_consequence);
        reliefRecyclerView = view.findViewById(R.id.high_relief_recyclerview);
        consequenceRecyclerView = view.findViewById(R.id.high_consequences_recyclerview);
        backButton = view.findViewById(R.id.high_page_five_back);
        nextButton = view.findViewById(R.id.high_page_five_next);
    }

    private void setRecyclerViews() {
        reliefsList = new ArrayList<>();
        reliefsList.addAll(highViewModel.getHighReliefs());

        SimpleRecyclerAdapter reliefRecyclerAdapter = new SimpleRecyclerAdapter(reliefsList);
        reliefRecyclerView.setAdapter(reliefRecyclerAdapter);

        reliefRecyclerAdapter.setOnItemClickListener(position -> {
            reliefsList.remove(position);
            reliefRecyclerAdapter.notifyDataSetChanged();
        });

        consequencesList = new ArrayList<>();
        consequencesList.addAll(highViewModel.getHighConsequences());

        SimpleRecyclerAdapter consequenceRecyclerAdapter = new SimpleRecyclerAdapter(consequencesList);
        consequenceRecyclerView.setAdapter(consequenceRecyclerAdapter);

        consequenceRecyclerAdapter.setOnItemClickListener(position -> {
            consequencesList.remove(position);
            consequenceRecyclerAdapter.notifyDataSetChanged();
        });
    }

    private void setButtons() {
        addRelief.setOnClickListener(v ->{
            addToList(reliefsList);
            highViewModel.setHighReliefs(reliefsList);
        });
        addConsequence.setOnClickListener(v ->{
            addToList(consequencesList);
            highViewModel.setHighConsequences(consequencesList);
        });

        NavController controller = Navigation.findNavController(requireView());

        backButton.setOnClickListener(v -> controller.navigateUp());
        nextButton.setOnClickListener(v -> controller.navigate(R.id.action_HIGHPageFive_to_HIGHPageSix));
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(requireActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }
}