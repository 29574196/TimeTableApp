package com.example.timetableapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{


    RelativeLayout relative_lay1,relative_lay2; //declaring relative layouts
    private Button btn_login,btn_SignUp;
    private EditText user,password;
    private boolean login_status;
    private int counter = 3;
    private Intent second;

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
        login_status = false;


        relative_handler.postDelayed(runnable,2000);//will appear after 5 seconds


        //Working login with hard coded information
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //keeping track of user attempts
                if(counter >0) {
                    String userName = user.getText().toString();
                    String userPassword = password.getText().toString();
                    //checking if login info is valid
                    if (userName.equals("hey") && userPassword.equals("hey")) {
                        //creating new activity
                        second = new Intent(LoginActivity.this, FrontPage.class);
                        startActivity(second);
                        finish();
                    } else {
                        //subtracting from attempts if users get details wrong
                        counter --;
                        //pop up message alerting users that they password and user name is wrong
                        String toastNote = "Wrong User name or password \n " + counter + " attempt/s remaining" ;
                        Toast notice = Toast.makeText(getApplicationContext(), toastNote, Toast.LENGTH_LONG);
                        notice.setMargin(0, 0);
                        notice.setGravity(0,-2,2);
                        notice.show();
                        //********************************************************************
                    }
                    user.setText("");
                    password.setText("");
                }else
                {
                    btn_login.setEnabled(false);
                }

                }
            });
    }




}