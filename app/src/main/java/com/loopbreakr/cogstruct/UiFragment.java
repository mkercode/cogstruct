package com.loopbreakr.cogstruct;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.loopbreakr.cogstruct.logs.activities.LogsActivity;


public class UiFragment extends Fragment {
    private NavController navController;
    private CardView thoughtJournalCard, prosConsCard, howdIGetHereCard, badBehaviorsCard, identifyBarriersCard, abcsCard;
    private ImageView logsIcon, insightsIcon, learnMoreIcon, aboutIcon;


    public UiFragment() {
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
        return inflater.inflate(R.layout.home_fragment_ui, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setToolbar(view);
        findViews(view);
        setListenerBehavior();

    }

    private void setToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.homeToolBar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_logout));
        toolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.action_logout){
                ((MainActivity)requireActivity()).signOutClicked();
            }
            return false;
        });
    }


    private void findViews(View view) {
        //find cardviews
        thoughtJournalCard = view.findViewById(R.id.thought_journal_card);
        prosConsCard = view.findViewById(R.id.pros_cons_card);
        howdIGetHereCard = view.findViewById(R.id.howd_i_get_here_card);
        badBehaviorsCard = view.findViewById(R.id.negative_thoughts_card);
        identifyBarriersCard = view.findViewById(R.id.identify_barriers_card);
        abcsCard = view.findViewById(R.id.abcs_card);
        //find bottom menu icons
        logsIcon = view.findViewById(R.id.logs_image);
        insightsIcon = view.findViewById(R.id.insights_image);
        learnMoreIcon = view.findViewById(R.id.learn_more_image);
        aboutIcon = view.findViewById(R.id.about_image);

    }

    private void setListenerBehavior() {
        thoughtJournalCard.setOnClickListener(v -> navController.navigate(R.id.action_fragmentCbtActivities_to_TJViewFragment));
        prosConsCard.setOnClickListener(v -> navController.navigate(R.id.action_fragmentCbtActivities_to_PCViewFragment));
        howdIGetHereCard.setOnClickListener(v -> navController.navigate(R.id.action_fragmentCbtActivities_to_HIGHViewFragment));
        badBehaviorsCard.setOnClickListener(v -> navController.navigate(R.id.action_fragmentCbtActivities_to_BBViewFragment));
        identifyBarriersCard.setOnClickListener(v -> navController.navigate(R.id.action_fragmentCbtActivities_to_IBViewFragment));
        abcsCard.setOnClickListener(v -> navController.navigate(R.id.action_fragmentCbtActivities_to_ABCViewFragment));
        insightsIcon.setOnClickListener(v -> navController.navigate(R.id.action_fragmentCbtActivities_to_insightsFragment));
        learnMoreIcon.setOnClickListener(v -> navController.navigate(R.id.action_fragmentCbtActivities_to_learnMoreFragment));
        aboutIcon.setOnClickListener(v -> navController.navigate(R.id.action_fragmentCbtActivities_to_aboutFragment));
        logsIcon.setOnClickListener(v ->{
            Intent intent = new Intent(this.requireActivity(), LogsActivity.class);
            startActivity(intent);
        });
    }

    private void logOut(){
        Intent intent = new Intent(this.requireActivity(), LoginActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

}
