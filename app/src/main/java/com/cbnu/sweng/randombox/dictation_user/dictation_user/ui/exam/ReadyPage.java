package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.MyFirebaseMessagingService;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ReadyPage extends AppCompatActivity {

    private Boolean isReceiveKey = false;
    private Boolean isTeacherInfo = false;
    private ArrayList<Teacher> teachers;
    private Teacher selectedTeacher;
    @BindView(R.id.tvTeacherSchoolName) TextView tvTeacherSchoolName;
    @BindView(R.id.tvTeacherName) TextView tvTeacherName;
    @BindView(R.id.btExamReady) ActionProcessButton btExamReady;
    @BindView(R.id.spTeacherId) Spinner spTeacherId;

    @OnClick(R.id.btExamReady)
    void onClickBtExamReady() {
        if(isTeacherInfo){
            if (btExamReady.getProgress() < 100) { // LOADING
                btExamReady.setProgress(btExamReady.getProgress() + 25);
                isReceiveKey = true;
            }
            else if (btExamReady.getProgress() == 100) { // SUCCESS

            }
        }
        else{
            Toast.makeText(getApplicationContext(), "선생님 정보가 올바르지 않습니다.", Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_ready_page);
        ButterKnife.bind(this);
        FirebaseMessaging.getInstance().subscribeToTopic("teacherId");
        FirebaseInstanceId.getInstance().getToken();
        registerReceiver(myReceiver, new IntentFilter(MyFirebaseMessagingService.START_INTENT));

        getServerData();
        initTeacherId();

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

    private void getServerData(){
        ApiRequester.getInstance().getStudentsTeachers(Student.getInstance().getStudentId().toString(),
                                                            new ApiRequester.UserCallback<List<Teacher>>() {
            @Override
            public void onSuccess(List<Teacher> result) {
                teachers = (ArrayList<Teacher>) result;
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
            //
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
}
