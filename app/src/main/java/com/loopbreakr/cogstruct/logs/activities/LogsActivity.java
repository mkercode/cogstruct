package com.loopbreakr.cogstruct.logs.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.loopbreakr.cogstruct.home.activities.LoginActivity;
import com.loopbreakr.cogstruct.R;


public class LogsActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logs_activity);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.logs_fragment_container);
        navController = navHostFragment.getNavController();
    }

    public void logOut(){
        Toast.makeText(this,"Network Error!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
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
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(firebaseAuth.getCurrentUser() == null){
            logOut();
        }
    }

    public void handleFailure(Throwable e, String type){
        //send exception to firebase
        FirebaseCrashlytics.getInstance().recordException(e);

        //log exception to user, navigate back if fetching
        if(type.equals("FETCH")){
            Toast.makeText(this, "Error, could not fetch log.", Toast.LENGTH_SHORT).show();
            navController.popBackStack(R.id.allLogsFragment, true);
            navController.navigate(R.id.allLogsFragment);
        }
        else if(type.equals("EDIT")){
            Toast.makeText(this, "Error, could not edit log.", Toast.LENGTH_SHORT).show();
        }

    }
}