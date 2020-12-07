package com.example.inforait;


import android.app.MediaRouteButton;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> sDescription = new ArrayList<>();
    private ArrayList<String> lDescription = new ArrayList<>();
    private ArrayList<Integer> id = new ArrayList<>();

    String user;
    SQLiteDatabase db;
    DataBase helper;
    ContentValues contentValues;
    ContentValues contentValues1;
    Context context;


//    private RecyclerAdapter2Interface adapterInterface;
//    public onItemClickListener mListener;
//    public interface onItemClickListener{
//        void onItemClick(int position);
//    }

    public RecyclerAdapter2(ArrayList<String> mDataSet1, ArrayList<String> mDataSet2, ArrayList<String> mDataSet3,ArrayList<Integer> mDataSet4,Context context,String user) {
        titles = mDataSet1;
        sDescription = mDataSet2;
        lDescription = mDataSet3;
        id = mDataSet4;
        this.user = user;
        this.context = context;
        helper = new DataBase(context);
        db = helper.getWritableDatabase();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        public TextView itemDescription;
        public TextView itemId;
        public Button interested;


        public ViewHolder(View itemView) {
            super(itemView);

            itemTitle = (TextView) itemView.findViewById(R.id.item_title);
            itemDetail = (TextView) itemView.findViewById(R.id.item_detail);
            itemDescription = (TextView) itemView.findViewById(R.id.item_interested_count);
            itemId = (TextView) itemView.findViewById(R.id.item_id);
            interested = (Button) itemView.findViewById(R.id.interested_button);






            interested.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    Log.d("position", String.valueOf(id.get(position)));
                    contentValues = new ContentValues();
                    contentValues1 = new ContentValues();
                    Cursor c  = db.rawQuery("select interested from events where ID =?",new String[]{String.valueOf(id.get(position))});
                    int i = 0;

                    //Toast.makeText(context,String.valueOf(c.getCount()), Toast.LENGTH_LONG).show();
                    if(c!=null && c.moveToFirst()){
                        //Toast.makeText(context,String.valueOf(c.getInt(c.getColumnIndex("INTERESTED"))) , Toast.LENGTH_LONG).show();
                        i  = c.getInt(c.getColumnIndex("INTERESTED")) + 1;
                    }

                    contentValues1.put(DataBase.Enrolled_COL_1,user);
                    contentValues1.put(DataBase.Enrolled_COL_2,String.valueOf(id.get(position)));
                    if((db.insert(DataBase.TABLE_NAME3,null,contentValues1))!=-1){
                       // Toast.makeText(context,"Row added", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(context,"error thrown", Toast.LENGTH_LONG).show();
                    }
                    contentValues.put(DataBase.Event_COL_6,String.valueOf(i));
                    if((db.update(DataBase.TABLE_NAME2,contentValues, DataBase.Event_COL_1 + " = ?",new String[]{String.valueOf(id.get(position))}))>0){
                        notifyItemChanged(position);
                        Toast.makeText(context,"You showed interest!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(context,"error", Toast.LENGTH_LONG).show();
                    }

                  //  helper.buttonClicked(id.get(position));
//                    Snackbar.make(v, "Click detected on item " + position,
//                            Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();

                }
            });
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_view_student, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        viewHolder.itemTitle.setText("Event Title: " + titles.get(i));
        viewHolder.itemDetail.setText("Event Details: " + sDescription.get(i));
        viewHolder.itemDescription.setText("More Information: " + lDescription.get(i));
        viewHolder.itemId.setText("ID: " + String.valueOf(id.get(i)));

            Cursor c  = db.rawQuery("select * from enrolled where USERNAME =? and ID = ?",new String[]{user, String.valueOf(id.get(i))});
            Log.d("c", String.valueOf(c.getCount()));

            //Toast.makeText(context,String.valueOf(c.getInt(c.getColumnIndex("INTERESTED"))) , Toast.LENGTH_LONG).show();
            if(c.getCount()>=1){

                Log.d("Gone","Hiii");
                viewHolder.interested.setVisibility(View.INVISIBLE);
            }


    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
//    public interface RecyclerAdapter2Interface{
//        void OnItemClicked(int item_id);
//    }
}