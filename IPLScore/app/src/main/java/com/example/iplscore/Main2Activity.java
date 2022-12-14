package com.example.iplscore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Arrays;

import java.util.Collections;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ImageView imageView;

    String[] strings = {"https://i.pinimg.com/564x/e0/94/0d/e0940dd03ab2e51889821cc941323fb8.jpg","https://i.pinimg.com/564x/90/f8/e7/90f8e78f30e1ce562a974c3d947cf690.jpg","https://i.pinimg.com/564x/dc/56/80/dc5680bf074709e3a197beec11ae6176.jpg","https://i.pinimg.com/564x/4e/4b/18/4e4b182f7cd271c078a073d4ae2db6d6.jpg","https://i.pinimg.com/564x/b7/bc/bb/b7bcbb74dd8faa3f63590dabb78ec077.jpg","https://i.pinimg.com/564x/84/36/02/843602e48b7621d90eeca6d785c1ab3e.jpg","https://i.pinimg.com/564x/4e/50/ba/4e50baf8364258af59601bee175f40b2.jpg","https://i.pinimg.com/564x/7f/a2/27/7fa227247d0990b181e64d2443124074.jpg","https://i.pinimg.com/564x/7e/18/bc/7e18bc6c438a053e63bd7b6d9ac70214.jpg","https://i.pinimg.com/564x/04/62/ad/0462adf825ad30e9f71303325563c4aa.jpg","https://i.pinimg.com/564x/13/71/d0/1371d01d20c064cec71c3be2a4a3af9e.jpg","https://i.pinimg.com/564x/0c/f3/c0/0cf3c0ac01e0a62bf532d73ea3d2c55a.jpg","https://i.pinimg.com/564x/e8/b9/9a/e8b99a6a41b9cf28ccc2991532e1ace2.jpg","https://i.pinimg.com/564x/df/85/df/df85dfea765497f61c8732122b22511a.jpg","https://i.pinimg.com/564x/df/3d/df/df3ddf87049a041886fa75c3402170ed.jpg","https://i.pinimg.com/564x/a8/98/41/a8984143726df46b2d682931e02e67c3.jpg","https://i.pinimg.com/564x/5e/64/fc/5e64fc4a3d87e7a5bf3715bf9eb33070.jpg","https://i.pinimg.com/564x/03/a2/40/03a2400df153e33c011a628114489f1b.jpg","https://i.pinimg.com/564x/71/db/a6/71dba6093e115b3185079e7a5eddbcdb.jpg","https://i.pinimg.com/564x/23/da/69/23da69ce0c562e608d00f40d49999f64.jpg","https://i.pinimg.com/564x/fa/37/8a/fa378ab8f0e7738748555df520138737.jpg","https://i.pinimg.com/564x/a1/bf/de/a1bfdeb5cb63a1a84ecbdb1d69063994.jpg","https://i.pinimg.com/564x/05/65/17/056517c6dbf88ee7b5f86a731c9e3940.jpg","https://i.pinimg.com/564x/fc/db/99/fcdb99b4e45e32d9a916d096c1651f6c.jpg","https://i.pinimg.com/564x/90/76/d2/9076d2939caf6664b7999ad4df1e8c7b.jpg","https://i.pinimg.com/564x/8d/41/b2/8d41b290db35c5b701d12820bc890e0d.jpg","https://i.pinimg.com/564x/6f/d0/60/6fd060a4a440e5b15106262bab47c5d2.jpg","https://i.pinimg.com/564x/0a/9c/0f/0a9c0f1a7ac93f9e5bc434015f625139.jpg","https://i.pinimg.com/564x/5c/4b/fd/5c4bfd97870fc7d1314f2234f157f890.jpg","https://i.pinimg.com/564x/db/8d/f0/db8df022cfe669edb2f9307abed24034.jpg","https://i.pinimg.com/564x/52/d8/95/52d895d120626d4c8b6dc77aebf48b99.jpg","https://i.pinimg.com/564x/b2/60/07/b260078e473833f54537b3c810b03a51.jpg","https://i.pinimg.com/564x/e1/3a/62/e13a62009088b7852b07e995e88be092.jpg","https://i.pinimg.com/564x/8d/a1/97/8da1979541804de42f13b206430c23f5.jpg","https://i.pinimg.com/564x/6e/8a/3e/6e8a3eee02f017f8605e2b094eb0c79b.jpg","https://i.pinimg.com/564x/ef/f4/c7/eff4c77838fd203ab0c40656e3ffd94d.jpg","https://i.pinimg.com/564x/52/c2/4a/52c24a3b1cff51dc34764e2dd1331812.jpg","https://i.pinimg.com/564x/c9/62/e8/c962e8d7c3f99041a402cc385604fe65.jpg","https://i.pinimg.com/564x/3e/7f/ce/3e7fce3215d256ee1d9ab037c42c4991.jpg","https://i.pinimg.com/564x/4b/9a/2a/4b9a2a671ccc2ba124e466bebab0a37e.jpg","https://i.pinimg.com/564x/e6/f0/2d/e6f02db30dfe285e5fa4667204278618.jpg","https://i.pinimg.com/564x/78/73/4b/78734b433d2fe9510d96de75e3b1fdf7.jpg","https://i.pinimg.com/564x/f1/64/60/f16460452871b9a0723ce9ccf5c9112d.jpg","https://i.pinimg.com/564x/36/0a/97/360a97be8e0afa21cc6d60e4d5de50f9.jpg","https://i.pinimg.com/564x/5c/7a/d1/5c7ad1a311d564994ba4356ffaecadcf.jpg","https://i.pinimg.com/564x/f5/82/86/f58286381f865eb2f862443a2fcd8b78.jpg","https://i.pinimg.com/564x/f2/83/71/f28371a39081d0b79b884cb5b990a7ac.jpg","https://i.pinimg.com/564x/23/a0/81/23a08133938358f5c983fce7b21e23e0.jpg","https://i.pinimg.com/564x/1c/02/cd/1c02cd323d7a51c2c0ab377790b05e6b.jpg","https://i.pinimg.com/564x/cb/4d/3a/cb4d3a8fa5d2740295cd941af2768497.jpg","https://i.pinimg.com/564x/ac/7f/ec/ac7fecaa30fa1ba3fcde5df65a177465.jpg","https://i.pinimg.com/564x/d1/06/f8/d106f8dd9121d30783fa08b4a66d29ac.jpg","https://i.pinimg.com/564x/01/f5/63/01f563cb574c2f149f230d7f53d2b357.jpg","https://i.pinimg.com/564x/ff/79/a0/ff79a0f5733519214ebcc4aec480b04b.jpg","https://i.pinimg.com/564x/8a/08/0c/8a080c82a9155cf81532b7f08639ac5f.jpg","https://i.pinimg.com/564x/99/93/7c/99937c47194c1bbe1894fe3922703414.jpg","https://i.pinimg.com/564x/e5/f4/31/e5f431f44b05abfce40eee0e206b3d5a.jpg","https://i.pinimg.com/564x/e1/fb/38/e1fb3816d57773b60dc0dba4ed68025d.jpg"};
