package com.loopbreakr.cogstruct.badbehaviors;

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


public class BBPageThree extends Fragment {
    private BBViewModel bbViewModel;
    private Button backButton, nextButton;
    private FloatingActionButton addDistraction;
    private RecyclerView distractionsRecyclerView;
    private List<String> distractionsList;


    public BBPageThree() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bbViewModel = new ViewModelProvider(requireActivity()).get(BBViewModel.class);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bb_fragment_page_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setRecyclerView();
        setButtons();
    }

    private void findViews(View view) {
        addDistraction = view.findViewById(R.id.bb_add_distraction);
        distractionsRecyclerView = view.findViewById(R.id.bb_distractions_recyclerview);
        backButton = view.findViewById(R.id.bb_page_three_back);
        nextButton = view.findViewById(R.id.bb_page_three_next);
    }

    private void setRecyclerView() {
        distractionsList = new ArrayList<>();
        distractionsList.addAll(bbViewModel.getBbDistractions());

        SimpleRecyclerAdapter distractionsRecyclerAdapter = new SimpleRecyclerAdapter(distractionsList);
        distractionsRecyclerView.setAdapter(distractionsRecyclerAdapter);

        distractionsRecyclerAdapter.setOnItemClickListener(position -> {
            distractionsList.remove(position);
            distractionsRecyclerAdapter.notifyDataSetChanged();
            bbViewModel.setBbDistractions(distractionsList);
        });
    }

    private void setButtons() {
        addDistraction.setOnClickListener(v ->{
            addToList(distractionsList);
            bbViewModel.setBbDistractions(distractionsList);
        });

        NavController controller = Navigation.findNavController(requireView());

        backButton.setOnClickListener(v ->{
            controller.popBackStack(R.id.BBPageTwo, true);
            controller.navigate(R.id.BBPageTwo);
        });
        nextButton.setOnClickListener(v -> controller.navigate(R.id.action_BBPageThree_to_BBPageFour));
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(getActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }
}