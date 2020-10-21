package com.example.foodorder.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodorder.Model.Login_user;
import com.example.foodorder.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User_Signup extends AppCompatActivity {
    EditText number,name,password,confirm_password;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__signup);


        number=(EditText) findViewById(R.id.user_register_number);
        name=(EditText) findViewById(R.id.user_register_name);
        password=(EditText) findViewById(R.id.user_register_password);
        confirm_password=(EditText) findViewById(R.id.user_confirm_register_password);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("users");
    }

    public void Register(View view) {
        final String str_number=number.getText().toString();
        final String str_name=name.getText().toString();
        final String str_password=password.getText().toString();
        final String str_isstaff="false";
        String str_confirm_password=confirm_password.getText().toString();
        if(validation(str_number,str_name,str_password,str_confirm_password)){
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(str_number).exists()){
                        Toast.makeText(User_Signup.this,"User already exist",Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        Login_user login_user=new Login_user(str_number,str_name,str_password,str_isstaff);
                        reference.child(str_number).setValue(login_user);

                        Toast.makeText(User_Signup.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                        Intent Login_intent=new Intent(User_Signup.this,Login.class);
                        startActivity(Login_intent);

                       finish();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

    public Boolean validation(String str_number, String str_name, String str_password, String str_confirm_password){
        Boolean isvalid=true;
        Pattern p=Pattern.compile("[A-Za-z]+");
        Matcher m=p.matcher(str_name);


        if(!(m.find())) {
            name.setError("Please Check name format");
            isvalid=false;
        }
        if(TextUtils.isEmpty(str_number)){
            isvalid=false;
            number.setError("Enter number");
        }
        if(TextUtils.isEmpty(str_name)){
            isvalid=false;
            name.setError("Enter Name");
        }
        if(TextUtils.isEmpty(str_password)){
            isvalid=false;
            password.setError("Enter password");
        }
        if(TextUtils.isEmpty(str_confirm_password)){
            isvalid=false;
            confirm_password.setError("Enter confirm password");
        }
        if(!str_password.contentEquals(str_confirm_password)){
            isvalid=false;
            confirm_password.setError("Password does not match");
        }

        return isvalid;
    }
}
