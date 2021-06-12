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
import com.loopbreakr.cogstruct.identifybarriers.models.IBViewModel;

import org.jetbrains.annotations.NotNull;


public class IBPageTwo extends Fragment {
    private IBViewModel ibViewModel;
    private Button backButton, nextButton;

    public IBPageTwo() {
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
        com.loopbreakr.cogstruct.databinding.IbFragmentPageTwoBinding binding = DataBindingUtil.inflate(inflater, R.layout.ib_fragment_page_two, container, false);
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
        backButton = view.findViewById(R.id.ib_page_two_back);
        nextButton = view.findViewById(R.id.ib_page_two_next);
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        backButton.setOnClickListener(v -> controller.navigateUp());
        nextButton.setOnClickListener(v ->{
            if(ibViewModel.getIbBarrierType().equals("Willingness")){
                controller.navigate(R.id.action_IBPageTwo_to_IBProblemSolving);
            }
            else{
                controller.navigate(R.id.action_IBPageTwo_to_IBPageThree);
            }
        });
    }
}