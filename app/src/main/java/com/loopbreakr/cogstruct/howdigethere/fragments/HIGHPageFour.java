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
import com.loopbreakr.cogstruct.SimpleRecyclerAdapter;
import com.loopbreakr.cogstruct.howdigethere.models.HIGHViewModel;

import java.util.ArrayList;
import java.util.List;


public class HIGHPageFour extends Fragment {
    private HIGHViewModel highViewModel;
    private Button backButton, nextButton;
    private FloatingActionButton addVulnerability;
    private RecyclerView vulnerabilitiesRecyclerView;
    private List<String> vulnerabilitiesList;

    public HIGHPageFour() {
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
        return inflater.inflate(R.layout.high_fragment_page_four, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setRecyclerView();
        setButtons();
    }

    private void findViews(View view) {
        addVulnerability = view.findViewById(R.id.high_add_vulnerability);
        vulnerabilitiesRecyclerView = view.findViewById(R.id.high_vulnerability_recyclerview);
        backButton = view.findViewById(R.id.high_page_four_back);
        nextButton = view.findViewById(R.id.high_page_four_next);
    }

    private void setRecyclerView() {
        vulnerabilitiesList = new ArrayList<>();
        vulnerabilitiesList.addAll(highViewModel.getHighVulnerabilities());

        SimpleRecyclerAdapter vulnerabilitiesRecyclerAdapter = new SimpleRecyclerAdapter(vulnerabilitiesList);
        vulnerabilitiesRecyclerView.setAdapter(vulnerabilitiesRecyclerAdapter);

        vulnerabilitiesRecyclerAdapter.setOnItemClickListener(position -> {
            vulnerabilitiesList.remove(position);
            vulnerabilitiesRecyclerAdapter.notifyDataSetChanged();
            highViewModel.setHighVulnerabilities(vulnerabilitiesList);
        });
    }

    private void setButtons() {
        addVulnerability.setOnClickListener(v ->{
            addToList(vulnerabilitiesList);
            highViewModel.setHighVulnerabilities(vulnerabilitiesList);
        });

        NavController controller = Navigation.findNavController(requireView());

        backButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.HIGHPageThree, true);
            controller.navigate(R.id.HIGHPageThree);
        });
        nextButton.setOnClickListener(v -> controller.navigate(R.id.action_HIGHPageFour_to_HIGHPageFive));
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(getActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }
}