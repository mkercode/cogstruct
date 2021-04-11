package com.loopbreakr.cogstruct.badbehaviors;

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
import com.loopbreakr.cogstruct.howdigethere.HIGHViewModel;

import java.util.ArrayList;
import java.util.List;


public class BBPageTwo extends Fragment {
    private BBViewModel bbViewModel;
    private Button backButton, nextButton;
    private FloatingActionButton addEnvironmental;
    private RecyclerView environmentalsRecyclerView;
    private List<String> environmentalsList;

    public BBPageTwo() {
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
        return inflater.inflate(R.layout.bb_fragment_page_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setRecyclerView();
        setButtons();
    }

    private void findViews(View view) {
        addEnvironmental = view.findViewById(R.id.bb_add_environmental);
        environmentalsRecyclerView = view.findViewById(R.id.bb_environmentals_recyclerview);
        backButton = view.findViewById(R.id.bb_page_two_back);
        nextButton = view.findViewById(R.id.bb_page_two_next);
    }

    private void setRecyclerView() {
        environmentalsList = new ArrayList<>();
        environmentalsList.addAll(bbViewModel.getBbEnvironmentals());

        SimpleRecyclerAdapter environmentalsRecyclerAdapter = new SimpleRecyclerAdapter(environmentalsList);
        environmentalsRecyclerView.setAdapter(environmentalsRecyclerAdapter);

        environmentalsRecyclerAdapter.setOnItemClickListener(position -> {
            environmentalsList.remove(position);
            environmentalsRecyclerAdapter.notifyDataSetChanged();
            bbViewModel.setBbEnvironmentals(environmentalsList);
        });
    }

    private void setButtons() {
        addEnvironmental.setOnClickListener(v ->{
            addToList(environmentalsList);
            bbViewModel.setBbEnvironmentals(environmentalsList);
        });

        NavController controller = Navigation.findNavController(requireView());

        backButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.BBPageOne, true);
            controller.navigate(R.id.BBPageOne);
        });
        nextButton.setOnClickListener(v -> controller.navigate(R.id.action_BBPageTwo_to_BBPageThree));
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(getActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }
}