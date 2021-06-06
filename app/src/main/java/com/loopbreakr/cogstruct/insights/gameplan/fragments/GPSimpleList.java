package com.loopbreakr.cogstruct.insights.gameplan.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.GpFragmentSimpleListBinding;
import com.loopbreakr.cogstruct.insights.gameplan.activities.GPActivity;
import com.loopbreakr.cogstruct.insights.gameplan.adapters.SingleLineAdapter;
import com.loopbreakr.cogstruct.insights.gameplan.models.GPViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class GPSimpleList extends Fragment {
    private GPViewModel gpViewModel;
    private Toolbar backToolbar;
    private List<String> changeFactorList;
    private RecyclerView changeListRecyclerview;

    public GPSimpleList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gpViewModel = new ViewModelProvider(requireActivity()).get(GPViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.GpFragmentSimpleListBinding binding = DataBindingUtil.inflate(inflater, R.layout.gp_fragment_simple_list, container, false);
        binding.setViewModel(gpViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewmodelData();
        findViews(view);
        setBackToolbar();
        setRecyclerView();
    }

    private void getViewmodelData() {
        changeFactorList = new ArrayList<>();
        changeFactorList = gpViewModel.getGpChangeFactors(gpViewModel.getGpSnapShotList(), gpViewModel.getGpBehavior(), gpViewModel.getGpChange());
    }

    private void findViews(View view) {
        backToolbar = view.findViewById(R.id.back_toolbar);
        changeListRecyclerview = view.findViewById(R.id.single_line_recyclerview);
    }

    private void setBackToolbar() {
        ((GPActivity)requireActivity()).gpSetToolbar(backToolbar);
    }

    private void setRecyclerView() {
        SingleLineAdapter changeFactorAdapter = new SingleLineAdapter(changeFactorList);
        changeListRecyclerview.setAdapter(changeFactorAdapter);
    }
}