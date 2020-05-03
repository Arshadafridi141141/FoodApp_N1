package com.example.foodorder.Customer;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.foodorder.Customer.Interface.ItemClickListener;
import com.example.foodorder.Model.Order;
import com.example.foodorder.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView text_cart_name,text_cart_price;

    private ItemClickListener itemClickListener;

    public void setText_cart_name(TextView text_cart_name) {
        this.text_cart_name = text_cart_name;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        text_cart_name=(TextView) itemView.findViewById(R.id.cart_item_name);
        text_cart_price=(TextView)itemView.findViewById(R.id.cart_item_Price);

    }

    @Override
    public void onClick(View v) {

    }
}

public class CardAdapter extends RecyclerView.Adapter<CartViewHolder> {
    public List<Order> ListData=new ArrayList<>();
    public Context context;

    public CardAdapter(List<Order> listData, Context context) {
        ListData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemview=inflater.inflate(R.layout.cart_layout,parent,false);

        return new CartViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        Locale locale=new Locale("en","US");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        int price=(Integer.parseInt(ListData.get(position).getPrice()))*(Integer.parseInt(ListData.get(position).getQuantity()));
        holder.text_cart_price.setText(fmt.format(price));
        holder.text_cart_name.setText(ListData.get(position).getProductName());


    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }
}
