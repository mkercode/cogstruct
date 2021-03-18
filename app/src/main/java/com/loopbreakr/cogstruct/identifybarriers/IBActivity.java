package com.loopbreakr.cogstruct.identifybarriers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import com.loopbreakr.cogstruct.R;

public class IBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ib_activity);
        NavHostFragment identifyBarriers = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.ib_container);
        NavController identifyBarriersController = identifyBarriers.getNavController();
    }
}