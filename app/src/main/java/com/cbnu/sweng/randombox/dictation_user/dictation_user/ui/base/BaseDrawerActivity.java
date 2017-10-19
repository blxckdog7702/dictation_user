package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
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
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.exam.ReadyPage;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.RecordManagerActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectPracticeTypeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.CircleTransformation;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
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
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui.SelectExamOrPractice")){
                        Util.getInstance().moveActivity(context, SelectExamOrPractice.class);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                }
                else if(menuItem.getTitle().equals("시험보기")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui.exam.SelectExamOrPractice")){
                        Util.getInstance().moveActivity(context, ReadyPage.class);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                }
                else if(menuItem.getTitle().equals("공부하기")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui.practice.SelectPracticeTypeActivity")){
                        Util.getInstance().moveActivity(context, SelectPracticeTypeActivity.class);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                }
                else if(menuItem.getTitle().equals("내성적 열람")){
                    drawerlayout.closeMenu();
                    if(!getLocalClassName().toString().equals("ui..myprofile.RecordManagerActivity")){
                        Util.getInstance().moveActivity(context, RecordManagerActivity.class);
                        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_right_out);
                    }
                }
                else{
                    //
                }
                return false;
            }
        }) ;
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
