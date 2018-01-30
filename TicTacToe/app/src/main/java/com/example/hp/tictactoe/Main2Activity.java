package com.example.hp.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {
    int a=0,c=0;
    ImageView iv,iv2;
    Handler handler = new Handler();
    int b=0;
    String s1,s2;
    TextView tv,tv1;
    private Vibrator myVib;
    EditText e1,e2;
    MediaPlayer mp;
    SeekBar sb;
    AudioManager am;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv =(TextView)findViewById(R.id.textView4);
        tv1 =(TextView)findViewById(R.id.textView6);
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText);
        iv = (ImageView)findViewById(R.id.imageView);
        iv2 = (ImageView)findViewById(R.id.imageView2);
        imagechange();
        myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        am = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int vol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        sb = (SeekBar)findViewById(R.id.seekBar);
        sb.setProgress(vol);
        sb.setMax(max);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            am.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//                sb.animate().translationY(-1000f);
    }
    public void sound(View v){
        if(c==0){
            if(b==0)
                myVib.vibrate(200);
            sb.animate().translationX(10000f);
            tv1.setText("SOUND OFF");
            c=1;
        }
        else if(c==1){
            mp = MediaPlayer.create(this,R.raw.red);
            mp.start();
            if(b==0)
                myVib.vibrate(200);
            sb.animate().translationX(00f);
            tv1.setText("SOUND ON");
            c=0;
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public void imagechange(){
        handler.postDelayed(new Runnable() {
            public void run() {
                imagechange();
            }
        }, 5000);

        if(a==0){
            iv2.animate().alpha(0).setDuration(2000);
            iv.setImageResource(R.drawable.aaaaa);
            iv.animate().alpha(0.3f).setDuration(2000);
            a=1;
        }
        else if(a==1){
            iv.animate().alpha(0).setDuration(2000);
            iv2.setImageResource(R.drawable.aa);
            iv2.animate().alpha(0.3f).setDuration(2000);
            a=2;
        }
        else if(a==2){
            iv2.animate().alpha(0).setDuration(2000);
            iv.setImageResource(R.drawable.aaa);
            iv.animate().alpha(0.3f).setDuration(2000);
            a=3;
        }
        else if(a==3){
            iv.animate().alpha(0).setDuration(2000);
            iv2.setImageResource(R.drawable.aaaa);
            iv2.animate().alpha(0.3f).setDuration(2000);
            a=4;
        }

        else if(a==4){
            iv2.animate().alpha(0).setDuration(2000);
            iv.setImageResource(R.drawable.a);
            iv.animate().alpha(0.3f).setDuration(2000);
            a=5;
        }
        else if(a==5){
            iv.animate().alpha(0).setDuration(2000);
            iv2.setImageResource(R.drawable.aaaaaa);
            iv2.animate().alpha(0.3f).setDuration(2000);
            a=0;
        }




    }
    public void play(View v){
        if(b==0)
            myVib.vibrate(200);
        Intent i = new Intent(Main2Activity.this, MainActivity.class);
        s1= e1.getText().toString();
        s2= e2.getText().toString();
        if(TextUtils.isEmpty(s1)) {
            e1.setError("Enter The Name");
            if(c==0) {
                mp = MediaPlayer.create(this, R.raw.error);
                mp.start();
            }
            return;
        }
        else if(TextUtils.isEmpty(s2)) {
            e2.setError("Enter The Name");
            if(c==0) {
                mp = MediaPlayer.create(this, R.raw.error);
                mp.start();
            }
            return;
        }
        else {
            Bundle b1 = new Bundle();
            b1.putString("first", s1);
            b1.putString("second", s2);
            b1.putInt("vibration", b);
            b1.putInt("sound",c);
            i.putExtra("bundle", b1);
            if(c==0){
                mp = MediaPlayer.create(this,R.raw.play);
                mp.start();
            }
            startActivity(i);
        }
    }
    public void vibration(View v){
        if(b==0){
            tv.setText("VIBRATION OFF");
            b=1;
        }
        else if(b==1){
            myVib.vibrate(200);
            tv.setText("VIBRATION ON");
            b=0;
        }
    }
    public void exit(View v){
        onBackPressed();
    }
}
