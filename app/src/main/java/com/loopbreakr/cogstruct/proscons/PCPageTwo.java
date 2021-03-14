package com.loopbreakr.cogstruct.proscons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopbreakr.cogstruct.R;

import java.util.ArrayList;
import java.util.List;


public class PCPageTwo extends Fragment {
    private RecyclerView prosRecyclerView, consRecyclerView;
    private Button backButton, nextButton;
    private PCRecyclerAdapter prosRecyclerAdapter, consRecyclerAdapter;
    private List<String> prosList, consList;
    private FloatingActionButton addPro, addCon;

    public PCPageTwo() {
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
        return inflater.inflate(R.layout.pc_fragment_page_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        initializeRecyclerViews();
        setButtons();
    }

    private void findViews(View view) {
        prosRecyclerView = view.findViewById(R.id.change_pros_recyclerview);
        consRecyclerView = view.findViewById(R.id.change_cons_recyclerview);
        backButton = view.findViewById(R.id.pc_page_two_back);
        nextButton = view.findViewById(R.id.pc_page_two_next);
        addPro = view.findViewById(R.id.add_change_pro);
        addCon = view.findViewById(R.id.add_change_con);
    }

    private void initializeRecyclerViews() {
        prosList = new ArrayList<>();
        consList = new ArrayList<>();

        prosRecyclerAdapter = new PCRecyclerAdapter(prosList);
        consRecyclerAdapter = new PCRecyclerAdapter(consList);
        prosRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        consRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        prosRecyclerView.setAdapter(prosRecyclerAdapter);
        consRecyclerView.setAdapter(consRecyclerAdapter);

        prosList.add("hello");
        prosList.add("hi");
        prosList.add("howdy");
        prosList.add("yo");
    }

    private void setButtons() {
        NavController controller = Navigation.findNavController(requireView());
        backButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.PCPageOne, true);
            controller.navigate(R.id.PCPageOne);
        });

        nextButton.setOnClickListener(v -> controller.navigate(R.id.action_PCPageTwo_to_PCPageThree));
    }
}