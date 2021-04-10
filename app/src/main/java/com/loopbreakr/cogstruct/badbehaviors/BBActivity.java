package com.loopbreakr.cogstruct.badbehaviors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.loopbreakr.cogstruct.MainActivity;
import com.loopbreakr.cogstruct.R;

import java.text.DateFormat;
import java.util.Date;

public class BBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bb_activity);
        NavHostFragment badbehavior = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.bb_container);
        NavController badbehaviorNavController = badbehavior.getNavController();
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