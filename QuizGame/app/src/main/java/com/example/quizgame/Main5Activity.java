package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {
int maxx,minn,ans=0;
String name;
TextView score,perform,totttt,correct,wrong;
Button playagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Intent over=getIntent();
        maxx=over.getIntExtra("maxvalue",0);
        minn=over.getIntExtra("minvalue",0);
        name=over.getStringExtra("name3");

        score=(TextView)findViewById(R.id.score);
        perform=(TextView)findViewById(R.id.perfrom);
        totttt=(TextView)findViewById(R.id.annnn);
        wrong=(TextView)findViewById(R.id.maxxxx);
        correct=(TextView)findViewById(R.id.minnnn);

        playagain=(Button)findViewById(R.id.playagain);
        totttt.setText(String.valueOf(maxx));
        correct.setText(String.valueOf(minn));
        wrong.setText(String.valueOf(maxx-minn));


        ans=minn-(maxx-minn);
        score.setText(String.valueOf(ans));
        if(ans>15)
        {
            perform.setText("GOD LEVEL");
        }
        else if(ans>10)
        {
            perform.setText("EXCELLENT");
        }
        else if(ans>5) {
  perform.setText("    GOOD");
        }
        else if(ans>0)
        {
            perform.setText("     Bad");
        }
        else {
            perform.setText("Very Bad");
        }
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back2=new Intent(Main5Activity.this,Main4Activity.class);
                back2.putExtra("name2",name);
                startActivity(back2);
            }
        });
    }
}
