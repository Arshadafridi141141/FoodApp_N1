package com.example.foodorder.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodorder.Customer.Interface.ItemClickListener;
import com.example.foodorder.Model.Fooditem;
import com.example.foodorder.R;
import com.example.foodorder.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.util.Objects.requireNonNull;

public class Menu extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    FloatingActionButton cart;
    FirebaseRecyclerAdapter<Fooditem, Viewholder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        RecyclerView recyclerView;
        final FirebaseDatabase database;
        DatabaseReference reference;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("Food_details");
       recyclerView=(RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cart=(FloatingActionButton) findViewById(R.id.meni_cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,Cart.class);
                startActivity(intent);
            }
        });



        firebaseRecyclerAdapter=

                new FirebaseRecyclerAdapter<Fooditem, Viewholder>(
                        Fooditem.class,
                        R.layout.customized_menu,
                        Viewholder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(Viewholder viewholder, final Fooditem food, int i) {

                        viewholder.setdetails(getApplicationContext(),food.getImage(),food.getName());

                        viewholder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean islongclick) {
                                Intent foodetail=new Intent(Menu.this,Fooddetails.class);
                                foodetail.putExtra("Foodid",firebaseRecyclerAdapter.getRef(position).getKey());
                                startActivity(foodetail);
                            }
                        });
                    }
                };
       recyclerView.setAdapter(firebaseRecyclerAdapter);


    }



}
