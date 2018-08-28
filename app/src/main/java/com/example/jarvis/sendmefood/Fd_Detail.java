package com.example.jarvis.sendmefood;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.jarvis.sendmefood.Model.MyFood;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Fd_Detail extends AppCompatActivity {

    TextView fd_name,fd_price,fd_description;
    ImageView fd_img;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btn_Cart;
    ElegantNumberButton nmb_Btn;
    String food_ID = "";

    FirebaseDatabase db;
    DatabaseReference db_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd__detail);

        db = FirebaseDatabase.getInstance();
        db_ref = db.getReference("Foods");

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse);

        btn_Cart = (FloatingActionButton) findViewById(R.id.buyBtn);
        nmb_Btn = (ElegantNumberButton) findViewById(R.id.num_btn);
        fd_description = (TextView) findViewById(R.id.fd_description);
        fd_price = (TextView) findViewById(R.id.fd_price);
        fd_name = (TextView) findViewById(R.id.my_fd_name);
        fd_img = (ImageView) findViewById(R.id.food_image_header);

        if(getIntent() != null){
            food_ID = getIntent().getStringExtra("Fd_ID");
        }
        if(!food_ID.isEmpty()){
            detailFood(food_ID);
        }

    }

    private void detailFood(String food_id) {
        db_ref.child(food_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MyFood fd = dataSnapshot.getValue(MyFood.class);
                Picasso.with(getBaseContext()).load(fd.getImage()).into(fd_img);

                collapsingToolbarLayout.setTitle(fd.getName());
                fd_price.setText(fd.getPrice());
                fd_name.setText(fd.getName());
                fd_description.setText(fd.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
