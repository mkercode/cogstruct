package com.loopbreakr.cogstruct.identifybarriers;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.IbFragmentProblemSolvingBinding;


public class IBProblemSolving extends Fragment {
    private IbFragmentProblemSolvingBinding binding;
    private IBViewModel ibViewModel;

    public IBProblemSolving() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibViewModel = new ViewModelProvider(requireActivity()).get(IBViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.ib_fragment_problem_solving, container, false);
        binding.setViewModel(ibViewModel);
        return binding.getRoot();
    }
}