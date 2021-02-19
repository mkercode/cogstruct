package com.loopbreakr.brainstruct;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentUiHome extends Fragment {
    private Toolbar toolbar;



    public FragmentUiHome() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ui_home, container, false);

        toolbar = view.findViewById(R.id.homeToolBar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_logout));

        return view;

    }
    private void setToolbar() {


    }
}
