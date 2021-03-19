package com.loopbreakr.cogstruct.proscons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.SimpleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class PCPageThree extends Fragment {
    private PCViewModel pcViewModel;
    private RecyclerView prosRecyclerView, consRecyclerView;
    private Button backButton, reviewButton;
    private List<String> prosList, consList;
    private FloatingActionButton addPro, addCon;

    public PCPageThree() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pcViewModel = new ViewModelProvider(requireActivity()).get(PCViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.pc_fragment_page_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        initializeRecyclerViews();
        setButtons();
    }

    private void findViews(View view) {
        prosRecyclerView = view.findViewById(R.id.dont_change_pros_recyclerview);
        consRecyclerView = view.findViewById(R.id.dont_change_cons_recyclerview);
        backButton = view.findViewById(R.id.pc_page_three_back);
        reviewButton = view.findViewById(R.id.pc_review);
        addPro = view.findViewById(R.id.add_dont_change_pro);
        addCon = view.findViewById(R.id.add_dont_change_con);
    }

    private void initializeRecyclerViews() {
        prosList = new ArrayList<>();
        prosList.addAll(pcViewModel.getDontChangePros());
        consList = new ArrayList<>(pcViewModel.getDontChangePros());

        SimpleRecyclerAdapter prosRecyclerAdapter = new SimpleRecyclerAdapter(prosList);
        SimpleRecyclerAdapter consRecyclerAdapter = new SimpleRecyclerAdapter(consList);

        prosRecyclerView.setAdapter(prosRecyclerAdapter);
        consRecyclerView.setAdapter(consRecyclerAdapter);

        prosRecyclerAdapter.setOnItemClickListener(position -> {
            prosList.remove(position);
            prosRecyclerAdapter.notifyDataSetChanged();
        });
        consRecyclerAdapter.setOnItemClickListener(position -> {
            consList.remove(position);
            consRecyclerAdapter.notifyDataSetChanged();
        });
    }

    private void setButtons() {
        addPro.setOnClickListener(v -> {
            addToList(prosList);
            pcViewModel.setDontChangePros(prosList);
        });
        addCon.setOnClickListener(v -> {
            addToList(consList);
            pcViewModel.setDontChangeCons(consList);
        });

        NavController controller = Navigation.findNavController(requireView());
        backButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.PCPageTwo, true);
            controller.navigate(R.id.PCPageTwo);
        });
        reviewButton.setOnClickListener(v -> controller.navigate(R.id.action_PCPageThree_to_PCPageFour));
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(getActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }


}