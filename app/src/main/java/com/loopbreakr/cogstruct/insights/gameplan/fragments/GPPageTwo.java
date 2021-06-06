package com.loopbreakr.cogstruct.insights.gameplan.fragments;

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
import com.loopbreakr.cogstruct.insights.adapters.InsightsRecyclerAdapter;
import com.loopbreakr.cogstruct.insights.gameplan.activities.GPActivity;
import com.loopbreakr.cogstruct.insights.gameplan.models.GPViewModel;

import java.util.List;


public class GPPageTwo extends Fragment {
    private GPViewModel gpViewModel;
    private List<String> gpChangeList;
    private Toolbar backToolbar;
    private RecyclerView changeRecyclerview;
    private ProgressBar loadingBar;
    private TextView noDataText;

    public GPPageTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gpViewModel = new ViewModelProvider(requireActivity()).get(GPViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.gp_fragment_page_two, container, false);
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
        gpChangeList = gpViewModel.getGpChangeList(gpViewModel.getGpSnapShotList(), gpViewModel.getGpBehavior());
    }


    private void findViews(View view) {
        backToolbar = view.findViewById(R.id.iback_toolbar);
        changeRecyclerview = view.findViewById(R.id.gp_change_recyclerview);
        loadingBar = view.findViewById(R.id.loading_bar);
        noDataText = view.findViewById(R.id.no_data_text);
    }

    private void setToolbar() {
        ((GPActivity)requireActivity()).gpSetToolbar(backToolbar);
    }


    private void setRecyclerView() {
        loadingBar.setVisibility(View.GONE);

        if(gpChangeList == null || gpChangeList.isEmpty()){
            noDataText.setVisibility(View.VISIBLE);
        }
        else{
            noDataText.setVisibility(View.GONE);
            InsightsRecyclerAdapter gpRecyclerAdapter = new InsightsRecyclerAdapter(gpChangeList);
            changeRecyclerview.setAdapter(gpRecyclerAdapter);

            gpRecyclerAdapter.setOnItemClickListener(position -> {
                gpViewModel.setGpChange(gpChangeList.get(position));
                handleNavigation();
            });
        }
    }

    private void handleNavigation() {
        switch (gpViewModel.getGpChangeDisplay()){
            case "Pros/Cons":
                (Navigation.findNavController(requireView())).navigate(R.id.action_GPPageTwo_to_GPProConsSelect);
                break;
            case "Barriers":
                (Navigation.findNavController(requireView())).navigate(R.id.action_GPPageTwo_to_GPTypeList);
                break;
            default:
                (Navigation.findNavController(requireView())).navigate(R.id.action_GPPageTwo_to_GPSimpleList);
                break;
        }
    }
}