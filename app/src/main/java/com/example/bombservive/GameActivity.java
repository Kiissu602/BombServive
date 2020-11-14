package com.example.bombservive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import game.Bomb;
import game.Line;
import game.Setting;

public class GameActivity extends AppCompatActivity {
    ImageView line1, line2, line3, line4, line5, line6, line7, line8;
    ImageView cut1, cut2, cut3, cut4, cut5, cut6, cut7, cut8;
    ImageView boom, win;
    TextView text;
    int linesCount = 0;
    MediaPlayer BoomSound;
    int num;
    int time;
    private Line[] line = new Line[8];
    boolean isBomed = false;
    Setting set;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow().getContext());
        setContentView(R.layout.activity_game);

        BoomSound = MediaPlayer.create(this, R.raw.boommmm);
        Random random = new Random();

        int count = 0, ran;
        text = findViewById(R.id.text1);


        if(Setting.getChoice() == 0) {
            time = 7000;
            num = random.nextInt(1) + 1;
            text.setText( "cut 3 wires to defused");
        }

        else if(Setting.getChoice() == 1) {
            time = 5000;
            num = random.nextInt(2)+3;
            text.setText( "cut 5 wires to defused");
        }
        else {
            time = 2000;
            num = random.nextInt(2) + 6;
            text.setText("cut 1 wires to defused");
        }

        timer = countdown(time);
        timer.start();

        win = findViewById(R.id.win);

        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line4 = findViewById(R.id.line4);
        line5 = findViewById(R.id.line5);
        line6 = findViewById(R.id.line6);
        line7 = findViewById(R.id.line7);
        line8 = findViewById(R.id.line8);

        cut1 = findViewById(R.id.cuted1);
        cut2 = findViewById(R.id.cuted2);
        cut3 = findViewById(R.id.cuted3);
        cut4 = findViewById(R.id.cuted4);
        cut5 = findViewById(R.id.cuted5);
        cut6 = findViewById(R.id.cuted6);
        cut7 = findViewById(R.id.cuted7);
        cut8 = findViewById(R.id.cuted8);

        boom = findViewById(R.id.boom);

        for(int i = 0; i < 8; i++){
            line[i] = new Line();
        }

        while (count < num){
            ran = random.nextInt(7);
            if(!line[ran].isBoom()){
                line[ran].setBoom();
                count++;
            }
        }

    }

    public void clickButton(View view) throws InterruptedException {
        if (!isBomed) {
            switch (view.getId()) {
                case R.id.line1:
                    cut(0, line1, cut1);
                    break;

                case R.id.line2:
                    cut(1, line2, cut2);
                    break;

                case R.id.line3:
                    cut(2, line3, cut3);
                    break;

                case R.id.line4:
                    cut(3, line4, cut4);
                    break;

                case R.id.line5:
                    cut(4, line5, cut5);
                    break;

                case R.id.line6:
                    cut(5, line6, cut6);
                    break;

                case R.id.line7:
                    cut(6, line7, cut7);
                    break;

                case R.id.line8:
                    cut(7, line8, cut8);
                    break;
            }
        }
    }

    @Override public void onBackPressed(){}

    @Override public void onPause(){
        super.onPause();
        BoomSound.pause();
    }

    void endActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GameActivity.this, FinishActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        },4500);
    }

    public static void setFullScreen(Context context) {
        Window window = ((AppCompatActivity) context).getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    void cut(int n, ImageView li, ImageView cut){
        timer.cancel();
        timer = countdown(time);
        timer.start();
        if(!line[n].isCut()){
            line[n].setCut();
            linesCount++;
            li.setVisibility(View.INVISIBLE);
            cut.setVisibility(View.VISIBLE);
        }
        if(line[n].isBoom()) {
            text.setText("You Lose!!!");
            boom.setVisibility(View.VISIBLE);
            isBomed = true;
            timer.cancel();
            BoomSound.start();
            endActivity();
        }

        else if(Setting.getChoice() == 0 && linesCount == 3){
            isBomed = true;
            win.setVisibility(View.VISIBLE);
            text.setText("You Win!!!");
            timer.cancel();
            endActivity();

        }
        else if(Setting.getChoice() == 1 && linesCount == 5){
            isBomed = true;
            text.setText("You Win!!!");
            win.setVisibility(View.VISIBLE);
            timer.cancel();
            endActivity();
        }

        else if(Setting.getChoice() == 2 && linesCount == 1){
            isBomed = true;
            text.setText("You Win!!!");
            win.setVisibility(View.VISIBLE);
            timer.cancel();
            endActivity();
        }


    }
    public CountDownTimer countdown(int times){
        final int parTime = times;
        return new CountDownTimer(times, 1000) {
            TextView nalika = findViewById(R.id.text2);
            int timie = parTime;

            @Override
            public void onTick(long millisUntilFinished) {
                nalika.setText(""+timie/1000+" s.");
                timie -= 1000;
            }

            @Override
            public void onFinish() {
                nalika.setText("00:00");
                boom.setVisibility(View.VISIBLE);
                isBomed = true;
                BoomSound.start();
                text.setText("Timeout!!!");
                endActivity();
            }
        };
    }
}
