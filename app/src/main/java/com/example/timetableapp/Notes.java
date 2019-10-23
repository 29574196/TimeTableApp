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

public class Notes extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener,Tab4.OnFragmentInteractionListener{

    private AppBarConfiguration mAppBarConfiguration;

    private NavigationView navigationView;
    private Toolbar toolbar =null;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tablelayout = (TabLayout) findViewById(R.id.tablayout);
        tablelayout.addTab(tablelayout.newTab().setText("Mon"));
        tablelayout.addTab(tablelayout.newTab().setText("Tue"));
        tablelayout.addTab(tablelayout.newTab().setText("Wed"));
        tablelayout.addTab(tablelayout.newTab().setText("Thu"));
        tablelayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tablelayout.getTabCount());
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablelayout));

        tablelayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
                R.id.nav_dailyview, R.id.nav_dashboard, R.id.nav_notes,
                R.id.nav_module, R.id.nav_share)
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
                Intent h = new Intent(Notes.this,Dashboard.class);
                startActivity(h);
                finish();
                break;
            case R.id.nav_dailyview:
                Intent i = new Intent(Notes.this,DailyView.class);
                startActivity(i);
                finish();
                break;
            case R.id.nav_notes:
                Intent j = new Intent(Notes.this,Notes.class);
                startActivity(j);
                finish();
                break;
        }


        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
