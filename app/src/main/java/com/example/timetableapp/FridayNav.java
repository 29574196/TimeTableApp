package com.example.timetableapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.timetableapp.Retrofit.ClassModel;
import com.example.timetableapp.Retrofit.INodeJS;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;
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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FridayNav extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;

    private NavigationView navigationView;
    private Toolbar toolbar =null;
    private DrawerLayout drawer;

    private String [][] timeTableArr;
    private List<ClassModel> classList;

    private String student;

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday_nav);
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        drawer = findViewById(R.id.drawer_layout);
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

        //********************************************************
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://196.252.252.110:3000/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        myAPI = retrofit.create(INodeJS.class);
        //********************************************************
        student = getIntent().getStringExtra("student");
        getClasses();


    }

    //create cardView for all time slots
    public void addCardView()
    {

        LinearLayout l = findViewById(R.id.fri);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(setSize(10),setSize(10),setSize(10),setSize(10));

        for(int r = 1 ; r <=6 ;r++) {
            LinearLayout infoLayout = new LinearLayout(this);
            infoLayout.setOrientation(LinearLayout.VERTICAL);

            CardView card = new CardView(this);
            //card.setPadding(setSize(30),setSize(30),setSize(30),setSize(30));


            TextView tempR1 = new TextView(this);
            tempR1.setText(timeTableArr[r][0]+"\t\t\t\t\t\t\t\t\t\t\t"+timeTableArr[r][5]);
            tempR1.setTextSize(setSize(5));
            tempR1.setTextColor(Color.WHITE);
            infoLayout.addView(tempR1);
            card.addView(infoLayout);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, setSize(65));
            params.setMargins(setSize(10),setSize(10),setSize(10),setSize(10));
            //
            card.setLayoutParams(params);

            card.setBackgroundResource(R.drawable.bg1);
            l.addView(card);
        }


    }
    //converting size to dp
    public int setSize(int size)
    {
        float scale = getResources().getDisplayMetrics().density;
        int s = (int) (size*scale + 0.5f);
        return s;
    }
    //populate timetable array
    public void populateFree(List<ClassModel> cl)
    {

        for( ClassModel cm : cl) {
            if (cm.getTime_slot().equals("1")) {
                switch (cm.getDay_code().toUpperCase()) {
                    case "FRI":
                        timeTableArr[1][5] = cm.getModule_code() +"\n\t\t\t\t\t\t\t\t\t\t\t"+cm.getBuilding()+"  "+cm.getRoom();
                        break;
                }
            } else if(cm.getTime_slot().equals("2")){
                switch (cm.getDay_code().toUpperCase()) {
                    case "FRI":
                        timeTableArr[2][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;

                }
            } else if(cm.getTime_slot().equals("3")){
                switch (cm.getDay_code().toUpperCase()) {
                    case "FRI":
                        timeTableArr[3][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                }
            } else if(cm.getTime_slot().equals("4")) {
                switch (cm.getDay_code().toUpperCase()) {
                    case "FRI":
                        timeTableArr[4][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;

                }
            }else if(cm.getTime_slot().equals("5")){
                switch (cm.getDay_code().toUpperCase()) {
                    case "FRI":
                        timeTableArr[5][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                }
            }else if(cm.getTime_slot().equals("6")){
                switch (cm.getDay_code().toUpperCase()) {
                    case "FRI":
                        timeTableArr[6][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                }
            }
        }


    }
    //initialise array with empty spaces
    public void initArr()
    {
        timeTableArr = new String[7][6];
        //////////////////////////////////////////
        timeTableArr[0][1] = "MON";
        timeTableArr[0][2] = "TUE";
        timeTableArr[0][3] = "WED";
        timeTableArr[0][4] = "THUR";
        timeTableArr[0][5] = "FRI";
        /////////////////////////////////////////
        timeTableArr[1][0] = "7:30";
        timeTableArr[2][0] = "9:30";
        timeTableArr[3][0] = "11:00";
        timeTableArr[4][0] = "13:00";
        timeTableArr[5][0] = "14:30";
        timeTableArr[6][0] = "16:00";

        for(int r = 1;r <= 6;r++)
        {
            for(int c = 1;c<=5;c++)
            {
                timeTableArr[r][c] = "     ";
            }
        }

    }
    //method to call api and get class information
    public void getClasses()
    {
        Call<List<ClassModel>> call = myAPI.getClassInfo(student);

        //Toast.makeText(FrontPage.this, "Uhm : hey" , Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<ClassModel>>() {
            @Override
            public void onResponse(Call<List<ClassModel>> call, Response<List<ClassModel>> response) {

                classList = response.body();

                initArr();
                populateFree(classList);
                addCardView();
            }

            @Override
            public void onFailure(Call<List<ClassModel>> call, Throwable t) {
                Log.i("log ",t.getMessage());
            }
        });
    }
    //******************************************************************//

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
                Intent h = new Intent(FridayNav.this,FrontPage.class);
                h.putExtra("student",student);
                startActivity(h);
                finish();
                break;

            case R.id.nav_notes:
                Intent j = new Intent(FridayNav.this,Notes.class);
                j.putExtra("student",student);
                startActivity(j);
                finish();
                break;

            case R.id.nav_module:
                Intent mod = new Intent(FridayNav.this,Module.class);
                mod.putExtra("student",student);
                startActivity(mod);
                finish();
                break;

            case R.id.nav_appointment:
                Intent app = new Intent(FridayNav.this,Appointment.class);
                app.putExtra("student",student);
                startActivity(app);
                finish();
                break;

            case R.id.nav_mon:
                Intent mon = new Intent(FridayNav.this,MondayNav.class);
                mon.putExtra("student",student);
                startActivity(mon);
                finish();
                break;

            case R.id.nav_tue:
                Intent tue = new Intent(FridayNav.this,TuesdayNav.class);
                tue.putExtra("student",student);
                startActivity(tue);
                finish();
                break;

            case R.id.nav_wes:
                Intent wes = new Intent(FridayNav.this,WednesdayNav.class);
                wes.putExtra("student",student);
                startActivity(wes);
                finish();
                break;

            case R.id.nav_thu:
                Intent thu = new Intent(FridayNav.this,ThursdayNav.class);
                thu.putExtra("student",student);
                startActivity(thu);
                finish();
                break;


            case R.id.nav_fri:
                Intent fri = new Intent(FridayNav.this,FridayNav.class);
                fri.putExtra("student",student);
                startActivity(fri);
                finish();
                break;

        }


        return true;
    }
}
