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

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Question;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;

import java.util.ArrayList;
import java.util.List;
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
    public void moveAcitivity(Context context, final Class<? extends Activity> ActivityToOpen,
                                QuizResult quizResult, ArrayList<Question> questionsList) {
        Intent intent = new Intent(context, ActivityToOpen);
        intent.putExtra("quizResult", quizResult);
        intent.putExtra("questionsList", questionsList);
        context.startActivity(intent);
    }

    public void moveAcitivity(Context context, final Class<? extends Activity> ActivityToOpen,
                                QuizResult quizResult, ArrayList<Question> questionsList,
                                int questionNumber) {
        Intent intent = new Intent(context, ActivityToOpen);
        intent.putExtra("quizResult", quizResult);
        intent.putExtra("questionNumber", questionNumber);
        intent.putExtra("questionsList", questionsList);
        context.startActivity(intent);
    }

}
