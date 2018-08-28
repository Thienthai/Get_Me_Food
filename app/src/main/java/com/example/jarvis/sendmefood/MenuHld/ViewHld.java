package com.example.jarvis.sendmefood.MenuHld;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jarvis.sendmefood.Interface.ListenerClck;
import com.example.jarvis.sendmefood.R;

public class ViewHld extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name_menu;
    public ImageView img;

    public ListenerClck listenerClck;

    public ViewHld(View itemView) {
        super(itemView);

        img = (ImageView) itemView.findViewById(R.id.menu_image);
        name_menu = (TextView) itemView.findViewById(R.id.name_menu);

        itemView.setOnClickListener(this);
    }

    public void setlistenerClck(ListenerClck listenerClck){
        this.listenerClck = listenerClck;
    }

    @Override
    public void onClick(View v) {
        listenerClck.onClick(getAdapterPosition(),v,false);
    }
}