int max=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.imageview);


        ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE},1);

            List<String> list = Arrays.asList(strings);
            strings = new String[list.size()];
            Collections.shuffle(list);
            strings = list.toArray(new String[0]);


            method();

    }

    private void method() {

        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap bitmap;
        try {


            bitmap = imageDownloader.execute(strings[max]).get();
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void front(View view){
      if(max<57) {
max++;
          method();
      }
      else {
          Toast.makeText(this, "Can't Go Forward", Toast.LENGTH_SHORT).show();
      }

    }
    public void back(View view) {
        if (max >= 0) {
            max--;
            method();
        }
        else {
            Toast.makeText(this, "Can't Go Back", Toast.LENGTH_SHORT).show();

        }
    }
    public void downlo(View view){
        BitmapDrawable bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
        Bitmap imr=bitmapDrawable.getBitmap();
        File file= Environment.getExternalStorageDirectory();
        File ok=new File(file.getAbsolutePath()+"/Wallpapers");
        ok.mkdirs();
        String name=String.format("%d.jpg",System.currentTimeMillis());
        File out=new File(ok,name);
        FileOutputStream outputStream=null;
        try {


            outputStream = new FileOutputStream(out);
          imr.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
          outputStream.flush();
          outputStream.close();
            Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(out));
            sendBroadcast(intent);
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }




    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... str) {
            try {
                URL url = new URL(str[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();

               return BitmapFactory.decodeStream(inputStream);


            } catch (Exception e) {

                e.printStackTrace();
                return null;

            }
        }
    }
}
