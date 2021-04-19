package com.loopbreakr.cogstruct.insights.behavioralinspection.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.behavioralinspection.activities.BIActivity;
import com.loopbreakr.cogstruct.insights.adapters.InsightsRecyclerAdapter;
import com.loopbreakr.cogstruct.insights.behavioralinspection.models.BIViewModel;

import java.util.List;


public class BIPageTwo extends Fragment {
    private BIViewModel biViewModel;
    private List<String> biInspectionList;
    private Toolbar backToolbar;
    private RecyclerView inspectionRecyclerview;
    private ProgressBar loadingBar;
    private TextView noDataText;

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
        noDataText = view.findViewById(R.id.no_data_text);
    }

    private void setToolbar() {
        ((BIActivity)requireActivity()).biSetToolbar(backToolbar);
    }


    private void setRecyclerView() {
        loadingBar.setVisibility(View.GONE);

        if(biInspectionList == null || biInspectionList.isEmpty()){
            noDataText.setVisibility(View.VISIBLE);
        }
        else{
            noDataText.setVisibility(View.GONE);
            InsightsRecyclerAdapter inspectionRecyclerAdapter = new InsightsRecyclerAdapter(biInspectionList);
            inspectionRecyclerview.setAdapter(inspectionRecyclerAdapter);

            inspectionRecyclerAdapter.setOnItemClickListener(position -> {
                biViewModel.setBiInspection(biInspectionList.get(position));
                (Navigation.findNavController(requireView())).navigate(R.id.action_BIPageTwo_to_BIPageThree);
            });
        }

    }
}