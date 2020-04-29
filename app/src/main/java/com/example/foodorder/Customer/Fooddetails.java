package com.example.foodorder.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.foodorder.Model.Fooditem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddetails);
        database=FirebaseDatabase.getInstance();
        foods=database.getReference().child("Food_details");
        number_button=(ElegantNumberButton) findViewById(R.id.number_button);
        cart_button=(FloatingActionButton)findViewById(R.id.btnCart);


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
                Fooditem food=dataSnapshot.getValue(Fooditem.class);
                Picasso.get().load(food.getImage()).into(food_image);
                foodprice.setText(food.getPrice());
                fooddescription.setText(food.getDescription());
                foodname.setText(food.getName());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
