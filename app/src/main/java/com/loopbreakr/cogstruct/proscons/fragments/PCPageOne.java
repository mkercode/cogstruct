package com.loopbreakr.cogstruct.proscons.fragments;

import android.content.Intent;
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

import com.loopbreakr.cogstruct.home.activities.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.PcFragmentPageOneBinding;
import com.loopbreakr.cogstruct.proscons.models.PCViewModel;

import org.jetbrains.annotations.NotNull;


public class PCPageOne extends Fragment {
    private PCViewModel pcViewModel;
    private Button returnButton, nextButton;

    public PCPageOne() {
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
        com.loopbreakr.cogstruct.databinding.PcFragmentPageOneBinding binding = DataBindingUtil.inflate(inflater, R.layout.pc_fragment_page_one, container, false);
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
        returnButton = view.findViewById(R.id.pc_return);
        nextButton = view.findViewById(R.id.pc_page_one_next);
    }

    private void setButtons() {
        returnButton.setOnClickListener(v ->{
            Intent intent = new Intent(this.requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();

        });

        nextButton.setOnClickListener(v ->{
            NavController controller = Navigation.findNavController(requireView());
            controller.navigate(R.id.action_PCPageOne_to_PCPageTwo);
        });
    }
}