package com.loopbreakr.brainstruct;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentActivityView extends Fragment {
    private Toolbar toolbar;


    public FragmentActivityView() {
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
        View view = inflater.inflate(R.layout.fragment_activity_view, container, false);
        setToolbar(view);
        return view;
    }

    private void setToolbar(View view) {
        toolbar = view.findViewById(R.id.homeToolBar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_logout));
    }
}