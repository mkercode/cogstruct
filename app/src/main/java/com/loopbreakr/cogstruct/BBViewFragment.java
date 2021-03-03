package com.loopbreakr.cogstruct;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;


public class BBViewFragment extends Fragment {
    private Toolbar backToolbar;


    public BBViewFragment() {
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
        return inflater.inflate(R.layout.fragment_bb_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBackToolBar(view);
    }

    private void setBackToolBar(View view) {
        Toolbar backToolbar = view.findViewById(R.id.back_toolbar);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(backToolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowTitleEnabled(false);

        backToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        backToolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
    }
}