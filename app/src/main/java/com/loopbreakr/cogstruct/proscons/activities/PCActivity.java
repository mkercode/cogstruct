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
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopbreakr.cogstruct.home.activities.LoginActivity;
import com.loopbreakr.cogstruct.home.activities.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.proscons.objects.ProsConsObject;

import java.text.DateFormat;
import java.util.Date;

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
        ProsConsObject prosConsEntry = new ProsConsObject(dateCreated,userId, behavior, changePros, dontChangePros, changeCons, dontChangeCons);
        FirebaseFirestore.getInstance().collection("forms").add(prosConsEntry).addOnSuccessListener(documentReference ->
                Log.d("ADDING ENTRY...", "SUCCESS ADDING THOUGHT JOURNAL ENTRY: " + prosConsEntry.toString()))
                .addOnFailureListener(e -> Log.e("ADDING ENTRY...", "FAILURE ADDING THOUGHT JOURNAL ENTRY: " + prosConsEntry.toString() + "... ERROR: ", e));

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
}