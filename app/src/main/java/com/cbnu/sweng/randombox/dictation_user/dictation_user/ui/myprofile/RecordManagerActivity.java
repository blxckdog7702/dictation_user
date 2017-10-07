package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.os.Bundle;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseTabLayoutActivity;


public class RecordManagerActivity extends BaseTabLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_manager);

    }

    @Override
    protected void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new RecordFragment(), "성 적 표");
        adapter.addFrag(new RecordFragment(), "취 약 점");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}