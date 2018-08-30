package com.example.jarvis.sendmefood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jarvis.sendmefood.Current.Current;
import com.example.jarvis.sendmefood.Interface.ListenerClck;
import com.example.jarvis.sendmefood.MenuHld.ViewHld;
import com.example.jarvis.sendmefood.Model.Categories;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseRecyclerAdapter<Categories,ViewHld> adapter;

    FirebaseDatabase db;
    DatabaseReference db_ref;

    TextView user_fullname,my_number;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Send Me Food");
        setSupportActionBar(toolbar);

        db = FirebaseDatabase.getInstance();
        db_ref = db.getReference("Categories");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.order_window);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this,Order_Board.class);
//                startActivity(intent);
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        View top_menu = navigationView.getHeaderView(0);
        user_fullname = (TextView)top_menu.findViewById(R.id.user_fullname);
        user_fullname.setText(Current.usrCurrent.getName());
        my_number = (TextView)top_menu.findViewById(R.id.user_email);
        my_number.setText(Current.usrCurrent.getNumber());

        recycler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        mnLoad();


    }

    private void mnLoad() {

        adapter = new FirebaseRecyclerAdapter<Categories,ViewHld>(Categories.class,R.layout.list_item,ViewHld.class,db_ref){
            @Override
            protected void populateViewHolder(ViewHld viewHolder, Categories model, final int position) {
                viewHolder.name_menu.setText(model.getTitle());
                Picasso.with(getBaseContext()).load(model.getImg())
                        .into(viewHolder.img);
                final Categories click = model;
                viewHolder.setlistenerClck(new ListenerClck() {
                    @Override
                    public void onClick(int Pos, View view, boolean isClck) {
                        Intent intent = new Intent(Home.this,LstFood.class);
                        intent.putExtra("CatID",adapter.getRef(position).getKey());
                        startActivity(intent);
                    }
                });
            }
        };

        recycler_menu.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {
            Intent intent = new Intent(Home.this,Order_Board.class);
            startActivity(intent);
        } else if (id == R.id.nav_orders) {
            Intent intent = new Intent(Home.this,StatusOrdr.class);
            startActivity(intent);

        } else if (id == R.id.nav_menu) {

        } else if (id == R.id.sign_out) {

            Intent intent = new Intent(Home.this,Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
