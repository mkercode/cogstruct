package com.loopbreakr.cogstruct.logs.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.badbehaviors.models.BBViewModel;
import com.loopbreakr.cogstruct.badbehaviors.objects.BBObject;
import com.loopbreakr.cogstruct.databinding.LogsFragmentBbBinding;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import org.jetbrains.annotations.NotNull;


public class BBLogsFragment extends Fragment {
    private LogsFragmentBbBinding binding;
    private LogsViewModel logsViewModel;
    private BBViewModel bbViewModel;

    public BBLogsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        bbViewModel = new ViewModelProvider(requireActivity()).get(BBViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_bb, container, false);
        binding.setViewModel(bbViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        setViewModelData();
    }

    private void setToolbar(View view) {
        ((LogsActivity)requireActivity()).setViewToolbar(view.findViewById(R.id.logsToolbar), R.id.action_BBLogFragment_to_BBLogEditOne, logsViewModel.getSnapshot());
    }

    private void setViewModelData() {
        BBObject bbObject = logsViewModel.getSnapshot().toObject(BBObject.class);
        bbViewModel.setBBLog(bbObject);
    }
}
