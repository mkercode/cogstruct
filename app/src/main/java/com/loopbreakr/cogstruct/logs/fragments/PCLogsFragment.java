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
import android.widget.Toast;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.badbehaviors.activities.BBActivity;
import com.loopbreakr.cogstruct.databinding.LogsFragmentPcBinding;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;
import com.loopbreakr.cogstruct.proscons.models.PCViewModel;
import com.loopbreakr.cogstruct.proscons.objects.ProsConsObject;

import org.jetbrains.annotations.NotNull;

public class PCLogsFragment extends Fragment {
    LogsViewModel logsViewModel;
    PCViewModel pcViewModel;
    ProsConsObject prosAndConsLog;
    LogsFragmentPcBinding binding;

    public PCLogsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        pcViewModel = new  ViewModelProvider(requireActivity()).get(PCViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_pc, container, false);
        binding.setViewModel(pcViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        setViewModelData();
    }

    private void setToolbar(View view) {
        ((LogsActivity)requireActivity()).setToolbar(view.findViewById(R.id.logsToolbar), "VIEW", R.id.action_PCLogFragment_to_PCLogEditFragment, logsViewModel.getSnapshot());
    }

    private void setViewModelData() {
        prosAndConsLog = logsViewModel.getSnapshot().toObject(ProsConsObject.class);
        pcViewModel.setProsCons(prosAndConsLog);
    }
}