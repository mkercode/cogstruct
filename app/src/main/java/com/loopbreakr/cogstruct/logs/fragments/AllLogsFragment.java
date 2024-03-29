package com.loopbreakr.cogstruct.logs.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.logs.activities.LogsActivity;
import com.loopbreakr.cogstruct.logs.models.LogsViewModel;
import com.loopbreakr.cogstruct.logs.adapters.LogsRecyclerAdapter;
import com.loopbreakr.cogstruct.logs.objects.LogsPreview;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

import java.util.Objects;

public class AllLogsFragment extends Fragment implements FirebaseAuth.AuthStateListener, LogsRecyclerAdapter.LogsListener {
    private LogsViewModel logsViewModel;
    private RecyclerView recyclerView;
    private LogsRecyclerAdapter logsRecyclerAdapter;
    private Button allLogs, tjLogs, pcLogs, highLogs, bbLogs, ibLogs;
    private FirebaseUser user;
    ThoughtJournalObject thoughtJournalLog;

    public AllLogsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logsViewModel = new ViewModelProvider(requireActivity()).get(LogsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.logs_fragment_all, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setButtons();
    }

    //recreate RV on navigation change
    @Override
    public void onResume() {
        super.onResume();
        String form = logsViewModel.getFormFilter();
        //ensure RV is recreated on navigation change is pressed by clearing the form filter
        logsViewModel.setFormFilter("");
        createRecyclerView(user,form);
    }

    private void findViews(View view) {
        //find RV to populate
        recyclerView = view.findViewById(R.id.all_logs_recyclerview);
        //find buttons to filter
        allLogs = view.findViewById(R.id.all_logs);
        tjLogs = view.findViewById(R.id.tj_logs);
        pcLogs = view.findViewById(R.id.pc_logs);
        ibLogs = view.findViewById(R.id.ib_logs);
        bbLogs = view.findViewById(R.id.bb_logs);
        highLogs = view.findViewById(R.id.high_logs);
    }

    private void setButtons() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        allLogs.setOnClickListener(v-> createRecyclerView(user,"all"));
        tjLogs.setOnClickListener(v -> createRecyclerView(user,"Thought Journal"));
        pcLogs.setOnClickListener(v -> createRecyclerView(user,"Pros and Cons"));
        ibLogs.setOnClickListener(v -> createRecyclerView(user,"Identify Barriers"));
        bbLogs.setOnClickListener(v -> createRecyclerView(user, "Bad Behaviors"));
        highLogs.setOnClickListener(v -> createRecyclerView(user, "How'd I Get Here?"));
    }

    public void createRecyclerView(FirebaseUser user, String filterName){
        if(!filterName.equals(logsViewModel.getFormFilter()) || logsViewModel.isRunStatus()){
            logsViewModel.setFormFilter(filterName);
            Query query;
            if(filterName.equals("all")){
                query = FirebaseFirestore.getInstance()
                        .collection("forms")
                        .whereEqualTo("userId", user.getUid())
                        .orderBy("timeStamp", Query.Direction.DESCENDING);
                if(logsViewModel.isRunStatus()){
                    logsViewModel.setRunStatus(false);
                }
            }
            else{
                query = FirebaseFirestore.getInstance()
                        .collection("forms")
                        .whereEqualTo("userId", user.getUid())
                        .whereEqualTo("formName", filterName)
                        .orderBy("timeStamp", Query.Direction.DESCENDING);
            }

            //build firestore recycler options
            FirestoreRecyclerOptions<LogsPreview> options = new FirestoreRecyclerOptions.Builder<LogsPreview>()
                    .setQuery(query, LogsPreview.class)
                    .build();

            //bind to adapter
            logsRecyclerAdapter = new LogsRecyclerAdapter(options, this);
            recyclerView.setAdapter(logsRecyclerAdapter);
            logsRecyclerAdapter.startListening();
        }
    }

    //handle click behavior
    @Override
    public void clickLog(DocumentSnapshot snapshot) {

        //clear data
        logsViewModel.setSnapshot(null);
        if (snapshot != null) {
            NavController controller = Navigation.findNavController(requireView());
            controller.navigate(getSnapshotData(snapshot));
        }
    }

    //save snapshot fields to object and get corresponding form fragment for that object in a case/switch statement
    private int getSnapshotData(DocumentSnapshot snapshot){
        int navId = 0;
        switch (Objects.requireNonNull(snapshot.getString("formName"))){
            case "Thought Journal":
                thoughtJournalLog = snapshot.toObject(ThoughtJournalObject.class);
                logsViewModel.setSnapshot(snapshot);
                navId = R.id.action_allLogsFragment_to_tjLogFragment;
                break;

            case "Pros and Cons":
                logsViewModel.setSnapshot(snapshot);
                navId = R.id.action_allLogsFragment_to_prosConsLogFragment;
                break;

            case "How'd I Get Here?":
                logsViewModel.setSnapshot(snapshot);
                navId = R.id.action_allLogsFragment_to_HIGHLogFragment;
                break;

            case "Bad Behaviors":
                logsViewModel.setSnapshot(snapshot);
                navId = R.id.action_allLogsFragment_to_BBLogFragment;
                break;

            case "Identify Barriers":
                logsViewModel.setSnapshot(snapshot);
                navId = R.id.action_allLogsFragment_to_IBLogFragment;
                break;

        }
        return navId;
    }

    //listen for auth states changed
    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
        if(logsRecyclerAdapter != null){
            logsRecyclerAdapter.stopListening();
        }
    }

    //handle behavior on auth state change
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        //if token expires
        if(firebaseAuth.getCurrentUser() == null){
            ((LogsActivity)requireActivity()).logOut();
        }
        //recreate recyclerview when state changed
        createRecyclerView(firebaseAuth.getCurrentUser(), logsViewModel.getFormFilter());
    }


}