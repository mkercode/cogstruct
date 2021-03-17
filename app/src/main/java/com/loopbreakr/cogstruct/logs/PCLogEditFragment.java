package com.loopbreakr.cogstruct.logs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.proscons.PCRecyclerAdapter;
import com.loopbreakr.cogstruct.proscons.PCViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PCLogEditFragment extends Fragment {
    private LogsViewModel logsViewModel;
    private PCViewModel pcViewModel;
    private ArrayList<String> changeProsList, dontChangeProsList, changeConsList, dontChangeConsList;
    private FloatingActionButton addChangePro, addDontChangePro, addChangeCon, addDontChangeCon;
    private RecyclerView changeProsRecyclerView, changeConsRecyclerView, dontChangeProsRecyclerView, dontChangeConsRecyclerView;
    private EditText behaviorInput;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pc_log_edit, container, false);
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
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_done_edit_log) {
                setViewModelText();
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

        behaviorInput = view.findViewById(R.id.pc_behavior_edit_log);
        changeProsRecyclerView = view.findViewById(R.id.change_pros_log);
        dontChangeProsRecyclerView = view.findViewById(R.id.dont_change_pros_log);
        changeConsRecyclerView = view.findViewById(R.id.change_cons_log);
        dontChangeConsRecyclerView = view.findViewById(R.id.dont_change_cons_log);
    }

    private void getViewModelData() {

        behaviorInput.setText(pcViewModel.getPCBehavior());
        changeProsList =  pcViewModel.getDummyChangePros();
        dontChangeProsList = pcViewModel.getDummyDontChangePros();
        changeConsList = pcViewModel.getDummyChangeCons();
        dontChangeConsList = pcViewModel.getDummyDontChangeCons();
    }

    private void initializeRecyclerViews() {

        PCRecyclerAdapter changeProsRecyclerAdapter = new PCRecyclerAdapter(changeProsList);
        PCRecyclerAdapter dontChangeProsRecyclerAdapter = new PCRecyclerAdapter(dontChangeProsList);
        PCRecyclerAdapter changeConsRecyclerAdapter = new PCRecyclerAdapter(changeConsList);
        PCRecyclerAdapter dontChangeConsRecyclerAdapter = new PCRecyclerAdapter(dontChangeConsList);

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
            Log.d("Delete: ", dontChangeConsList.toString());
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
        new AlertDialog.Builder(getActivity()).setTitle("Add Entry")
                .setView(addEntryText)
                .setPositiveButton("Add", (dialog, which) ->
                        list.add(addEntryText.getText().toString())).setNegativeButton("Cancel", null).show();
    }

    private void setViewModelText() {
        pcViewModel.setPCBehavior(behaviorInput.getText().toString());
        pcViewModel.setDontChangeCons(dontChangeConsList);
        pcViewModel.setDontChangeCons(dontChangeConsList);
    }

    private void updateFirestoreDocument() {
        DocumentSnapshot logSnapshot = logsViewModel.getSnapshot();
        logSnapshot.getReference().update(
                "behavior", pcViewModel.getPCBehavior(),
                "changePros", pcViewModel.getChangeProsString(),
                "dontChangePros", pcViewModel.getDontChangeProsString(),
                "changeCons", pcViewModel.getChangeConsString(),
                "dontChangeCons", pcViewModel.getDontChangeConsString())
                .addOnFailureListener(e -> Log.e("UPDATING PROS AND CONS", "FAILED. ALL FIELDS OF " + logSnapshot.getData() , e)).addOnSuccessListener(aVoid -> Log.d("UPDATING PROS AND CONS", "SUCCESS. ALL FIELDS OF " + logSnapshot.getData()));

    }


}