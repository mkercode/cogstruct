package com.loopbreakr.cogstruct.logs.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.LogsFragmentHighBinding;
import com.loopbreakr.cogstruct.howdigethere.objects.HIGHObject;
import com.loopbreakr.cogstruct.howdigethere.models.HIGHViewModel;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import org.jetbrains.annotations.NotNull;


public class HIGHLogsFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private HIGHViewModel highViewModel;

    public HIGHLogsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        highViewModel = new ViewModelProvider(requireActivity()).get(HIGHViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.LogsFragmentHighBinding binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_high, container, false);
        binding.setViewModel(highViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        setViewModelData();
    }

    private void setToolbar(View view) {
        ((LogsActivity)requireActivity()).setViewToolbar(view.findViewById(R.id.logsToolbar), R.id.action_HIGHLogFragment_to_HIGHLogEditOneFragment, logsViewModel.getSnapshot());
    }

    private void setViewModelData() {
        HIGHObject highObject = logsViewModel.getSnapshot().toObject(HIGHObject.class);
        highViewModel.setHIGHLog(highObject);
    }
}