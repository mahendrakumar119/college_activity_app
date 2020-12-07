package com.example.inforait;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    TextView mTextViewRegister;
    Button mButtonLogin;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        db = new DataBase(this);

        mTextUsername = (EditText) findViewById(R.id.login_admincode);
        mTextPassword = (EditText) findViewById(R.id.admin_login_password);
        mButtonLogin = (Button) findViewById(R.id.login_button);



        mTextViewRegister = (TextView)findViewById(R.id.login_register_link);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(AdminLogin.this,AdminRegister.class);
                startActivity(registerIntent);
            }
        });


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String admincode = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Cursor c = db.checkAdminUser(admincode,pwd);

                Log.d("cursor", String.valueOf(c.getCount()));


                boolean res;
                if(c.getCount()>0){
                    res=true;
                }else{
                    res=false;
                }

                 if (res == true) {
                    Intent HomePage = new Intent(AdminLogin.this, TabViewActivity.class);
                    HomePage.putExtra("acode",admincode);

                    startActivity(HomePage);
                } else {
                    Toast.makeText(AdminLogin.this,"Admin code or password incorrect.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
