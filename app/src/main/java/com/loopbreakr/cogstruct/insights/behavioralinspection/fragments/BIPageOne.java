package com.loopbreakr.cogstruct.insights.behavioralinspection.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.insights.behavioralinspection.activities.BIActivity;
import com.loopbreakr.cogstruct.insights.behavioralinspection.adapters.InsightsRecyclerAdapter;
import com.loopbreakr.cogstruct.insights.behavioralinspection.models.BIViewModel;
import java.util.List;


public class BIPageOne extends Fragment {
    private BIViewModel biViewModel;
    private Toolbar backToolbar;
    private RecyclerView behaviorRecyclerView;
    private ProgressBar loadingBar;
    List<String> behaviorList;
    TextView noDataText;

    public BIPageOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biViewModel = new ViewModelProvider(requireActivity()).get(BIViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bi_fragment_page_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setBackToolBar();
        getViewModelData();
    }

    private void findViews(View view) {
        behaviorRecyclerView = view.findViewById(R.id.bi_behavior_recyclerview);
        backToolbar = view.findViewById(R.id.back_toolbar);
        loadingBar = view.findViewById(R.id.loading_bar);
        noDataText = view.findViewById(R.id.no_data_text);
    }

    private void setBackToolBar() {
        ((BIActivity)requireActivity()).biSetToolbar(backToolbar);
    }

    //get data from firebase
    private void getViewModelData() {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseFirestore.getInstance()
                    .collection("forms")
                    .whereEqualTo("userId", auth.getCurrentUser().getUid())
                    .get().addOnFailureListener(e -> Log.e("ERROR QUERY", "setViewModelData: ",e ))
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        setViewModel(snapshotList);
                    });
    }

    //set the viewmodel data
    private void setViewModel(List<DocumentSnapshot> snapshots){
        loadingBar.setVisibility(View.GONE);
        if(snapshots==null || snapshots.isEmpty()){
            noDataText.setVisibility(View.VISIBLE);
        }
        else {
            noDataText.setVisibility(View.GONE);
            biViewModel.setBiSnapshotList(snapshots);
            setRecyclerview(biViewModel.getBiBehaviorList());
        }
    }

    //create recyclerview from viewmodel data
    private void setRecyclerview(List<String> behaviorsList) {
        behaviorList = behaviorsList;
        InsightsRecyclerAdapter behaviorRecyclerAdapter = new InsightsRecyclerAdapter(behaviorList);

        behaviorRecyclerView.setAdapter(behaviorRecyclerAdapter);
        behaviorRecyclerAdapter.notifyDataSetChanged();

        behaviorRecyclerAdapter.setOnItemClickListener(position -> {
            biViewModel.setBiBehavior(behaviorList.get(position));
            (Navigation.findNavController(requireView())).navigate(R.id.action_BIPageOne_to_BIPageTwo);
        });
    }

}