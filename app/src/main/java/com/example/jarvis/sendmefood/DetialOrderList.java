package com.example.jarvis.sendmefood;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.jarvis.sendmefood.Current.Current;
import com.example.jarvis.sendmefood.Interface.ListenerClck;
import com.example.jarvis.sendmefood.MenuHld.LstFoodHld;
import com.example.jarvis.sendmefood.MenuHld.OrderDetailAdapter;
import com.example.jarvis.sendmefood.Model.Orders;
import com.example.jarvis.sendmefood.Model.RqData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetialOrderList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Orders,LstFoodHld> adapter;

    FirebaseDatabase db;
    DatabaseReference db_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_order_list);
        db = FirebaseDatabase.getInstance();
        db_ref = db.getReference("Requests").child(Current.currentRqData.getKey()).child("orders");

        recyclerView = (RecyclerView) findViewById(R.id.detailOrderList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//
//        OrderDetailAdapter adapter = new OrderDetailAdapter(Current.currentRqData.getOrders());
//        adapter.notifyDataSetChanged();
//        recyclerView.setAdapter(adapter);
        getOrder();
    }

    private void getOrder() {
        adapter = new FirebaseRecyclerAdapter<Orders, LstFoodHld>(
                Orders.class,
                R.layout.order_detail_list,
                LstFoodHld.class,
                db_ref
        ) {
            @Override
            protected void populateViewHolder(LstFoodHld viewHolder, Orders model, int position) {
                viewHolder.Price.setText(String.format("Price: $%s",model.getPrice()));
                viewHolder.Qantity.setText(String.format("Quantity: %s",model.getQntity()));
                viewHolder.Status.setText(String.format("Status: %s",statusCode(model.getStatus())));
                viewHolder.Name.setText(model.getProdName());
                viewHolder.Owner.setText(String.format("Phone: %s",model.getOwner()));
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


    }

    public static String statusCode(String code){
        if(code.equals("0")){
            return "your order is placed";
        }else if(code.equals("1")){
            return "your order is on the way";
        }else{
            return "your order is already shipped";
        }
    }

}
