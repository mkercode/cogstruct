package com.loopbreakr.cogstruct.identifybarriers.fragments;

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
import com.loopbreakr.cogstruct.databinding.IbFragmentPageFourBinding;
import com.loopbreakr.cogstruct.identifybarriers.models.IBViewModel;

import org.jetbrains.annotations.NotNull;


public class IBPageFour extends Fragment {
    private IbFragmentPageFourBinding binding;
    private IBViewModel ibViewModel;
    private Button backButton, reviewButton;

    public IBPageFour() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibViewModel = new ViewModelProvider(requireActivity()).get(IBViewModel.class);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.ib_fragment_page_four, container, false);
        binding.setViewModel(ibViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        backButton = view.findViewById(R.id.ib_page_four_back);
        reviewButton = view.findViewById(R.id.ib_review_button);
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        backButton.setOnClickListener(v -> controller.navigateUp());
        reviewButton.setOnClickListener(v -> controller.navigate(R.id.action_IBPageFour_to_IBProblemSolving));
    }
}