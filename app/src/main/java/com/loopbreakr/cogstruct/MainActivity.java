package com.loopbreakr.cogstruct;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loginCheck();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void loginCheck() {
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            Intent intent =  new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }


}