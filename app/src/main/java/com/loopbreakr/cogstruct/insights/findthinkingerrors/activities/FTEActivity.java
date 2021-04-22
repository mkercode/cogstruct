package com.loopbreakr.cogstruct.insights.findthinkingerrors.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.home.activities.LoginActivity;

import java.util.Objects;

public class FTEActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fte_activity);
        NavHostFragment findThinkingErrors = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fte_container);
        NavController fteController = findThinkingErrors.getNavController();
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

    public void fteSetToolbar(Toolbar backToolbar){
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