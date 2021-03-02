package com.loopbreakr.brainstruct;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class TJPageThree extends Fragment {
    private TJViewModel tjViewModel;
    private EditText behaviorInput;
    private Button backButton, nextButton;

    public TJPageThree() {
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
        return inflater.inflate(R.layout.fragment_tj_page_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setViewModelData();
        setButtons();
    }

    private void findViews(View view) {
        behaviorInput = view.findViewById(R.id.behavior_input);
        backButton = view.findViewById(R.id.page_three_back);
        nextButton = view.findViewById(R.id.page_three_next);
    }

    private void setViewModelData() {
        behaviorInput.setText(tjViewModel.getBehaviorText());
    }


    private void setButtons() {
        NavController controller = Navigation.findNavController(getView());
        backButton.setOnClickListener(v -> {
            getTextInput();
            controller.popBackStack(R.id.tjPageTwo, true);
            controller.navigate(R.id.tjPageTwo);
        });
        nextButton.setOnClickListener(v -> {
            getTextInput();
            controller.navigate(R.id.action_tjPageThree_to_tjPageFour);
        });
    }

    private void getTextInput() {
        tjViewModel.setBehaviorText(behaviorInput.getText());
    }
}