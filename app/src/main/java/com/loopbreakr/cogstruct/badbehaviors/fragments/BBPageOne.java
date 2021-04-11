package com.loopbreakr.cogstruct.badbehaviors.fragments;

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

import com.loopbreakr.cogstruct.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.badbehaviors.models.BBViewModel;
import com.loopbreakr.cogstruct.databinding.BbFragmentPageOneBinding;

import org.jetbrains.annotations.NotNull;


public class BBPageOne extends Fragment {
    private BbFragmentPageOneBinding binding;
    private BBViewModel bbViewModel;
    private Button returnButton, nextButton;

    public BBPageOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bbViewModel = new ViewModelProvider(requireActivity()).get(BBViewModel.class);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.bb_fragment_page_one, container, false);
        binding.setViewModel(bbViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        returnButton = view.findViewById(R.id.bb_return);
        nextButton = view.findViewById(R.id.bb_page_one_next);
    }

    private void setButtons() {
        returnButton.setOnClickListener(v ->{
            Intent intent = new Intent(this.requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });
        nextButton.setOnClickListener(v ->{
            NavController controller = Navigation.findNavController(requireView());
            controller.navigate(R.id.action_BBPageOne_to_BBPageTwo);
        });
    }
}