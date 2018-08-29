package com.example.jarvis.sendmefood.MenuHld;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.jarvis.sendmefood.Interface.ListenerClck;
import com.example.jarvis.sendmefood.Model.Orders;
import com.example.jarvis.sendmefood.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class OrderHlder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView item_name,item_price;
    public ImageView img_count;

    private ListenerClck listenerClck;

    public void setItem_name(TextView item_name) {
        this.item_name = item_name;
    }

    public OrderHlder(View itemView) {
        super(itemView);
        item_name = (TextView)itemView.findViewById(R.id.order_item_name);
        item_price = (TextView)itemView.findViewById(R.id.order_item_price);
        img_count = (ImageView)itemView.findViewById(R.id.order_item_count);
    }

    @Override
    public void onClick(View v) {

    }
}

public class OrderAdapter extends RecyclerView.Adapter<OrderHlder> {

    private List<Orders> Orders = new ArrayList<>();
    private Context context;

    public OrderAdapter(List<com.example.jarvis.sendmefood.Model.Orders> orders, Context context) {
        Orders = orders;
        this.context = context;
    }

    @Override
    public OrderHlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflt = LayoutInflater.from(context);
        View v = inflt.inflate(R.layout.order_layout,parent,false);
        return new OrderHlder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHlder holder, int position) {
        TextDrawable drawable = TextDrawable.builder().buildRound(""+Orders.get(position).getQntity(), Color.RED);
        holder.img_count.setImageDrawable(drawable);
        Locale loc = new Locale("en","US");
        NumberFormat format = NumberFormat.getCurrencyInstance(loc);
        holder.item_price.setText(format.format(Integer.parseInt(Orders.get(position).getPrice()) *
                (Integer.parseInt(Orders.get(position).getQntity()))));
        holder.item_name.setText(Orders.get(position).getProdName());
    }

    @Override
    public int getItemCount() {
        return Orders.size();
    }
}
