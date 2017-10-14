package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignInActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignUpActivity;

public class SplashActivity extends AppCompatActivity {

    static int SPLASH_TIME_OUT = 3000;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent loginIntent = new Intent(SplashActivity.this, SignInActivity.class);
                SplashActivity.this.startActivity(loginIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
