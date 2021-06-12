package com.loopbreakr.cogstruct.logs.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.adapters.SimpleRecyclerAdapter;
import com.loopbreakr.cogstruct.badbehaviors.models.BBViewModel;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class BBLogEditOne extends Fragment {
    private LogsViewModel logsViewModel;
    private BBViewModel bbViewModel;
    private FloatingActionButton addEnvironmental;
    private RecyclerView environmentalsRecyclerView;
    private List<String> environmentalsList;


    public BBLogEditOne() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        bbViewModel = new ViewModelProvider(requireActivity()).get(BBViewModel.class);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.LogsFragmentBbEditOneBinding binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_bb_edit_one, container, false);
        binding.setViewModel(bbViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        findViews(view);
        setRecyclerView();
        setButtons();
    }

    private void setToolbar(View view) {
        NavController controller = Navigation.findNavController(requireView());
        Toolbar toolbar = view.findViewById(R.id.edit_bb_logs_toolbar_one);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> controller.navigateUp());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_high) {
                updateFirestoreDocument();
                controller.popBackStack(R.id.allLogsFragment, true);
                controller.navigate(R.id.allLogsFragment);
                return true;
            }
            else if(item.getItemId() == R.id.action_next_edit_high){
                controller.navigate(R.id.action_BBLogEditOne_to_BBLogEditTwo);
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void findViews(View view) {
        addEnvironmental = view.findViewById(R.id.bb_log_add_environmental);
        environmentalsRecyclerView = view.findViewById(R.id.bb_log_environmentals_recyclerview);
    }

    private void setRecyclerView() {
        environmentalsList = new ArrayList<>();
        environmentalsList.addAll(bbViewModel.getBbEnvironmentals());

        SimpleRecyclerAdapter environmentalsRecyclerAdapter = new SimpleRecyclerAdapter(environmentalsList);
        environmentalsRecyclerView.setAdapter(environmentalsRecyclerAdapter);

        environmentalsRecyclerAdapter.setOnItemClickListener(position -> {
            environmentalsList.remove(position);
            environmentalsRecyclerAdapter.notifyDataSetChanged();
        });
    }

    private void setButtons() {
        addEnvironmental.setOnClickListener(v ->{
            addToList(environmentalsList);
            bbViewModel.setBbEnvironmentals(environmentalsList);
        });
    }

    private void addToList(List<String> list) {
        EditText addEntryText = new EditText(getActivity());
        new AlertDialog.Builder(requireActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }

    private void updateFirestoreDocument() {
        DocumentSnapshot logSnapshot = logsViewModel.getSnapshot();
        logSnapshot.getReference().update(
                "behavior", bbViewModel.getBbBehavior(),
                "environmentals", bbViewModel.getBbEnvironmentalsString(),
                "distractions", bbViewModel.getBbDistractionsString(),
                "solutions", bbViewModel.getBbSolutionsString())
                .addOnFailureListener(e -> ((LogsActivity)requireActivity()).handleFailure(e, "EDIT"));

    }
}