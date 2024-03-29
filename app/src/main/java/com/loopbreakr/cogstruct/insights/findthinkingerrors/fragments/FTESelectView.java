package com.loopbreakr.cogstruct.insights.findthinkingerrors.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.adapters.InsightsRecyclerAdapter;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.activities.FTEActivity;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.models.FTEViewModel;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.models.FTEVPViewModel;

import java.util.List;


public class FTESelectView extends Fragment {
    private FTEViewModel fteViewModel;
    private Toolbar backToolbar;
    private RecyclerView thoughtRecyclerview;
    private FloatingActionButton createEntry;
    private ProgressBar loadingBar;
    List<String> thoughtList;
    TextView noDataText;

    public FTESelectView() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fteViewModel = new ViewModelProvider(requireActivity()).get(FTEViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fte_fragment_select_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setBackToolBar();
        setButons();
        getViewModelData();
    }

    private void findViews(View view) {
        thoughtRecyclerview = view.findViewById(R.id.fte_sv_recyclerview);
        createEntry = view.findViewById(R.id.fte_create_entry);
        loadingBar = view.findViewById(R.id.loading_bar);
        backToolbar = view.findViewById(R.id.back_toolbar);
        noDataText = view.findViewById(R.id.no_data_text);
    }

    private void setBackToolBar() { ((FTEActivity)requireActivity()).fteSetToolbar(backToolbar);}

    private void setButons() {
        createEntry.setOnClickListener(v -> {
            (Navigation.findNavController(requireView())).navigate(R.id.action_FTESelectView_to_FTESelectCreate);
            restartData();
        });
        fteViewModel.setFteViewSnapshotList(null);
    }

    //clear any existing data from edit operations during the lifecycle from the viewmodels
    private void restartData() {
        fteViewModel.setFteViewSnapshotList(null);
        fteViewModel.setEditDocumentSnapshot(null);
        FTEVPViewModel ftevpViewModel = new ViewModelProvider(requireActivity()).get(FTEVPViewModel.class);
        ftevpViewModel.initializeData("CLEARING");
    }

    private void getViewModelData() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore.getInstance()
                .collection("thinkingErrors")
                .whereEqualTo("userId", auth.getCurrentUser().getUid()).orderBy("timeStamp", Query.Direction.DESCENDING)
                .get()
                .addOnFailureListener(e -> ((FTEActivity)requireActivity()).handleFailure(e, "FETCH"))
                .addOnSuccessListener(queryDocumentSnapshots -> setViewModel(queryDocumentSnapshots.getDocuments()));
    }

    //set the viewmodel data
    private void setViewModel(List<DocumentSnapshot> snapshots){
        loadingBar.setVisibility(View.GONE);
        if(snapshots==null || snapshots.isEmpty()){
            noDataText.setVisibility(View.VISIBLE);
        }
        else {
            noDataText.setVisibility(View.GONE);
            fteViewModel.setFteViewSnapshotList(snapshots);
            setRecyclerview(fteViewModel.getFteViewThoughtList());
        }
    }

    //create recyclerview from viewmodel data
    private void setRecyclerview(List<String> thoughts) {
        thoughtList = thoughts;
        InsightsRecyclerAdapter behaviorRecyclerAdapter = new InsightsRecyclerAdapter(thoughtList);

        thoughtRecyclerview.setAdapter(behaviorRecyclerAdapter);
        behaviorRecyclerAdapter.notifyDataSetChanged();

        behaviorRecyclerAdapter.setOnItemClickListener(position -> {
            fteViewModel.setFteThought(thoughtList.get(position));
            (Navigation.findNavController(requireView())).navigate(R.id.action_FTESelectView_to_FTEView);
        });
    }
}