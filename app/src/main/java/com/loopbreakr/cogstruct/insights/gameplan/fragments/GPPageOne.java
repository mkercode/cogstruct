package com.loopbreakr.cogstruct.insights.gameplan.fragments;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.adapters.InsightsRecyclerAdapter;
import com.loopbreakr.cogstruct.insights.gameplan.activities.GPActivity;
import com.loopbreakr.cogstruct.insights.gameplan.models.GPViewModel;

import java.util.List;


public class GPPageOne extends Fragment {
    private GPViewModel gpViewModel;
    private Toolbar backToolbar;
    private RecyclerView behaviorRecyclerView;
    private ProgressBar loadingBar;
    List<String> behaviorList;
    TextView noDataText;

    public GPPageOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gpViewModel = new ViewModelProvider(requireActivity()).get(GPViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.gp_fragment_page_one, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setBackToolBar();
        getViewModelData();
    }

    private void findViews(View view) {
        behaviorRecyclerView = view.findViewById(R.id.gp_behavior_recyclerview);
        backToolbar = view.findViewById(R.id.back_toolbar);
        loadingBar = view.findViewById(R.id.loading_bar);
        noDataText = view.findViewById(R.id.no_data_text);
    }

    private void setBackToolBar() {
        ((GPActivity)requireActivity()).gpSetToolbar(backToolbar);
    }

    //get data from firebase
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
            gpViewModel.setGpSnapshotList(snapshots);
            setRecyclerview(gpViewModel.getGpBehaviorList());
        }
    }

    //create recyclerview from viewmodel data
    private void setRecyclerview(List<String> behaviorsList) {
        behaviorList = behaviorsList;
        InsightsRecyclerAdapter behaviorRecyclerAdapter = new InsightsRecyclerAdapter(behaviorList);

        behaviorRecyclerView.setAdapter(behaviorRecyclerAdapter);
        behaviorRecyclerAdapter.notifyDataSetChanged();

        behaviorRecyclerAdapter.setOnItemClickListener(position -> {
            gpViewModel.setGpBehavior(behaviorList.get(position));
            Log.d("BEHAVIOR", "setRecyclerview: " + gpViewModel.getGpBehavior());
            (Navigation.findNavController(requireView())).navigate(R.id.action_GPPageOne_to_GPPageTwo);
        });
    }
}