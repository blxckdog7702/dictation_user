package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.SelectExamOrPractice;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.ThanksToActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam.ExamPage;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam.ReadyPage;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.RecordManagerActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.DictationPracticeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectGradeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectPracticeTypeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.VowelAndConsonantPracticeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.VowelOrConsonantPracticeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.WordPracticeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.CircleTransformation;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.picasso.Picasso;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseDrawerActivity extends BaseActivity {

    @BindView(R.id.drawerlayout) FlowingDrawer drawerlayout;
    @Nullable @BindView(R.id.vNavigation) NavigationView vNavigation;

    @BindDimen(R.dimen.global_menu_avatar_size)
    int avatarSize;

    //Cannot be bound via Butterknife, hosting view is initialized later (see setupHeader() method)
    private ImageView ivMenuUserProfilePhoto;
    private TextView tvStudentName;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentViewWithoutInject(R.layout.activity_base_drawer);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.lrContent);
        LayoutInflater.from(this).inflate(layoutResID, viewGroup, true);
        bindViews();
    }

    protected void bindViews() {
        super.bindViews();
        ButterKnife.bind(this);
        setupToolbar();
        setupMenu();
        setupHeader();
    }

    private void setupMenu() {
        drawerlayout.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        vNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if(menuItem.getTitle().equals("홈")){
                    if(!getLocalClassName().toString().equals("ui.SelectExamOrPractice")){
                        Intent intent = new Intent(context, SelectExamOrPractice.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                    drawerlayout.closeMenu();
                }
                else if(menuItem.getTitle().equals("시험보기")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui.exam.ReadyPage")){
                        setting = getSharedPreferences("setting", MODE_PRIVATE);
                        editor = setting.edit();// editor가져오기

                        if(TextUtils.isEmpty(setting.getString("myStudentId", ""))){
                            TastyToast.makeText(getApplicationContext(), "로그인을 해야 합니다.", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                        }
                        else
                        {
                            Util.getInstance().moveActivity(context, ReadyPage.class);
                            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
                        }
                    }
                }
                else if(menuItem.getTitle().equals("공부하기")){

                    if(!getLocalClassName().toString().equals("ui.practice.SelectPracticeTypeActivity")){
                        Intent intent = new Intent(context, SelectPracticeTypeActivity.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                    drawerlayout.closeMenu();
                }
                else if(menuItem.getTitle().equals("        자음 / 모음")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui.practice.VowelOrConsonantPracticeActivity")){
                        Intent intent = new Intent(context, VowelOrConsonantPracticeActivity.class);
                        context.startActivity(intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                }
                else if(menuItem.getTitle().equals("        자음 + 모음")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui.practice.VowelAndConsonantPracticeActivity")){
                        Intent intent = new Intent(context, VowelAndConsonantPracticeActivity.class);
                        context.startActivity(intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                }
                else if(menuItem.getTitle().equals("        낱말 카드")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui.practice.WordPracticeActivity")){
                        Intent intent = new Intent(context, WordPracticeActivity.class);
                        context.startActivity(intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                }
                else if(menuItem.getTitle().equals("        급수표 받아쓰기")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui.practice.SelectGradeActivity")){
                        Intent intent = new Intent(context, SelectGradeActivity.class);
                        context.startActivity(intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                }
                else if(menuItem.getTitle().equals("내성적 열람")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui..myprofile.RecordManagerActivity")){
                        setting = getSharedPreferences("setting", MODE_PRIVATE);
                        editor = setting.edit();// editor가져오기

                        if(TextUtils.isEmpty(setting.getString("myStudentId", ""))){
                            TastyToast.makeText(getApplicationContext(), "로그인을 해야 합니다.", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                        }
                        else
                        {
                            Intent intent = new Intent(context, RecordManagerActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                        }
                    }
                }
                else if(menuItem.getTitle().equals("도움을 주신 분")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui.ThanksToActivity")){
                        Intent intent = new Intent(context, ThanksToActivity.class);
                        context.startActivity(intent);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                }
                else{
                    //
                }
                return false;
            }
        }) ;
        vNavigation.setItemIconTintList(null);
    }

    private void setupHeader() {
        View headerView = vNavigation.getHeaderView(0);
        ivMenuUserProfilePhoto = (ImageView) headerView.findViewById(R.id.ivMenuUserProfilePhoto);
        tvStudentName = (TextView) headerView.findViewById(R.id.tvStudentName);
        headerView.findViewById(R.id.vGlobalMenuHeader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGlobalMenuHeaderClick(v);
            }
        });
        Picasso.with(this)
                .load(R.drawable.profile)
                .placeholder(R.drawable.img_circle_placeholder)
                .resize(avatarSize, avatarSize)
                .centerCrop()
                .transform(new CircleTransformation())
                .into(ivMenuUserProfilePhoto);
        tvStudentName.setText(Student.getInstance().getName());
    }

    public void onGlobalMenuHeaderClick(final View v) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                int[] startingLocation = new int[2];
//                v.getLocationOnScreen(startingLocation);
//                startingLocation[0] += v.getWidth() / 2;
//                UserProfileActivity.startUserProfileFromLocation(startingLocation, BaseDrawerActivity.this);
//                overridePendingTransition(0, 0);
            }
        }, 200);
    }

    @Override
    public void setupToolbar(){
        super.setupToolbar();
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.toggleMenu();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerlayout.isMenuVisible()) {
            drawerlayout.closeMenu();
        } else {
            Util.getInstance().onBackPressed(this);
        }
    }
}
