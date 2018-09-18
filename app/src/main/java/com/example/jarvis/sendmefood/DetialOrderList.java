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
import com.example.jarvis.sendmefood.MenuHld.OrderDetailAdapter;
import com.example.jarvis.sendmefood.Model.Orders;
import com.example.jarvis.sendmefood.Model.RqData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetialOrderList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_order_list);

        recyclerView = (RecyclerView) findViewById(R.id.detailOrderList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        OrderDetailAdapter adapter = new OrderDetailAdapter(Current.currentRqData.getOrders());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

}
