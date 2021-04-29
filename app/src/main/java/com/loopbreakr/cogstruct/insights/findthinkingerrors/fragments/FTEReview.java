package com.loopbreakr.cogstruct.insights.findthinkingerrors.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.badbehaviors.activities.BBActivity;
import com.loopbreakr.cogstruct.databinding.FteFragmentReviewBinding;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.activities.FTEActivity;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.models.FTEViewModel;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.models.FTEVPViewModel;

import org.jetbrains.annotations.NotNull;


public class FTEReview extends Fragment {
    private FTEVPViewModel ftevpViewModel;
    private FTEViewModel fteViewModel;
    private FteFragmentReviewBinding binding;
    private Button editButton, submitButton;
    private String teList;
    private String thought;
    private NavController controller;


    public FTEReview() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ftevpViewModel = new ViewModelProvider(requireActivity()).get(FTEVPViewModel.class);
        fteViewModel = new ViewModelProvider(requireActivity()).get(FTEViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fte_fragment_review, container, false);
        binding.setViewModel(ftevpViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        transferViewModelData();
        findViews(view);
        setButtons();
    }

    private void transferViewModelData() {
        teList = ftevpViewModel.getCheckedNameString();
        ftevpViewModel.setThought(fteViewModel.getFteThought());
        thought = ftevpViewModel.getThought();
    }

    private void findViews(View view) {
        editButton = view.findViewById(R.id.fte_edit);
        submitButton = view.findViewById(R.id.fte_submit);
    }

    private void setButtons() {
        controller = Navigation.findNavController(requireView());
        editButton.setOnClickListener(v ->{
                controller.popBackStack(R.id.FTECreate, true);
                controller.navigate(R.id.FTECreate);
        });
        submitButton.setOnClickListener(v ->{

            //logic to only submit data if the user has chosen atleast one thinking error
            if(teList.isEmpty() || teList.equals(null)){
                Toast.makeText(requireActivity().getApplicationContext(), "Please choose a thinking error!", Toast.LENGTH_SHORT).show();
            }

            else{
                //check if editing a document, if so update and if not create new
                if (fteViewModel.getEditDocumentSnapshot() == null){
                    ((FTEActivity)requireActivity()).sendToFirestore(thought, teList);
                    Toast.makeText(requireActivity().getApplicationContext(), "Saved in entries", Toast.LENGTH_SHORT).show();
                }
                else{
                    fteViewModel.getEditDocumentSnapshot().getReference().update(
                            "thinkingErrors", teList)
                            .addOnFailureListener(e -> Log.e("UPDATING FTE", "FAILED. ALL FIELDS OF " , e)).addOnSuccessListener(aVoid -> Log.d("UPDATING IB LOG", "SUCCESS"));
                    Toast.makeText(requireActivity().getApplicationContext(), "Updated entry", Toast.LENGTH_SHORT).show();
                }
                restartActivity();
            }
        });
    }

    private void restartActivity(){
        fteViewModel.setFteCreateSnapshotList(null);
        fteViewModel.setFteViewSnapshotList(null);
        fteViewModel.setEditDocumentSnapshot(null);
        ftevpViewModel.initalizeData("CLEARING");
        controller.popBackStack(R.id.FTESelectView, true);
        controller.navigate(R.id.FTESelectView);
    }
}