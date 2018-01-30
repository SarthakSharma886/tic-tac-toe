package com.example.hp.tictactoe;

import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    Bundle bundle;
    String s,s1;
    int m,k=0,t;
    String b = "";
    ImageView Red,Yellow;
    RelativeLayout rl1;
    Boolean gameactive = true;
    TextView tv,tv1,tv2;
    View view;
    GridLayout gr;
    private Vibrator myVib;
    int[]gamestate = {2,2,2,2,2,2,2,2,2};
    int [][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int player = 5;
    public void red(View v){
        if(m==0)
            myVib.vibrate(50);
        if (t==0){
            mp = MediaPlayer.create(this,R.raw.red);
            mp.start();
        }
        k=1;
        player = 1;
    }
    public void yellow(View v){
        if(m==0)
            myVib.vibrate(50);
        if (t==0){
            mp = MediaPlayer.create(this,R.raw.yellow);
            mp.start();
        }
        k=2;
        player = 0;
    }
    public void dropin(View v){
        if(gameactive) {
            view = v;
            ImageView iv = (ImageView) v;
            int index = Integer.parseInt(iv.getTag().toString());
            if (gamestate[index] == 2) {
                gamestate[index] = player;
                Red.setTranslationY(-1200f);
                Yellow.setTranslationY(-1200f);
                iv.setTranslationY(-1200f);
                if (player == 0) {
                    if (t==0){
                        mp = MediaPlayer.create(this,R.raw.yellow);
                        mp.start();
                    }
                    iv.setImageResource(R.drawable.yellow);
                    player++;
                }
                else if (player == 1) {
                    if (t==0){
                        mp = MediaPlayer.create(this,R.raw.red);
                        mp.start();
                    }
                    iv.setImageResource(R.drawable.red);
                    player = 0;
                }
                else {
                    if(m==0)
                        myVib.vibrate(100);
                    Red.setTranslationY(0f);
                    Yellow.setTranslationY(0f);
                    if(t==0) {
                        mp = MediaPlayer.create(this, R.raw.error);
                        mp.start();
                    }
                    Toast.makeText(this, "please choose a colour", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < 9; i++)
                        gamestate[i] = 2;
                }

                iv.animate().translationYBy(1200f).rotationBy(40f).setDuration(500);
                for (int[] winingposition : winningpositions) {
                    if (gamestate[winingposition[0]] == gamestate[winingposition[1]] &&
                            gamestate[winingposition[1]] == gamestate[winingposition[2]] &&
                            gamestate[winingposition[0]] != 2) {
                        if(m==0)
                            myVib.vibrate(400);
                        if (gamestate[winingposition[0]] == 0&&k==2) {
                            b = s;
                        } else if (gamestate[winingposition[0]] == 1&&k==1) {
                            b = s;
                        }
                        else {
                            b=s1;
                        }
                        if (t==0){
                            mp = MediaPlayer.create(this,R.raw.win);
                            mp.start();
                        }
                        tv.setText(b + " Win");
                        gameactive = false;
                        gr.animate().alpha(0f).setDuration(2000);
                        rl1.animate().alpha(1f).setDuration(2000);
                    }

                }
            } else {
                int a=0;
                for(int i=0;i<9;i++){
                    if(gamestate[i]!=2){
                        a++;
                    }
                }
                if(a<9) {
                    if(m==0)
                    myVib.vibrate(400);
                    Toast.makeText(this, "Can't override it", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(m==0)
                    myVib.vibrate(400);
                    if(t==0) {
                        mp = MediaPlayer.create(this, R.raw.draw);
                        mp.start();
                    }
                    b = " DRAW ";
                    tv.setText(b);
                    gameactive = false;
                    gr.animate().alpha(0f).setDuration(2000);
                    rl1.animate().alpha(1f).setDuration(2000);
                }

            }
        }
    }
    public void retry(View v){
        if(gameactive == false) {
            if(m==0)
                myVib.vibrate(400);
            for (int i = 0; i < 9; i++)
                gamestate[i] = 2;
            for (int i = 0; i < gr.getChildCount(); i++) {
                ((ImageView) gr.getChildAt(i)).setImageResource(0);
            }
            Red.setTranslationY(0f);
            Yellow.setTranslationY(0f);
            gameactive = true;
            player = 5;
            if(t==0) {
                mp = MediaPlayer.create(this, R.raw.retry);
                mp.start();
            }
            dropin(view);
            gr.animate().alpha(1f).setDuration(2000);
            rl1.animate().alpha(0f).setDuration(2000);

        }
    }
    public void exit(View v){
        if (gameactive ==false) {
            if(m==0)
                myVib.vibrate(500);
            if(t==0) {
                mp = MediaPlayer.create(this, R.raw.end);
                mp.start();
            }
            onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = getIntent().getBundleExtra("bundle");
        s = bundle.getString("first");
        s1= bundle.getString("second");
        m= bundle.getInt("vibration");
        t= bundle.getInt("sound");
        tv = (TextView)findViewById(R.id.textView2);
        tv1 =(TextView)findViewById(R.id.textView);
        gr = (GridLayout)findViewById(R.id.board);
        rl1 = (RelativeLayout)findViewById(R.id.relative);
        Red = (ImageView)findViewById(R.id.red);
        Yellow = (ImageView)findViewById(R.id.yellow);
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
    }
}
