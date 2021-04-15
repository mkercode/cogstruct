package com.loopbreakr.cogstruct.behavioralinspection.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.SimpleRecyclerAdapter;
import com.loopbreakr.cogstruct.behavioralinspection.activities.BIActivity;
import com.loopbreakr.cogstruct.behavioralinspection.models.BIViewModel;
import com.loopbreakr.cogstruct.identifybarriers.activities.IBActivity;
import com.loopbreakr.cogstruct.identifybarriers.models.IBViewModel;

import java.util.ArrayList;
import java.util.List;


public class BIPageOne extends Fragment {
    private BIViewModel biViewModel;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bi_fragment_page_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModelData();
    }

    //get data from firebase
    private void getViewModelData() {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseFirestore.getInstance()
                    .collection("forms")
                    .whereEqualTo("userId", auth.getCurrentUser().getUid())
                    .whereNotEqualTo("behavior", null)
                    .get().addOnFailureListener(e -> Log.e("ERROR QUERY", "setViewModelData: ",e ))
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        setViewModel(snapshotList);
                    });
    }

    //set the viewmodel data
    private void setViewModel(List<DocumentSnapshot> snapshots){
        biViewModel.setBiSnapshotList(snapshots);
    }

}