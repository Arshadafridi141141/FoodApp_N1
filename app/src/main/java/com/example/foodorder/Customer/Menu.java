package com.example.foodorder.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.foodorder.Model.Food;
import com.example.foodorder.R;
import com.example.foodorder.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.util.Objects.requireNonNull;

public class Menu extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        RecyclerView recyclerView;
        final FirebaseDatabase database;
        DatabaseReference reference;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("Foods");
       recyclerView=(RecyclerView) findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerAdapter<Food, Viewholder> firebaseRecyclerAdapter=

                new FirebaseRecyclerAdapter<Food, Viewholder>(
                        Food.class,
                        R.layout.customized_menu,
                        Viewholder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(Viewholder viewholder, Food food, int i) {

                        viewholder.setdetails(getApplicationContext(),food.getImage(),food.getName());

                    }
                };


       recyclerView.setAdapter(firebaseRecyclerAdapter);


    }



}
