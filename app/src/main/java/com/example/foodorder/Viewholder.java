package com.example.foodorder;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.Customer.Interface.ItemClickListener;
import com.squareup.picasso.Picasso;

public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView myimage;
    TextView myname;
    ItemClickListener itemClickListener;

    View myview;

    public Viewholder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
    }




    public Viewholder(@NonNull View itemView) {
        super(itemView);
        myview=itemView;
        myimage=(ImageView) myview.findViewById(R.id.cust_menu_image);
        myname=(TextView) myview.findViewById(R.id.cust_menu_name);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setdetails(Context ctx, String image, String name){



        myname.setText(name);
       // Picasso.get().load(image).into(myimage);
        Picasso.get()
                .load(image).into(myimage);
        //Log.e("checking","image location ="+image);
       // Picasso.get().load(image).into(myimage);
       // Picasso.get().load(image).into(myimage);
        //Picasso.get().load(image).into(myimage);



    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }


}
