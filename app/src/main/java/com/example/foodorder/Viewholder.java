package com.example.foodorder;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class Viewholder extends RecyclerView.ViewHolder {
    View myview;
    public Viewholder(@NonNull View itemView) {
        super(itemView);
        myview=itemView;
    }

    public void setdetails(Context ctx,String image, String name){


        ImageView myimage=(ImageView) myview.findViewById(R.id.cust_menu_image);
        TextView myname=(TextView) myview.findViewById(R.id.cust_menu_name);
        Log.e("checking",image);
        myname.setText(name);
       // Picasso.get().load(image).into(myimage);
        Picasso.get()
                .load(image).into(myimage);
        //Log.e("checking","image location ="+image);
       // Picasso.get().load(image).into(myimage);
       // Picasso.get().load(image).into(myimage);
        //Picasso.get().load(image).into(myimage);



    }
}
