package com.example.timetableapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";

    private CalendarView calView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calView = (CalendarView) findViewById(R.id.calendarView);

       calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
           @Override
           public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
               String date = (i1 +1) + "/" +i2 + "/" + i;
               Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:"+ date );

               Intent date_intent = new Intent(CalendarActivity.this,FrontPage.class);
               date_intent.putExtra("date",date);
               startActivity(date_intent);
           }
       });

    }
}
