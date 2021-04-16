package com.loopbreakr.cogstruct.insights.behavioralinspection.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.behavioralinspection.activities.BIActivity;
import com.loopbreakr.cogstruct.insights.behavioralinspection.adapters.InsightsRecyclerAdapter;
import com.loopbreakr.cogstruct.insights.behavioralinspection.models.BIViewModel;

import java.util.ArrayList;
import java.util.List;


public class BIPageTwo extends Fragment {
    private BIViewModel biViewModel;
    private List<String> biInspectionList;
    private Toolbar backToolbar;
    private RecyclerView inspectionRecyclerview;
    private ProgressBar loadingBar;

    public BIPageTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biViewModel = new ViewModelProvider(requireActivity()).get(BIViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bi_fragment_page_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewmodelData();
        findViews(view);
        setToolbar();
        setRecyclerView();
    }

    private void getViewmodelData() {
        biInspectionList = biViewModel.getBiInspectionList(biViewModel.getBiSnapShotList(), biViewModel.getBiBehavior());
    }


    private void findViews(View view) {
        backToolbar = view.findViewById(R.id.iback_toolbar);
        inspectionRecyclerview = view.findViewById(R.id.bi_inspection_recyclerview);
        loadingBar = view.findViewById(R.id.loading_bar);
    }

    private void setToolbar() {
        ((BIActivity)requireActivity()).biSetToolbar(backToolbar);
    }


    private void setRecyclerView() {
        loadingBar.setVisibility(View.GONE);
        InsightsRecyclerAdapter behaviorRecyclerAdapter = new InsightsRecyclerAdapter(biInspectionList);
        inspectionRecyclerview.setAdapter(behaviorRecyclerAdapter);

        behaviorRecyclerAdapter.setOnItemClickListener(position -> {
            biViewModel.setBiBehavior(biInspectionList.get(position));
            navigateNext().navigate(R.id.action_BIPageTwo_to_BIPageThree);
        });
    }

    private NavController navigateNext() {
        NavController controller = Navigation.findNavController(requireView());
        return controller;
    }

}