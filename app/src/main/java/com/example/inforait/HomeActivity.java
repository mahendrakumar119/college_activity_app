package com.example.inforait;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void goToStudent(View view){
        Intent intent=new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
    public void goToAdmin(View view){
        Intent intent=new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }

}