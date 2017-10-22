package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.TeacherListAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class TeacherList extends BaseActivity{

    SharedPreferences setting;
    SharedPreferences.Editor editor;
    List<Teacher> myDataset = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Context context = this;
    private SweetAlertDialog pDialog;
    private Boolean isTeacher = false;

    Teacher teacher;

    @BindView(R.id.btaddTeacher) Button btaddTeacher;
    @BindView(R.id.lrNotUser) LinearLayout lrNotUser;

    @OnClick(R.id.btaddTeacher)
    void onClickBtAddTeacher()
    {
        final Button btSearchTeacher = new Button(this);
        btSearchTeacher.setBackground(this.getResources().getDrawable(R.drawable.ic_search));
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        btSearchTeacher.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        final EditText etTeacherLoginId = new EditText(this);
        etTeacherLoginId.setHint("선생님ID를 입력해주세요.    ");
        etTeacherLoginId.setHintTextColor(Color.rgb(255,158,27));
        etTeacherLoginId.setTextColor(Color.rgb(255,158,27));
        etTeacherLoginId.setTextSize(16);
        etTeacherLoginId.setMaxLines(12);

        LinearLayout l1 = new LinearLayout(getApplicationContext());
        l1.setOrientation(LinearLayout.VERTICAL);

        LinearLayout l3 = new LinearLayout(getApplicationContext());
        l3.setOrientation(LinearLayout.HORIZONTAL);
        l3.addView(etTeacherLoginId);
        l3.addView(btSearchTeacher);

        l1.addView(l3);

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE);
        pDialog.setCustomView(l1);
        pDialog.setCancelText("취소");
        pDialog.setConfirmText("신청");
        pDialog.showCancelButton(true);
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                ApiRequester.getInstance().applyMatching(teacher.getLoginId(), Student.getInstance().getId(), new ApiRequester.UserCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        TastyToast.makeText(getApplicationContext(), "등록 신청이 되었습니다.", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);
                    }
                    @Override
                    public void onFail() {
                        TastyToast.makeText(getApplicationContext(), "서버와 연결이 원활하지 않습니다.", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                    }
                });
                sDialog.dismiss();
            }
        });
        pDialog.show();

        btSearchTeacher.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        ApiRequester.getInstance().searchTeacherByLoginID(etTeacherLoginId.getText().toString(), new ApiRequester.UserCallback<Teacher>() {
                            @Override
                            public void onSuccess(Teacher result)
                            {
                                teacher = result;
                                if(teacher==null) {
                                    TastyToast.makeText(getApplicationContext(), "해당 선생님이 없습니다.", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);
                                }
                                else {
                                    isTeacher = true;
                                    new SweetAlertDialog(context)
                                            .setContentText(teacher.getSchool() + "\n" + teacher.getGrade() + "학년 " + teacher.getClass_() + "반 "
                                                            + teacher.getInstance().getName() + " 선생님" + "\n" + "맞으신가요?" )
                                            .setCancelText("아니오")
                                            .setConfirmText("네")
                                            .showCancelButton(true)
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    if(isTeacher){
                                                        //
                                                    }
                                                    else{
                                                        TastyToast.makeText(getApplicationContext(), "선생님ID를 확인해주세요.", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                                                    }
                                                }
                                            })
                                            .show();
                                }
                            }
                            @Override
                            public void onFail()
                            {
                                TastyToast.makeText(getApplicationContext(), "서버와 연결이 원활하지 않습니다.", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                            }
                        });
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ButterKnife.bind(this);

        setting = getSharedPreferences("setting", MODE_PRIVATE);
        editor= setting.edit();

        ApiRequester.getInstance().getStudentsTeachers(Student.getInstance().getId(), new ApiRequester.UserCallback<List<Teacher>>() {
            @Override
            public void onSuccess(List<Teacher> result) {
                if(result.size() == 0) {
                    lrNotUser.setVisibility(View.VISIBLE);
                }
                else
                {
                    for(Teacher s : result)
                    {
                        myDataset.add(s);
                    }
                    mAdapter = new TeacherListAdapter(myDataset);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                        public boolean onMove(RecyclerView recyclerView,
                                              RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                            return true;
                        }
                        @Override
                        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                            final int deletedIndex = viewHolder.getAdapterPosition();
                            new SweetAlertDialog(context)
                                    .setContentText("선택한 선생님을 삭제하시겠습니까?")
                                    .setCancelText("아니오")
                                    .setConfirmText("네")
                                    .showCancelButton(true)
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            mAdapter.notifyDataSetChanged();
                                        }
                                    })
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            ApiRequester.getInstance().unConnectedMatching(Student.getInstance().getId(), myDataset.get(deletedIndex).getId(), new ApiRequester.UserCallback<Boolean>() {
                                                @Override
                                                public void onSuccess(Boolean result)
                                                {
                                                    myDataset.remove(deletedIndex);
                                                    mAdapter.notifyDataSetChanged();

                                                    TastyToast.makeText(getApplicationContext(), "삭제되었습니다.", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                                                }

                                                @Override
                                                public void onFail()
                                                {
                                                    TastyToast.makeText(getApplicationContext(), "서버와 연결이 원활하지 않습니다..", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                                                }
                                            });
                                        }
                                    })
                                    .show();
                        }
                    };
                    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
                    itemTouchHelper.attachToRecyclerView(mRecyclerView);
                }
            }
            @Override
            public void onFail() {
                TastyToast.makeText(getApplicationContext(), "서버와 연결이 원활하지 않습니다..", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.context_menu).setVisible(false);
        return true;
    }

    protected void setupToolbar() {
        super.setupToolbar();
    }
}
