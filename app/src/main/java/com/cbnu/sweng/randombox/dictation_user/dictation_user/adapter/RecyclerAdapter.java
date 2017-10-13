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
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile.TeacherList;

import java.util.List;

/**
 * Created by SM on 2017-10-13.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    List<Student> student;

    public RecyclerAdapter(List<Student> items){
        student = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView studentname;
        public TextView schoolname;
        public Button bt;

        public ViewHolder(View itemView) {
            super(itemView);
            studentname = (TextView) itemView.findViewById(R.id.name);
            schoolname = (TextView) itemView.findViewById(R.id.school);
            bt = (Button) itemView.findViewById(R.id.button);

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
        holder.studentname.setText(student.get(position).getName());
        holder.schoolname.setText(student.get(position).getSchool());

        Log.d("TAG", student.get(position).getName());
        Log.d("TAG", student.get(position).getSchool());
        Log.d("TAG", "씨바아아아아아아");

    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return student.size();
    }

    // 커스텀 뷰홀더
// item layout 에 존재하는 위젯들을 바인딩합니다.
//    class ItemViewHolder extends RecyclerView.ViewHolder{
//        private TextView mNameTv;
//        public ItemViewHolder(View itemView) {
//            super(itemView);
//            mNameTv = (TextView) itemView.findViewById(R.id.itemNameTv);
//        }
//    }


}
