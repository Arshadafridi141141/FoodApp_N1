package com.example.foodorder.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.foodorder.Database.Database;
import com.example.foodorder.Model.Fooditem;
import com.example.foodorder.Model.Order;
import com.example.foodorder.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Fooddetails extends AppCompatActivity {
    TextView foodname,foodprice,fooddescription;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton cart_button;
    ElegantNumberButton number_button;
    String foodid="";
    FirebaseDatabase database;
    DatabaseReference foods;
    Fooditem currentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddetails);
        database=FirebaseDatabase.getInstance();
        foods=database.getReference().child("Food_details");
        number_button=(ElegantNumberButton) findViewById(R.id.number_button);
        cart_button=(FloatingActionButton)findViewById(R.id.btnCart);


        cart_button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodid,
                        currentFood.getName(),
                        number_button.getNumber(),
                        currentFood.getPrice(),
                        "100"
                ));
                Toast.makeText(Fooddetails.this, "added to cart", Toast.LENGTH_SHORT).show();



            }
        }


        );


        foodname=(TextView)findViewById(R.id.food_name);
        foodprice=(TextView)findViewById(R.id.food_price);
        fooddescription=(TextView)findViewById(R.id.food_description);
        food_image=(ImageView) findViewById(R.id.detail_image);
        collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.ExpendedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        if (getIntent()!=null)
            foodid=getIntent().getStringExtra("Foodid");

        if(!foodid.isEmpty()){
            getDetailFood(foodid);
        }
    }
    void getDetailFood(final String Foodid){
        foods.child(Foodid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentFood=dataSnapshot.getValue(Fooditem.class);
                Picasso.get().load(currentFood.getImage()).into(food_image);
                foodprice.setText(currentFood.getPrice());
                fooddescription.setText(currentFood.getDescription());
                foodname.setText(currentFood.getName());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
