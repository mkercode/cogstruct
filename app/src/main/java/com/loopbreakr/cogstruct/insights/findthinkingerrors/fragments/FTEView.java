package com.loopbreakr.cogstruct.insights.findthinkingerrors.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.databinding.FteFragmentViewBinding;
import com.loopbreakr.cogstruct.insights.behavioralinspection.activities.BIActivity;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.activities.FTEActivity;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.adapters.EntryRecyclerAdapter;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.models.FTEViewModel;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.objects.FTEDisplayObject;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.objects.FTEObject;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.viewpager.models.FTEVPViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class FTEView extends Fragment {
    private FteFragmentViewBinding binding;
    private FTEViewModel fteViewModel;
    private RecyclerView entryRecyclerview;
    private ProgressBar loadingBar;
    private Toolbar backToolbar;
    private List<FTEDisplayObject> entries;

    public FTEView() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fteViewModel = new ViewModelProvider(requireActivity()).get(FTEViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fte_fragment_view, container, false);
        binding.setViewModel(fteViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getEntries();
        findViews(view);
        setBackToolBar();
        setRecyclerView();
    }

    private void getEntries(){
        entries = new ArrayList<>();
         for(DocumentSnapshot snapshot : fteViewModel.getFteViewSnapShotList()){
             FTEObject currFTE = snapshot.toObject(FTEObject.class);
             if(currFTE.getThought().trim().equals(fteViewModel.getFteThought().trim())){
                 FTEDisplayObject currFteDisplay = new FTEDisplayObject(currFTE.getDateCreated(), currFTE.getUserId(), currFTE.getThought(), currFTE.getThinkingErrors(), snapshot);
                 entries.add(currFteDisplay);
             }
         }
    }

    private void findViews(View view) {
        entryRecyclerview = view.findViewById(R.id.fte_v_recyclerview);
        loadingBar = view.findViewById(R.id.loading_bar);
        backToolbar = view.findViewById(R.id.back_toolbar);
    }

    private void setBackToolBar() { ((FTEActivity)requireActivity()).fteSetToolbar(backToolbar);}

    private void setRecyclerView() {
        loadingBar.setVisibility(View.GONE);
        EntryRecyclerAdapter entryRecyclerAdapter = new EntryRecyclerAdapter(entries);
        entryRecyclerview.setAdapter(entryRecyclerAdapter);
        entryRecyclerAdapter.notifyDataSetChanged();

        //handle popup menu behavior fetched from adapter
        entryRecyclerAdapter.setOnItemClickListener((position, action) -> {
            if(action.equals("Delete")){
                DocumentSnapshot snapshot = entries.get(position).getSnapshot();
                snapshot.getReference().delete().addOnSuccessListener(aVoid -> { entries.remove(position);
                    entryRecyclerAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> ((FTEActivity)requireActivity()).handleFailure(e, "DELETE"));

            }
            else{
                fteViewModel.setFteThought(entries.get(position).getThought());
                FTEVPViewModel ftevpViewModel =  new ViewModelProvider(requireActivity()).get(FTEVPViewModel.class);
                ftevpViewModel.initalizeData(entries.get(position).getThinkingErrors());
                Navigation.findNavController(requireView()).navigate(R.id.action_FTEView_to_FTECreate);
            }
        });
    }

}