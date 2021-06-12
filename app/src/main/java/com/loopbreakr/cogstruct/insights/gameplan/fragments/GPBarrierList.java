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
import com.loopbreakr.cogstruct.insights.gameplan.activities.GPActivity;
import com.loopbreakr.cogstruct.insights.gameplan.adapters.BarrierAdapter;
import com.loopbreakr.cogstruct.insights.gameplan.models.GPViewModel;
import com.loopbreakr.cogstruct.insights.gameplan.objects.Barrier;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class GPBarrierList extends Fragment {
    private GPViewModel gpViewModel;
    private Toolbar backToolbar;
    private Map<String, String> barrierMap;
    ArrayList<Barrier> barriers;
    private RecyclerView barrierRecyclerView;


    public GPBarrierList() {
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
        com.loopbreakr.cogstruct.databinding.GpFragmentBarrierListBinding binding = DataBindingUtil.inflate(inflater, R.layout.gp_fragment_barrier_list, container, false);
        binding.setViewModel(gpViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewmodelData();
        findViews(view);
        setBackToolbar();
        createBarrierList();
        setRecyclerView();
    }

    private void getViewmodelData() {
        barrierMap = new LinkedHashMap<>();
        barrierMap = gpViewModel.getGpBarrierMap(gpViewModel.getGpSnapShotList(), gpViewModel.getGpBehavior());
    }

    private void findViews(View view) {
        barrierRecyclerView = view.findViewById(R.id.barrier_recyclerview);
        backToolbar = view.findViewById(R.id.back_toolbar);
    }

    private void setBackToolbar() {
        ((GPActivity)requireActivity()).gpSetToolbar(backToolbar);
    }

    private void createBarrierList() {
        barriers = new ArrayList<>();
        for (Map.Entry<String, String> barrier : barrierMap.entrySet()) {
            Barrier tempBarrier = new Barrier(barrier.getKey(), barrier.getValue());
            barriers.add(tempBarrier);
        }
    }

    private void setRecyclerView() {
        BarrierAdapter barrierAdapter = new BarrierAdapter(barriers);
        barrierRecyclerView.setAdapter(barrierAdapter);
    }
}