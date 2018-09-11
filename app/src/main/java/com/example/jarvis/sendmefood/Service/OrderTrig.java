package com.example.jarvis.sendmefood.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.jarvis.sendmefood.Current.Current;
import com.example.jarvis.sendmefood.Model.RqData;
import com.example.jarvis.sendmefood.Order_Board;
import com.example.jarvis.sendmefood.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderTrig extends Service implements ChildEventListener {

    FirebaseDatabase db;
    DatabaseReference db_ref;

    public OrderTrig() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseDatabase.getInstance();
        db_ref = db.getReference("Requests");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        db_ref.addChildEventListener(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        RqData rq = dataSnapshot.getValue(RqData.class);
        notiAlrt(dataSnapshot.getKey(),rq);
    }

    private void notiAlrt(String key, RqData rq) {
        Intent intent = new Intent(getBaseContext(), Order_Board.class);
        intent.putExtra("number",rq.getNumbers());
        PendingIntent conIn = PendingIntent.getActivity(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("SMF").setContentInfo("Order Updated")
                .setContentText(key + " was update to " + Current.statusCode(rq.getStatus())).setContentInfo("Info").setSmallIcon(R.mipmap.ic_launcher);
        NotificationManager manager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
