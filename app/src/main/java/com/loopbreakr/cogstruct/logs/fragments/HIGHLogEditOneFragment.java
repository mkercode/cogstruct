package com.loopbreakr.cogstruct.logs.fragments;

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

import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.LogsFragmentHighEditOneBinding;
import com.loopbreakr.cogstruct.howdigethere.models.HIGHViewModel;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import org.jetbrains.annotations.NotNull;


public class HIGHLogEditOneFragment extends Fragment {
    private LogsFragmentHighEditOneBinding binding;
    private LogsViewModel logsViewModel;
    private HIGHViewModel highViewModel;

    public HIGHLogEditOneFragment() {
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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_high_edit_one, container, false);
        binding.setViewModel(highViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
    }

    private void setToolbar(View view) {
        NavController controller = Navigation.findNavController(requireView());
        Toolbar toolbar = view.findViewById(R.id.edit_high_log_toolbar_one);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_high) {
                updateFirestoreDocument();
                controller.navigateUp();
                return true;
            }
            else if(item.getItemId() == R.id.action_next_edit_high){
                controller.navigate(R.id.action_HIGHLogEditOneFragment_to_HIGHLogEditTwoFragment);
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
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