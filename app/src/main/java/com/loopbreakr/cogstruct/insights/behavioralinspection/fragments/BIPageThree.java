package com.loopbreakr.cogstruct.insights.behavioralinspection.fragments;


import android.graphics.Color;
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
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.adapters.LegendRecyclerAdapter;
import com.loopbreakr.cogstruct.insights.behavioralinspection.activities.BIActivity;
import com.loopbreakr.cogstruct.insights.behavioralinspection.models.BIViewModel;
import com.loopbreakr.cogstruct.insights.behavioralinspection.objects.Factor;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class BIPageThree extends Fragment {
    private BIViewModel biViewModel;
    private Toolbar backToolbar;
    private RecyclerView factorRecyclerview;
    private PieChart pieChart;
    private String behavior;
    private Map<String, Integer> factorMap;
    ArrayList<Integer> colors;
    ArrayList<Factor> factors;
    PieData data;



    public BIPageThree() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biViewModel = new ViewModelProvider(requireActivity()).get(BIViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.BiFragmentPageThreeBinding binding = DataBindingUtil.inflate(inflater, R.layout.bi_fragment_page_three, container, false);
        binding.setViewModel(biViewModel);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewmodelData();
        findViews(view);
        setBackToolbar();
        setupPieChart();
        setupPieChartColors();
        populatePieChart();
        animatePieChart();
        createFactorList();
        setRecyclerView();
    }

    private void getViewmodelData() {
        behavior = biViewModel.getBiBehavior();
        factorMap = new LinkedHashMap<>();
        factorMap = biViewModel.getBiFactorList(biViewModel.getBiSnapShotList(), behavior, biViewModel.getBiInspection());
    }

    private void findViews(View view) {
        backToolbar = view.findViewById(R.id.iback_toolbar);
        pieChart = view.findViewById(R.id.factor_piechart);
        factorRecyclerview = view.findViewById(R.id.factor_recyclerview);
    }

    private void setBackToolbar() {
        ((BIActivity)requireActivity()).biSetToolbar(backToolbar);
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setCenterTextColor(Color.DKGRAY);
        pieChart.setHoleRadius(35);
        pieChart.setTransparentCircleRadius(50);
        pieChart.setCenterText(biViewModel.getBiInspectionDisplay());
        pieChart.setCenterTextSize(16);
        pieChart.setDrawEntryLabels(false);
        Description description = pieChart.getDescription();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.getLegend().setEnabled(false);
    }

    private void setupPieChartColors() {
        colors = new ArrayList<>();
        for(int color: ColorTemplate.VORDIPLOM_COLORS){ colors.add(color); }
        for(int color: ColorTemplate.COLORFUL_COLORS){ colors.add(color); }
        for(int color: ColorTemplate.PASTEL_COLORS){ colors.add(color); }
        for(int color: ColorTemplate.LIBERTY_COLORS){ colors.add(color); }
        for(int color: ColorTemplate.JOYFUL_COLORS){ colors.add(color); }
    }

    private void populatePieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: factorMap.entrySet()){
            float floatValue = (float) entry.getValue();
            entries.add(new PieEntry(floatValue, entry.getKey()));
        }

        //write arrays to piechart
        PieDataSet dataSet = new PieDataSet(entries,biViewModel.getBiInspectionDisplay());
        dataSet.setColors(colors);
        data = new PieData(dataSet);

        data.setDrawValues(false);

        pieChart.setData(data);

    }

    private void animatePieChart() {
        //add animation
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    private void createFactorList() {
        factors = new ArrayList<>();

        for(int i = 0; i< data.getDataSet().getEntryCount(); i++){

            String factorString = data.getDataSet().getEntryForIndex(i).getLabel();

            float numTimes = data.getDataSet().getEntryForIndex(i).getValue();
            float percentage = (numTimes/data.getYValueSum())*100;
            String percentString = ((int) percentage) + " %";

            int color = data.getDataSet().getColor(i);

            Factor tempFactor = new Factor(color, factorString, (int) numTimes, percentString, behavior);
            factors.add(tempFactor);
        }

    }

    private void setRecyclerView() {
        LegendRecyclerAdapter distractionsRecyclerAdapter = new LegendRecyclerAdapter(factors);
        factorRecyclerview.setAdapter(distractionsRecyclerAdapter);
    }

}