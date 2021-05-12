package com.loopbreakr.cogstruct.insights.findthinkingerrors.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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

import java.util.List;


public class FTESelectCreate extends Fragment {
    private FTEViewModel fteViewModel;
    private Toolbar backToolbar;
    private RecyclerView thoughtRecyclerview;
    private ProgressBar loadingBar;
    List<String> thoughtList;
    TextView noDataText;

    public FTESelectCreate() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fteViewModel = new ViewModelProvider(requireActivity()).get(FTEViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fte_fragment_select_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setBackToolBar();
        getViewModelData();
    }

    private void findViews(View view) {
        thoughtRecyclerview = view.findViewById(R.id.fte_sc_recyclerview);
        loadingBar = view.findViewById(R.id.loading_bar);
        backToolbar = view.findViewById(R.id.back_toolbar);
        noDataText = view.findViewById(R.id.no_data_text);
    }

    private void setBackToolBar() { ((FTEActivity)requireActivity()).fteSetToolbar(backToolbar);}

    private void getViewModelData() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore.getInstance()
                .collection("forms")
                .whereEqualTo("userId", auth.getCurrentUser().getUid()).orderBy("timeStamp", Query.Direction.DESCENDING)
                .get().addOnFailureListener(e -> Log.e("ERROR QUERY", "setViewModelData: ",e ))
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
            fteViewModel.setFteCreateSnapshotList(snapshots);
            setRecyclerview(fteViewModel.getFteThoughtCreateList());
        }
    }

    //create recyclerview from viewmodel data
    private void setRecyclerview(List<String> behaviorsList) {
        thoughtList = behaviorsList;
        InsightsRecyclerAdapter behaviorRecyclerAdapter = new InsightsRecyclerAdapter(thoughtList);

        thoughtRecyclerview.setAdapter(behaviorRecyclerAdapter);
        behaviorRecyclerAdapter.notifyDataSetChanged();

        behaviorRecyclerAdapter.setOnItemClickListener(position -> {
            fteViewModel.setFteThought(thoughtList.get(position));
            (Navigation.findNavController(requireView())).navigate(R.id.action_FTESelectCreate_to_FTECreate);
        });
    }
}