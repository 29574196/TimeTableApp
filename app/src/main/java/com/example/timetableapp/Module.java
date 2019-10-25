package com.example.timetableapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.timetableapp.Retrofit.INodeJS;
import com.example.timetableapp.Retrofit.RetrofitClient;
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
import com.google.android.material.textfield.TextInputLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Module extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;

    private NavigationView navigationView;
    private Toolbar toolbar =null;
    private DrawerLayout drawer;
    private EditText module_Edit;
    private Button mod_Btn,mod_remove_Btn;
    private String student;


    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mod_Btn = (Button) findViewById(R.id.mod_btn);
        mod_remove_Btn = (Button) findViewById(R.id.mod_remove_btn);
        module_Edit = (EditText) findViewById(R.id.mod_edit);

        //getting student number from prior activity
        student = getIntent().getStringExtra("student");
        //button to add module
        mod_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMod(student,module_Edit.getText().toString());
            }
        });

        mod_remove_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMod(student,module_Edit.getText().toString());
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
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.open,R.string.close);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_notes,R.id.nav_module
                ,R.id.nav_mon,R.id.nav_tue,R.id.nav_wes,R.id.nav_thu,R.id.nav_fri)
                .setDrawerLayout(drawer)
                .build();

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);


    }

    //function that uses the api and retrieves response then checks and notifies user
    public void addMod(String student,String mod)
    {
        compositeDisposable.add(myAPI.addModule(student,mod)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.equals("1"))//column name from database
                        {
                            Toast.makeText(Module.this, "Module added successfully", Toast.LENGTH_SHORT).show();
                        } else if(s.equals("0")){
                            Toast.makeText(Module.this, "module does not exist", Toast.LENGTH_SHORT).show();
                        } else if (s.equals("2")){
                            Toast.makeText(Module.this,"duplicate module",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        );
    }
    //function that uses api call and deletes user module
    public void deleteMod(String student,String mod)
    {
        compositeDisposable.add(myAPI.deleteModule(student,mod)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.equals("1"))//column name from database
                        {
                            Toast.makeText(Module.this, "Module Deleted successfully", Toast.LENGTH_SHORT).show();
                        } else if(s.equals("0")){
                            Toast.makeText(Module.this, "module does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.front_page, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id)
        {

            case R.id.nav_dashboard:
                Intent h = new Intent(Module.this,FrontPage.class);
                h.putExtra("student",student);
                startActivity(h);
                finish();
                break;


            case R.id.nav_module:
                Intent mod = new Intent(Module.this,Module.class);
                mod.putExtra("student",student);
                startActivity(mod);
                finish();
                break;


            case R.id.nav_notes:
                Intent j = new Intent(Module.this,Notes.class);
                j.putExtra("student",student);
                startActivity(j);
                finish();
                break;

            case R.id.nav_mon:
                Intent mon = new Intent(Module.this,MondayNav.class);
                mon.putExtra("student",student);
                startActivity(mon);
                finish();
                break;

            case R.id.nav_tue:
                Intent tue = new Intent(Module.this,TuesdayNav.class);
                tue.putExtra("student",student);
                startActivity(tue);
                finish();
                break;

            case R.id.nav_wes:
                Intent wes = new Intent(Module.this,WednesdayNav.class);
                wes.putExtra("student",student);
                startActivity(wes);
                finish();
                break;

            case R.id.nav_thu:
                Intent thu = new Intent(Module.this,ThursdayNav.class);
                thu.putExtra("student",student);
                startActivity(thu);
                finish();
                break;


            case R.id.nav_fri:
                Intent fri = new Intent(Module.this,FridayNav.class);
                fri.putExtra("student",student);
                startActivity(fri);
                finish();
                break;

        }


        return true;
    }
}
