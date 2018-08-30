package com.example.jarvis.sendmefood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jarvis.sendmefood.Current.Current;
import com.example.jarvis.sendmefood.MenuHld.OrderStatusHld;
import com.example.jarvis.sendmefood.Model.RqData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusOrdr extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<RqData,OrderStatusHld> adapter;

    FirebaseDatabase db;
    DatabaseReference db_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_ordr);

        db = FirebaseDatabase.getInstance();
        db_ref = db.getReference("Requests");

        recyclerView = (RecyclerView) findViewById(R.id.order_status_lists);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrdr(Current.usrCurrent.getNumber());
    }

    private void loadOrdr(String number) {

        adapter = new FirebaseRecyclerAdapter<RqData, OrderStatusHld>(
                RqData.class,
                R.layout.order_status_layout,
                OrderStatusHld.class,
                db_ref.orderByChild("numbers").equalTo(number)
        ) {
            @Override
            protected void populateViewHolder(OrderStatusHld viewHolder, RqData model, int position) {
                viewHolder.order_id.setText(adapter.getRef(position).getKey());
                viewHolder.order_status.setText(stat(model.getStatus()));
                viewHolder.order_address.setText(model.getAddress());
                viewHolder.order_phone.setText(model.getNumbers());
            }
        };

        recyclerView.setAdapter(adapter);

    }

    private String stat(String stat) {
        if("0".equals(stat)){
            return "Order is Already Place";
        }else if(stat.equals("1")){
            return "Food is on the way";
        }else{
            return "Food is already arrive";
        }
    }
}
