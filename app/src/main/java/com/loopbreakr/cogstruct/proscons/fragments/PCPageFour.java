package com.loopbreakr.cogstruct.proscons.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.PcFragmentPageFourBinding;
import com.loopbreakr.cogstruct.proscons.models.PCViewModel;
import com.loopbreakr.cogstruct.proscons.activities.PCActivity;

import org.jetbrains.annotations.NotNull;


public class PCPageFour extends Fragment {
    private PCViewModel pcViewModel;
    private Button editButton, submitButton;
    private PcFragmentPageFourBinding binding;

    public PCPageFour() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pcViewModel = new ViewModelProvider(requireActivity()).get(PCViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.pc_fragment_page_four, container, false);
        binding.setViewModel(pcViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        editButton = view.findViewById(R.id.pc_edit);
        submitButton = view.findViewById(R.id.pc_submit);
    }

    private void setButtons() {
        editButton.setOnClickListener(v ->{
            NavController controller = Navigation.findNavController(requireView());
            controller.popBackStack(R.id.PCPageThree, true);
            controller.navigate(R.id.PCPageThree);
        });
        submitButton.setOnClickListener(v -> submitData());
    }

    private void submitData() {
        ((PCActivity)requireActivity()).sendToFirestore(pcViewModel.getPCBehavior(),pcViewModel.getChangeProsString(), pcViewModel.getDontChangeProsString(), pcViewModel.getChangeConsString(), pcViewModel.getDontChangeConsString());
    }
}