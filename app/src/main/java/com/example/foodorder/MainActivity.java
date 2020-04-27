package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodorder.Customer.Login;
import com.example.foodorder.Customer.Menu;
import com.example.foodorder.Customer.User_Signup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button Signin,Signup;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Signin=(Button)findViewById(R.id.btnSignin);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("Users");


    }

    public void Signin(View view) {
        Intent intent=new Intent(MainActivity.this, Menu.class);
        startActivity(intent);
    }

    public void signup(View view) {
        Intent intent=new Intent(MainActivity.this, User_Signup.class);
        startActivity(intent);
    }
}
