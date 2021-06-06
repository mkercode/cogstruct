package com.loopbreakr.cogstruct.insights.gameplan.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.gameplan.activities.GPActivity;
import com.loopbreakr.cogstruct.insights.gameplan.models.GPViewModel;

public class GPProConsSelect extends Fragment {
    private GPViewModel gpViewModel;
    private Toolbar backToolbar;
    private Button changePros, dontChangePros, changeCons, dontChangeCons;

    public GPProConsSelect() {
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
        return inflater.inflate(R.layout.gp_fragment_pc_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
        setBackToolBar();
    }

    private void findViews(View view) {
        backToolbar = view.findViewById(R.id.back_toolbar);
        changePros = view.findViewById(R.id.gp_change_pros);
        changeCons = view.findViewById(R.id.gp_change_cons);
        dontChangePros = view.findViewById(R.id.gp_dont_change_pros);
        dontChangeCons =  view.findViewById(R.id.gp_dont_change_cons);
    }

    private void setButtons() {
        changePros.setOnClickListener(v -> {
            gpViewModel.setGpChange("Change Pros");
            (Navigation.findNavController(requireView())).navigate(R.id.action_GPProConsSelect_to_GPSimpleList); });
        changeCons.setOnClickListener(v -> {
            gpViewModel.setGpChange("Change Cons");
            (Navigation.findNavController(requireView())).navigate(R.id.action_GPProConsSelect_to_GPSimpleList); });
        dontChangePros.setOnClickListener(v -> {
            gpViewModel.setGpChange("Don't Change Pros");
            (Navigation.findNavController(requireView())).navigate(R.id.action_GPProConsSelect_to_GPSimpleList);});
        dontChangeCons.setOnClickListener(v -> {
            gpViewModel.setGpChange("Don't Change Cons");
            (Navigation.findNavController(requireView())).navigate(R.id.action_GPProConsSelect_to_GPSimpleList);});
    }

    private void setBackToolBar() {
        ((GPActivity)requireActivity()).gpSetToolbar(backToolbar);
    }

}