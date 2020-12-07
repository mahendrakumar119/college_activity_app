package com.example.inforait;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> sDescription = new ArrayList<>();
    private ArrayList<Integer> interestedCount = new ArrayList<>();
    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        public TextView itemInterestedCount;
        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemDetail = (TextView)itemView.findViewById(R.id.item_detail);
            itemInterestedCount = (TextView)itemView.findViewById(R.id.item_interested_count);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    int position = getAdapterPosition();
//
//                    Snackbar.make(v, "Click detected on item " + position,
//                            Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//
//                }
//            });
        }
    }
    public RecyclerAdapter(ArrayList<String> mDataSet1,ArrayList<String> mDataSet2,ArrayList<Integer> mDataSet3){
        titles = mDataSet1;
        sDescription = mDataSet2;
        interestedCount = mDataSet3;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_view_event, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText("Event Title: "+titles.get(i));
        viewHolder.itemDetail.setText("Event Details: "+sDescription.get(i));
        viewHolder.itemInterestedCount.setText("Interested: "+String.valueOf(interestedCount.get(i)));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
}