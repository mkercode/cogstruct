package com.loopbreakr.cogstruct.home.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.loopbreakr.cogstruct.R;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login/Register Activity";
    private final int AUTHUI_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        authorizationCheck();
        Button button = findViewById(R.id.logreg_button);
        button.setOnClickListener(v -> handleLoginRegister());
    }

    private void authorizationCheck() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
    private void handleLoginRegister() {
        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.GoogleBuilder().build()))
                .setTheme(R.style.LoginTheme)
                .build();
        startActivityForResult(intent, AUTHUI_REQUEST_CODE);

    }
    //check code to see if sign in is successful
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTHUI_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // We have signed in the user or we have a new user

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();

            }
        }
    }
}