package com.loopbreakr.cogstruct.identifybarriers.fragments;

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
import android.widget.Toast;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.identifybarriers.activities.IBActivity;
import com.loopbreakr.cogstruct.identifybarriers.models.IBViewModel;

import org.jetbrains.annotations.NotNull;


public class IBProblemSolving extends Fragment {
    private IBViewModel ibViewModel;
    private Button editButton, submitButton;


    public IBProblemSolving() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibViewModel = new ViewModelProvider(requireActivity()).get(IBViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.IbFragmentProblemSolvingBinding binding = DataBindingUtil.inflate(inflater, R.layout.ib_fragment_problem_solving, container, false);
        binding.setViewModel(ibViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        editButton = view.findViewById(R.id.ib_edit);
        submitButton = view.findViewById(R.id.ib_submit);
    }


    private void setButtons() {


        NavController controller = Navigation.findNavController(requireView());
        editButton.setOnClickListener(v -> controller.navigateUp());
        submitButton.setOnClickListener(v -> {
          if(ibViewModel.getIbBehavior() != null && ibViewModel.getIbBarrier() != null && ibViewModel.getIbNescessaryAction() != null && ibViewModel.getIbSolutions() != null){
              submitData();
          }
          else{
              Toast.makeText(requireActivity().getApplicationContext(), "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
          }
        });

    }

    private void submitData() {
        ((IBActivity)requireActivity()).sendToFirestore(ibViewModel.getIbBehavior(),ibViewModel.getIbNescessaryAction(),ibViewModel.getIbBarrierType(),ibViewModel.getIbBarrier(),ibViewModel.getIbSolutions());
    }


}
