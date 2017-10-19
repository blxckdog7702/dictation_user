package com.cbnu.sweng.randombox.dictation_user.dictation_user.service;

/**
 * Created by SAMSUNG on 2017-10-15.
 */

public interface ItemTouchHelperListener {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemRemove(int position);
}
