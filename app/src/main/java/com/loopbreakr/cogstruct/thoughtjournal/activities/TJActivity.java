package com.loopbreakr.cogstruct.thoughtjournal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.ThoughtJournalObject;
import com.loopbreakr.cogstruct.thoughtjournal.models.TJViewModel;

import java.util.Arrays;
import java.util.List;


public class TJActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought_journal);


        NavHostFragment thoughtJournal = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.thought_journal_fragment);
        NavController thoughtJournalController = thoughtJournal.getNavController();

    }

    public void sendToFirestore(String location, String time, String people, String situation, String behavior, String emotion, float emotionRating, String emotionDetail, String thoughts){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ThoughtJournalObject thoughtJournalEntry = new ThoughtJournalObject(userId,location,time,people,situation,behavior,emotion,emotionRating,emotionDetail,thoughts);

        FirebaseFirestore.getInstance().collection("thoughtJournals").add(thoughtJournalEntry).addOnSuccessListener(documentReference ->
                Log.d("ADDING ENTRY...", "SUCCESS ADDING THOUGHT JOURNAL ENTRY: " + thoughtJournalEntry.toString()))
                .addOnFailureListener(e -> Log.e("ADDING ENTRY...", "FAILURE ADDING THOUGHT JOURNAL ENTRY: " + thoughtJournalEntry.toString() + "... ERROR: ", e));

    }
}