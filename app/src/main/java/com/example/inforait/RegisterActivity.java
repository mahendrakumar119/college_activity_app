package com.example.inforait;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    DataBase db;
    byte img[];
    ImageView imageView;
    Bitmap bitmap=null;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextFirstName;
    EditText mTextLastName;
    Button mButtonRegister,openCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        db = new DataBase(this);
        mTextUsername = (EditText)findViewById(R.id.reg_username);
        mTextPassword = (EditText)findViewById(R.id.reg_password);
        mTextFirstName = (EditText)findViewById(R.id.reg_first_name);
        mTextLastName = (EditText)findViewById(R.id.reg_last_name);
        mButtonRegister = (Button)findViewById(R.id.reg_button);
        imageView= (ImageView) findViewById(R.id.iv1);
        //openCamera=(Button)findViewById(R.id.bt4);
        if(ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.CAMERA)!=
        PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(RegisterActivity.this,new String[]{Manifest.permission.CAMERA},100);

        }


        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = mTextFirstName.getText().toString().trim();
                String lname = mTextLastName.getText().toString().trim();
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                if(mTextFirstName.getText().toString().length()==0 || mTextLastName.getText().toString().length()==0 || mTextUsername.getText().toString().length()==0 || mTextPassword.getText().toString().length()==0) {
                    Toast.makeText(RegisterActivity.this, "Error : Enter all details", Toast.LENGTH_LONG).show();
                }


//
//                    mTextFirstName.setError("First name not entered");
//                    mTextFirstName.requestFocus();
//                }
//
//                String lname = mTextLastName.getText().toString().trim();
//                if(){
//                    mTextLastName.setError("Last name not entered");
//                    mTextLastName.requestFocus();
//                }
//
//
//                if(){
//                    mTextUsername.setError("Username is Required");
//                    mTextUsername.requestFocus();
//                }
//
//
//                if(){
//                    mTextPassword.setError("Password not entered");
//                    mTextPassword.requestFocus();
//                }

                else{
                    long val = db.addUser(fname,lname,user,pwd,img);

                    if(val > 0) {
                        Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_SHORT).show();
                    }
                }

/*
                long val = db.addUser(fname,lname,user,pwd);
                    if(val > 0){
                        Toast.makeText(student_register.this,"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(student_register.this,student_login.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(student_register.this,"Registration Error",Toast.LENGTH_SHORT).show();
                    }
*/
                //      https://www.youtube.com/watch?v=IYJHQnZPAFk&ab_channel=KarunShrestha

            }



        });
    }


    public void selectimg(View v){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,100);

    }
    @Override


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            bitmap=(Bitmap)data.getExtras().get("data");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
            resized.compress(Bitmap.CompressFormat.PNG, 100, bos);
            img = bos.toByteArray();
            imageView.setImageBitmap(bitmap);
        }
//        if (resultCode == 100) {

            //Uri selectedImage = data.getData();


//                Bitmap captureImg=(Bitmap)data.getExtras().get("data");

//                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                //Bitmap resized = Bitmap.createScaledBitmap(captureImg, (int)(captureImg.getWidth()*0.8), (int)(captureImg.getHeight()*0.8), true);
                //ByteArrayOutputStream bos = new ByteArrayOutputStream();
                //resized.compress(Bitmap.CompressFormat.PNG, 100, bos);
                //img = bos.toByteArray();
                //imageView.setImageBitmap(captureImg);

//        }

    }


}


