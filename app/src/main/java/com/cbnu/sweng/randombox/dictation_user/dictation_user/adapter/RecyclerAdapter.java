package com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.RecyclerItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.ItemTouchHelperListener;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.TeacherList;

import java.util.List;

/**
 * Created by SM on 2017-10-13.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements ItemTouchHelperListener

{
    List<Teacher> teacher;


    public RecyclerAdapter(List<Teacher> items) {
        teacher = items;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < 0 || fromPosition >= teacher.size() || toPosition < 0 || toPosition >= teacher.size()) {
            return false;
        }
        return true;
    }
            @Override
            public void onItemRemove(int position) {
                teacher.remove(position);
                notifyItemRemoved(position);
            }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView studentname;

        public ViewHolder(View itemView) {
            super(itemView);
            studentname = (TextView) itemView.findViewById(R.id.name);
        }
    }
    // 새로운 뷰 홀더 생성
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.studentname.setText(teacher.get(position).getName());
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return teacher.size();
    }
}
