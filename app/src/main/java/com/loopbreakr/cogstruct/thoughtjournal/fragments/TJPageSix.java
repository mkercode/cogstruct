package com.loopbreakr.cogstruct.thoughtjournal.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.activities.TJActivity;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;

import java.util.Arrays;
import java.util.List;

public class TJPageSix extends Fragment{
    private TJViewModel tjViewModel;
    public TJPageSix() {
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
        return inflater.inflate(R.layout.fragment_tj_page_six, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("LOGGING", "onViewCreated: " + "start");
        List<String> thoughtList = Arrays.asList(tjViewModel.getThoughtText().split("\\s*,\\s*"));
        Log.d("LOGGING", "onViewCreated: " + thoughtList.toString());
        String[] thoughtArray = (String[]) thoughtList.toArray();
        Log.d("LOGGING", "onViewCreated: " + thoughtArray[0]);
        ((TJActivity)requireActivity()).sendToFirestore(tjViewModel.getLocationText(),tjViewModel.getTimeText(),tjViewModel.getPeopleText(),tjViewModel.getSituationText(),tjViewModel.getBehaviorText(),tjViewModel.getEmotionText(),tjViewModel.getEmotionRating(),tjViewModel.getEmotionDetailText(), tjViewModel.getThoughtText());
    }
}