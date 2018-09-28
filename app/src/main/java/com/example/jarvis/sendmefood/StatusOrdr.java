package com.example.jarvis.sendmefood;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jarvis.sendmefood.Current.Current;
import com.example.jarvis.sendmefood.Interface.ListenerClck;
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
            protected void populateViewHolder(OrderStatusHld viewHolder, final RqData model, final int position) {
                viewHolder.order_id.setText(String.format("#%s",adapter.getRef(position).getKey()));
                viewHolder.order_address.setText(model.getAddress());
                viewHolder.order_phone.setText(model.getNumbers());
                viewHolder.btn_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(StatusOrdr.this,DetialOrderList.class);
                        model.setKey(adapter.getRef(position).getKey());
                        Current.currentRqData = model;
                        startActivity(intent);
                    }
                });
                viewHolder.setListenerClck(new ListenerClck() {
                    @Override
                    public void onClick(int Pos, View view, boolean isClck) {

                    }
                });

                viewHolder.cmplt_ordr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OrderComplete(adapter.getRef(position).getKey());
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }

    private void OrderComplete(final String key) {
        final AlertDialog.Builder alertDialg = new AlertDialog.Builder(StatusOrdr.this);
        alertDialg.setTitle("Please enter yout address: ");

        final TextView addrs = new TextView(StatusOrdr.this);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        addrs.setText("Are you sure ?");
        addrs.setPadding(300,0,0,0);
        addrs.setTextColor(Color.BLACK);
        addrs.setTextSize(20);
        alertDialg.setView(addrs);
        alertDialg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDialg(key);
                dialog.dismiss();
            }
        });
        alertDialg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialg.show();
    }

    private void deleteDialg(String key) {
        db_ref.child(key).removeValue();
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
