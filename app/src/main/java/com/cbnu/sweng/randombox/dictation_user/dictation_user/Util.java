package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.CandWordList;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.Error;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.Help;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuErrorWord;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuErrorWordList;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PnuNlpSpeller.PnuNlpSpeller;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Question;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

    public void moveActivity(Context context, final Class<? extends Activity> ActivityToOpen) {
        Intent intent = new Intent(context, ActivityToOpen);
        context.startActivity(intent);
    }
    public void moveActivity(Context context, final Class<? extends Activity> ActivityToOpen,
                             QuizResult quizResult, ArrayList<Question> questionsList) {
        Intent intent = new Intent(context, ActivityToOpen);
        intent.putExtra("quizResult", quizResult);
        intent.putExtra("questionsList", questionsList);
        context.startActivity(intent);
    }

    public void moveActivity(Context context, final Class<? extends Activity> ActivityToOpen,
                             QuizResult quizResult, ArrayList<Question> questionsList,
                             int questionNumber) {
        Intent intent = new Intent(context, ActivityToOpen);
        intent.putExtra("quizResult", quizResult);
        intent.putExtra("questionNumber", questionNumber);
        intent.putExtra("questionsList", questionsList);
        context.startActivity(intent);
    }

    public String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }
        if (str2 == null) {
            return str1;
        }
        int at = getIndexOfDifference(str1, str2);
        if (at == -1) {
            return "";
        }
        return str2.substring(at);
    }
    public int getIndexOfDifference(String str1, String str2) {
        if (str1 == str2) {
            return -1;
        }
        if (str1 == null || str2 == null) {
            return 0;
        }
        int i;
        for (i = 0; i < str1.length() && i < str2.length(); ++i) {
            if (str1.charAt(i) != str2.charAt(i)) {
                break;
            }
        }
        if (i < str2.length() || i < str1.length()) {
            return i;
        }
        return -1;
    }
}
