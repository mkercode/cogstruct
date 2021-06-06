package com.loopbreakr.cogstruct.thoughtjournal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.TjFragmentPageSixBinding;
import com.loopbreakr.cogstruct.thoughtjournal.activities.TJActivity;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TJPageSix extends Fragment{
    private TJViewModel tjViewModel;
    private List<String> requiredInputs;
    private Button editButton, submitButton;

    public TJPageSix() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tjViewModel = new ViewModelProvider(requireActivity()).get(TJViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.TjFragmentPageSixBinding binding = DataBindingUtil.inflate(inflater, R.layout.tj_fragment_page_six, container, false);
        binding.setViewModel(tjViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViews(view);
        getViewModelData();
        setButtons();
    }

    private void getViews(View view) {
        editButton = view.findViewById(R.id.tj_edit);
        submitButton = view.findViewById(R.id.tj_submit);
    }

    private void getViewModelData() {
        requiredInputs =  new ArrayList<>(Arrays. asList(tjViewModel.getLocationText(), tjViewModel.getTimeText(), tjViewModel.getPeopleText(), tjViewModel.getBehaviorText(),tjViewModel.getEmotionText(), tjViewModel.getEmotionRatingString(), tjViewModel.getThoughtText()));
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        editButton.setOnClickListener(v -> controller.navigateUp());
        submitButton.setOnClickListener(v ->{
            if(!requiredInputs.contains("") &&!requiredInputs.contains(null) && !tjViewModel.getThoughtList().isEmpty()){
                submitData();
            }
            else{
                Toast.makeText(requireActivity().getApplicationContext(), "Please fill in all required fields!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitData() {
       ((TJActivity)requireActivity()).sendToFirestore(tjViewModel.getLocationText(),tjViewModel.getTimeText(),tjViewModel.getPeopleText(),tjViewModel.getSituationText(),tjViewModel.getBehaviorText(),tjViewModel.getEmotionText(),tjViewModel.getEmotionRating(),tjViewModel.getEmotionDetailText(), tjViewModel.getThoughtText());
    }
}