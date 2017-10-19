package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.ReadyAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.RecordAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.MyFirebaseMessagingService;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.CircleTransformation;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.RecyclerItemClickListener;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.FcmRequester;
import com.dd.processbutton.iml.ActionProcessButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.sdsmdg.tastytoast.TastyToast;
import com.skyfishjy.library.RippleBackground;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReadyPage extends BaseActivity {

    private Handler handler = new Handler();
    private Boolean isReceiveKey = false;
    private Boolean isTeacherInfo = false;
    private Boolean isCancle = false;
    private ArrayList<Teacher> teachers;
    private Teacher selectedTeacher = null;
    private AnimatorSet animatorSet;
    private ReadyAdapter readyAdapter;
    @BindView(R.id.tvTeacherSchoolName) TextView tvTeacherSchoolName;
    @BindView(R.id.tvTeacherName) TextView tvTeacherName;
    @BindView(R.id.ivStudentPhone) ImageView ivStudentPhone;
    @BindView(R.id.ivTeacherPhone) ImageView ivTeacherPhone;
    @BindView(R.id.rbRippleBackground) RippleBackground rbRippleBackground;
    @BindDimen(R.dimen.global_menu_avatar_size) int avatarSize;
    @BindView(R.id.rvReady) RecyclerView rvReady;
    @BindView(R.id.ivTeacher) CircleImageView ivTeacher;
    @BindView(R.id.ivStudent) CircleImageView ivStudent;



    @OnClick(R.id.btExamReady)
    void btExamReady() {
        if(isCancle){
            //animatorSet.cancle();
            unsubScribe(selectedTeacher.getLoginId());
            isCancle = false;
        }
        else{
            if(isTeacherInfo){
                isReceiveKey = true;
                isCancle = true;
                subScribe(selectedTeacher.getLoginId());
                rbRippleBackground.startRippleAnimation();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        foundDevice();
                    }
                },300);
            }
            else{
                TastyToast.makeText(getApplicationContext(), "선생님 정보가 올바르지 않습니다.", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_page);
        ButterKnife.bind(this);
        FirebaseInstanceId.getInstance().getToken();
        registerReceiver(myReceiver, new IntentFilter(MyFirebaseMessagingService.START_INTENT));

        getServerData();

        rvReady.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), rvReady, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //teachers.get(position)
                        ivTeacher.setVisibility();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));

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
                setupView();
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void setupView() {
        rvReady.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(this);
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvReady.setLayoutManager(MyLayoutManager);

        readyAdapter = new ReadyAdapter(this, teachers);
        rvReady.setAdapter(readyAdapter);
        readyAdapter.notifyDataSetChanged();
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ivTeacherPhone.setVisibility(View.VISIBLE);

            String historyId = intent.getStringExtra("quizHistoryId");
            String quizNumber = intent.getStringExtra("quizNumber");

            if(isReceiveKey){
                Intent intent2 = new Intent(ReadyPage.this, ExamPage.class);
                intent2.putExtra("quizHistoryId", historyId);
                intent2.putExtra("quizNumber", quizNumber);
                intent2.putExtra("Teacher", selectedTeacher);
                startActivity(intent2);
                animatorSet.end();
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

    private void foundDevice(){
        animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList=new ArrayList<Animator>();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(ivTeacherPhone, "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(ivTeacherPhone, "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator);
        animatorSet.playTogether(animatorList);
        animatorSet.start();
    }
}
