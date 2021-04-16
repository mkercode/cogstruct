package com.loopbreakr.cogstruct.insights.behavioralinspection.fragments;


import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.behavioralinspection.activities.BIActivity;
import com.loopbreakr.cogstruct.insights.behavioralinspection.models.BIViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BIPageThree extends Fragment {
    private BIViewModel biViewModel;
    private Toolbar backToolbar;
    private RecyclerView factorRecyclerview;
    private PieChart pieChart;
    private Map<String, Integer> factorMap;


    public BIPageThree() {
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
        return inflater.inflate(R.layout.bi_fragment_page_three, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewmodelData();
        findViews(view);
        setBackToolbar();
        setupPieChart();
        populatePieChart();
        animatePieChart();
        setRecyclerView();
    }

    private void getViewmodelData() {
        factorMap = new HashMap<>();
        factorMap = biViewModel.getBiFactorList(biViewModel.getBiSnapShotList(), biViewModel.getBiBehavior(), biViewModel.getBiInspection());
    }

    private void findViews(View view) {
        backToolbar = view.findViewById(R.id.iback_toolbar);
        pieChart = view.findViewById(R.id.factor_piechart);
    }

    private void setBackToolbar() {
        ((BIActivity)requireActivity()).biSetToolbar(backToolbar);
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setCenterTextColor(Color.DKGRAY);
        pieChart.setDrawRoundedSlices(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(14);
        pieChart.setHoleRadius(40);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText(biViewModel.getBiInspectionDisplay());
        pieChart.setCenterTextSize(18);
        pieChart.getDescription().setEnabled(false);
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
    }

    private void populatePieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: factorMap.entrySet()){
            float floatValue = (float) entry.getValue();
            entries.add(new PieEntry(floatValue, entry.getKey()));
        }

        ArrayList<Integer> colors = new ArrayList<>();

        for(int color: ColorTemplate.VORDIPLOM_COLORS){ colors.add(color); }
        for(int color: ColorTemplate.COLORFUL_COLORS){ colors.add(color); }
        for(int color: ColorTemplate.PASTEL_COLORS){ colors.add(color); }
        for(int color: ColorTemplate.LIBERTY_COLORS){ colors.add(color); }
        for(int color: ColorTemplate.JOYFUL_COLORS){ colors.add(color); }


        //write arrays to piechart
        PieDataSet dataSet = new PieDataSet(entries,"Triggers");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void animatePieChart() {
        //add animation
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    private void setRecyclerView() {
        Log.d("STRANG", "setRecyclerView: " + factorMap);
    }

}