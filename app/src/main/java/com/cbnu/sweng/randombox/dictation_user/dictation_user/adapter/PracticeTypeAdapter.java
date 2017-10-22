package com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter;

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
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectGradeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.PracticeTypeModel;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.DictationPracticeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.VowelAndConsonantPracticeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.VowelOrConsonantPracticeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.WordPracticeActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PracticeTypeAdapter extends RecyclerView.Adapter<PracticeTypeAdapter.PracTypeViewHolder> {

    private ArrayList<PracticeTypeModel> practiceTypeModels;
    private Context context;

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

        float imageWidth = ((Util.getInstance().getDisplayWidth(context) / 2)
                                - (Util.getInstance().getMargin(context)));
        float imageHeight = ((Util.getInstance().getDisplayHeigth(context) / 7 * 2)
                                - Util.getInstance().getMargin(context));

        Bitmap image = BitmapFactory.decodeResource(context.getResources(), practiceTypeModel.getImage());
        holder.ivPractice.setImageBitmap(Bitmap.createScaledBitmap(image, (int) imageWidth, (int) imageHeight, false));
        holder.tvTitle.setText(practiceTypeModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return (null != practiceTypeModels ? practiceTypeModels.size() : 0);
    }

    public class PracTypeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPractice) ImageView ivPractice;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.lrPractice) LinearLayout lrPractice;

        public PracTypeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.lrPractice)
        public void onCheck() {
            int postion = getAdapterPosition();
            if(postion == 0){
                Util.getInstance().moveActivity(context, VowelOrConsonantPracticeActivity.class);
            }
            else if(postion == 1){
                Util.getInstance().moveActivity(context, VowelAndConsonantPracticeActivity.class);
            }
            else if(postion == 2){
                Util.getInstance().moveActivity(context, WordPracticeActivity.class);
            }
            else if(postion == 3){
                Util.getInstance().moveActivity(context, SelectGradeActivity.class);
            }
        }

    }
}
