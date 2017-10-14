package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.RecyclerAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.RecyclerItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;

public class TeacherList extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<Student> myDataset = new ArrayList<>();


        for(int i=0; i<20; i++)
        {
            Student.getInstance().setName("장다혜");
            Student.getInstance().setSchool("증안초등학교");

            myDataset.add(Student.getInstance());
        }



        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }
}
