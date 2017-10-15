package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.CheckMyInfo;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.RecordManagerActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.TeacherList;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectPracticeTypeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignInActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignUpActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    Context context = this;

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    public @Nullable @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }

    protected void bindViews() {
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        setupToolbar();
        initMenuFragment();
    }

    public void setContentViewWithoutInject(int layoutResId) {
        super.setContentView(layoutResId);
    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        menuParams.setAnimationDuration(140);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("내 정보 관리");
        send.setResource(R.drawable.icn_1);

        MenuObject like = new MenuObject("존나 카리스마 있어");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("내 선생님 관리");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("내 성적 관리");
        addFav.setResource(R.drawable.icn_4);

        MenuObject block = new MenuObject("로그아웃");
        block.setResource(R.drawable.icn_5);

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);
        return menuObjects;
    }

    protected void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void addFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStackName = fragment.getClass().getName();
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
        if (!fragmentPopped) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(containerId, fragment, backStackName)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            if (addToBackStack)
                transaction.addToBackStack(backStackName);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finish();
        }
    }

    //TODO 버튼별 화면 연결바람
    @Override
    public void onMenuItemClick(View clickedView, int position) {
        if(position == 0){

        }
        else if(position == 1){ // 내 정보 관리
            setting = getSharedPreferences("setting", MODE_PRIVATE);
            editor = setting.edit();// editor가져오기

            if(setting.getString("myStudentId", "") == null || setting.getString("myStudentId", "").trim().equals("")){
                Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent t = new Intent(BaseActivity.this, CheckMyInfo.class);
                startActivity(t);
            }
        }
        else if(position == 2){ // 존나 카리스마 있어
            setting = getSharedPreferences("setting", MODE_PRIVATE);
            editor = setting.edit();// editor가져오기

            if(setting.getString("myStudentId", "") == null || setting.getString("myStudentId", "").trim().equals("")){
                Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_LONG).show();
            }
            else
            {
                // 이동할 액티비티 작성
            }

        }
        else if(position == 3){ // 내 선생님 관리
            setting = getSharedPreferences("setting", MODE_PRIVATE);
            editor = setting.edit();// editor가져오기

            if(setting.getString("myStudentId", "") == null || setting.getString("myStudentId", "").trim().equals("")){
                Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent e = new Intent(BaseActivity.this, TeacherList.class);
                startActivity(e);
            }

        }
        else if(position == 4){ // 내 성적 관리
            setting = getSharedPreferences("setting", MODE_PRIVATE);
            editor = setting.edit();// editor가져오기

            if(setting.getString("myStudentId", "") == null || setting.getString("myStudentId", "").trim().equals("")){
                Toast.makeText(getApplicationContext(), "로그인을 해주세요", Toast.LENGTH_LONG).show();
            }
            else
            {
                Util.getInstance().moveActivity(this, RecordManagerActivity.class);
            }
        }
        else if(position == 5){ // 로그아웃
            new AlertDialog.Builder(this)
                    .setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                    .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            setting = getSharedPreferences("setting", MODE_PRIVATE);
                            editor = setting.edit();// editor가져오기

                            editor.clear();
                            editor.commit(); // 파일에 최종 반영

                            Intent i = new Intent(BaseActivity.this, SignInActivity.class);

                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {

    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
