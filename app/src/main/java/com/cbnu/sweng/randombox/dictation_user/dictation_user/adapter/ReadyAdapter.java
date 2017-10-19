package com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.RecordModel;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.RecordResultActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadyAdapter extends RecyclerView.Adapter<ReadyAdapter.ReadyViewHolder> {

    private ArrayList<Teacher> teachers;
    private Context context;

    public ReadyAdapter(Context context, ArrayList<Teacher> teachers) {
        this.context = context;
        this.teachers = teachers;
    }

    @Override
    public ReadyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup v = (ViewGroup) mInflater.inflate(R.layout.item_record, parent, false);
        return new ReadyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ReadyViewHolder holder, final int position) {
        final Teacher teacher = teachers.get(position);

        holder.tvTeacherNameInList.setText(teacher.getLoginId());
        holder.tvTeacherNameInList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != teachers ? teachers.size() : 0);
    }

    public class ReadyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTeacherNameInList) TextView tvTeacherNameInList;

        public ReadyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
