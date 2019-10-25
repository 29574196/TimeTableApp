package com.example.timetableapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Appointment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private Toolbar toolbar =null;
    private DrawerLayout drawer;

    private EditText app_Edit;
    private Button app_Btn;
    private Button appnotification_Btn;

    private TimePicker apptime;
    private String student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.open,R.string.close);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_notes,R.id.nav_appointment,R.id.nav_module
                ,R.id.nav_mon,R.id.nav_tue,R.id.nav_wes,R.id.nav_thu,R.id.nav_fri)
                .setDrawerLayout(drawer)
                .build();

        apptime = (TimePicker) findViewById(R.id.timepickerapp);
        app_Btn = (Button) findViewById(R.id.appoint_btn);
        app_Edit = (EditText) findViewById(R.id.appoint_edit);

        appnotification_Btn = (Button) findViewById(R.id.appnotification_btn);

        student = getIntent().getStringExtra("student");

        appnotification_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                if(Build.VERSION.SDK_INT>=23)
                {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            apptime.getHour(),
                            apptime.getMinute(),
                            0

                    );
                }
                else
                {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            apptime.getCurrentHour(),
                            apptime.getCurrentMinute(),
                            0

                    );
                }

                setAlarm(calendar.getTimeInMillis());

            }
        });

        app_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app_Edit.getText().toString();
            }
        });

    }

    private void setAlarm(long timeInMillis) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyAlarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);


        Toast.makeText(this, "Notification and Alarm set!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.front_page, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings)
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id)
        {

            case R.id.nav_dashboard:
                Intent h = new Intent(Appointment.this,FrontPage.class);
                h.putExtra("student",student);
                startActivity(h);
                finish();
                break;

            case R.id.nav_appointment:
                Intent a = new Intent(Appointment.this,Appointment.class);
                a.putExtra("student",student);
                startActivity(a);
                finish();
                break;

            case R.id.nav_module:
                Intent mod = new Intent(Appointment.this,Module.class);
                mod.putExtra("student",student);
                startActivity(mod);
                finish();
                break;
            case R.id.nav_notes:
                Intent j = new Intent(Appointment.this,Notes.class);
                j.putExtra("student",student);
                startActivity(j);
                finish();
                break;

            case R.id.nav_mon:
                Intent mon = new Intent(Appointment.this,MondayNav.class);
                mon.putExtra("student",student);
                startActivity(mon);
                finish();
                break;

            case R.id.nav_tue:
                Intent tue = new Intent(Appointment.this,TuesdayNav.class);
                tue.putExtra("student",student);
                startActivity(tue);
                finish();
                break;

            case R.id.nav_wes:
                Intent wes = new Intent(Appointment.this,WednesdayNav.class);
                wes.putExtra("student",student);
                startActivity(wes);
                finish();
                break;

            case R.id.nav_thu:
                Intent thu = new Intent(Appointment.this,ThursdayNav.class);
                thu.putExtra("student",student);
                startActivity(thu);
                finish();
                break;


            case R.id.nav_fri:
                Intent fri = new Intent(Appointment.this,FridayNav.class);
                fri.putExtra("student",student);
                startActivity(fri);
                finish();
                break;


        }


        return true;
    }
}
