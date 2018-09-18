package com.example.jarvis.sendmefood.MenuHld;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jarvis.sendmefood.Interface.ListenerClck;
import com.example.jarvis.sendmefood.R;

public class OrderDetailHld extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public TextView orderDtlName,orderDtlPrice,orderDtlQntity,orderDtlStatus;

    ListenerClck listenerClck;

    public OrderDetailHld(View itemView) {
        super(itemView);
        orderDtlName = itemView.findViewById(R.id.order_detail_item_name);
        orderDtlPrice = itemView.findViewById(R.id.order_detail_item_price);
        orderDtlQntity = itemView.findViewById(R.id.order_detail_item_qntity);
        orderDtlStatus = itemView.findViewById(R.id.order_detail_item_status);

        itemView.setOnClickListener(this);
    }

    public void setListenerClck(ListenerClck listenerClck) {
        this.listenerClck = listenerClck;
    }

    @Override
    public void onClick(View view) {

    }
}
