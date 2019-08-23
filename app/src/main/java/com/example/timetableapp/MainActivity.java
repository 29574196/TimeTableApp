package com.example.timetableapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relative_lay1,relative_lay2; //declaring relative layouts
    Handler relative_handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relative_lay1.setVisibility(View.VISIBLE); //making first layout visible when first loaded
            relative_lay2.setVisibility(View.VISIBLE);//making second layout visible when first loaded

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relative_lay1 = (RelativeLayout) findViewById(R.id.relative_lay1);
        relative_lay2 = (RelativeLayout) findViewById(R.id.relative_lay2);

        relative_handler.postDelayed(runnable,1000);//will appear after 2 seconds

    }
}
