package com.tanmaya.careergator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView=findViewById(R.id.imageView2);
        textView=findViewById(R.id.tv);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        imageView.startAnimation(myanim);
        textView.startAnimation(myanim);

        final Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        Thread timer =  new Thread(){
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();

                }
            }
        };
        timer.start();

    }

}
