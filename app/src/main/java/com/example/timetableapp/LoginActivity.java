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

import com.example.timetableapp.Retrofit.INodeJS;
import com.example.timetableapp.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login,btn_SignUp;
    private TextInputLayout textInputStudent,textInputPassword;
    private String student,userPassword;

    private int counter = 5;

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
        setContentView(R.layout.activity_login);

        //initialize api
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);

        //assign EditText to corresponding TextInputLayout
        textInputStudent = findViewById(R.id.usr_EditText);
        textInputPassword = findViewById(R.id.pass_EditText);
        //
      //Working login with hard coded information
        /*btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //keeping track of user attempts
                if(counter >0) {
                    user = findViewById(R.id.usr_EditText);
                    password = findViewById(R.id.pass_EditText);
                    String userName = user.getText().toString();
                    String userPassword = password.getText().toString();
                    //checking if login info is valid
                    if (userName.equals("hey") && userPassword.equals("hey")) {
                        //creating new activity
                        //Intent frontP = new Intent(LoginActivity.this, FrontPage.class);
                        //startActivity(frontP);
                        //finish();
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
            });*/

    }

    private boolean validateStudentNumber()
    {
        if(student.isEmpty())
        {
            textInputStudent.setError("Field is required");
            return false;
        }else if(student.length() != 8){
            textInputStudent.setError("Student number must be 8 digits");
            return false;
        }else
        {
            textInputStudent.setError(null);
            textInputStudent.setErrorEnabled(false);
            return true;
        }
    }

    public void login(View view) {
            student = textInputStudent.getEditText().getText().toString().trim();
            userPassword = textInputPassword.getEditText().getText().toString().trim();
            if(!validateStudentNumber())
            {
                return;
            }
            //checking if login info is valid
            loginUser(student, userPassword);
    }

    public void button_registerForm(View view) {
        startActivity(new Intent(getApplicationContext(), SignUp.class));
    }

    private void loginUser(String sN, String pass) {
        compositeDisposable.add(myAPI.userLogin(sN, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.equals("1"))//column name from database
                        {
                            Toast.makeText(LoginActivity.this, "Successful login  ", Toast.LENGTH_SHORT).show();
                            //creating new activity
                            Intent frontP = new Intent(LoginActivity.this, FrontPage.class);
                            startActivity(frontP);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "unsuccessful login", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        );
    }
}
