package com.example.foodorder.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.Customer.Interface.ItemClickListener;
import com.example.foodorder.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView orderid,orderstatus,orderphone,orderaddress;
    public ItemClickListener itemClickListener;
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        orderid=(TextView) itemView.findViewById(R.id.order_id);
        orderstatus=(TextView) itemView.findViewById(R.id.order_status);
        orderphone=(TextView) itemView.findViewById(R.id.order_phone);
        orderaddress=(TextView) itemView.findViewById(R.id.order_address);
    }

    public OrderViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }
}
