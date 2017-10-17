package com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.RecordModel;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.RecordResultActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private ArrayList<RecordModel> recordModels;
    private Context context;

    public RecordAdapter(Context context, ArrayList<RecordModel> recordModels) {
        this.context = context;
        this.recordModels = recordModels;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup v = (ViewGroup) mInflater.inflate(R.layout.item_record, parent, false);
        return new RecordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecordViewHolder holder, final int position) {
        final RecordModel recordModel = recordModels.get(position);

        holder.tvDate.setText(recordModel.getDate());
        holder.tvRank.setText(recordModel.getRank() + "등");
        holder.tvScore.setText(recordModel.getScore() + "점");
        if(recordModel.getScore() >= 80){
            holder.tvComment.setTextColor(Color.GREEN);
        }
        else if(recordModel.getScore() >= 50){
            holder.tvComment.setTextColor(Color.BLUE);
        }
        else{
            holder.tvComment.setTextColor(Color.RED);
        }
        holder.tvComment.setText(recordModel.getComment());
        holder.lrRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.getInstance().moveActivity(context, RecordResultActivity.class, recordModel.getQuizhistoryId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != recordModels ? recordModels.size() : 0);
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvRank) TextView tvRank;
        @BindView(R.id.tvScore) TextView tvScore;
        @BindView(R.id.tvDate) TextView tvDate;
        @BindView(R.id.tvComment) TextView tvComment;
        @BindView(R.id.lrRecord) LinearLayout lrRecord;

        public RecordViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
