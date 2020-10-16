package com.example.foodorder.Customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorder.Customer.Common.Common;
import com.example.foodorder.Database.Database;
import com.example.foodorder.Model.Order;
import com.example.foodorder.Model.Request;
import com.example.foodorder.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference requests;
    TextView totalprice;
    Button placeorder;
    List<Order> cart=new ArrayList<>();
    CardAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");
        recyclerView=(RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        totalprice=(TextView) findViewById(R.id.TotalPrice);
        placeorder=(Button) findViewById(R.id.btnCart);

        LoadListFood();



    }
    public void LoadListFood(){
        cart=new Database(this).getCarts();
        adapter=new CardAdapter(cart,this);
        recyclerView.setAdapter(adapter);
        int total=0;
        for (Order order : cart) {
               total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
           }



        Locale locale=new Locale("en","US");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        String str_tota = String.valueOf(total);
        totalprice.setText(str_tota);

    }

    public void Place_Order(View view) {
        String price=totalprice.getText().toString();
        if(price.equals("0")){
            Toast.makeText(getBaseContext(),"please place at least one order",Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(Cart.this);
            builder.setMessage("Enter your Address");
            builder.setTitle("One Step to go");
            final EditText setAddress = new EditText(Cart.this);
            LinearLayout.LayoutParams Ip = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            setAddress.setLayoutParams(Ip);
            builder.setView(setAddress);
            builder.setIcon(R.drawable.cart_icon_foreground);


                builder.setCancelable(false);
                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Request request = new Request(
                                        Common.current_user.getName(),
                                        Common.current_user.getNumber(),
                                        totalprice.getText().toString(),
                                        setAddress.getText().toString()
                                        //solve problem for food list
                                );
                                if(Validationn(setAddress)) {
                                    requests.child(Common.current_user.getNumber()).setValue(request);
                                    new Database(getBaseContext()).clearcart();
                                    dialog.dismiss();
                                    Toast.makeText(getBaseContext(),"Order is Placed",Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getBaseContext(),"Please enter valid address",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        });



            AlertDialog alertDialog = builder.create();

            alertDialog.show();
        }
    }

    private boolean Validationn(EditText setAddress) {
        Pattern p=Pattern.compile("[A-Za-z]+");
        String address=setAddress.getText().toString();
        final Matcher m=p.matcher(address);
        if(m.find())
            return  true;
        else
            return  false;

    }
}
