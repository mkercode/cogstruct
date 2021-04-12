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
import com.loopbreakr.cogstruct.SimpleRecyclerAdapter;
import com.loopbreakr.cogstruct.howdigethere.models.HIGHViewModel;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import java.util.ArrayList;
import java.util.List;


public class HighLogEditFourFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private HIGHViewModel highViewModel;
    private FloatingActionButton addSolution, addRepair;
    private RecyclerView solutionRecyclerView, repairRecyclerView;
    private List<String> soluitionsList, repairsList;

    public HighLogEditFourFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        highViewModel = new ViewModelProvider(requireActivity()).get(HIGHViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.logs_fragment_high_edit_four, container, false);
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
        Toolbar toolbar = view.findViewById(R.id.edit_high_logs_toolbar_four);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_log) {
                updateFirestoreDocument();
                controller.popBackStack(R.id.allLogsFragment, true);
                controller.navigate(R.id.allLogsFragment);
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void findViews(View view) {
        addRepair = view.findViewById(R.id.high_log_add_repair);
        addSolution= view.findViewById(R.id.high_log_add_solution);
        repairRecyclerView = view.findViewById(R.id.high_log_edit_repairs);
        solutionRecyclerView = view.findViewById(R.id.high_log_edit_solutions);
    }

    private void setRecyclerView() {

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
                "behavior", highViewModel.getHighBehavior(),
                "triggerEvent", highViewModel.getHighTriggerEvent(),
                "emotion", highViewModel.getHighEmotion(),
                "emotionRating", highViewModel.getHighEmotionIntensity(),
                "thoughts", highViewModel.getHighThoughtsString(),
                "vulnerabilities", highViewModel.getHighVulnerabilitiesString(),
                "reliefs", highViewModel.getHighReliefsString(),
                "consequences", highViewModel.getHighConsequencesString(),
                "solutions", highViewModel.getHighConsequencesString(),
                "repairs", highViewModel.getHighRepairsString())
                .addOnFailureListener(e -> Log.e("UPDATING PROS AND CONS", "FAILED. ALL FIELDS OF " + logSnapshot.getData() , e)).addOnSuccessListener(aVoid -> Log.d("UPDATE HOWD I GET HERE", "SUCCESS. ALL FIELDS OF " + logSnapshot.getData()));
    }
}