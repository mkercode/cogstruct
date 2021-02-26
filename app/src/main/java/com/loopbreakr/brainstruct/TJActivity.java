package com.loopbreakr.brainstruct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

public class TJActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought_journal);

        NavHostFragment thoughtJournal = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.thought_journal_fragment);
        NavController thoughtJournalController = thoughtJournal.getNavController();
    }
}