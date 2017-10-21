package com.cbnu.sweng.randombox.dictation_user.dictation_user.service;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by SAMSUNG on 2017-10-21.
 */

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;

    public VerticalSpaceItemDecoration(int verticalSpaceHeight){
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
// 마지막 아이템이 아닌 경우, 공백 추가
//        if(parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() -1 ){
//            outRect.bottom = verticalSpaceHeight;
//        }
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top=verticalSpaceHeight;
    }
}