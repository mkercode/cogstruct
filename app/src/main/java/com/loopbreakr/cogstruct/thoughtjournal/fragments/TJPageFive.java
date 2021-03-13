package com.loopbreakr.cogstruct.thoughtjournal.fragments;

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
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TJPageFive extends Fragment {
    private TJViewModel tjViewModel;
    private EditText thoughtInput;
    private Button addThoughtButton, backButton, reviewButton;
    private ChipGroup thoughtChipGroup;
    private HorizontalScrollView thoughtScrollView;
    private List<String> thoughtList = new ArrayList<>();


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
        addThoughtButton = view.findViewById(R.id.add_thought_button);
        thoughtChipGroup = view.findViewById(R.id.tj_thought_chipgroup);
        backButton = view.findViewById(R.id.page_five_back);
        reviewButton = view.findViewById(R.id.tj_review);
    }

    private void getViewModelData() {
       if(tjViewModel.getThoughtList() != null && !tjViewModel.getThoughtList().isEmpty()){
           thoughtList = tjViewModel.getThoughtList();
           thoughtInput.setText(thoughtList.toString());

           //create chips from past input
           for(String thought: thoughtList){
               createThoughtChip(thought);
           }
       }
    }

    private void setButtons() {

        addThoughtButton.setOnClickListener(v -> {
            String thought = thoughtInput.getText().toString().trim();
            if (!thought.equals("") && !thoughtInput.getText().toString().isEmpty()){
                createThoughtChip(thought);
                thoughtList.add(thought);
                thoughtInput.setText("");
            }
            Log.d( "setButtons: ", thoughtList.toString());
        });

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

    private void createThoughtChip(String thought) {
        Chip chip = new Chip(requireActivity());
        chip.setText(thought);
        chip.setChipBackgroundColorResource(R.color.colorPrimary);
        chip.setTextColor(getResources().getColor(R.color.colorWhite));
        chip.setCloseIconVisible(true);
        chip.setCloseIconTintResource(R.color.colorWhite);
        thoughtChipGroup.addView(chip);
        chip.setOnCloseIconClickListener(view -> {
            thoughtChipGroup.removeView(chip);
            thoughtList.remove(thought);
        });
    }

    private void getTextInput(){
        tjViewModel.setThoughtList(thoughtList);
    }


}