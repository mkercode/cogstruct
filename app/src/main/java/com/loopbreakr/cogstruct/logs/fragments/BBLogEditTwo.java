package com.loopbreakr.cogstruct.logs.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.adapters.SimpleRecyclerAdapter;
import com.loopbreakr.cogstruct.badbehaviors.models.BBViewModel;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import java.util.ArrayList;
import java.util.List;


public class BBLogEditTwo extends Fragment {
    private LogsViewModel logsViewModel;
    private BBViewModel bbViewModel;
    private FloatingActionButton addDistraction, addSolution;
    private RecyclerView distractionRecyclerView, solutionRecyclerView;
    private List<String> distractionsList, soluitionsList;


    public BBLogEditTwo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        bbViewModel = new ViewModelProvider(requireActivity()).get(BBViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.logs_fragment_bb_edit_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        findViews(view);
        setRecyclerView();
        setButtons();
    }

    private void setToolbar(View view) {
        NavController controller = Navigation.findNavController(requireView());
        Toolbar toolbar = view.findViewById(R.id.edit_bb_logs_toolbar_two);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_log) {
                updateFirestoreDocument();
                controller.navigateUp();
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void findViews(View view) {
        addDistraction = view.findViewById(R.id.bb_log_add_distraction);
        addSolution= view.findViewById(R.id.bb_log_add_solution);
        distractionRecyclerView = view.findViewById(R.id.bb_log_distractions_recyclerview);
        solutionRecyclerView = view.findViewById(R.id.bb_log_solutions_recyclerview);
    }

    private void setRecyclerView() {

        distractionsList = new ArrayList<>();
        distractionsList.addAll(bbViewModel.getBbDistractions());

        SimpleRecyclerAdapter distractionRecyclerAdapter = new SimpleRecyclerAdapter(distractionsList);
        distractionRecyclerView.setAdapter(distractionRecyclerAdapter);

        distractionRecyclerAdapter.setOnItemClickListener(position -> {
            distractionsList.remove(position);
            distractionRecyclerAdapter.notifyDataSetChanged();
        });

        soluitionsList = new ArrayList<>();
        soluitionsList.addAll(bbViewModel.getBbSolutions());

        SimpleRecyclerAdapter solutionRecyclerAdapter = new SimpleRecyclerAdapter(soluitionsList);
        solutionRecyclerView.setAdapter(solutionRecyclerAdapter);

        solutionRecyclerAdapter.setOnItemClickListener(position -> {
            soluitionsList.remove(position);
            solutionRecyclerAdapter.notifyDataSetChanged();
        });
    }

    private void setButtons() {
        addDistraction.setOnClickListener(v ->{
            addToList(distractionsList);
            bbViewModel.setBbDistractions(distractionsList);
        });

        addSolution.setOnClickListener(v ->{
            addToList(soluitionsList);
            bbViewModel.setBbSolutions(soluitionsList);
        });
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(getActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }

    private void updateFirestoreDocument() {
        DocumentSnapshot logSnapshot = logsViewModel.getSnapshot();
        logSnapshot.getReference().update(
                "behavior", bbViewModel.getBbBehavior(),
                "environmentals", bbViewModel.getBbEnvironmentalsString(),
                "distractions", bbViewModel.getBbDistractionsString(),
                "solutions", bbViewModel.getBbSolutionsString())
                .addOnFailureListener(e -> ((LogsActivity)requireActivity()).handleFailure(e, "EDIT"));

    }
}