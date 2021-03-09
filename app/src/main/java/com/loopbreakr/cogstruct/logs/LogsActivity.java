package com.loopbreakr.cogstruct.logs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.loopbreakr.cogstruct.LoginActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.logs.adapters.LogsRecyclerAdapter;
import com.loopbreakr.cogstruct.logs.objects.LogsPreview;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

import java.util.List;

public class LogsActivity extends AppCompatActivity  implements FirebaseAuth.AuthStateListener, LogsRecyclerAdapter.LogsListener{
    private RecyclerView recyclerView;
    private LogsRecyclerAdapter logsRecyclerAdapter;
    ThoughtJournalObject thoughtJournalLog;
    public final String failTAG = "FAILED OPERATION ";
    public final String successTAG = "SUCCEEDED OPERATION ";
    public List<DocumentSnapshot> snapshotList;
    public List<DocumentSnapshot> documentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.all_logs_recyclerview);
    }


    //keep user signed in by attatching authstatelistener to the lifecycle methods
    @Override
    protected void onStart() {
        super.onStart();
        //listen for auth state changed
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
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
            logOut();
            return;
        }
        //recreate recyclerview when state changed
        createRecyclerView(firebaseAuth.getCurrentUser());
    }

    private void createRecyclerView(FirebaseUser user){
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
    public void clickLog(DocumentSnapshot snapshot) { ;
        if (snapshot != null) {
            if(snapshot.get("formName").equals("Thought Journal"))
            {
                thoughtJournalLog = snapshot.toObject(ThoughtJournalObject.class);
                Log.i("LOGGER","Emotion "+ thoughtJournalLog);
            }
        }
    }
    private void logOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}