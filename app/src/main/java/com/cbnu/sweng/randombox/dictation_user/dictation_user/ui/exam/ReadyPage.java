package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.MyFirebaseMessagingService;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.FcmRequester;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ReadyPage extends BaseActivity {

    private Boolean isReceiveKey = false;
    private Boolean isTeacherInfo = false;
    private ArrayList<Teacher> teachers;
    private Teacher selectedTeacher = null;
    @BindView(R.id.tvTeacherSchoolName) TextView tvTeacherSchoolName;
    @BindView(R.id.tvTeacherName) TextView tvTeacherName;
    @BindView(R.id.btExamReady) ActionProcessButton btExamReady;
    @BindView(R.id.spTeacherId) Spinner spTeacherId;

    @OnClick(R.id.btExamReady)
    void onClickBtExamReady() {
        if(isTeacherInfo){
            if(!isReceiveKey) {
                if (btExamReady.getProgress() < 100) { // LOADING
                    btExamReady.setProgress(btExamReady.getProgress() + 25);
                    isReceiveKey = true;
                    subScribe(selectedTeacher.getLoginId());
                }
                else if (btExamReady.getProgress() == 100) { // SUCCESS

                }
            }
            //버튼 눌림 상태일 때, 누르면 구독 해제 처리
            else {
                btExamReady.setProgress(0);
                isReceiveKey = false;
                unSubScribe(selectedTeacher.getLoginId());
            }

        }
        else{
            Toast.makeText(getApplicationContext(), "선생님 정보가 올바르지 않습니다.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_ready_page);
        ButterKnife.bind(this);
        FirebaseInstanceId.getInstance().getToken();
        registerReceiver(myReceiver, new IntentFilter(MyFirebaseMessagingService.START_INTENT));

        getServerData();

        spTeacherId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                isTeacherInfo = true;
                String selectedTeacherId = parent.getItemAtPosition(position).toString();
                for(Teacher teacher : teachers){
                    if(selectedTeacherId.equals(teacher.getLoginId())){
                        tvTeacherSchoolName.setText(teacher.getSchool());
                        tvTeacherName.setText(teacher.getName());
                        selectedTeacher = teacher;
                        break;
                    }
                }

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    private void getServerData(){
        ApiRequester.getInstance().getStudentsTeachers(Student.getInstance().getId(),
                                                            new ApiRequester.UserCallback<List<Teacher>>() {
            @Override
            public void onSuccess(List<Teacher> result) {
                teachers = (ArrayList<Teacher>) result;
                initTeacherId();
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void initTeacherId(){
        if(teachers != null){
            String[] items = new String[teachers.size()];
            for(int i = 0; i < teachers.size(); i++){
                items[i] = teachers.get(i).getLoginId();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
            spTeacherId.setAdapter(adapter);
        }
        else{
            Toast.makeText(getApplicationContext(), "등록된 선생님이 없습니다.", Toast.LENGTH_SHORT).show();
        }

    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String historyId = intent.getStringExtra("quizHistoryId");
            String quizNumber = intent.getStringExtra("quizNumber");

            if(isReceiveKey){
                btExamReady.setProgress(100);
                Intent intent2 = new Intent(ReadyPage.this, ExamPage.class);
                intent2.putExtra("quizHistoryId", historyId);
                intent2.putExtra("quizNumber", quizNumber);
                intent2.putExtra("Teacher", selectedTeacher);
                startActivity(intent2);
            }
        }
    };

    private void subScribe(String topic){
        FirebaseMessaging.getInstance().subscribeToTopic(topic);
        FcmRequester.getInstance().notifyToTeacherSubscribe(topic, Student.getInstance(), true);
    }

    private void unSubScribe(String topic){
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
        FcmRequester.getInstance().notifyToTeacherSubscribe(topic, Student.getInstance(), false);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            if(selectedTeacher != null){
                unSubScribe(selectedTeacher.getLoginId());
            }
            finish();
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
        }
    }

}
