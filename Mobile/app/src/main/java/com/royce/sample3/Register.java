package com.royce.sample3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by johnroycepunay on 9/18/2016.
 */
public class Register extends Activity {
    DatabaseHelper myDb;
    EditText editTextFname,editTextMname,editTextLname,editTextEmail,editTextUsername,editTextPassword,editTextConfirmPassword;
    Button btnRegister;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        myDb = new DatabaseHelper(this);
        editTextFname =(EditText)findViewById(R.id.FirstName);
        editTextMname=(EditText)findViewById(R.id.Middlename);
        editTextLname=(EditText)findViewById(R.id.Lastname);
        editTextEmail=(EditText)findViewById(R.id.Email);
        editTextUsername=(EditText)findViewById(R.id.etUsername);
        editTextPassword=(EditText)findViewById(R.id.etPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.etConfirmPassword);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        addData();
        Typeface myFont = Typeface.createFromAsset(getAssets(),"fonts/Georgia.ttf");
        editTextFname.setTypeface(myFont);
        editTextMname.setTypeface(myFont);
        editTextLname.setTypeface(myFont);
        editTextEmail.setTypeface(myFont);
        editTextUsername.setTypeface(myFont);
        editTextPassword.setTypeface(myFont);
        editTextConfirmPassword.setTypeface(myFont);
        btnRegister.setTypeface(myFont);

    }

    public void addData(){
        btnRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String Fname = editTextFname.getText().toString();
                        String Mname = editTextMname.getText().toString();
                        String Lname = editTextLname.getText().toString();
                        String Email = editTextEmail.getText().toString();
                        String Username = editTextUsername.getText().toString();
                        String Password = editTextPassword.getText().toString();
                        String Password2 = editTextConfirmPassword.getText().toString();
                        String storedUser = myDb.Exist(Username);

                        if (Fname.matches("") || Mname.matches("") || Lname.matches("") || Email.matches("")
                                || Username.matches("") || Password.matches("")) {
                            Toast.makeText(getApplicationContext(), "Please Fill the text fields!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(Password) || Password.length() < 8) {
                            Toast.makeText(getApplicationContext(), "You must have atleast 8 Characters in your Password!", Toast.LENGTH_SHORT).show();
                            editTextPassword.setText("");
                            editTextConfirmPassword.setText("");
                            return;
                        }
                        if (!Password.equals(Password2)) {
                            Toast.makeText(getApplicationContext(), "Sorry! Password do not match!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (Username.equals(storedUser)) {
                            Toast.makeText(getApplicationContext(), "Sorry! Username already exist!", Toast.LENGTH_SHORT).show();
                            return;
                        } else {

                            myDb.insertData(Fname, Mname, Lname, Email, Username, Password);
                            Toast.makeText(Register.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                            Intent ourIntent2 = new Intent(Register.this, Login.class);
                            startActivity(ourIntent2);
                            editTextFname.setText("");
                            editTextMname.setText("");
                            editTextLname.setText("");
                            editTextEmail.setText("");
                            editTextUsername.setText("");
                            editTextPassword.setText("");
                            editTextConfirmPassword.setText("");
                            finish();


                        }
                    }
                }
        );
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit Registration?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent ourIntent2 = new Intent(Register.this, Login.class);
                        startActivity(ourIntent2);
                        finish();
                    }
                }).create().show();
    }





}
