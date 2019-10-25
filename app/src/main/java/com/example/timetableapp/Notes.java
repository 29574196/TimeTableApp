package com.example.timetableapp;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Notes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;

    private NavigationView navigationView;
    private Toolbar toolbar =null;
    private DrawerLayout drawer;

    private EditText notes_Edit;
    private Button notes_Btn;
    private Button notification_Btn;
    private NotificationManager notificationManager;

    private String student;

    private TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        timePicker = (TimePicker) findViewById(R.id.timepicker);
        notes_Btn = (Button) findViewById(R.id.notes_btn);
        notes_Edit = (EditText) findViewById(R.id.notes_edit);




        notes_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes_Edit.getText().toString();
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.open,R.string.close);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_notes
                ,R.id.nav_mon,R.id.nav_tue,R.id.nav_wes,R.id.nav_thu,R.id.nav_fri)
                .setDrawerLayout(drawer)
                .build();

        student = getIntent().getStringExtra("student");

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
                Intent h = new Intent(Notes.this,FrontPage.class);
                h.putExtra("student",student);
                startActivity(h);
                finish();
                break;

            case R.id.nav_module:
                Intent mod = new Intent(Notes.this,Module.class);
                mod.putExtra("student",student);
                startActivity(mod);
                finish();
                break;

            case R.id.nav_notes:
                Intent j = new Intent(Notes.this,Notes.class);
                j.putExtra("student",student);
                startActivity(j);
                finish();
                break;

            case R.id.nav_mon:
                Intent mon = new Intent(Notes.this,MondayNav.class);
                mon.putExtra("student",student);
                startActivity(mon);
                finish();
                break;

            case R.id.nav_tue:
                Intent tue = new Intent(Notes.this,TuesdayNav.class);
                tue.putExtra("student",student);
                startActivity(tue);
                finish();
                break;

            case R.id.nav_wes:
                Intent wes = new Intent(Notes.this,WednesdayNav.class);
                wes.putExtra("student",student);
                startActivity(wes);
                finish();
                break;

            case R.id.nav_thu:
                Intent thu = new Intent(Notes.this,ThursdayNav.class);
                thu.putExtra("student",student);
                startActivity(thu);
                finish();
                break;


            case R.id.nav_fri:
                Intent fri = new Intent(Notes.this,FridayNav.class);
                fri.putExtra("student",student);
                startActivity(fri);
                finish();
                break;

        }


        return true;
    }



}
