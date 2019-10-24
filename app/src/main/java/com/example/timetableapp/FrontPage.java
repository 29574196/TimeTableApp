package com.example.timetableapp;

import android.content.Intent;
import android.graphics.Color;
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

import android.content.Intent;
import android.media.MediaExtractor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.R.color;

import com.example.timetableapp.Retrofit.ClassModel;
import com.example.timetableapp.Retrofit.INodeJS;
import com.example.timetableapp.Retrofit.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.HEAD;


public class FrontPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private NavigationView navigationView;
    private Toolbar toolbar =null;
    private DrawerLayout drawer;
    private List<ClassModel> classList;

    private String [][] timeTableArr;

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

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.open,R.string.close);
        toggle.syncState();
         navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_notes,
                R.id.nav_module, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();

        //********************************************************
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://196.252.252.110:3000/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        myAPI = retrofit.create(INodeJS.class);
        //********************************************************
        getClasses();

    }

    public void getClasses()
    {
        Call<List<ClassModel>> call = myAPI.getClassInfo("12121212");

        //Toast.makeText(FrontPage.this, "Uhm : hey" , Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<ClassModel>>() {
            @Override
            public void onResponse(Call<List<ClassModel>> call, Response<List<ClassModel>> response) {

                classList = response.body();

                initArr();
                populateFree(classList);
                addTable();
            }

            @Override
            public void onFailure(Call<List<ClassModel>> call, Throwable t) {
                Log.i("log ",t.getMessage());
            }
        });
    }

    //****************************************************************//
    public void populateFree(List<ClassModel> cl)
    {

        for( ClassModel cm : cl) {
            if (cm.getTime_slot().equals("1")) {
                switch (cm.getDay_code().toUpperCase()) {
                    case "MON":
                        timeTableArr[1][1] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "TUES":
                        timeTableArr[1][2] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "WED":
                        timeTableArr[1][3] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "THURS":
                        timeTableArr[1][4] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "FRI":
                        timeTableArr[1][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;

                }
            } else if(cm.getTime_slot().equals("2")){
                switch (cm.getDay_code().toUpperCase()) {
                    case "MON":
                        timeTableArr[2][1] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "TUES":
                        timeTableArr[2][2] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "WED":
                        timeTableArr[2][3] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "THURS":
                        timeTableArr[2][4] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "FRI":
                        timeTableArr[2][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;

                }
            } else if(cm.getTime_slot().equals("3")){
                switch (cm.getDay_code().toUpperCase()) {
                    case "MON":
                        timeTableArr[3][1] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "TUES":
                        timeTableArr[3][2] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "WED":
                        timeTableArr[3][3] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "THURS":
                        timeTableArr[3][4] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "FRI":
                        timeTableArr[3][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;

                }
            } else if(cm.getTime_slot().equals("4")) {
                switch (cm.getDay_code().toUpperCase()) {
                    case "MON":
                        timeTableArr[4][1] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "TUES":
                        timeTableArr[4][2] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "WED":
                        timeTableArr[4][3] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "THURS":
                        timeTableArr[4][4] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "FRI":
                        timeTableArr[4][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;

                }
            }else if(cm.getTime_slot().equals("5")){
                switch (cm.getDay_code().toUpperCase()) {
                    case "MON":
                        timeTableArr[5][1] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "TUES":
                        timeTableArr[5][2] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "WED":
                        timeTableArr[5][3] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "THURS":
                        timeTableArr[5][4] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;
                    case "FRI":
                        timeTableArr[5][5] = cm.getModule_code() +"\n"+cm.getBuilding()+"\n"+cm.getRoom();
                        break;

                }
            }
        }


    }

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

    //Table Update
    public void addTable()
    {
        //ViewGroup layout = (ViewGroup) findViewById(R.id.contentTable);
        TableLayout t = (TableLayout) findViewById(R.id.timetable);
        t.setStretchAllColumns(true);
        t.bringToFront();

        TableRow.LayoutParams l = new TableRow.LayoutParams(50,260);

        for(int r = 0;r <= 6;r++)
        {
            TableRow row= new TableRow(this);

            for(int c = 0;c<=5;c++)
            {
                TextView tempCol = new TextView(this);
                tempCol.setText(timeTableArr[r][c]);
                if(r%2 == 0)
                {
                    tempCol.setBackgroundColor(Color.LTGRAY);
                }
                if (c==0||r==0) {
                    tempCol.setTextSize(15);
                }
                row.addView(tempCol,l);
            }
            t.addView(row);
        }
        //layout.addView(t);
    }

    public String[][] getArr(){
        return timeTableArr;
    }

    public List<ClassModel> getClassList()
    {
        return classList;
    }
    //*******************************************************************************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.front_page,menu);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id)
        {

            case R.id.nav_dashboard:
                Intent h = new Intent(FrontPage.this,Dashboard.class);
                        startActivity(h);
                finish();
                        break;

            case R.id.nav_notes:
                Intent j = new Intent(FrontPage.this,Notes.class);
                startActivity(j);
                finish();
                break;

            case R.id.nav_module:
                Intent mod = new Intent(FrontPage.this,Module.class);
                startActivity(mod);
                finish();
                break;


            case R.id.nav_mon:
                Intent mon = new Intent(FrontPage.this,MondayNav.class);
                startActivity(mon);
                finish();
                break;

            case R.id.nav_tue:
                Intent tue = new Intent(FrontPage.this,TuesdayNav.class);
                startActivity(tue);
                finish();
                break;

            case R.id.nav_wes:
                Intent wes = new Intent(FrontPage.this,WednesdayNav.class);
                startActivity(wes);
                finish();
                break;

            case R.id.nav_thu:
                Intent thu = new Intent(FrontPage.this,ThursdayNav.class);
                startActivity(thu);
                finish();
                break;


            case R.id.nav_fri:
                Intent fri = new Intent(FrontPage.this,FridayNav.class);
                startActivity(fri);
                finish();
                break;
        }


        return true;
    }
}
