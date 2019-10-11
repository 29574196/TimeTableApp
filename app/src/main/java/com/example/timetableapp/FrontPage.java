package com.example.timetableapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.navigation.Navigation;

import android.content.Intent;
import android.media.MediaExtractor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import retrofit2.http.HEAD;




public class FrontPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navView;

    private TextView headerStudentNo,headerEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        mDrawerlayout = (DrawerLayout) findViewById(R.id.navDrawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        navView = (NavigationView) findViewById(R.id.navView);

        mDrawerlayout.addDrawerListener(mToggle);

        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //headerEmail.setText("");
        //header = mDrawerlayout;
        //headerStudentNo = (TextView) findViewById(R.id.studentNumber);
        //headerStudentNo.setText("heyyyy");
        //headerEmail = (TextView) findViewById(R.id.emailAddress);



        navView.setNavigationItemSelectedListener(this);


        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navDaily:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new DailyviewFragment()).commit();
                break;
            case R.id.navAppoint:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new AppointmentFragment()).commit();
                break;
            case R.id.navModule:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new ModuleFragment()).commit();
                break;


        }
        mDrawerlayout.closeDrawer(GravityCompat.START);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;

        }
        return super.onOptionsItemSelected(item);

    }



}
