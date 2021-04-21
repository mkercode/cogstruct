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
import com.loopbreakr.cogstruct.howdigethere.models.HIGHViewModel;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import java.util.ArrayList;
import java.util.List;


public class HIGHLogEditThreeFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private HIGHViewModel highViewModel;
    private FloatingActionButton addRelief, addConsequence;
    private RecyclerView reliefRecyclerView,consequenceRecyclerView;
    private List<String> reliefsList, consequencesList;



    public HIGHLogEditThreeFragment() {
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
        return inflater.inflate(R.layout.logs_fragment_high_edit_three, container, false);
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
        Toolbar toolbar = view.findViewById(R.id.edit_high_logs_toolbar_three);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_high) {
                updateFirestoreDocument();
                controller.popBackStack(R.id.allLogsFragment, true);
                controller.navigate(R.id.allLogsFragment);
                return true;
            }
            else if(item.getItemId() == R.id.action_next_edit_high){
                controller.navigate(R.id.action_HIGHLogEditThreeFragment_to_highLogEditFourFragment);
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void findViews(View view) {
        addRelief = view.findViewById(R.id.high_log_add_relief);
        addConsequence= view.findViewById(R.id.high_log_add_consequence);
        reliefRecyclerView = view.findViewById(R.id.high_log_edit_reliefs);
        consequenceRecyclerView = view.findViewById(R.id.high_log_edit_consequences);
    }

    private void setRecyclerView() {

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
                .addOnFailureListener(e -> Log.e("UPDATING...", "FAILED. ALL FIELDS OF " + logSnapshot.getData() , e)).addOnSuccessListener(aVoid -> Log.d("UPDATE HOWD I GET HERE", "SUCCESS. ALL FIELDS OF " + logSnapshot.getData()));
    }
}