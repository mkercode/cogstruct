package com.loopbreakr.cogstruct.logs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.logs.adapters.LogsRecyclerAdapter;
import com.loopbreakr.cogstruct.logs.objects.LogsPreview;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

public class AllLogsFragment extends Fragment implements FirebaseAuth.AuthStateListener, LogsRecyclerAdapter.LogsListener {
    private RecyclerView recyclerView;
    private LogsRecyclerAdapter logsRecyclerAdapter;
    ThoughtJournalObject thoughtJournalLog;
    public final String failTAG = "FAILED OPERATION ";
    public final String successTAG = "SUCCEEDED OPERATION ";

    public AllLogsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_logs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView(view);

    }

    private void setRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.all_logs_recyclerview);
    }

    public void createRecyclerView(FirebaseUser user){
        Query query = FirebaseFirestore.getInstance()
                .collection("forms")
                .whereEqualTo("userId", user.getUid());

        //build firestore recycler options
        FirestoreRecyclerOptions<LogsPreview> options = new FirestoreRecyclerOptions.Builder<LogsPreview>()
                .setQuery(query, LogsPreview.class)
                .build();

        logsRecyclerAdapter = new LogsRecyclerAdapter(options, this);
        recyclerView.setAdapter(logsRecyclerAdapter);
        //listen for updates in realtime to add to recyclerview
        logsRecyclerAdapter.startListening();
    }

    @Override
    public void clickLog(DocumentSnapshot snapshot) {
        if (snapshot != null) {
            if(snapshot.get("formName").equals("Thought Journal"))
            {
                thoughtJournalLog = snapshot.toObject(ThoughtJournalObject.class);
                Log.i("LOGGER","Emotion "+ thoughtJournalLog);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //listen for auth state changed
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

    //monitor if the json web token expires
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(firebaseAuth.getCurrentUser() == null){
            ((LogsActivity)requireActivity()).logOut();
            return;
        }
        //recreate recyclerview when state changed
        createRecyclerView(firebaseAuth.getCurrentUser());
    }
}