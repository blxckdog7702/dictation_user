package com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.Util;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PracticeTypeModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017-09-30.
 */

public class PracticeTypeAdapter extends RecyclerView.Adapter<PracticeTypeAdapter.PracTypeViewHolder> {

    private ArrayList<PracticeTypeModel> practiceTypeModels;
    private Context context;

    private PracTypeViewHolder holder;

    public PracticeTypeAdapter(Context context, ArrayList<PracticeTypeModel> practiceTypeModels) {
        this.context = context;
        this.practiceTypeModels = practiceTypeModels;
    }

    @Override
    public PracTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup v = (ViewGroup) mInflater.inflate(R.layout.item_practice, parent, false);
        return new PracTypeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PracTypeViewHolder holder, final int position) {
        final PracticeTypeModel practiceTypeModel = practiceTypeModels.get(position);
        this.holder = holder;

        float displayWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        float displayHeigth = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
        float margin = (int) Util.getInstance().convertDpToPixel(10f, (Activity) context);
        float imageWidth = ((displayWidth - (margin)) / 2);
        float imageHeight = ((displayHeigth / 4) - margin);

        Bitmap image = BitmapFactory.decodeResource(context.getResources(), practiceTypeModel.getImage());
        holder.ivPractice.setImageBitmap(Bitmap.createScaledBitmap(image, (int) imageWidth, (int) imageHeight, false));

        holder.tvPracticeType.setText(practiceTypeModel.getPraticeType());
        holder.tvTitle.setText(practiceTypeModel.getTitle());

        holder.lrPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){

                }
                if(position == 1){

                }
                if(position == 2){

                }
                if(position == 3){

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != practiceTypeModels ? practiceTypeModels.size() : 0);
    }

    public class PracTypeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvPracticeType) TextView tvPracticeType;
        @BindView(R.id.ivPractice) ImageView ivPractice;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.lrPractice) LinearLayout lrPractice;

        public PracTypeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
