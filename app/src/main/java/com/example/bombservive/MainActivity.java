package com.example.bombservive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import game.Setting;


public class MainActivity extends AppCompatActivity {

    private long backpressTime;
    Toast backToast;

    private static ImageView play, exit;
    private static ImageView easy , normal, hard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(getWindow().getContext());
        setContentView(R.layout.activity_main);



    }

    public void OnPlay(View view){

        play = findViewById(R.id.PlayButton);
        exit = findViewById(R.id.ExitButton);
        easy = findViewById(R.id.easy);
        normal = findViewById(R.id.normal);
        hard = findViewById(R.id.hard);

        play.setVisibility(View.GONE);
        exit.setVisibility(View.GONE);
        easy.setVisibility(View.VISIBLE);
        normal.setVisibility(View.VISIBLE);
        hard.setVisibility(View.VISIBLE);

    }

    public void onEasy(View view){
        Setting.setChoice(0);
        onPlays();
    }

    public void onNormal(View view){
        Setting.setChoice(1);
        onPlays();
    }

    public void onHard(View view){
        Setting.setChoice(2);
        onPlays();
    }


    public void onExit(View view){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void onPlays(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {


        if(backpressTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else{
            backToast = Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backpressTime = System.currentTimeMillis();
    }
    public static void setFullScreen(Context context){
        Window window = ((AppCompatActivity) context).getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
