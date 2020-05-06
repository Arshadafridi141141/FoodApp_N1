package com.example.foodorder.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodorder.Customer.Common.Common;
import com.example.foodorder.Model.Login_user;
import com.example.foodorder.Navigation_drawer;
import com.example.foodorder.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText number,password;
    Button login;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        number=(EditText) findViewById(R.id.login_number);
        password=(EditText)findViewById(R.id.login_password);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("users");
    }

    public void log_in(View view) {
        final String str_number;
       final String str_password;
        str_number=number.getText().toString();
        str_password=password.getText().toString();

        if(Validation(str_number,str_password)) {


            reference.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Login_user login_user=dataSnapshot.child(str_number).getValue(Login_user.class);


                   if (dataSnapshot.child(str_number).exists()){

                       if (login_user.getPassword().equals(str_password)) {
                            Toast.makeText(Login.this, "Sign in Succssfully", Toast.LENGTH_SHORT).show();
                           Common.current_user=login_user;
                           Intent intent=new Intent(Login.this, Navigation_drawer.class);
                           startActivity(intent);
                        } else {

                            Toast.makeText(Login.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(Login.this, "User does not exist", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }

    private boolean Validation(String str_number, String str_password) {
        Boolean isvalid=true;
        if(TextUtils.isEmpty(str_number)){
            isvalid=false;
            number.setError("Enter number");
        }
        if(TextUtils.isEmpty(str_password)){
            isvalid=false;
            password.setError("Enter Password");
        }

        return isvalid;
    }
}
