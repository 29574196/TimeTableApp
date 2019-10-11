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




public class FrontPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navView;
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

        navView.setNavigationItemSelectedListener(this);

        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //initialize api
        //Retrofit retrofit = RetrofitClient.getInstance();


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
                    s += "  " +  c.getModule();

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
