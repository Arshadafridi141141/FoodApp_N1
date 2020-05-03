package com.example.foodorder.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.foodorder.Model.Order;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper{
    public static String Database_name="FoodOrder.db";
    public static String Table_name="OrderDetails";
    public static String COL_1="ID";
    public static String COL_2="ProductID";
    public static String COL_3="ProductName";
    public static String COL_4="Quantity";
    public static String COL_5="Price";
    public static String COL_6="Discount";
    public Database( Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String order_details="CREATE TABLE \"OrderDetails\" (\n" +
                "\t\"ID\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,\n" +
                "\t\"ProductID\"\tTEXT,\n" +
                "\t\"ProductName\"\tTEXT,\n" +
                "\t\"Quantity\"\tTEXT,\n" +
                "\t\"Price\"\tTEXT,\n" +
                "\t\"Discount\"\tINTEGER\n" +
                ");";
        db.execSQL(order_details);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);

    }
    public void addToCart(Order order){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues con=new ContentValues();
        con.put(COL_2,order.getProductId());
        con.put(COL_3,order.getProductName());
        con.put(COL_4,order.getQuantity());
        con.put(COL_5,order.getPrice());
        con.put(COL_6,order.getDiscount());
        database.insert(Table_name,null,con);


    }
    public final List<Order> getCarts(){
        String querry = "select * from "+Table_name;

        SQLiteDatabase database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery(querry,null);

        if (cursor.getCount()!=1){
            List<Order> orders=new ArrayList<>();
            while (cursor.moveToNext()){
                String id=cursor.getString(cursor.getColumnIndex(COL_2));
                String name=cursor.getString(cursor.getColumnIndex(COL_3));
                String Quantity=cursor.getString(cursor.getColumnIndex(COL_4));
                String Price=cursor.getString(cursor.getColumnIndex(COL_5));
                String Discount=cursor.getString(cursor.getColumnIndex(COL_6));
                Order order=new Order();
                order.setProductId(id);
                order.setProductName(name);
                order.setQuantity(Quantity);
                order.setPrice(Price);
                order.setDiscount(Discount);
                orders.add(order);

            }
            return orders;

        }

        return null;

    }

}