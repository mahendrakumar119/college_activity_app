package com.example.inforait;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminRegister extends AppCompatActivity {
    Button mButtonRegister;
    DataBase db;
    EditText mTextAcode,mTextPassword,mTextUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        db = new DataBase(this);
        mTextUsername = (EditText)findViewById(R.id.reg_admin_username);
        mTextPassword = (EditText)findViewById(R.id.reg_admin_password);
        mTextAcode = (EditText)findViewById(R.id.reg_admincode);
        mButtonRegister=(Button)findViewById(R.id.reg_button);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String acode = mTextAcode.getText().toString().trim();
                String username = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();

                if (mTextAcode.getText().toString().length() == 0 || mTextUsername.getText().toString().length() == 0 || mTextPassword.getText().toString().length() == 0) {
                    Toast.makeText(AdminRegister.this, "Error: Enter all details", Toast.LENGTH_LONG).show();
                }

                else {
                    long val = db.addAdmin(acode, username, pwd);

                    if (val > 0) {
                        Toast.makeText(AdminRegister.this, "You have registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(AdminRegister.this, AdminLogin.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(AdminRegister.this, "Registration Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            });
    }

}