package com.cbnu.sweng.randombox.dictation_user.dictation_user.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class TestActivity extends AppCompatActivity {


    @BindView(R.id.konfettiView) KonfettiView konfettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ButterKnife.bind(this);

        konfettiView.build()
                .addColors(Color.rgb(241, 95, 95), Color.rgb(165, 102, 255), Color.rgb(250, 237, 125))
                .setDirection(0.0, 359.0)
                .setSpeed(3f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT)
                .addSizes(new Size(12, 5f))
                .setPosition(0f, Util.getInstance().getDisplayWidth(this), 0f, 50f)
                .stream(300, 3000L);
    }
}
