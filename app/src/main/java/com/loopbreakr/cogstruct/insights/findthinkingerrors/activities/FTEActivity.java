package com.loopbreakr.cogstruct.insights.findthinkingerrors.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.badbehaviors.objects.BBObject;
import com.loopbreakr.cogstruct.home.activities.LoginActivity;
import com.loopbreakr.cogstruct.insights.findthinkingerrors.objects.FTEObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class FTEActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fte_activity);
        NavHostFragment findThinkingErrors = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fte_container);
        NavController fteController = findThinkingErrors.getNavController();
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

    public void fteSetToolbar(Toolbar backToolbar){
        this.setSupportActionBar(backToolbar);
        Objects.requireNonNull(this.getSupportActionBar()).setDisplayShowTitleEnabled(false);
        backToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        backToolbar.setNavigationOnClickListener(v -> this.onBackPressed());
    }

    public void logOut(){
        Toast.makeText(this,"Network Error!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void sendToFirestore(String thought, String thinkingErrors){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String dateCreated = getTimeDate();
        String timeStamp = getTimeStamp();

        FTEObject fteEntry = new FTEObject(dateCreated,userId,thought, thinkingErrors, timeStamp);
        FirebaseFirestore.getInstance().collection("thinkingErrors").add(fteEntry).addOnSuccessListener(documentReference ->
                Log.d("ADDING ENTRY...", "SUCCESS ADDING HIGH ENTRY: " + fteEntry.toString()))
                .addOnFailureListener(e -> Log.e("ADDING ENTRY...", "FAILURE ADDING HIGH ENTRY: " +fteEntry.toString() + "... ERROR: ", e));
    }

    private String getTimeDate() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    private String getTimeStamp(){
        return String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }
}