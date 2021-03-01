package com.loopbreakr.brainstruct;

import android.content.Intent;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TJPageOne extends Fragment {
    private TJViewModel tjViewModel;
    private EditText placeInput, peopleInput;
    private RadioGroup timeRadioGroup , peopleRadioGroup;
    private Button returnButton, nextButton;

    public TJPageOne() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tjViewModel = new ViewModelProvider(requireActivity()).get(TJViewModel.class);
      }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tj_page_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setRadioGroups(view);
        setButtons();
    }

    private void findViews(View view) {
        placeInput = view.findViewById(R.id.place_input);
        peopleInput = view.findViewById(R.id.people_input);
        returnButton = view.findViewById(R.id.tj_return);
        nextButton = view.findViewById(R.id.page_one_next);
        timeRadioGroup = view.findViewById(R.id.time_radiogroup);
        peopleRadioGroup = view.findViewById(R.id.people_radiogroup);
    }

    private void setRadioGroups(View view) {
        timeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton timeRadioButton = (RadioButton) view.findViewById(checkedId);
            tjViewModel.setTimeText(timeRadioButton.getText().toString());
        });
        peopleRadioGroup.setOnCheckedChangeListener(((group, checkedId) -> {
            RadioButton peopleRadioButton = (RadioButton) view.findViewById(checkedId);
            tjViewModel.setPeopleText(peopleRadioButton.getText().toString());
        }));
    }

    private void setButtons() {
        returnButton.setOnClickListener(v ->{
            Intent intent = new Intent(this.requireActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        nextButton.setOnClickListener(v ->{
            getInputs();
            NavController navController = Navigation.findNavController(getView());
            navController.navigate(R.id.action_tjPageOne_to_tjPageTwo);
        });
    }

    private void getInputs() {
        tjViewModel.setlocationText(placeInput.getText().toString());
        if(tjViewModel.getPeopleText().equals("With others")){
            tjViewModel.setPeopleText(peopleInput.getText().toString());
        }
    }
}