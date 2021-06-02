package com.loopbreakr.cogstruct.badbehaviors.fragments;

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
import com.loopbreakr.cogstruct.badbehaviors.models.BBViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class BBPageFour extends Fragment {
    private BBViewModel bbViewModel;
    private Button backButton, reviewButton;
    private FloatingActionButton addSolution;
    private RecyclerView solutionsRecyclerView;
    private List<String> solutionsList;

    public BBPageFour() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bbViewModel = new ViewModelProvider(requireActivity()).get(BBViewModel.class);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bb_fragment_page_four, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setRecyclerView();
        setButtons();
    }

    private void findViews(View view) {
        addSolution = view.findViewById(R.id.bb_add_solution);
        solutionsRecyclerView = view.findViewById(R.id.bb_solutions_recyclerview);
        backButton = view.findViewById(R.id.bb_page_four_back);
        reviewButton = view.findViewById(R.id.bb_review);
    }

    private void setRecyclerView() {
        solutionsList = new ArrayList<>();
        solutionsList.addAll(bbViewModel.getBbSolutions());

        SimpleRecyclerAdapter solutionsRecyclerAdapter = new SimpleRecyclerAdapter(solutionsList);
        solutionsRecyclerView.setAdapter(solutionsRecyclerAdapter);

        solutionsRecyclerAdapter.setOnItemClickListener(position -> {
            solutionsList.remove(position);
            solutionsRecyclerAdapter.notifyDataSetChanged();
            bbViewModel.setBbSolutions(solutionsList);
        });
    }

    private void setButtons() {
        addSolution.setOnClickListener(v ->{
            addToList(solutionsList);
            bbViewModel.setBbSolutions(solutionsList);
        });

        NavController controller = Navigation.findNavController(requireView());

        backButton.setOnClickListener(v -> controller.navigateUp());
        reviewButton.setOnClickListener(v -> controller.navigate(R.id.action_BBPageFour_to_BBReview));
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(requireActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }
}