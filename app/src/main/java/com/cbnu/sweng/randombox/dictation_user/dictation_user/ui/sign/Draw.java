package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;

import java.util.ArrayList;
import java.util.Random;

import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;

public class Draw extends AppCompatActivity {

    private DrawableView drawableView;
    private DrawableViewConfig config = new DrawableViewConfig();
    protected ArrayMap<Integer, String> words;
    protected ArrayList<Integer> keys;
    protected int wordNum = 0;
    protected int wordSize = 0;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        initUi();
    }

    private void initUi() {
        drawableView = (DrawableView) findViewById(R.id.paintView);
        Button strokeWidthMinusButton = (Button) findViewById(R.id.strokeWidthMinusButton);
        Button strokeWidthPlusButton = (Button) findViewById(R.id.strokeWidthPlusButton);
        Button changeColorButton = (Button) findViewById(R.id.changeColorButton);
        Button undoButton = (Button) findViewById(R.id.undoButton);

        config.setStrokeColor(getResources().getColor(android.R.color.black));
        config.setShowCanvasBounds(true);
        config.setStrokeWidth(20.0f);
        config.setMinZoom(1.0f);
        config.setMaxZoom(3.0f);
        config.setCanvasHeight(1080);
        config.setCanvasWidth(1920);
        drawableView.setConfig(config);

        strokeWidthPlusButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                config.setStrokeWidth(config.getStrokeWidth() + 10);
            }
        });
        strokeWidthMinusButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                config.setStrokeWidth(config.getStrokeWidth() - 10);
            }
        });
        changeColorButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                Random random = new Random();
                config.setStrokeColor(
                        Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            }
        });
        undoButton.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                drawableView.undo();
            }
        });
    }
}
