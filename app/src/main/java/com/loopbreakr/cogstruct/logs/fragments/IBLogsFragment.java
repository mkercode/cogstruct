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
import com.loopbreakr.cogstruct.identifybarriers.objects.IBObject;
import com.loopbreakr.cogstruct.identifybarriers.models.IBViewModel;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import org.jetbrains.annotations.NotNull;


public class IBLogsFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private IBViewModel ibViewModel;

    public IBLogsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        ibViewModel = new ViewModelProvider(requireActivity()).get(IBViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.LogsFragmentIbBinding binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_ib, container, false);
        binding.setViewModel(ibViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        setViewModelData();
    }

    private void setToolbar(View view) {
        ((LogsActivity)requireActivity()).setViewToolbar(view.findViewById(R.id.logsToolbar), R.id.action_IBLogFragment_to_IBLogEditFragment, logsViewModel.getSnapshot());
    }

    private void setViewModelData() {
        IBObject ibLog = logsViewModel.getSnapshot().toObject(IBObject.class);
        ibViewModel.setIBLog(ibLog);
    }
}