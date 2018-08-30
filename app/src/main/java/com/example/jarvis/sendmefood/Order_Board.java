package com.example.jarvis.sendmefood;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarvis.sendmefood.Current.Current;
import com.example.jarvis.sendmefood.Databases.Databases;
import com.example.jarvis.sendmefood.MenuHld.OrderAdapter;
import com.example.jarvis.sendmefood.Model.Orders;
import com.example.jarvis.sendmefood.Model.RqData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Order_Board extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase db;
    DatabaseReference db_ref;

    TextView totalPrice;
    Button order_submit;

    List<Orders> orders = new ArrayList<>();

    OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__board);

        db = FirebaseDatabase.getInstance();
        db_ref = db.getReference("Requests");

        recyclerView = (RecyclerView) findViewById(R.id.list_orders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        totalPrice = (TextView) findViewById(R.id.sum);
        order_submit = (Button) findViewById(R.id.submit_order);

        order_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialg();
            }
        });

        getOrders();
    }

    private void alertDialg() {
        AlertDialog.Builder alrt = new AlertDialog.Builder(Order_Board.this);
        alrt.setTitle("Please enter yout address: ");

        final EditText addrs = new EditText(Order_Board.this);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        addrs.setLayoutParams(l);
        addrs.setTextColor(Color.BLACK);
        alrt.setView(addrs);
        alrt.setIcon(R.drawable.ic_local_grocery_store_black_24dp);

        alrt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RqData rq = new RqData(
                        Current.usrCurrent.getNumber(),
                        addrs.getText().toString(),
                        totalPrice.getText().toString(),
                        orders,
                        Current.usrCurrent.getName(),
                        "0"
                );

                db_ref.child(String.valueOf(System.currentTimeMillis())).setValue(rq);

                new Databases(getBaseContext()).clearOrders();
                Toast.makeText(Order_Board.this,"The rquest is already send",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alrt.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alrt.show();
    }

    private void getOrders() {
        orders = new Databases(this).getFood();
        adapter = new OrderAdapter(orders,this);

        recyclerView.setAdapter(adapter);
        int priceTotal = 0;
        for(Orders myOrder:orders) {
            priceTotal = priceTotal + (Integer.parseInt(myOrder.getPrice()) * Integer.parseInt(myOrder.getQntity()));
        }
        Locale loc = new Locale("en","US");
        NumberFormat format = NumberFormat.getCurrencyInstance(loc);
        totalPrice.setText(format.format(priceTotal));
    }
}
