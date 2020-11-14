package com.example.bombservive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.PixelCopy;
import android.widget.VideoView;

import static com.example.bombservive.MainActivity.setFullScreen;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        setFullScreen(getWindow().getContext());

        VideoView loading = (VideoView) findViewById(R.id.loading);
        String load = "android.resource://"+getPackageName()+"/"+R.raw.loading;
        loading.setVideoURI(Uri.parse(load));
        loading.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        },3000);

    }
}
