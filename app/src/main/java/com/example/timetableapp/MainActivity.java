package com.example.timetableapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relative_lay1,relative_lay2; //declaring relative layouts
    private Button btn_login;
    private EditText user,password;
    private int count =3;
    Handler relative_handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relative_lay1.setVisibility(View.VISIBLE); //making first layout visible when first loaded
            relative_lay2.setVisibility(View.VISIBLE);//making second layout visible when first loaded

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relative_lay1 = (RelativeLayout) findViewById(R.id.relative_lay1);
        relative_lay2 = (RelativeLayout) findViewById(R.id.relative_lay2);
        btn_login = (Button) findViewById(R.id.btn_Login);
        user = (EditText) findViewById(R.id.usr_EditText);
        password = (EditText) findViewById(R.id.pass_EditText);

        relative_handler.postDelayed(runnable,5000);//will appear after 5 seconds



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login_Seq(user.getText().toString(),password.getText().toString());//Yet to use database for this

            }
        });




    }
    private void login_Seq(String userN,String userP) //Yet to add back for username and password
    {
        if((userN == "Student") && (userP == "1234"))
        {
            Intent second_PageIntent = new Intent(MainActivity.this, FrontPage.class); //move from login page to front page
            startActivity(second_PageIntent);
        }
        else
        {
            count--;

            if(count == 0)
            {
                btn_login.setEnabled(false);
            }
        }
    }
}
