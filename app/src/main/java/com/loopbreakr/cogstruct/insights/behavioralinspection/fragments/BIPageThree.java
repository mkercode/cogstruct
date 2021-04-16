package com.loopbreakr.cogstruct.insights.behavioralinspection.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.behavioralinspection.models.BIViewModel;

public class BIPageThree extends Fragment {
    private BIViewModel biViewModel;


    public BIPageThree() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biViewModel = new ViewModelProvider(requireActivity()).get(BIViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bi_fragment_page_three, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("STRANG", "onViewCreated: " + biViewModel.getBiFactorList(biViewModel.getBiSnapShotList(), biViewModel.getBiBehavior(), biViewModel.getBiInspection()));
    }
}