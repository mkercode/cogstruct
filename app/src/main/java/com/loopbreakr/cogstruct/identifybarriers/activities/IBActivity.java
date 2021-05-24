package com.loopbreakr.cogstruct.identifybarriers.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopbreakr.cogstruct.home.activities.LoginActivity;
import com.loopbreakr.cogstruct.home.activities.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.identifybarriers.objects.IBObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class IBActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ib_activity);
        NavHostFragment identifyBarriers = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.ib_container);
        NavController identifyBarriersController = identifyBarriers.getNavController();
    }


    public void sendToFirestore(String behavior, String nesscessaryAction, String barrierType, String barrier, String solutions){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String dateCreated = getTimeDate();
        String timeStamp = getTimeStamp();

        IBObject ibEntry= new IBObject(dateCreated,userId,behavior,nesscessaryAction,barrierType,barrier,solutions, timeStamp);
        FirebaseFirestore.getInstance().collection("forms").add(ibEntry).addOnFailureListener(this::sendException);
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