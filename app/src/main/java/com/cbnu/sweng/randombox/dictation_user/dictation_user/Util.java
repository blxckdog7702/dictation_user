package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;

import java.util.Random;

public class Util {

    private static Util util = null;

    public static synchronized Util getInstance()
    {
        if(util == null){}
        try{
            if(util==null)
                util = new Util();
            return util;
        }
        finally {}
    }

    public void moveAcitivity(Context context, final Class<? extends Activity> ActivityToOpen) {
        Intent intent = new Intent(context, ActivityToOpen);
        context.startActivity(intent);
    }
    public void moveAcitivity(Context context, final Class<? extends Activity> ActivityToOpen, QuizResult quizResult) {
        Intent intent = new Intent(context, ActivityToOpen);
        intent.putExtra("OBJECT", quizResult);
        context.startActivity(intent);
    }

    public void moveAcitivity(Context context, final Class<? extends Activity> ActivityToOpen, QuizResult quizResult, int questionNumber) {
        Intent intent = new Intent(context, ActivityToOpen);
        intent.putExtra("OBJECT", quizResult);
        intent.putExtra("questionNumber", questionNumber);
        context.startActivity(intent);
    }

}
