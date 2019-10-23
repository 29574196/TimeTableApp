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

import android.content.Intent;
import android.media.MediaExtractor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                R.id.nav_dailyview, R.id.nav_dashboard, R.id.nav_notes,
                R.id.nav_module, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();

        //********************************************************
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.43.224:3000/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        myAPI = retrofit.create(INodeJS.class);
        //********************************************************
        getClasses();

    }

    public void getClasses()
    {
        Call<List<ClassModel>> call = myAPI.getClassInfo("12121212");

        Toast.makeText(FrontPage.this, "Uhm : hey" , Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<ClassModel>>() {
            @Override
            public void onResponse(Call<List<ClassModel>> call, Response<List<ClassModel>> response) {

                classList = response.body();

                String s="";
                for(ClassModel c : classList)
                {
                    s += "  " +  c.getModule_code();

                    //creating new activity
                }
                //Test to see if data was retrieved successfully
                Toast.makeText(FrontPage.this, "Uhm : " + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ClassModel>> call, Throwable t) {
                Log.i("log ",t.getMessage());
            }
        });
    }

    public void creatRow()
    {

    }

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
            case R.id.nav_dailyview:
                Intent i = new Intent(FrontPage.this,DailyView.class);
                startActivity(i);
                finish();
                break;
            case R.id.nav_notes:
                Intent j = new Intent(FrontPage.this,Notes.class);
                startActivity(j);
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
