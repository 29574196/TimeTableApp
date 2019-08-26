package com.example.timetableapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FrontPage extends AppCompatActivity {
    private TextView date;
    private Button btnGoCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        date = (TextView) findViewById(R.id.txtDate);
        btnGoCalendar = (Button) findViewById(R.id.btnCal);

        btnGoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FrontPage.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

    }
}
