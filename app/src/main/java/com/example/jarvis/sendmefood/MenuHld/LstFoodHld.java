package com.example.jarvis.sendmefood.MenuHld;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.jarvis.sendmefood.R;

public class LstFoodHld extends RecyclerView.ViewHolder{
    public TextView Name,Price,Status,Qantity;


    public LstFoodHld(View itemView) {
        super(itemView);

        Name = (TextView) itemView.findViewById(R.id.order_detail_item_name);
        Price = (TextView) itemView.findViewById(R.id.order_detail_item_price);
        Status = (TextView) itemView.findViewById(R.id.order_detail_item_status);
        Qantity = (TextView) itemView.findViewById(R.id.order_myquantity);
    }
}
