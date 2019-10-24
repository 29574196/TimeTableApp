package com.example.timetableapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
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

public class Notes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;

    private NavigationView navigationView;
    private Toolbar toolbar =null;
    private DrawerLayout drawer;

    private EditText module_Edit;
    private Button mod_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mod_Btn = (Button) findViewById(R.id.mod_btn);
        module_Edit = (EditText) findViewById(R.id.mod_edit);

        mod_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                module_Edit.getText().toString();
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
                startActivity(h);
                finish();
                break;

            case R.id.nav_module:
                Intent mod = new Intent(Notes.this,Module.class);
                startActivity(mod);
                finish();
                break;

            case R.id.nav_notes:
                Intent j = new Intent(Notes.this,Notes.class);
                startActivity(j);
                finish();
                break;

            case R.id.nav_mon:
                Intent mon = new Intent(Notes.this,MondayNav.class);
                startActivity(mon);
                finish();
                break;

            case R.id.nav_tue:
                Intent tue = new Intent(Notes.this,TuesdayNav.class);
                startActivity(tue);
                finish();
                break;

            case R.id.nav_wes:
                Intent wes = new Intent(Notes.this,WednesdayNav.class);
                startActivity(wes);
                finish();
                break;

            case R.id.nav_thu:
                Intent thu = new Intent(Notes.this,ThursdayNav.class);
                startActivity(thu);
                finish();
                break;


            case R.id.nav_fri:
                Intent fri = new Intent(Notes.this,FridayNav.class);
                startActivity(fri);
                finish();
                break;

        }


        return true;
    }



}
