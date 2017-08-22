package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.Util;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.MyFirebaseMessagingService;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainPage extends AppCompatActivity {
    @BindView(R.id.tvTeacherSchoolName)
    TextView tvTeacherSchoolName;
    @BindView(R.id.tvTeacherName)
    TextView tvTeacherName;
    @BindView(R.id.etTeacherId)
    EditText etTeacherId;
    @BindView(R.id.btExamReady)
    ActionProcessButton btExamReady;

    @OnClick(R.id.btExamReady)
    void onClickBtExamReady() {
        if (btExamReady.getProgress() < 100) { // LOADING
            btExamReady.setProgress(btExamReady.getProgress() + 25);
        } else if (btExamReady.getProgress() == 100) { // SUCCESS
            //Util.getInstance().moveAcitivity(this, ExamActivity.class);
        } else {
            btExamReady.setProgress(-1); // ERROR
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main_page);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("teacherId");
        FirebaseInstanceId.getInstance().getToken();
        registerReceiver(myReceiver, new IntentFilter(MyFirebaseMessagingService.START_INTENT));

        etTeacherId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //TODO 서버에서 교사정보 가져와서 설정하기
                if (false) { // 교사정보 있으면
                    tvTeacherSchoolName.setText("OK");
                    tvTeacherName.setText("OK");
                } else { // 없으면
                    tvTeacherSchoolName.setText("아이디를 다시 입력해주세요.");
                    tvTeacherSchoolName.setPadding(20, 5, 20, 5);
                    tvTeacherName.setText("아이디를 다시 입력해주세요.");
                    tvTeacherName.setPadding(20, 5, 20, 5);
                }

            }
        });
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Util.getInstance().moveAcitivity(MainPage.this, ExamActivity.class);
        }
    };
}
