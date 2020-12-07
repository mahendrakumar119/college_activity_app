package com.example.inforait;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
    }

    public void goToLogin(View view){
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToRegister(View view){
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}


