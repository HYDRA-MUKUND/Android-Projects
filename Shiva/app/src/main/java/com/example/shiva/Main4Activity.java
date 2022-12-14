package com.example.shiva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main4Activity extends AppCompatActivity {
ImageView download,nextt,backk,curr;
private int i=0,flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        download=(ImageView)findViewById(R.id.downloadbut);
        nextt=(ImageView)findViewById(R.id.frontview);
        backk=(ImageView)findViewById(R.id.backview);
        curr=(ImageView)findViewById(R.id.img1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        final String[] imgarr={"wallone","walltwo","wallthree","wallfour","wallfive","wallsix","wallseven","walleight","wallnine","wallten","walleleven","walltwele","wallthirteen","wallfourteen","wallfifteen","wallsisteen","wallseventeen"};
         backk.setVisibility(View.INVISIBLE);
        nextt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                backk.setVisibility(View.VISIBLE);
                if(i==16)
                { flag=1;
                    curr.setImageResource(getResources().getIdentifier(imgarr[i],"drawable",getPackageName()));
                    nextt.setVisibility(View.INVISIBLE);
                }
                else {


                    curr.setImageResource(getResources().getIdentifier(imgarr[i],"drawable",getPackageName()));

                }


            }
        });
        backk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                --i;
                if(flag==1)
                {


                  flag=0;
                    curr.setImageResource(getResources().getIdentifier(imgarr[i],"drawable",getPackageName()));
                    nextt.setVisibility(View.VISIBLE);


                }
                else if(i==0)
                {
                    curr.setImageResource(getResources().getIdentifier(imgarr[i],"drawable",getPackageName()));
                    backk.setVisibility(View.INVISIBLE);

                }
                else {
                    curr.setImageResource(getResources().getIdentifier(imgarr[i],"drawable",getPackageName()));
                }

            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dow();
            }
        });
    }

    private void dow() {
       BitmapDrawable bitmapDrawable =(BitmapDrawable)curr.getDrawable();
      Bitmap  bitmap=bitmapDrawable.getBitmap();
        FileOutputStream outputStream=null;
        File path= Environment.getExternalStorageDirectory();
        File dir=new File(path.getAbsolutePath()+"/Wallpapers");

        dir.mkdirs();

        String s=String.format("%d.jpg",System.currentTimeMillis());

        File out=new File(dir,s);
        try {
            outputStream=new FileOutputStream(out);

            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.flush();

            outputStream.close();

            Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(out));
            sendBroadcast(intent);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast jj=Toast.makeText(this, "Downloaded!!", Toast.LENGTH_SHORT);
        View view=jj.getView();
        view.setBackgroundColor(Color.TRANSPARENT);
        TextView textView=(TextView)view.findViewById(android.R.id.message);
        textView.setTextColor(Color.WHITE);
        jj.setDuration(Toast.LENGTH_SHORT);
        textView.setTextSize(14);
        jj.show();
    }
}
