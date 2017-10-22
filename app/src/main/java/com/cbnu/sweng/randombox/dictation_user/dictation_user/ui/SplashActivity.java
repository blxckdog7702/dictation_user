package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignInActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignUpActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.sdsmdg.tastytoast.TastyToast;

public class SplashActivity extends AppCompatActivity {

    static int SPLASH_TIME_OUT = 3000;
    Context context = this;
    SharedPreferences setting;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                setting = getSharedPreferences("setting", 0);
                editor= setting.edit();

                Log.e("auto_login : ", "" + setting.getBoolean("Auto_Login_enabled", false));
                if(setting.getBoolean("Auto_Login_enabled", false)){
                    Student.getInstance().setName(setting.getString("myname", ""));
                    Student.getInstance().setSchool(setting.getString("myschool", ""));
                    Student.getInstance().setGrade(setting.getString("mygrade", ""));
                    Student.getInstance().setClass_(setting.getString("myclass", ""));
                    Student.getInstance().setStudentId(Integer.parseInt(setting.getString("myStudentId", "")));

                    ApiRequester.getInstance().loginStudent(Student.getInstance(), new ApiRequester.UserCallback<Student>() {
                        @Override
                        public void onSuccess(Student result) {
                            if(result == null)
                            {
                                TastyToast.makeText(getApplicationContext(), "사용자 정보가 존재하지 않습니다.", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);
                            }
                            else
                            {
                                Student.getInstance().setStudent(result);
                                Intent loginIntent = new Intent(SplashActivity.this, SelectExamOrPractice.class);
                                SplashActivity.this.startActivity(loginIntent);
                                SplashActivity.this.finish();
                            }
                        }
                        @Override
                        public void onFail() {
                        }
                    });
                }
                else{
                    Util.getInstance().moveActivity(SplashActivity.this, SignInActivity.class);
                }

            }
        }, SPLASH_TIME_OUT);
    }
}
