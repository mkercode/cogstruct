package com.loopbreakr.cogstruct.howdigethere;

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

import java.util.ArrayList;
import java.util.List;


public class HIGHPageThree extends Fragment {
    private HIGHViewModel highViewModel;
    private Button backButton, nextButton;
    private FloatingActionButton addThought;
    private RecyclerView thoughtsRecyclerView;
    private List<String> thoughtsList;


    public HIGHPageThree() {
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
        return inflater.inflate(R.layout.high_fragment_page_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setRecyclerView();
        setButtons();
    }

    private void findViews(View view) {
        addThought = view.findViewById(R.id.high_add_thought);
        thoughtsRecyclerView = view.findViewById(R.id.high_thoughts_recyclerview);
        backButton = view.findViewById(R.id.high_page_three_back);
        nextButton = view.findViewById(R.id.high_page_three_next);
    }

    private void setRecyclerView() {
        thoughtsList = new ArrayList<>();
        thoughtsList.addAll(highViewModel.getHighThoughts());

        SimpleRecyclerAdapter thoughtsRecyclerAdapter = new SimpleRecyclerAdapter(thoughtsList);
        thoughtsRecyclerView.setAdapter(thoughtsRecyclerAdapter);

        thoughtsRecyclerAdapter.setOnItemClickListener(position -> {
            thoughtsList.remove(position);
            thoughtsRecyclerAdapter.notifyDataSetChanged();
            highViewModel.setHighThoughts(thoughtsList);
        });
    }

    private void setButtons() {
        addThought.setOnClickListener(v ->{
            addToList(thoughtsList);
            highViewModel.setHighThoughts(thoughtsList);
        });

        NavController controller = Navigation.findNavController(requireView());

        backButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.HIGHPageTwo, true);
            controller.navigate(R.id.HIGHPageTwo);
        });
        nextButton.setOnClickListener(v -> controller.navigate(R.id.action_HIGHPageThree_to_HIGHPageFour));
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(getActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }
}