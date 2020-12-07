package com.example.inforait;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<String> eventTitle = new ArrayList<>();
    ArrayList<String> sDescription= new ArrayList<>();
    ArrayList<String> lDescription = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();
    String value1;
    Context context;
    SQLiteDatabase helper;
    DataBase db;
    RecyclerAdapter2 adapter1;
    private TextView textView;

    String acode;

//Updation of Acode is here------------------------
    public void UpdateAcode(String acode){
        Cursor c=db.getEventDetails(acode);


        for (int i = 0; i < c.getCount(); i++) {
            if( c != null && c.moveToNext() ) {
                eventTitle.add(c.getString(c.getColumnIndex("ETITLE")));
                sDescription.add(c.getString(c.getColumnIndex("SDESCRIPTION")));
                lDescription.add(c.getString(c.getColumnIndex("LDESCRIPTION")));
                id.add(c.getInt(c.getColumnIndex("ID")));

            }
        }

        adapter1 = new RecyclerAdapter2(eventTitle, sDescription,lDescription,id,getActivity(),value1);
        recyclerView.setAdapter(adapter1);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }
//Updation of Acode ends here----------------------

//Clearing ArrayList-------------------------------
    public void clearList() {
        eventTitle.clear();
        sDescription.clear();
        lDescription.clear();
        id.clear();
    }
//Done clearing---------------------------------
    public void AllDetails(){
        Cursor c=db.getAllEventDetails();
        Log.d("inside culture btn", String.valueOf(c.getCount()));


        Log.d("count", String.valueOf(c.getCount()));
        for (int i = 0; i < c.getCount(); i++) {
            if( c != null && c.moveToNext() ) {
                eventTitle.add(c.getString(c.getColumnIndex("ETITLE")));
                sDescription.add(c.getString(c.getColumnIndex("SDESCRIPTION")));
                lDescription.add(c.getString(c.getColumnIndex("LDESCRIPTION")));
                id.add(c.getInt(c.getColumnIndex("ID")));
            }
        }

        adapter1 = new RecyclerAdapter2(eventTitle, sDescription,lDescription,id,getActivity(),value1);
        recyclerView.setAdapter(adapter1);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }
//    public void changeItem(int position) {
//        mExampleList.get(position).changeText1(text);
//        mAdapter.notifyItemChanged(position);
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        MainActivity activity = (MainActivity) getActivity();

        Bundle results = activity.getMyData();
        value1 = results.getString("val1");

        Button b1 = (Button)view.findViewById(R.id.cultural);
        Button b2 = (Button)view.findViewById(R.id.workshop);
        Button b3 = (Button)view.findViewById(R.id.placement);
        Button b4 = (Button)view.findViewById(R.id.all);
        textView = view.findViewById(R.id.item_id);
        Button interested = (Button)view.findViewById(R.id.interested_button);

//        Button interested = (Button)view.findViewById(R.id.interested);
        db = new DataBase(getActivity());
        setHasOptionsMenu(true);
        //Default all values------------------------------------
        clearList();
        Cursor c=db.getAllEventDetails();
        for (int i = 0; i < c.getCount(); i++) {
            if( c != null && c.moveToNext() ) {
                eventTitle.add(c.getString(c.getColumnIndex("ETITLE")));
                sDescription.add(c.getString(c.getColumnIndex("SDESCRIPTION")));
                lDescription.add(c.getString(c.getColumnIndex("LDESCRIPTION")));
                id.add(c.getInt(c.getColumnIndex("ID")));

            }
        }
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        RecyclerAdapter2 adapter = new RecyclerAdapter2(eventTitle, sDescription,lDescription,id,getActivity(),value1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//        interested.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        //Ending of default all code----------------------------
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
                AllDetails();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
                acode = "101";
                UpdateAcode(acode);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
                acode = "102";
                UpdateAcode(acode);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
                acode = "103";
                UpdateAcode(acode);
            }
        });


        return view;
    }

//    RecyclerAdapter2.RecyclerAdapter2Interface adapterInterface = new RecyclerAdapter2.RecyclerAdapter2Interface() {
//        @Override
//        public void OnItemClicked(int item_id) {
//            db.buttonClicked(item_id);
//        }
//    };

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