package com.example.inforait;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.inforait.R;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }
    public void goToAdminLogin(View view){
        Intent intent=new Intent(this, AdminLogin.class);
        startActivity(intent);
    }

    public void goToAdminRegister(View view){
        Intent intent=new Intent(this, AdminRegister.class);
        startActivity(intent);
    }
}