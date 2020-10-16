package com.example.foodorder.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodorder.Customer.Common.Common;
import com.example.foodorder.Model.Request;
import com.example.foodorder.R;
import com.example.foodorder.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderStatus extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");

        recyclerView=(RecyclerView)findViewById(R.id.list_orders);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadorders(Common.current_user.getNumber());

    }

    private void loadorders(String number) {

        adapter=new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requests.orderByChild("phone").equalTo(number)
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder orderViewHolder, Request request, int i) {
                orderViewHolder.orderid.setText(adapter.getRef(i).getKey());
                orderViewHolder.orderstatus.setText(convertCodeToStatus(request.getStatus()));
                orderViewHolder.orderphone.setText(request.getPhone());
                orderViewHolder.orderaddress.setText(request.getAddress());
                orderViewHolder.totalPrice.setText(request.getTotal());


            }
        };

        recyclerView.setAdapter(adapter);
    }

    public  String convertCodeToStatus(String status){
        if(status.equals("0"))
            return "Placed";
        else if(status.equals("1"))
            return "On way";
        else
                return "shipped";

    }
}
