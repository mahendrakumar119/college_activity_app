package com.example.inforait;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

public class ProfileFragment extends Fragment {
    TextView username,fname,lname,email;
    ImageView pic;
    DataBase db;
    Cursor cursor;
    String value1;
    byte[] img;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        db = new DataBase(getActivity());
        pic=(ImageView)view.findViewById(R.id.profile_pic) ;
        username = (TextView)view.findViewById(R.id.profile_username);
        fname=(TextView)view.findViewById(R.id.profile_fname) ;
        lname=(TextView)view.findViewById(R.id.profile_lname) ;
        email=(TextView)view.findViewById(R.id.profile_email) ;
        MainActivity activity=(MainActivity)getActivity();
        Bundle results=activity.getMyData();
        Bundle results1=activity.getMyImage();
         value1=results.getString("val1");

        username.setText(value1);
        Log.d("uid",value1);
        cursor=db.getProfileDetails(value1);
        if( cursor != null && cursor.moveToFirst() ){
            byte[] byteArray=results1.getByteArray("image");
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


            pic.setImageBitmap(bitmap);
            fname.setText(cursor.getString(cursor.getColumnIndex("FIRSTNAME")));
            lname.setText(cursor.getString(cursor.getColumnIndex("LASTNAME")));
            String dummyfname=cursor.getString(cursor.getColumnIndex("FIRSTNAME"));
            String dummylname=cursor.getString(cursor.getColumnIndex("LASTNAME"));
            String dummy=dummylname+"."+dummyfname+"."+value1+"@gmail.com";
            email.setText(dummy);
            cursor.close();
        }




        return view;
    }

}
