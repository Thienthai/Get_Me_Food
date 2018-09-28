package com.example.jarvis.sendmefood.MenuHld;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.jarvis.sendmefood.Model.Orders;
import com.example.jarvis.sendmefood.R;

import java.util.List;

class MyViewHld extends RecyclerView.ViewHolder{

    public TextView name,price,status;

    public ImageView qntity;

    public MyViewHld(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.order_detail_item_name);
        price = (TextView) itemView.findViewById(R.id.order_detail_item_price);
        status = (TextView) itemView.findViewById(R.id.order_detail_item_status);
        qntity = (ImageView) itemView.findViewById(R.id.order_detail_item_qntity);
    }
}

public class  OrderDetailAdapter extends RecyclerView.Adapter<MyViewHld> {

    List<Orders> myOrders;

    public OrderDetailAdapter(List<Orders> myOrders) {
        this.myOrders = myOrders;
    }

    @NonNull
    @Override
    public MyViewHld onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_detail_list,parent,false);
        return new MyViewHld(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHld holder, int position) {
        Orders orders = myOrders.get(position);
        holder.name.setText(String.format("Name: %s",orders.getProdName()));
        holder.price.setText(String.format("Price: %s",orders.getPrice()));
        holder.status.setText(String.format("Status: %s",stat(orders.getStatus())));
        TextDrawable drawable = TextDrawable.builder().buildRound(""+orders.getQntity(), Color.RED);
        holder.qntity.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return myOrders.size();
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
