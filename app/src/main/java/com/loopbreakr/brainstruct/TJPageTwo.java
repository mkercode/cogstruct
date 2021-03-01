package com.loopbreakr.brainstruct;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class TJPageTwo extends Fragment {
    private TJViewModel tjViewModel;
    private EditText detailsInput;
    private Button backButton, nextButton;


    public TJPageTwo() {
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
        return inflater.inflate(R.layout.fragment_tj_page_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setViewModelData();
        setButtons(view);
    }

    private void findViews(View view) {
        detailsInput = view.findViewById(R.id.details_input);
        backButton = view.findViewById(R.id.page_two_back);
        nextButton = view.findViewById(R.id.page_two_next);
    }

    private void setViewModelData(){
        detailsInput.setText(tjViewModel.getSituationText());
    }

    private void setButtons(View view) {
        backButton.setOnClickListener(v -> {
            getTextInput();
            NavController controller = Navigation.findNavController(view);
            controller.popBackStack(R.id.tjPageOne, true);
            controller.navigate(R.id.tjPageOne);
        });
        nextButton.setOnClickListener(v -> {
            getTextInput();
            NavController navController = Navigation.findNavController(getView());
            navController.navigate(R.id.action_tjPageTwo_to_tjPageThree);

        });
    }

    private void getTextInput(){
        tjViewModel.setSituationText(detailsInput.getText());
    }
}