package com.loopbreakr.cogstruct.home.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.activities.FTEActivity;
import com.loopbreakr.cogstruct.insights.gameplan.activities.GPActivity;

import java.util.Objects;


public class FTEViewFragment extends Fragment {

    public FTEViewFragment() {
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
        return inflater.inflate(R.layout.home_fragment_fte_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBackToolBar(view);
        setBeginButton(view);
    }

    private void setBackToolBar(View view) {
        Toolbar backToolbar = view.findViewById(R.id.back_toolbar);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(backToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);

        backToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        backToolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
    }
    private void setBeginButton(View view) {
        Button beginButton = view.findViewById(R.id.fte_begin_button);
        beginButton.setOnClickListener(v -> openActivity());
    }

    private void openActivity(){
        Intent intent = new Intent(this.requireActivity(), FTEActivity.class);
        startActivity(intent);
    }
}