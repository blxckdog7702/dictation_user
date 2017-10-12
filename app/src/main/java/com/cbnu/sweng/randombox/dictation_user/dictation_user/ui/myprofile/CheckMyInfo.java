package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckMyInfo extends AppCompatActivity {

    @BindView(R.id.showname) TextView showname;
    @BindView(R.id.showschoolname) TextView showschoolname;
    @BindView(R.id.showinfo) TextView showinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_info);
        ButterKnife.bind(this);

        SharedPreferences setting = getSharedPreferences("setting", MODE_PRIVATE);

        showname.setText(setting.getString("studentname", ""));
        showschoolname.setText(setting.getString("schoolname", ""));
        showinfo.setText(setting.getString("studentInfo", ""));
    }

}