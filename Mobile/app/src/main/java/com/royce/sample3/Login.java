package com.royce.sample3;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by johnroycepunay on 9/18/2016.
 */

public class Login extends Activity implements View.OnClickListener {
    DatabaseHelper myDb;
    TextView signUp, signup1;


    private  EditText editTextUsername, editTextPassword;
    private Button btnLogin;
    private Button btnRegister;
    private String name = null;
    private ProgressDialog progress;

    private String username;
    private String password;
    private String mUsername, mPassword;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        signUp = (TextView)findViewById(R.id.signup);
        signUp.setOnClickListener(this);
        signup1 = (TextView)findViewById(R.id.signup1);
        editTextUsername = (EditText) findViewById(R.id.etUsername);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        myDb = new DatabaseHelper (this);
        try {
            myDb=myDb.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SignIn();

        Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/Georgia.ttf");
        btnLogin.setTypeface(myFont);
        editTextPassword.setTypeface(myFont);
        editTextUsername.setTypeface(myFont);
        signUp.setTypeface(myFont);
        signup1.setTypeface(myFont);


    }
    public void onBackPressed() {
        finish();

    }


    public void SignIn(){

        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userName = editTextUsername.getText().toString();
                        String password = editTextPassword.getText().toString();
                        String storedPassword = myDb.getSingleEntry(userName);

                        if (password.equals(storedPassword)) {
                            Intent ourIntent3 = new Intent(Login.this, Home.class);
                           ourIntent3.putExtra("Username", userName);
                            startActivity(ourIntent3);
                            // startActivity(new Intent(Login.this, MainActivity.class));
                            Toast.makeText(Login.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            Toast.makeText(Login.this, "Username or Password does not match", Toast.LENGTH_LONG).show();
                        }


                    }
                }
        );
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.signup:
                Intent ourIntent2 = new Intent(Login.this, Register.class);
                startActivity(ourIntent2);
                finish();
                break;

            default:
                break;
        }
    }



}

