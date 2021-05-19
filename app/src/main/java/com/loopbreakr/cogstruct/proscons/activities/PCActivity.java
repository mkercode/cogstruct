package com.loopbreakr.cogstruct.proscons.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopbreakr.cogstruct.home.activities.LoginActivity;
import com.loopbreakr.cogstruct.home.activities.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.proscons.objects.ProsConsObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PCActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pc_activity);
        NavHostFragment prosCons = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.pros_cons_container);
        NavController thoughtJournalController = prosCons.getNavController();
    }

    public void sendToFirestore(String behavior, String changePros, String dontChangePros, String changeCons, String dontChangeCons){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String dateCreated = getTimeDate();
        String timeStamp = getTimeStamp();
        ProsConsObject prosConsEntry = new ProsConsObject(dateCreated,userId, behavior, changePros, dontChangePros, changeCons, dontChangeCons, timeStamp);
        FirebaseFirestore.getInstance().collection("forms").add(prosConsEntry).addOnFailureListener(this::sendException);

        closeActivity();
    }

    private String getTimeDate() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    private String getTimeStamp(){
        return String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }

    private void closeActivity() {
        Toast.makeText(this, "Saved in logs", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
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
    public void logOut(){
        Toast.makeText(this,"Network Error!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void sendException(Throwable e){
        FirebaseCrashlytics.getInstance().recordException(e);
        Toast.makeText(this, "Error, could not save worksheet.", Toast.LENGTH_SHORT).show();
    }
}