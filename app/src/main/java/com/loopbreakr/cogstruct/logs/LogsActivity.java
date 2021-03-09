package com.loopbreakr.cogstruct.logs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.firestore.DocumentSnapshot;
import com.loopbreakr.cogstruct.LoginActivity;
import com.loopbreakr.cogstruct.R;


public class LogsActivity extends AppCompatActivity {
    public final String failTAG = "FAILED OPERATION ";
    public final String successTAG = "SUCCEEDED OPERATION ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.logs_fragment_container);
        NavController navController = navHostFragment.getNavController();
    }

    public void logOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

}