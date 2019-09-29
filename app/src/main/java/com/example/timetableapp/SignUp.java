package com.example.timetableapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.widget.Toast;
import android.view.View;
import com.example.timetableapp.Retrofit.INodeJS;
import com.example.timetableapp.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SignUp extends AppCompatActivity {

    private TextInputLayout textInputStudent,textInputPassword,textInputPassword2,textInputEmail;
    private String student,email,password,password2;

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onStop(){
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initialize api
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);


        //assign EditText to corresponding TextInputLayout
        textInputEmail = findViewById(R.id.usr_email);
        textInputStudent = findViewById(R.id.usr_student);
        textInputPassword = findViewById(R.id.pass1);
        textInputPassword2 = findViewById(R.id.pass2);
        //
       }

    private boolean validateEmailAddress()
    {
        if(email.isEmpty())
        {
            textInputEmail.setError("Field is required");
            return false;
        }else{
            textInputEmail.setError(null);
            textInputEmail.setErrorEnabled(false);
            return true;
        }
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
            textInputEmail.setError(null);
            textInputStudent.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword()
    {
        if(password.isEmpty())
        {
            textInputPassword.setError("Field is required");
            return false;
        }else if(password2.isEmpty())
        {
            textInputPassword2.setError("Field is required");
            return false;
        }else if(!password.equals(password2))
        {
            textInputPassword.setError("Passwords don't match");
            textInputPassword2.setError("Passwords don't match");
            return false;
        }else
        {
            textInputPassword.setError(null);
            textInputPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void signUpUser(View view)
    {

        email = textInputEmail.getEditText().getText().toString().trim();
        student = textInputStudent.getEditText().getText().toString().trim();
        password = textInputPassword.getEditText().getText().toString().trim();
        password2 = textInputPassword2.getEditText().getText().toString().trim();

            userRegister(student, password, email);

    }

    private void userRegister(String student,String pass1,String email)
    {
        compositeDisposable.add(myAPI.userRegister(student,pass1,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>(){
                    @Override
                    public void accept(String s) throws Exception {
                        if(s.contains("0"))//column name from database
                        {
                            Toast.makeText(SignUp.this,"User created",Toast.LENGTH_SHORT).show();
                            //creating new activity
                            Intent loginScreen = new Intent(SignUp.this, LoginActivity.class);
                            startActivity(loginScreen);
                            finish();
                        }else{
                            Toast.makeText(SignUp.this,"Error "+s,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        );
    }
}
