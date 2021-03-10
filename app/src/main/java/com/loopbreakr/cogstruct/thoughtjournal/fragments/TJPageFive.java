package com.loopbreakr.cogstruct.thoughtjournal.fragments;

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

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;

public class TJPageFive extends Fragment {
    private TJViewModel tjViewModel;
    private EditText thoughtInput;
    private Button backButton, reviewButton;

    public TJPageFive() {
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
        return inflater.inflate(R.layout.tj_fragment_page_five, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        getViewModelData();
        setButtons();
    }

    private void findViews(View view) {
        thoughtInput = view.findViewById(R.id.thought_input);
        backButton = view.findViewById(R.id.page_five_back);
        reviewButton = view.findViewById(R.id.tj_review);
    }

    private void getViewModelData() {
       thoughtInput.setText(tjViewModel.getThoughtText());
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        backButton.setOnClickListener(v ->{
            getTextInput();
            controller.popBackStack(R.id.tjPageFour, true);
            controller.navigate(R.id.tjPageFour);
        });
        reviewButton.setOnClickListener(v ->{
            getTextInput();
            controller.navigate(R.id.action_tjPageFive_to_tjPageSix);
        });
    }

    private void getTextInput(){
        tjViewModel.setThoughtText(thoughtInput.getText());
    }
}