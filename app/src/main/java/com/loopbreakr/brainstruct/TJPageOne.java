package com.loopbreakr.brainstruct;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class TJPageOne extends Fragment {

    private TJViewModel tjViewModel;
    private EditText placeInput, peopleInput;
    private RadioGroup timeRadioGroup , peopleRadioGroup;
    private RadioButton timeRadioButton, peopleRadioButton;
    private Button returnButton, nextButton;

    public TJPageOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        setRadioGroups();
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

    private void setRadioGroups() {
        peopleRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Get the selected Radio Button
            timeRadioButton = (RadioButton)group.findViewById(checkedId);
        });
        timeRadioGroup.setOnCheckedChangeListener(((group, checkedId) -> {
            peopleRadioButton = (RadioButton)group.findViewById(checkedId);
        }));
    }

    private void setButtons() {
        returnButton.setOnClickListener(v ->{
            Intent intent = new Intent(this.requireActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        nextButton.setOnClickListener(v ->{
            int selectedTimeId = timeRadioGroup.getCheckedRadioButtonId();
            if (selectedTimeId == -1) {

            }
            else {
                RadioButton radioButton = (RadioButton)timeRadioGroup
                        .findViewById(selectedTimeId);

                Toast.makeText(getActivity().getApplicationContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();
            }


            int selectedPeopleId = timeRadioGroup.getCheckedRadioButtonId();
            if (selectedPeopleId == -1) {

            }
            else {
                RadioButton radioButton
                        = (RadioButton)timeRadioGroup
                        .findViewById(selectedTimeId);
                // Now display the value of selected item by the Toast message
                Toast.makeText(getActivity().getApplicationContext(), radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}