package com.loopbreakr.cogstruct.proscons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.loopbreakr.cogstruct.R;
import com.loopbreakr.cogstruct.thoughtjournal.objects.ThoughtJournalObject;

import java.text.DateFormat;
import java.util.Date;

public class PCActivity extends AppCompatActivity {

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
    }

    private String getTimeDate() {
        return DateFormat.getDateTimeInstance().format(new Date());
    }
}