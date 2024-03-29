package com.loopbreakr.cogstruct.howdigethere.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.howdigethere.activities.HIGHActivity;
import com.loopbreakr.cogstruct.howdigethere.models.HIGHViewModel;

import org.jetbrains.annotations.NotNull;


public class HIGHReview extends Fragment {
    private HIGHViewModel highViewModel;
    private Button editButton, submitButton;

    public HIGHReview() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        highViewModel = new ViewModelProvider(requireActivity()).get(HIGHViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.HighFragmentReviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.high_fragment_review, container, false);
        binding.setViewModel(highViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        editButton = view.findViewById(R.id.high_edit);
        submitButton = view.findViewById(R.id.high_submit);
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        editButton.setOnClickListener(v -> controller.navigateUp());
        submitButton.setOnClickListener(v -> {
            if(highViewModel.getHighBehavior() == null || highViewModel.getHighBehavior().equals("")){
                Toast.makeText(this.requireActivity().getApplicationContext(), "Please enter the behavior!", Toast.LENGTH_SHORT).show();
            }
            else{
                submitData();
            }
            });
    }

    private void submitData() {
        ((HIGHActivity)requireActivity()).sendToFirestore(highViewModel.getHighBehavior(),highViewModel.getHighTriggerEvent(), highViewModel.getHighEmotion(),highViewModel.getHighEmotionIntensity(), highViewModel.getHighThoughtsString(), highViewModel.getHighVulnerabilitiesString(), highViewModel.getHighReliefsString(), highViewModel.getHighConsequencesString(), highViewModel.getHighSolutionsString(), highViewModel.getHighRepairsString());
    }
}