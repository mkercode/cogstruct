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

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.HighFragmentPageTwoBinding;
import com.loopbreakr.cogstruct.howdigethere.HIGHViewModel;

import org.jetbrains.annotations.NotNull;


public class HIGHPageTwo extends Fragment {
    private HIGHViewModel highViewModel;
    private Button backButton, nextButton;
    private HighFragmentPageTwoBinding binding;


    public HIGHPageTwo() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.high_fragment_page_two, container, false);
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
        backButton = view.findViewById(R.id.high_page_two_back);
        nextButton = view.findViewById(R.id.high_page_two_next);
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        backButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.HIGHPageOne, true);
            controller.navigate(R.id.HIGHPageOne);
        });
        nextButton.setOnClickListener(v -> controller.navigate(R.id.action_HIGHPageTwo_to_HIGHPageThree));
    }
}