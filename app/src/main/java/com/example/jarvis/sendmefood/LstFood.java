package com.example.jarvis.sendmefood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jarvis.sendmefood.Interface.ListenerClck;
import com.example.jarvis.sendmefood.MenuHld.FdViewHld;
import com.example.jarvis.sendmefood.Model.MyFood;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class LstFood extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<MyFood,FdViewHld> adapter;

    String catID = "";

    FirebaseDatabase db;
    DatabaseReference db_rf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_food);

        db = FirebaseDatabase.getInstance();
        db_rf = db.getReference("Foods");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null){
            catID = getIntent().getStringExtra("CatID");
        }

        if(!catID.isEmpty() && catID != null){
            getListFood(catID);
        }
    }

    private void getListFood(String catID) {
        adapter = new FirebaseRecyclerAdapter<MyFood, FdViewHld>(MyFood.class,R.layout.food_itm,FdViewHld.class,db_rf.orderByChild("menuId").equalTo(catID)) {
            @Override
            protected void populateViewHolder(FdViewHld viewHolder, MyFood model, final int position) {
                viewHolder.fd_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.fd_image);
                final MyFood locate = model;
                viewHolder.setListenerClck(new ListenerClck() {
                    @Override
                    public void onClick(int Pos, View view, boolean isClck) {
                        Log.d("TAG","CLICKCLICKCLICKCLICKCLICKCLICKCLICK");
                        Intent intent = new Intent(LstFood.this,Fd_Detail.class);
                        intent.putExtra("Fd_ID",adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);
    }
}
