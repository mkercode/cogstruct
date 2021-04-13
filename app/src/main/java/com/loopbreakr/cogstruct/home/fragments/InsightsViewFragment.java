package com.loopbreakr.cogstruct.home.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopbreakr.cogstruct.R;

import java.util.Objects;


public class InsightsViewFragment extends Fragment {
    private NavController navController;
    private CardView behavioralInspectionCard, gamePlanCard, findThinkingErrorsCard;

    public InsightsViewFragment() {
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
        return inflater.inflate(R.layout.home_fragment_insights_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setBackToolBar(view);
        findViews(view);
        setListenerBehavior();
    }

    private void setBackToolBar(View view) {
        Toolbar backToolbar = view.findViewById(R.id.back_toolbar);

        ((AppCompatActivity)requireActivity()).setSupportActionBar(backToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);

        backToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        backToolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
    }

    private void findViews(View view) {

        behavioralInspectionCard= view.findViewById(R.id.behavioral_inspection_card);
        gamePlanCard = view.findViewById(R.id.game_plan_card);
        findThinkingErrorsCard = view.findViewById(R.id.find_thinking_errors_card);
    }

    private void setListenerBehavior(){
        behavioralInspectionCard.setOnClickListener(v -> navController.navigate(R.id.action_insightsViewFragment_to_BIViewFragment));
        gamePlanCard.setOnClickListener(v -> navController.navigate(R.id.action_insightsViewFragment_to_GPViewFragment));
        findThinkingErrorsCard.setOnClickListener(v -> navController.navigate(R.id.action_insightsViewFragment_to_FTEViewFragment));
    }


}