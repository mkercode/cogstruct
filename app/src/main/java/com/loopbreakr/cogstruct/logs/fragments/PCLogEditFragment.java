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
import com.loopbreakr.cogstruct.databinding.LogsFragmentPcEditBinding;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;
import com.loopbreakr.cogstruct.proscons.models.PCViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class PCLogEditFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private PCViewModel pcViewModel;
    private ArrayList<String> changeProsList, dontChangeProsList, changeConsList, dontChangeConsList;
    private FloatingActionButton addChangePro, addDontChangePro, addChangeCon, addDontChangeCon;
    private RecyclerView changeProsRecyclerView, changeConsRecyclerView, dontChangeProsRecyclerView, dontChangeConsRecyclerView;

    public PCLogEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
        pcViewModel = new  ViewModelProvider(requireActivity()).get(PCViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.loopbreakr.cogstruct.databinding.LogsFragmentPcEditBinding binding = DataBindingUtil.inflate(inflater, R.layout.logs_fragment_pc_edit, container, false);
        binding.setViewModel(pcViewModel);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbar(view);
        findViews(view);
        getViewModelData();
        initializeRecyclerViews();
        setButtons();
    }

    private void setToolbar(View view) {
        NavController controller = Navigation.findNavController(requireView());
        Toolbar toolbar = view.findViewById(R.id.edit_logs_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> controller.navigateUp());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_log) {
                updateFirestoreDocument();
                controller.popBackStack(R.id.allLogsFragment, true);
                controller.navigate(R.id.allLogsFragment);
                return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    private void findViews(View view) {
        addChangePro = view.findViewById(R.id.add_log_change_pro);
        addDontChangePro = view.findViewById(R.id.add_log_dont_change_pro);
        addChangeCon = view.findViewById(R.id.add_log_change_con);
        addDontChangeCon = view.findViewById(R.id.add_log_dont_change_con);

        changeProsRecyclerView = view.findViewById(R.id.change_pros_log);
        dontChangeProsRecyclerView = view.findViewById(R.id.dont_change_pros_log);
        changeConsRecyclerView = view.findViewById(R.id.change_cons_log);
        dontChangeConsRecyclerView = view.findViewById(R.id.dont_change_cons_log);
    }

    private void getViewModelData() {
        changeProsList = new ArrayList<>();
        dontChangeProsList = new ArrayList<>();
        changeConsList = new ArrayList<>();
        dontChangeConsList = new ArrayList<>();

        changeProsList.addAll(pcViewModel.getChangePros());
        dontChangeProsList.addAll(pcViewModel.getDontChangePros());
        changeConsList.addAll(pcViewModel.getChangeCons());
         dontChangeConsList.addAll(pcViewModel.getDontChangeCons());
    }

    private void initializeRecyclerViews() {

        SimpleRecyclerAdapter changeProsRecyclerAdapter = new SimpleRecyclerAdapter(changeProsList);
        SimpleRecyclerAdapter dontChangeProsRecyclerAdapter = new SimpleRecyclerAdapter(dontChangeProsList);
        SimpleRecyclerAdapter changeConsRecyclerAdapter = new SimpleRecyclerAdapter(changeConsList);
        SimpleRecyclerAdapter dontChangeConsRecyclerAdapter = new SimpleRecyclerAdapter(dontChangeConsList);

        changeProsRecyclerView.setAdapter(changeProsRecyclerAdapter);
        dontChangeProsRecyclerView.setAdapter(dontChangeProsRecyclerAdapter);
        changeConsRecyclerView.setAdapter(changeConsRecyclerAdapter);
        dontChangeConsRecyclerView.setAdapter(dontChangeConsRecyclerAdapter);

        changeProsRecyclerAdapter.setOnItemClickListener(position -> {
            changeProsList.remove(position);
            changeProsRecyclerAdapter.notifyDataSetChanged();
        });
        dontChangeProsRecyclerAdapter.setOnItemClickListener(position -> {
            dontChangeProsList.remove(position);
            dontChangeProsRecyclerAdapter.notifyDataSetChanged();
        });
        changeConsRecyclerAdapter.setOnItemClickListener(position -> {
            changeConsList.remove(position);
            changeConsRecyclerAdapter.notifyDataSetChanged();
        });
        dontChangeConsRecyclerAdapter.setOnItemClickListener(position -> {
            dontChangeConsList.remove(position);
            dontChangeConsRecyclerAdapter.notifyDataSetChanged();
        });
    }

    private void setButtons() {
        addChangePro.setOnClickListener(v ->{
            addToList(changeProsList);
            pcViewModel.setChangePros(changeProsList);
        });
        addDontChangePro.setOnClickListener( v->{
            addToList(dontChangeProsList);
            pcViewModel.setDontChangePros(dontChangeProsList);
        });
        addChangeCon.setOnClickListener(v ->{
            addToList(changeConsList);
            pcViewModel.setChangeCons(changeConsList);
        });
        addDontChangeCon.setOnClickListener(v ->{
            addToList(dontChangeConsList);
            pcViewModel.setDontChangeCons(dontChangeConsList);
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
                "behavior", pcViewModel.getPCBehavior(),
                "changePros", pcViewModel.getChangeProsString(),
                "dontChangePros", pcViewModel.getDontChangeProsString(),
                "changeCons", pcViewModel.getChangeConsString(),
                "dontChangeCons", pcViewModel.getDontChangeConsString())
                .addOnFailureListener(e -> ((LogsActivity)requireActivity()).handleFailure(e, "EDIT"));

    }


}