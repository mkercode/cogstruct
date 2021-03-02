package com.loopbreakr.brainstruct;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

public class TJPageFive extends Fragment {
    private TJViewModel tjViewModel;
    private EditText thoughtInput;
    private Button backButton, submitButton;

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
        return inflater.inflate(R.layout.fragment_tj_page_five, container, false);
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
        submitButton = view.findViewById(R.id.tj_submit);
    }

    private void getViewModelData() {
       thoughtInput.setText(tjViewModel.getThoughtText());
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(getView());
        backButton.setOnClickListener(v ->{
            getTextInput();
            controller.popBackStack(R.id.tjPageFour, true);
            controller.navigate(R.id.tjPageFour);
        });
        submitButton.setOnClickListener(v ->{
            getTextInput();
            controller.navigate(R.id.action_tjPageFive_to_tjPageSix);
            List<String> items = Arrays.asList(tjViewModel.getThoughtText().split("\\s*,\\s*"));
            Log.d("LOGGING....", "Arraylist: " + items);
        });
    }

    private void getTextInput(){
        tjViewModel.setThoughtText(thoughtInput.getText());
    }
}