package com.loopbreakr.cogstruct.logs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
    private LogsViewModel logsViewModel;
    private RecyclerView recyclerView;
    private LogsRecyclerAdapter logsRecyclerAdapter;
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

        //bind to adapter
        logsRecyclerAdapter = new LogsRecyclerAdapter(options, this);
        recyclerView.setAdapter(logsRecyclerAdapter);
        logsRecyclerAdapter.startListening();
    }

    //handle click behavior
    @Override
    public void clickLog(DocumentSnapshot snapshot) {
        int navId = 0;

        if (snapshot != null) {
            //save snapshot fields to object and get corresponding form fragment for that object in a case/switch statement
            if(snapshot.getString("formName").equals("Thought Journal"))
            {
                thoughtJournalLog = snapshot.toObject(ThoughtJournalObject.class);
                logsViewModel.setSnapshot(snapshot);
                logsViewModel.setForm(thoughtJournalLog);
                Log.i("LOGGER"," "+ ((ThoughtJournalObject) logsViewModel.getForm()).getDateCreated());
            }

            NavController controller = Navigation.findNavController(requireView());
            controller.navigate(navId);
        }
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
            return;
        }
        //recreate recyclerview when state changed
        createRecyclerView(firebaseAuth.getCurrentUser());
    }
}