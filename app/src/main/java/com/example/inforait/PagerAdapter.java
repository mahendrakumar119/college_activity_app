package com.example.inforait;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mTabs;
    public PagerAdapter(@NonNull FragmentManager fm, int mTabs) {
        super(fm);
        this.mTabs = mTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                AddEventFragment Tab1 = new AddEventFragment();
                return Tab1;
            case 1:
                ViewEventsFragment Tab2 = new ViewEventsFragment();
                return Tab2;
            case 2:
                ThirdFragment Tab3 = new ThirdFragment();
                return Tab3;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mTabs;
    }
}
