package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Game extends AppCompatActivity implements View.OnTouchListener {
    Field field;
    DrawField drawField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);

        field = new Field(metricsB.widthPixels, metricsB.heightPixels);
        drawField = findViewById(R.id.field);
        drawField.field = field;
        drawField.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        field.click(event.getX(), event.getY());
        drawField.invalidate();
        return true;
    }
}
