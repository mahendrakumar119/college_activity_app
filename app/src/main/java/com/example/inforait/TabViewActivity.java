package com.example.inforait;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class TabViewActivity extends AppCompatActivity {
    FloatingActionButton logout;
    String acode;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);

        TabLayout tabLayout = findViewById(R.id.tabview);
        viewPager = findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText("Add Event"));
        tabLayout.addTab(tabLayout.newTab().setText("View Event"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         logout=(FloatingActionButton)findViewById(R.id.fab);

        Bundle bundle = getIntent().getExtras();
        acode = bundle.getString("acode");

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (viewPager.getCurrentItem() == 1) {
                    ViewEventsFragment frag1 = (ViewEventsFragment) viewPager.getAdapter().instantiateItem(viewPager, viewPager.getCurrentItem());
                    frag1.onResume();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public Bundle getMyData(){
        Bundle hm=new Bundle();
        hm.putString("val1",acode);
        return hm;
    }
    public void adminLogout(View view){
        Intent intent=new Intent(this, AdminLogin.class);
        startActivity(intent);
    }
}