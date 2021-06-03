package com.loopbreakr.cogstruct.logs.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.LogsFragmentTjEditBinding;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;
import com.loopbreakr.cogstruct.logs.models.TJLogViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TJLogsEditFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private TJLogViewModel tjViewModel;
    private ChipGroup thoughtChipGroup;
    private Button addThoughtButton;
    private LogsFragmentTjEditBinding binding;
    private List<String> thoughtList;

    public TJLogsEditFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        tjViewModel = new ViewModelProvider(requireActivity()).get(TJLogViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_tj_edit, container, false);
        binding.setViewModel(tjViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        findViews(view);
        getViewModelData();
        setButtons();
    }

    private void setToolbar(View view) {
        NavController controller = Navigation.findNavController(requireView());
        Toolbar toolbar = view.findViewById(R.id.edit_logs_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> controller.navigateUp());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_log) {
                updateFirestoreDocument();
                controller.popBackStack();
                controller.navigateUp();
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void findViews(View view) {
        thoughtChipGroup = view.findViewById(R.id.tj_thoughts_log_chipgroup);
        addThoughtButton = view.findViewById(R.id.add_thought_log_button);
    }

    private void getViewModelData() {
        thoughtList = new ArrayList<>();
        if(tjViewModel.getTjLogThoughtList() != null && !tjViewModel.getTjLogThoughtList().isEmpty()){
            thoughtList.addAll(tjViewModel.getTjLogThoughtList());
            //create chips from past input
            for(String thought: thoughtList){
                createThoughtChip(thought);
            }
        }
    }

    private void setButtons() {
        addThoughtButton.setOnClickListener(v -> addToList());
    }

    private void createThoughtChip(String thought) {
        Chip chip = new Chip(requireActivity());
        chip.setText(thought);
        chip.setChipBackgroundColorResource(R.color.colorPrimary);
        chip.setTextColor(getResources().getColor(R.color.colorWhite));
        chip.setCloseIconVisible(true);
        chip.setCloseIconTintResource(R.color.colorWhite);
        thoughtChipGroup.addView(chip);
        chip.setOnClickListener(v -> editInList(thought, chip));
        chip.setOnCloseIconClickListener(view -> {
            thoughtChipGroup.removeView(chip);
            thoughtList.remove(thought);
        });
    }

    private void updateThoughtList(){
        tjViewModel.setTjLogThoughtList(thoughtList);
    }

    private void addToList() {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(requireActivity()).setTitle("Add Thought")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->{
                    thoughtList.add(addEntryText.getText().toString());
                    createThoughtChip(addEntryText.getText().toString());
                }).setNegativeButton("Cancel", null).show();
    }

    private void editInList(String thought, Chip chip) {
        EditText addEntryText = new EditText(getActivity());
        addEntryText.setText(thought);
        new AlertDialog.Builder(requireActivity()).setTitle("Edit Thought")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->{
                    thoughtList.set(thoughtList.indexOf(thought), addEntryText.getText().toString());
                    chip.setText(addEntryText.getText().toString());
                }).setNegativeButton("Cancel", null).show();
    }

    private void updateFirestoreDocument() {
        updateThoughtList();

        DocumentSnapshot logSnapshot = logsViewModel.getSnapshot();
        logSnapshot.getReference().update(
                "time", tjViewModel.getTjLogTime(),
                "location", tjViewModel.getTjLogLocation(),
                "people", tjViewModel.getTjLogPeople(),
                "situation", tjViewModel.getTjLogSituation(),
                "behavior", tjViewModel.getTjLogBehavior(),
                "emotion", tjViewModel.getTjLogEmotion(),
                "emotionRating", tjViewModel.getTjLogEmotionRating(),
                "emotionDetail", tjViewModel.getTjLogEmotionDetail(),
                "thoughts", tjViewModel.getTjLogThoughtText()).addOnFailureListener(e ->
                ((LogsActivity)requireActivity()).handleFailure(e, "EDIT"));
    }
    }