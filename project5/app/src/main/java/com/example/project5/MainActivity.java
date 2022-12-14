package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
int activeplayer=0;
int []game={2,2,2,2,2,2,2,2,2};
int [][]winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{2,5,8},{2,4,6},{0,4,8}};
    public void clickme(View view)
    {

        ImageView counter=(ImageView) view;
        int tapped=Integer.parseInt(counter.getTag().toString());
        if(game[tapped]==2) {
            game[tapped] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                activeplayer = 1;
                counter.setImageResource(R.drawable.fit);
            } else {
                activeplayer = 0;
                counter.setImageResource(R.drawable.fitt);

            }
            counter.animate().translationYBy(1500).rotation(1800).setDuration(1000);
            for (int[] play : winningpositions) {
                if (game[play[0]] == game[play[1]] && game[play[1]] == game[play[2]] && game[play[0]] != 2) {
                    Toast.makeText(this, "WON", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
