package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseDrawerActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;

import butterknife.BindView;

public class SelectClassActivity extends BaseDrawerActivity {

    @BindView(R.id.tlSelectClass) TableLayout tlSelectClass;

    TableRow tableRow;
    int QuesionNumber = 23;
    int studentClass;
    int studentGrade;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);

        Intent intent = getIntent();
        studentClass = intent.getIntExtra("int", 0);

        int i = 0;
        while (i <= QuesionNumber) {
            if (i % 5 == 0) {
                tableRow = new TableRow(this);
                tlSelectClass.addView(tableRow);
            }
            Button btn = new Button(this);
            btn.setText(i+1 + "급");
            btn.setId(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    studentGrade = v.getId();
                    Util.getInstance().moveActivity(SelectClassActivity.this, DictationPracticeActivity.class, studentClass + "-" + studentGrade);
                    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
                }
            });
            tableRow.addView(btn);
            i++;
        }
    }

}
