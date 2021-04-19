package com.loopbreakr.cogstruct.insights.behavioralinspection.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.loopbreakr.cogstruct.home.activities.LoginActivity;
import com.loopbreakr.cogstruct.R;

import java.util.Objects;

public class BIActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bi_activity);
        NavHostFragment behavioralInspection = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.bi_container);
        NavController biController = behavioralInspection.getNavController();
    }


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

    public void biSetToolbar(Toolbar backToolbar){
        this.setSupportActionBar(backToolbar);
        Objects.requireNonNull(this.getSupportActionBar()).setDisplayShowTitleEnabled(false);
        backToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        backToolbar.setNavigationOnClickListener(v -> this.onBackPressed());
    }


    public void logOut(){
        Toast.makeText(this,"Network Error!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }
}