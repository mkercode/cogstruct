package com.loopbreakr.cogstruct.howdigethere;

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

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.HighFragmentReviewBinding;
import com.loopbreakr.cogstruct.thoughtjournal.activities.TJActivity;

import org.jetbrains.annotations.NotNull;


public class HIGHReview extends Fragment {
    private HIGHViewModel highViewModel;
    private Button editButton, submitButton;
    private HighFragmentReviewBinding binding;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.high_fragment_review, container, false);
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
        editButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.HIGHPageSix, true);
            controller.navigate(R.id.HIGHPageSix);
        });
        submitButton.setOnClickListener(v -> submitData());
    }

    private void submitData() {
        ((HIGHActivity)requireActivity()).sendToFirestore(highViewModel.getHighBehavior(),highViewModel.getHighTriggerEvent(), highViewModel.getHighEmotion(),highViewModel.getHighEmotionIntensity(), highViewModel.getHighThoughtsString(), highViewModel.getHighVulnerabilitiesString(), highViewModel.getHighReliefsString(), highViewModel.getHighConsequencesString(), highViewModel.getHighSolutionsString(), highViewModel.getHighRepairsString());
    }
}