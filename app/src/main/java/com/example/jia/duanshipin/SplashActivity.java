package com.example.jia.duanshipin;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    private FullScreeVideoView videoView;
    private TextView mTVtimer;
    private CountDownTimer timer;
    private int Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        videoView = (FullScreeVideoView) findViewById(R.id.vv_play);
        mTVtimer = (TextView) findViewById(R.id.textView);
        mTVtimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Time < 1){
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
            }
        });

        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+File.separator+R.raw.hzgmt));

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

         timer = new CountDownTimer(5, new ICountDownHander() {
            @Override
            public void onTicker(int time) {
                mTVtimer.setText(time + " 秒");
                Time = time;
            }

            @Override
            public void OnFinish() {
                mTVtimer.setText("跳过");
            }
        });
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
