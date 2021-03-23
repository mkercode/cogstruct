package com.loopbreakr.cogstruct.identifybarriers.activities;

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
import com.loopbreakr.cogstruct.LoginActivity;
import com.loopbreakr.cogstruct.MainActivity;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.identifybarriers.objects.IBObject;

import java.text.DateFormat;
import java.util.Date;

public class IBActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ib_activity);
        NavHostFragment identifyBarriers = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.ib_container);
        NavController identifyBarriersController = identifyBarriers.getNavController();
    }


    public void sendToFirestore(String behavior, String nesscessaryAction, String barrierType, String barrier, String solution){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String dateCreated = getTimeDate();
        IBObject ibEntry= new IBObject(dateCreated,userId,behavior,nesscessaryAction,barrierType,barrier,solution);
        FirebaseFirestore.getInstance().collection("forms").add(ibEntry).addOnSuccessListener(documentReference ->
                Log.d("ADDING ENTRY...", "SUCCESS ADDING THOUGHT JOURNAL ENTRY: " + ibEntry.toString()))
                .addOnFailureListener(e -> Log.e("ADDING ENTRY...", "FAILURE ADDING THOUGHT JOURNAL ENTRY: " + ibEntry.toString() + "... ERROR: ", e));
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