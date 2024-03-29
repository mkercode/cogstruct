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
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;
import com.loopbreakr.cogstruct.logs.models.TJLogViewModel;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

import org.jetbrains.annotations.NotNull;


public class TjLogsFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private TJLogViewModel tjViewModel;


    public TjLogsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        tjViewModel = new ViewModelProvider(requireActivity()).get(TJLogViewModel.class);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.LogsFragmentTjBinding binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_tj, container, false);
        binding.setViewModel(tjViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        setViewModelData();
    }

    private void setViewModelData() {
        ThoughtJournalObject thoughtJournalLog = logsViewModel.getSnapshot().toObject(ThoughtJournalObject.class);
        tjViewModel.setThoughtJournal(thoughtJournalLog);
    }

    private void setToolbar(View view) {
        ((LogsActivity)requireActivity()).setViewToolbar(view.findViewById(R.id.logsToolbar), R.id.action_tjLogFragment_to_TJLogEditFragment, logsViewModel.getSnapshot());
    }

}