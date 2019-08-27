package com.example.timetableapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
/**Activity will be used for returning users
   preventing tedious logins on the same device**/
public class EmptyMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent activityIntent;

        // go straight to main if a token is stored
        activityIntent = new Intent(this,LoginActivity.class);

        startActivity(activityIntent);
        finish();
    }
}
