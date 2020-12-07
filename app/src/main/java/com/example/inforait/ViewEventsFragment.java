package com.example.inforait;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class ViewEventsFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<String> eventTitle = new ArrayList<>();
    ArrayList<String> sDescription= new ArrayList<>();
    ArrayList<Integer> interestedCount = new ArrayList<>();
    DataBase db;

    public void onResume() {
        eventTitle.clear();
        sDescription.clear();
        interestedCount.clear();
        db = new DataBase(getActivity());
        setHasOptionsMenu(true);

        TabViewActivity activity=(TabViewActivity) getActivity();
        Bundle results=activity.getMyData();
        acode=results.getString("val1");

        Cursor c=db.getEventDetails(acode);

        Log.d("count", String.valueOf(c.getCount()));
        for (int i = 0; i < c.getCount(); i++) {
            if( c != null && c.moveToNext() ) {
                eventTitle.add(c.getString(c.getColumnIndex("ETITLE")));
                sDescription.add(c.getString(c.getColumnIndex("SDESCRIPTION")));
                interestedCount.add(c.getInt(c.getColumnIndex("INTERESTED")));
            }
        }




        RecyclerAdapter adapter = new RecyclerAdapter(eventTitle, sDescription,interestedCount);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        super.onResume();
    }

    String acode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        db = new DataBase(getActivity());
        setHasOptionsMenu(true);

        TabViewActivity activity=(TabViewActivity) getActivity();
        Bundle results=activity.getMyData();
        acode=results.getString("val1");

        Cursor c=db.getEventDetails(acode);
        Log.d("count", String.valueOf(c.getCount()));
        for (int i = 0; i < c.getCount(); i++) {
            if( c != null && c.moveToNext() ) {
                eventTitle.add(c.getString(c.getColumnIndex("ETITLE")));
                sDescription.add(c.getString(c.getColumnIndex("SDESCRIPTION")));
                interestedCount.add(c.getInt(c.getColumnIndex("INTERESTED")));
            }
        }



        View view=inflater.inflate(R.layout.view_event_admin, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        RecyclerAdapter adapter = new RecyclerAdapter(eventTitle, sDescription,interestedCount);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//                FragmentTransaction ft = getFragmentManager().beginTransaction();



        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_card_demo, menu);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}