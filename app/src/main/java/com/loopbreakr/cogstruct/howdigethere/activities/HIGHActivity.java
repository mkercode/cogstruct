package com.loopbreakr.cogstruct.howdigethere.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopbreakr.cogstruct.home.activities.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.howdigethere.objects.HIGHObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HIGHActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_activity);
        NavHostFragment howdIGetHere = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.high_container);
        NavController thowdIGetHereController = howdIGetHere.getNavController();
    }

    public void sendToFirestore(String behavior, String triggerEvent, String emotion, float emotionRating, String thoughts, String vulnerabilities, String reliefs, String consequences, String solutions, String repairs){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String dateCreated = getTimeDate();
        String timeStamp = getTimeStamp();
        HIGHObject highEntry = new HIGHObject(dateCreated,userId,behavior,triggerEvent,emotion,emotionRating,thoughts,vulnerabilities,reliefs,consequences,solutions,repairs, timeStamp);
        FirebaseFirestore.getInstance().collection("forms").add(highEntry).addOnFailureListener(this::sendException);
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