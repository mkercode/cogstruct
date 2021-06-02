package com.loopbreakr.cogstruct.badbehaviors.activities;

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
import com.loopbreakr.cogstruct.home.activities.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.badbehaviors.objects.BBObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bb_activity);
        NavHostFragment badbehavior = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.bb_container);
        assert badbehavior != null;
        NavController badbehaviorNavController = badbehavior.getNavController();
    }

    public void sendToFirestore(String behavior, String environmentals, String distractions, String solutions){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String dateCreated = getTimeDate();
        String timeStamp = getTimeStamp();

        BBObject bbEntry = new BBObject(dateCreated,userId,behavior,environmentals,distractions,solutions, timeStamp);
        FirebaseFirestore.getInstance().collection("forms").add(bbEntry).addOnFailureListener(this::sendException);
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

    private void sendException(Throwable e){
        FirebaseCrashlytics.getInstance().recordException(e);
            Toast.makeText(this, "Error, could not save worksheet.", Toast.LENGTH_SHORT).show();
    }

}