package com.example.inforait;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddEventFragment extends Fragment {
    String acode;
    Button createEvent;
    DataBase db;
    EditText eventTitle,eventSDescription,eventLDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_event, container, false);
        db = new DataBase(getActivity());
        eventTitle=(EditText)view.findViewById(R.id.title);
        eventSDescription=(EditText)view.findViewById(R.id.SDescription);
        eventLDescription=(EditText)view.findViewById(R.id.LDescription);
        createEvent=(Button)view.findViewById(R.id.create_event_button);

        TabViewActivity activity=(TabViewActivity) getActivity();
        Bundle results=activity.getMyData();
        acode=results.getString("val1");
        createEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String etitle = eventTitle.getText().toString().trim();
                String sdescription = eventSDescription.getText().toString().trim();
                String ldescription = eventLDescription.getText().toString().trim();

                if (eventTitle.getText().toString().length()==0 || eventSDescription.getText().toString().length()==0 || eventLDescription.getText().toString().length()==0) {
                    // Toast.makeText(getApplicationContext(), "please enter password upto 6char ", 1000).show();
                    Toast.makeText(getActivity(), "Error : Please enter all fields", Toast.LENGTH_LONG).show();

                }
                else {
                    long res = db.addEvent(acode, etitle, sdescription, ldescription);
                    Cursor res1 = db.getEventDetails(acode);

                    Log.d("admin result", String.valueOf(res));
                    if (res > 0) {

                        Toast.makeText(getActivity(), "Event has been added successfully!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Check Event Detail", Toast.LENGTH_SHORT).show();
                    }
                    eventTitle.setText("");
                    eventSDescription.setText("");
                    eventLDescription.setText("");
                }
            }
        });



        return view;
    }
}