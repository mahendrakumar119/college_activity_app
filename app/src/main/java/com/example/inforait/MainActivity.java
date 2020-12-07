package com.example.inforait;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.maps.MapFragment;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    DataBase db;


    String user;
    String out;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        Intent intent=getIntent();
        out=intent.getExtras().getString("name");


        //start--Getting user name from login page
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_logged_in_user);
        Bundle bundle = getIntent().getExtras();
        user = bundle.getString("name");
        txtProfileName.setText("Welcome, " + user);
        //end

//        Intent in = new Intent(MainActivity.this,ProfileFragment.class);
//        Bundle extrass = new Bundle();
//        Log.d("useername",user);
//
//        extrass.putString("number", user);
//        in.putExtras(extrass);

        //start--Getting image from login page
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        if(null == image) {
            Log.d("Error", "Ouh! there is no there is no child view with R.id.imageView ID within my parent view View.");
        }
        image.setImageBitmap(bmp);
        //end
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }
    public Bundle getUser() {
        Bundle hm = new Bundle();
        hm.putString("user",user);
        return hm;
    }
    public Bundle getMyData(){
        Bundle hm=new Bundle();
        hm.putString("val1",out);
        return hm;
    }
    public Bundle getMyImage(){
        Bitmap resized = Bitmap.createScaledBitmap(bmp, (int)(bmp.getWidth()*0.8), (int)(bmp.getHeight()*0.8), true);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        resized.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] byteArray=bos.toByteArray();

        Bundle b=new Bundle();
        b.putByteArray("image",byteArray);
        return b;
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_aboutus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutUsFragment()).commit();
                break;
            case R.id.nav_location:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MapsFragment()).commit();
                break;
            case R.id.nav_logout:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //    new LogoutFragment()).commit();
                Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}