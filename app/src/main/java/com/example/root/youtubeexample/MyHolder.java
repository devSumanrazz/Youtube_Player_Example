package com.example.root.youtubeexample;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by root on 8/9/17.
 */

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView title,date;
    RelativeLayout rootelement;
    public MyHolder(View itemView) {
        super(itemView);

        title =itemView.findViewById(R.id.title);
        date=itemView.findViewById(R.id.date);
        image=itemView.findViewById(R.id.image);
        rootelement=itemView.findViewById(R.id.rootElement);



    }


}
