package com.loopbreakr.cogstruct.logs.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
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
import com.loopbreakr.cogstruct.databinding.LogsFragmentIbEditBinding;
import com.loopbreakr.cogstruct.identifybarriers.models.IBViewModel;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import org.jetbrains.annotations.NotNull;


public class IBLogEditFragment extends Fragment {
    private LogsFragmentIbEditBinding binding;
    private LogsViewModel logsViewModel;
    private IBViewModel ibViewModel;


    public IBLogEditFragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_ib_edit, container, false);
        binding.setViewModel(ibViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);

    }



    private void setToolbar(View view) {
        NavController controller = Navigation.findNavController(requireView());
        Toolbar toolbar = view.findViewById(R.id.edit_logs_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_log) {
                updateFirestoreDocument();
                controller.popBackStack(R.id.allLogsFragment, true);
                controller.navigate(R.id.allLogsFragment);
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void updateFirestoreDocument() {
        DocumentSnapshot logSnapshot = logsViewModel.getSnapshot();
        logSnapshot.getReference().update(
                "behavior", ibViewModel.getIbBehavior(),
                "necessaryAction", ibViewModel.getIbNescessaryAction(),
                "barrierType", ibViewModel.getIbBarrierType(),
                "barrier", ibViewModel.getIbBarrier(),
                "solution",ibViewModel.getIbSolution())
                .addOnFailureListener(e -> Log.e("UPDATING IB LOG", "FAILED. ALL FIELDS OF " + logSnapshot.getData() , e)).addOnSuccessListener(aVoid -> Log.d("UPDATING IB LOG", "SUCCESS. ALL FIELDS OF " + logSnapshot.getData()));

    }
}