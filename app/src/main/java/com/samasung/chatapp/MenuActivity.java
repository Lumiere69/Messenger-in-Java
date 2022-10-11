package com.samasung.chatapp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;


public class MenuActivity extends Activity implements View.OnClickListener {
    Button btnChat1,btnChat2,btnChat3;
    FloatingActionButton btnPlus, btnThemes;
    RelativeLayout menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menu = (RelativeLayout) findViewById(R.id.activity_menu);
        btnChat1 = (Button) findViewById(R.id.btnChat1);
        btnChat2 = (Button) findViewById(R.id.btnChat2);
        btnChat3 = (Button) findViewById(R.id.btnChat3);

        btnPlus = (FloatingActionButton) findViewById(R.id.btnPlus);
        btnThemes = (FloatingActionButton) findViewById(R.id.btnThemes);

        btnChat1.setOnClickListener(this);
        btnChat2.setOnClickListener(this);
        btnChat3.setOnClickListener(this);

        btnPlus.setOnClickListener(this);
        btnThemes.setOnClickListener(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MenuActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MenuActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MenuActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MenuActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MenuActivity: onDestroy()");
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnChat1:
                //----Переход на активность по кнопке 1
                Intent vaska1 = new Intent();
                vaska1.setClass(this, Chat1Activity.class);
                startActivity(vaska1);
                break;
            case R.id.btnChat2:
                //----Переход на активность по кнопке 2

              /*  Snackbar.make(view, "Переход по кноке 2 в разработке", Snackbar.LENGTH_LONG).show();
                break;*/
                Intent vaska2 = new Intent();
                vaska2.setClass(this, Chat2Activity.class);
                startActivity(vaska2);
                break;
            case R.id.btnChat3:
                //----Переход на активность по кнопке 3

              /*  Snackbar.make(view, "Переход по кноке 3 в разработке", Snackbar.LENGTH_LONG).show();
                break;*/
                Intent vaska3 = new Intent();
                vaska3.setClass(this, Chat3Activity.class);
                startActivity(vaska3);
                break;
            case  R.id.btnPlus:
                int color2 = Color.rgb(255,255,255);
                int color3 = Color.rgb(111,116,220);
                btnChat1.setBackgroundColor(color3);
                btnChat2.setBackgroundColor(color3);
                btnChat3.setBackgroundColor(color3);
                btnPlus.setBackgroundColor(color3);
                btnThemes.setBackgroundColor(color3);
                menu.setBackgroundColor(color2);
                break;
            case  R.id.btnThemes:
                int color = Color.rgb(0,0,0);
                int color1 = Color.rgb(57,57,57);
                btnChat1.setBackgroundColor(color1);
                btnChat2.setBackgroundColor(color1);
                btnChat3.setBackgroundColor(color1);
                btnPlus.setBackgroundColor(color1);
                btnThemes.setBackgroundColor(color1);
                menu.setBackgroundColor(color);
                break;



        }
    }
}