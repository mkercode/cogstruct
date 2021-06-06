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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.adapters.SimpleRecyclerAdapter;
import com.loopbreakr.cogstruct.howdigethere.models.HIGHViewModel;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import java.util.ArrayList;
import java.util.List;


public class HIGHLogEditTwoFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private HIGHViewModel highViewModel;
    private FloatingActionButton addThought, addVulnerabilites;
    private RecyclerView thoughtsRecyclerView, vulnerabilitiesRecyclerView;
    private List<String> thoughtsList, vulnerabilitiesList;

    public HIGHLogEditTwoFragment() {
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
        return inflater.inflate(R.layout.logs_fragment_high_edit_two, container, false);
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
        Toolbar toolbar = view.findViewById(R.id.edit_high_logs_toolbar_two);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> controller.navigateUp());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_high) {
                updateFirestoreDocument();
                controller.popBackStack(R.id.allLogsFragment, true);
                controller.navigate(R.id.allLogsFragment);
                return true;
            }
            else if(item.getItemId() == R.id.action_next_edit_high){
                controller.navigate(R.id.action_HIGHLogEditTwoFragment_to_HIGHLogEditThreeFragment);
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void findViews(View view) {
        addThought = view.findViewById(R.id.high_log_add_thought);
        addVulnerabilites = view.findViewById(R.id.high_log_add_vulnerability);
        thoughtsRecyclerView = view.findViewById(R.id.high_log_edit_thoughts);
        vulnerabilitiesRecyclerView = view.findViewById(R.id.high_log_edit_vulnerabilities);
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
        addThought.setOnClickListener(v ->{
            addToList(thoughtsList);
            highViewModel.setHighThoughts(thoughtsList);
        });

        addVulnerabilites.setOnClickListener(v ->{
            addToList(vulnerabilitiesList);
            highViewModel.setHighVulnerabilities(vulnerabilitiesList);
        });
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(requireActivity()).setTitle("Add Entry")
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
                .addOnFailureListener(e -> ((LogsActivity)requireActivity()).handleFailure(e, "EDIT"));
    }
}
