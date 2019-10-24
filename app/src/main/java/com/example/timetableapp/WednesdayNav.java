package com.example.timetableapp;

import android.content.Intent;
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

public class WednesdayNav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;

    private NavigationView navigationView;
    private Toolbar toolbar =null;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wednesday_nav);
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

        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                 R.id.nav_dashboard, R.id.nav_notes
                ,R.id.nav_mon,R.id.nav_tue,R.id.nav_wes,R.id.nav_thu,R.id.nav_fri)
                .setDrawerLayout(drawer)
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.front_page, menu);
        return true;
    }



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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id)
        {

            case R.id.nav_dashboard:
                Intent h = new Intent(WednesdayNav.this,FrontPage.class);
                startActivity(h);
                finish();
                break;

            case R.id.nav_notes:
                Intent j = new Intent(WednesdayNav.this,Notes.class);
                startActivity(j);
                finish();
                break;

            case R.id.nav_module:
                Intent mod = new Intent(WednesdayNav.this,Module.class);
                startActivity(mod);
                finish();
                break;

            case R.id.nav_mon:
                Intent mon = new Intent(WednesdayNav.this,MondayNav.class);
                startActivity(mon);
                finish();
                break;

            case R.id.nav_tue:
                Intent tue = new Intent(WednesdayNav.this,TuesdayNav.class);
                startActivity(tue);
                finish();
                break;

            case R.id.nav_wes:
                Intent wes = new Intent(WednesdayNav.this,WednesdayNav.class);
                startActivity(wes);
                finish();
                break;

            case R.id.nav_thu:
                Intent thu = new Intent(WednesdayNav.this,ThursdayNav.class);
                startActivity(thu);
                finish();
                break;


            case R.id.nav_fri:
                Intent fri = new Intent(WednesdayNav.this,FridayNav.class);
                startActivity(fri);
                finish();
                break;

        }


        return true;
    }
}
