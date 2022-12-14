package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       button=(Button)findViewById(R.id.button);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this)
                      .setContentText("check it broo...!!")
                      .setContentTitle("NOTI")
                      .setSmallIcon(android.R.drawable.alert_dark_frame)
                      .setPriority()
           }
       });

    }


}
