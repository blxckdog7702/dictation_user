package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;

public class RecordFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_record,null);
    }

}
