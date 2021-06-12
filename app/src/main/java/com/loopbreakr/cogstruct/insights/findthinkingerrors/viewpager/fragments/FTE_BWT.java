package com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.models.FTEVPViewModel;

import org.jetbrains.annotations.NotNull;


public class FTE_BWT extends Fragment {
    private FTEVPViewModel ftevpViewModel;

    public FTE_BWT() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ftevpViewModel = new ViewModelProvider(requireActivity()).get(FTEVPViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.FteViewpagerBwtBinding binding = DataBindingUtil.inflate(inflater, R.layout.fte_viewpager_bwt, container, false);
        binding.setViewModel(ftevpViewModel);
        return binding.getRoot();
    }
}