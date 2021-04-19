package com.loopbreakr.cogstruct.howdigethere.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopbreakr.cogstruct.home.activities.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.howdigethere.objects.HIGHObject;

import java.text.DateFormat;
import java.util.Date;

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
        HIGHObject highEntry = new HIGHObject(dateCreated,userId,behavior,triggerEvent,emotion,emotionRating,thoughts,vulnerabilities,reliefs,consequences,solutions,repairs);
        FirebaseFirestore.getInstance().collection("forms").add(highEntry).addOnSuccessListener(documentReference ->
                Log.d("ADDING ENTRY...", "SUCCESS ADDING HIGH ENTRY: " + highEntry.toString()))
                .addOnFailureListener(e -> Log.e("ADDING ENTRY...", "FAILURE ADDING HIGH ENTRY: " + highEntry.toString() + "... ERROR: ", e));
        closeActivity();
    }

    private String getTimeDate() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    private void closeActivity() {
        Toast.makeText(this, "Saved in logs", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}