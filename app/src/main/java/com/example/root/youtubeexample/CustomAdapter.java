package com.example.root.youtubeexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 8/9/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<MyHolder> implements View.OnClickListener{
    Context c;
    List<Module> alist;
    LayoutInflater inflater;
    public CustomAdapter(MainActivity mainActivity, ArrayList<Module> mydata) {
        c=mainActivity;
        alist =mydata;
        inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView =inflater.inflate(R.layout.single_item,null);
        MyHolder mholder = new MyHolder(convertView);
        convertView.setOnClickListener(this);


        return mholder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        holder.title.setText("Title :"+alist.get(position).getTitle());
        holder.date.setText("Date :"+alist.get(position).getDate());
        Picasso.with(c).load(alist.get(position).getUrl()).into(holder.image);

        holder.rootelement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(c,PlayerActivity.class);
                Bundle bundle = new Bundle();
                 bundle.putString("key",alist.get(position).getVideoid());
                i.putExtras(bundle);
                c.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return alist.size();
    }


    @Override
    public void onClick(View view) {
        //int position = view.
    }

}
