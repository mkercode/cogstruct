package com.loopbreakr.cogstruct.badbehaviors;

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
import com.loopbreakr.cogstruct.databinding.BbFragmentReviewBinding;

import org.jetbrains.annotations.NotNull;


public class BBReview extends Fragment {
    private BbFragmentReviewBinding binding;
    private BBViewModel bbViewModel;
    private Button editButton, submitButton;

    public BBReview() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bbViewModel = new ViewModelProvider(requireActivity()).get(BBViewModel.class);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.bb_fragment_review, container, false);
        binding.setViewModel(bbViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        editButton = view.findViewById(R.id.bb_edit);
        submitButton = view.findViewById(R.id.bb_submit);
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        editButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.BBPageFour, true);
            controller.navigate(R.id.BBPageFour);
        });
        submitButton.setOnClickListener(v -> submitData());
    }

    private void submitData() {
        ((BBActivity)requireActivity()).sendToFirestore(bbViewModel.getBbBehavior(), bbViewModel.getBbEnvironmentalsString(), bbViewModel.getBbDistractionsString(), bbViewModel.getBbSolutionsString());
    }
}