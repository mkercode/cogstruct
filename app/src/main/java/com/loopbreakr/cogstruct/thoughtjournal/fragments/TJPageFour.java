package com.loopbreakr.cogstruct.thoughtjournal.fragments;

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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.TjFragmentPageFourBinding;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;

public class TJPageFour extends Fragment {
    private TJViewModel tjViewModel;
    private Button backButton, nextButton;
    private TjFragmentPageFourBinding binding;

    public TJPageFour() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tjViewModel = new ViewModelProvider(requireActivity()).get(TJViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.tj_fragment_page_four, container, false);
        binding.setViewModel(tjViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    private void findViews(View view) {
        backButton = view.findViewById(R.id.page_four_back);
        nextButton = view.findViewById(R.id.page_four_next);
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        backButton.setOnClickListener(v -> {
            controller.popBackStack(R.id.tjPageThree, true);
            controller.navigate(R.id.tjPageThree);
        });
        nextButton.setOnClickListener(v -> controller.navigate(R.id.action_tjPageFour_to_tjPageFive));
    }
}