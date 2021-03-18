package com.loopbreakr.cogstruct.logs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.LogsFragmentTjEditBinding;
import com.loopbreakr.cogstruct.logs.models.TJLogViewModel;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TJLogsEditFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private TJLogViewModel tjViewModel;
    private EditText thoughtsEditLog;
    private ChipGroup thoughtChipGroup;
    private Button addThoughtButton;
    private List<String> thoughtList;
    private LogsFragmentTjEditBinding binding;

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
    }

    private void setToolbar(View view) {
        NavController controller = Navigation.findNavController(requireView());
        Toolbar toolbar = view.findViewById(R.id.edit_logs_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_log) {
                setViewModelText();
                updateFirestoreDocument();
                controller.popBackStack(R.id.tjLogFragment, true);
                controller.navigate(R.id.tjLogFragment);
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void findViews(View view) {

        thoughtsEditLog = view.findViewById(R.id.thought_log_input);
        thoughtChipGroup = view.findViewById(R.id.tj_thoughts_log_chipgroup);
        addThoughtButton = view.findViewById(R.id.add_thought_log_button);
    }

    private void getViewModelData() {
        thoughtList = new ArrayList<>();
    }

    private void setViewModelText() {

    }

    private void setThoughtChips() {
        for(String thought: thoughtList){
            createThoughtChip(thought);
        }
    }





    private void setButtons() {
        addThoughtButton.setOnClickListener(v ->{
            String thought = thoughtsEditLog.getText().toString().trim();
            if (!thought.equals("") && !thought.isEmpty()){
                createThoughtChip(thought);
//                thoughtJournalData.addToThoughtLogs(thought);
                thoughtsEditLog.setText("");
            }
        });
    }

    private void createThoughtChip(String thought) {
        Chip chip = new Chip(requireActivity());
        chip.setText(thought);
        chip.setChipBackgroundColorResource(R.color.colorPrimary);
        chip.setTextColor(getResources().getColor(R.color.colorWhite));
        chip.setCloseIconVisible(true);
        chip.setCloseIconTintResource(R.color.colorWhite);
        thoughtChipGroup.addView(chip);
        chip.setOnCloseIconClickListener(view -> {
            thoughtChipGroup.removeView(chip);
//            thoughtJournalData.removeFromThoughtLogs(thought);
        });
    }

    private void updateFirestoreDocument() {
//        thoughtJournalData.updateThoughtString();
//
//        DocumentSnapshot logSnapshot = logsViewModel.getSnapshot();
//        logSnapshot.getReference().update(
//                "time", thoughtJournalData.getTime(),
//                "location", thoughtJournalData.getLocation(),
//                "people", thoughtJournalData.getPeople(),
//                "situation", thoughtJournalData.getSituation(),
//                "behavior", thoughtJournalData.getBehavior(),
//                "emotion", thoughtJournalData.getEmotion(),
//                "emotionRating", thoughtJournalData.getEmotionRating(),
//                "emotionDetail", thoughtJournalData.getEmotionDetail(),
//                "thoughts", thoughtJournalData.getThoughtStringList()).addOnFailureListener(e -> Log.e("UPDATING THOUGHTJOURNAL", "FAILED. ALL FIELDS OF " + logSnapshot.getData() , e)).addOnSuccessListener(aVoid -> Log.d("UPDATING THOUGHTJOURNAL", "SUCCESS. ALL FIELDS OF " + logSnapshot.getData()));
//    }
    }}