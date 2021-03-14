package com.loopbreakr.cogstruct.proscons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.loopbreakr.cogstruct.R;

public class PCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_activity);
        NavHostFragment prosCons = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.pros_cons_container);
        NavController thoughtJournalController = prosCons.getNavController();
    }
}