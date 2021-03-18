package com.loopbreakr.cogstruct.identifybarriers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.SimpleRecyclerAdapter;
import com.loopbreakr.cogstruct.databinding.IbFragmentProblemSolvingBinding;

import java.util.List;


public class IBProblemSolving extends Fragment {
    private IbFragmentProblemSolvingBinding binding;
    private IBViewModel ibViewModel;
    private Button editButton, submitButton;
    private FloatingActionButton addSolution;


    public IBProblemSolving() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibViewModel = new ViewModelProvider(requireActivity()).get(IBViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.ib_fragment_problem_solving, container, false);
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
        editButton.setOnClickListener(v ->{
            controller.navigateUp();
        });
//        submitButtonButton.setOnClickListener(v -> controller.navigate(R.id.action_PCPageThree_to_PCPageFour));
    }


}
