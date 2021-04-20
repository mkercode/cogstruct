package com.loopbreakr.cogstruct.insights.gameplan.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.gameplan.models.GPViewModel;


public class GPSimpleList extends Fragment {
    private GPViewModel gpViewModel;

    public GPSimpleList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gpViewModel = new ViewModelProvider(requireActivity()).get(GPViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.gp_fragment_simple_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("CHANGE ", "onViewCreated: " + gpViewModel.getGpChange());
    }
}