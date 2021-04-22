package com.loopbreakr.cogstruct.insights.findthinkingerrors.fragments;

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
import com.loopbreakr.cogstruct.databinding.FteFragmentCreateBinding;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.models.FTEViewModel;

import org.jetbrains.annotations.NotNull;


public class FTECreate extends Fragment {
    private FTEViewModel fteViewModel;
    private FteFragmentCreateBinding binding;
    private Button backButton, reviewButton;

    public FTECreate() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fteViewModel = new ViewModelProvider(requireActivity()).get(FTEViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fte_fragment_create, container, false);
        binding.setViewModel(fteViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        backButton = view.findViewById(R.id.fte_back);
        reviewButton = view.findViewById(R.id.fte_review);
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        backButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.FTESelectCreate, true);
            controller.navigate(R.id.FTESelectCreate);
        });
        reviewButton.setOnClickListener( v -> controller.navigate(R.id.action_FTECreate_to_FTEReview));
    }
}