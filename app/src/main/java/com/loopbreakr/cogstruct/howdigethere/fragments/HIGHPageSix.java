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


public class HIGHPageSix extends Fragment {
    private HIGHViewModel highViewModel;
    private Button backButton, reviewButton;
    private FloatingActionButton addSolution, addRepair;
    private RecyclerView solutionRecyclerView, repairRecyclerView;
    private List<String> soluitionsList, repairsList;


    public HIGHPageSix() {
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
        return inflater.inflate(R.layout.high_fragment_page_six, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setRecyclerViews();
        setButtons();
    }

    private void findViews(View view) {
        addSolution= view.findViewById(R.id.high_add_solution);
        addRepair= view.findViewById(R.id.high_add_repair);
        solutionRecyclerView = view.findViewById(R.id.high_solutions_recyclerview);
        repairRecyclerView = view.findViewById(R.id.high_repair_recyclerview);
        backButton = view.findViewById(R.id.high_page_six_back);
        reviewButton = view.findViewById(R.id.high_review);
    }

    private void setRecyclerViews() {
        soluitionsList = new ArrayList<>();
        soluitionsList.addAll(highViewModel.getHighSolutions());

        SimpleRecyclerAdapter solutionRecyclerAdapter = new SimpleRecyclerAdapter(soluitionsList);
        solutionRecyclerView.setAdapter(solutionRecyclerAdapter);

        solutionRecyclerAdapter.setOnItemClickListener(position -> {
            soluitionsList.remove(position);
            solutionRecyclerAdapter.notifyDataSetChanged();
        });

        repairsList = new ArrayList<>();
        repairsList.addAll(highViewModel.getHighRepairs());

        SimpleRecyclerAdapter repairRecyclerAdapter = new SimpleRecyclerAdapter(repairsList);
        repairRecyclerView.setAdapter(repairRecyclerAdapter);

        repairRecyclerAdapter.setOnItemClickListener(position -> {
            repairsList.remove(position);
            repairRecyclerAdapter.notifyDataSetChanged();
        });
    }

    private void setButtons() {
        addSolution.setOnClickListener(v ->{
            addToList(soluitionsList);
            highViewModel.setHighSolutions(soluitionsList);
        });
        addRepair.setOnClickListener(v ->{
            addToList(repairsList);
            highViewModel.setHighRepairs(repairsList);
        });

        NavController controller = Navigation.findNavController(requireView());

        backButton.setOnClickListener(v -> controller.navigateUp());
        reviewButton.setOnClickListener(v -> controller.navigate(R.id.action_HIGHPageSix_to_HIGHReview));
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(requireActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }
}