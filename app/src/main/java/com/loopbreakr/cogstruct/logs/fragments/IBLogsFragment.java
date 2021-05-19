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
import com.loopbreakr.cogstruct.databinding.LogsFragmentIbBinding;
import com.loopbreakr.cogstruct.identifybarriers.objects.IBObject;
import com.loopbreakr.cogstruct.identifybarriers.models.IBViewModel;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import org.jetbrains.annotations.NotNull;


public class IBLogsFragment extends Fragment {
    private LogsFragmentIbBinding binding;
    private LogsViewModel logsViewModel;
    private IBViewModel ibViewModel;
    private IBObject ibLog;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_ib, container, false);
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
        Toolbar toolbar = view.findViewById(R.id.logsToolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOverflowIcon(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_white_dots));
        toolbar.setOnMenuItemClickListener(item -> {
            NavController controller = Navigation.findNavController(requireView());
            switch (item.getItemId()) {
                case R.id.action_editLog :
                    controller.navigate(R.id.action_IBLogFragment_to_IBLogEditFragment);
                    return true;
                case R.id.action_deleteLog:
                    DocumentSnapshot snapshot = logsViewModel.getSnapshot();
                    snapshot.getReference().delete().addOnFailureListener(e ->
                            ((LogsActivity)requireActivity()).handleFailure(e, "FETCH"));
                    controller.popBackStack(R.id.allLogsFragment, true);
                    controller.navigate(R.id.allLogsFragment);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        });
    }

    private void setViewModelData() {
        ibLog = logsViewModel.getSnapshot().toObject(IBObject.class);
        ibViewModel.setIBLog(ibLog);
    }
}