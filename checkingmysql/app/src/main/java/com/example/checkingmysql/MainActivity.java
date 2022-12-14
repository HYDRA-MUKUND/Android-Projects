package com.example.checkingmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ListView listViewname;
    ArrayList<String> arrayList1 = new ArrayList<String>();
    ArrayAdapter arrayAdapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewname = (ListView) findViewById(R.id.listviewname);



    }

    public void hh(View view) {
        String res = "";
        Mysqlprac mysqlprac = new Mysqlprac(this);
        try {
            res = mysqlprac.execute("http:/192.168.43.6/practice4.php").get();
            res=res.replace("null","");
            String []newdata=res.split("#");
            int len=newdata.length;
           for(int i=0;i<len;i++){
String [] sh=newdata[i].split("&");
while (sh[0].length()<35){
    sh[0]+=" ";
}
    arrayList1.add(sh[0]+sh[1]);

              }

            arrayAdapter1=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList1);
            listViewname.setAdapter(arrayAdapter1);




        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
