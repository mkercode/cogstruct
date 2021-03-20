package com.loopbreakr.cogstruct.howdigethere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.loopbreakr.cogstruct.R;

public class HIGHActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_activity);
        NavHostFragment howdIGetHere = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.high_container);
        NavController thowdIGetHereController = howdIGetHere.getNavController();
    }
}