package com.loopbreakr.cogstruct.howdigethere;

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
import com.loopbreakr.cogstruct.databinding.HighFragmentPageOneBinding;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;

import org.jetbrains.annotations.NotNull;


public class HIGHPageOne extends Fragment {
    private HIGHViewModel highViewModel;
    private Button returnButton, nextButton;
    private HighFragmentPageOneBinding binding;


    public HIGHPageOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        highViewModel = new ViewModelProvider(requireActivity()).get(HIGHViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment;
        binding = DataBindingUtil.inflate(inflater, R.layout.high_fragment_page_one, container, false);
        binding.setViewModel(highViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        returnButton = view.findViewById(R.id.high_return);
        nextButton = view.findViewById(R.id.high_page_one_next);
    }

    private void setButtons() {
        returnButton.setOnClickListener(v ->{
            Intent intent = new Intent(this.requireActivity(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });
        nextButton.setOnClickListener(v ->{
            NavController controller = Navigation.findNavController(requireView());
            controller.navigate(R.id.action_HIGHPageOne_to_HIGHPageTwo);
        });
    }
}