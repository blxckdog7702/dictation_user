package com.cbnu.sweng.randombox.dictation_user.dictation_user.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Question;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util {

    private static Util util = null;
    private long backKeyPressedTime = 0;
    private Toast toast;

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
    public void moveActivity(Context context, final Class<? extends Activity> ActivityToOpen, int i) {
        Intent intent = new Intent(context, ActivityToOpen);
        intent.putExtra("int", i);
        context.startActivity(intent);
    }
    public void moveActivity(Context context, final Class<? extends Activity> ActivityToOpen, String s) {
        Intent intent = new Intent(context, ActivityToOpen);
        intent.putExtra("String", s);
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
    //TODO []로 바꿔서 틀린 부분 여러개로 수정해야함
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

    public int wordSimilarity(String correctAnswer, String submittedAnswer) {
        int result;
        int len1 = correctAnswer.length();
        int len2 = submittedAnswer.length();

        // len1+1, len2+1, because finally return dp[len1][len2]
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = correctAnswer.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = submittedAnswer.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }
        result = 100 - ((dp[len1][len2] / correctAnswer.length()) * 100);
        return result;
    }

    public float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public float getDisplayWidth(Context context){
        return (float) ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
    }

    public float getDisplayHeigth(Context context){
        return (float) ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
    }

    public float getMargin(Context context){
        return (int) convertDpToPixel(10f, (Activity) context);
    }

    public float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }

    public void onBackPressed(Activity activity){

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finishAffinity();
            activity.moveTaskToBack(true);
            //activity.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            toast.cancel();
        }
    }

    public String getDate(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        String nowDate = simpleDateFormat.format(date) + " " + getDayOfWeek();

        return nowDate;
    }

    private String getDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        String strWeek = null;

        int nWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (nWeek == 1) {
            strWeek = "일요일";
        } else if (nWeek == 2) {
            strWeek = "월요";
        } else if (nWeek == 3) {
            strWeek = "화요일";
        } else if (nWeek == 4) {
            strWeek = "수요일";
        } else if (nWeek == 5) {
            strWeek = "목요일";
        } else if (nWeek == 6) {
            strWeek = "금요일";
        } else if (nWeek == 7) {
            strWeek = "토요일";
        }
        return strWeek;
    }
}
