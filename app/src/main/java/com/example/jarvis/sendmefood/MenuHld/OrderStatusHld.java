package com.example.jarvis.sendmefood.MenuHld;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jarvis.sendmefood.Interface.ListenerClck;
import com.example.jarvis.sendmefood.R;

public class OrderStatusHld extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView order_id,order_status,order_phone,order_address;

    ListenerClck listenerClck;

    public Button btn_detail,cmplt_ordr;

    public OrderStatusHld(View itemView) {
        super(itemView);

        order_id = itemView.findViewById(R.id.order_status_item_name);
        order_status = itemView.findViewById(R.id.order_item_stat);
        order_phone = itemView.findViewById(R.id.order_status_phone);
        order_address = itemView.findViewById(R.id.order_stat_address);
        btn_detail = itemView.findViewById(R.id.order_detail_btn);
        cmplt_ordr = itemView.findViewById(R.id.complete_order);


        itemView.setOnClickListener(this);
    }

    public void setListenerClck(ListenerClck listenerClck) {
        this.listenerClck = listenerClck;
    }

    @Override
    public void onClick(View v) {
        listenerClck.onClick(getAdapterPosition(),v,false);
    }
}
