package com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.ItemTouchHelperListener;

import java.util.List;

/**
 * Created by SM on 2017-10-13.
 */

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.ViewHolder> implements ItemTouchHelperListener {

    List<Teacher> teacher;

    public TeacherListAdapter(List<Teacher> items) {
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
        TextView tvTeacherName;
        TextView tvSchoolName;
        TextView tvTeacherInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTeacherName = (TextView) itemView.findViewById(R.id.tvTeacherName);
            tvSchoolName = (TextView) itemView.findViewById(R.id.tvSchoolName);
        }
    }
    // 새로운 word_59 홀더 생성
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTeacherName.setText(teacher.get(position).getGrade() + "학년 " + teacher.get(position).getClass_() + "반 "
                + teacher.get(position).getName() + " 선생님");
        holder.tvSchoolName.setText(teacher.get(position).getSchool());
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return teacher.size();
    }
}
