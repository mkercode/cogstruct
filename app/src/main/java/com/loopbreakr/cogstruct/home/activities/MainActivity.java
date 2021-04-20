package com.loopbreakr.cogstruct.home.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.loopbreakr.cogstruct.R;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if( FirebaseAuth.getInstance().getCurrentUser() == null){
            setContentView(R.layout.loading_screen);
        }
        else{
            setContentView(R.layout.home_activity_main);
        }
    }

    //keep user signed in by attatching authstatelistener to the lifecycle methods
    @Override
    protected void onStart() {
        super.onStart();
        //listen for auth state changed
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }

    //monitor if the json web token expires
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if(firebaseAuth.getCurrentUser() == null){
            openLogin();
        }
    }

    private void openLogin() {
            Intent intent =  new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
    }

    public void signOutClicked(){
        AuthUI.getInstance().signOut(this);
    }

}