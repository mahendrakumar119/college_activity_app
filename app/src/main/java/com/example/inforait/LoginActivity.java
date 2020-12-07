package com.example.inforait;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    TextView mTextViewRegister;
    Button mButtonLogin;
    DataBase db;
    byte[] img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DataBase(this);

        mTextUsername = (EditText) findViewById(R.id.login_username);
        mTextPassword = (EditText) findViewById(R.id.login_password);
        mButtonLogin = (Button) findViewById(R.id.login_button);



        mTextViewRegister = (TextView)findViewById(R.id.login_register_link);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Cursor c = db.checkUser(user,pwd);

                Log.d("cursor", String.valueOf(c.getCount()));

                if(c.moveToFirst()){
                    img = c.getBlob(0);
                    Log.d("cursor1", String.valueOf(img));
                }


                //Boolean res = db.checkUser(user, pwd);
                boolean res;
                if(c.getCount()>0){
                    res=true;
                }else{
                    res=false;
                }

                if (user.equals("admin") && pwd.equals("admin"))
                {
                    Intent xyz = new Intent(LoginActivity.this, AdminHomeActivity.class);
                    startActivity(xyz);
                }
                else if (res == true) {
                    Intent HomePage = new Intent(LoginActivity.this, MainActivity.class);
                    HomePage.putExtra("name",user);
                    HomePage.putExtra("picture", img);
                    startActivity(HomePage);
                } else {
                    Toast.makeText(LoginActivity.this, "Username or password incorrect.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}

