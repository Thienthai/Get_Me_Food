package com.example.jarvis.sendmefood.MenuHld;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jarvis.sendmefood.Interface.ListenerClck;
import com.example.jarvis.sendmefood.R;

public class FdViewHld extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView fd_name;
    public ImageView fd_image;

    private ListenerClck listenerClck;

    public void setListenerClck(ListenerClck listenerClck) {
        this.listenerClck = listenerClck;
    }

    public FdViewHld(View itemView) {
        super(itemView);

        fd_name = (TextView)itemView.findViewById(R.id.fd_name);
        fd_image = (ImageView)itemView.findViewById(R.id.fd_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listenerClck.onClick(getAdapterPosition(),v,false);
    }
}
