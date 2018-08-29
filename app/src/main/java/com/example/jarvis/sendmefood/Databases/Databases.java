package com.example.jarvis.sendmefood.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.jarvis.sendmefood.Model.Orders;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Databases extends SQLiteAssetHelper {

    public Databases(Context context) {
        super(context, "SendMeFood.db",null, 1);
    }

    public List<Orders> getFood(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder query = new SQLiteQueryBuilder();
        String[] select = {"ProdId","ProdName","Qntity","Price","Discnt"};
        String table = "Order_Detail";
        query.setTables(table);

        Cursor cur = query.query(db,select,null,null,null,null,null);
        final List<Orders> rslt = new ArrayList<>();
        if(cur.moveToFirst()){
            do{
                rslt.add(new Orders(cur.getString(cur.getColumnIndex("ProdId")),
                        cur.getString(cur.getColumnIndex("ProdName")),
                        cur.getString(cur.getColumnIndex("Qntity")),
                        cur.getString(cur.getColumnIndex("Price")),
                        cur.getString(cur.getColumnIndex("Discnt"))
                        ));
            }while(cur.moveToNext());
        }
        return rslt;
    }

    public void clearOrders(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Order_Detail");
        db.execSQL(query);
    }

    public void addFood(Orders orders){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("Insert INTO Order_Detail(ProdId,ProdName,Qntity,Price,Discnt) VALUES('%s','%s','%s','%s','%s');",
                orders.getProdId(),orders.getProdName(),orders.getQntity(),orders.getPrice(),orders.getDiscnt());
        db.execSQL(query);
    }
}
