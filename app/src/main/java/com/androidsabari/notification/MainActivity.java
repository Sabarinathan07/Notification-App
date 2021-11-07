package com.androidsabari.notification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    int counter = 0;

    public final String CHANNEL_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                button.setText(" "+counter);
                if(counter==5){
                    startNotification();
                }

            }
        });
    }


    public void startNotification(){
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID,"1",
                    NotificationManager.IMPORTANCE_DEFAULT);
        }

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(MainActivity.this,CHANNEL_ID);
        }
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Title")
                .setContentText("Notification text")
                .setPriority(Notification.PRIORITY_DEFAULT);

        NotificationManagerCompat compat = NotificationManagerCompat.from(MainActivity.this);
        compat.notify(1,builder.build());


    }
}