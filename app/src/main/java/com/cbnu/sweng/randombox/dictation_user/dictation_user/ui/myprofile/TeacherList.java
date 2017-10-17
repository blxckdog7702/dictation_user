package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.adapter.RecyclerAdapter;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.RecyclerItem;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.School;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.ItemTouchHelperCallback;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.ItemTouchHelperListener;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.base.BaseActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignInActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeacherList extends AppCompatActivity{

    SharedPreferences setting;
    SharedPreferences.Editor editor;
    List<Teacher> myDataset = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String id; // 학생 기본키
    private String searchTeacherID; // 선생님ID검색 API 로 넘어가는 학교 값
    private String name[];
    private String teachername;
    private int temp;
    private Context context = this;

    private Button teachersearch; // 다이얼로그 선생님검색 버튼
    private EditText teacherLoginId; // 다이얼로그에 있는 선생님로그인아이디 란

    @BindView(R.id.btaddTeacher) Button btaddTeacher;
    @OnClick(R.id.btaddTeacher)
    void onClickBtAddTeacher()
    {
        Toast.makeText(getApplicationContext(), "선생님 추가하기", Toast.LENGTH_LONG).show();
        View mView = getLayoutInflater().inflate(R.layout.dialog_add_teacher_name, null);
        teacherLoginId = ButterKnife.findById(mView, R.id.etTeacherName);
        teachersearch = ButterKnife.findById(mView, R.id.btSearchTeacher);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setTitle("선생님을 검색합니다.");
        teachersearch.setOnClickListener( // 검색하기 버튼 누르는 부분
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        searchTeacherID = teacherLoginId.getText().toString();
                        ApiRequester.getInstance().searchTeacherByLoginID(searchTeacherID, new ApiRequester.UserCallback<Teacher>() {
                            @Override
                            public void onSuccess(Teacher result)
                            {
                                if(result==null)
                                {
                                    Toast.makeText(getApplicationContext(), "입력한 선생님이 없습니다.", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), result.getName(), Toast.LENGTH_LONG).show();
                                    teachername = result.getName();
                                        Log.d("받아오기성공", result.getName());
                                        editor.putString("teacherLoginId", searchTeacherID);
                                }
                            }
                            @Override
                            public void onFail()
                            {
                                Log.d("TAG", "실패");
                            }
                        });
                    }
                });
        builder.setPositiveButton("매칭 신청하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Log.d("TEACHER", searchTeacherID);
                Log.d("TEACHER", id);

                ApiRequester.getInstance().applyMatching(searchTeacherID, id, new ApiRequester.UserCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean result) {
                        Toast.makeText(getApplicationContext(), "매칭신청완료!!", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFail() {
                        Toast.makeText(getApplicationContext(), "매칭실패ㅠㅠ", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                    }
                });
        builder.setView(mView);

        AlertDialog dialog = builder.create();
        dialog.show();
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
        id = setting.getString("id", "");
        Log.d("학생고유아이디", id);

        ApiRequester.getInstance().getStudentsTeachers(id, new ApiRequester.UserCallback<List<Teacher>>() {
            @Override
            public void onSuccess(List<Teacher> result) {
                Log.d("받아오기성공", id);

                if(result==null) {
                    Toast.makeText(getApplicationContext(), "등록된 선생님이 없습니다.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    for(Teacher s : result)
                    {
                        Log.d("받아오기성공", s.getName());
                        myDataset.add(s);
                    }
                    // specify an adapter (see also next example)1
                    mAdapter = new RecyclerAdapter(myDataset);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                        public boolean onMove(RecyclerView recyclerView,
                                              RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                            return true;
                        }
                        @Override
                        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


                            //Remove swiped item from list and notify the RecyclerView
                            final int deletedIndex = viewHolder.getAdapterPosition();
                            System.out.println("선생아이디"+myDataset.get(deletedIndex).getId() + "학생아이디" + id);

                            alertDialogBuilder
                                    .setTitle("매칭끊기").setMessage("선생님과의 매칭을 끊으시겠습니까?")
                                    .setPositiveButton("끊기", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton)
                                        {
                                            ApiRequester.getInstance().unConnectedMatching(id, myDataset.get(deletedIndex).getId(), new ApiRequester.UserCallback<Boolean>() {
                                                   @Override
                                                   public void onSuccess(Boolean result)
                                                   {
                                                       myDataset.remove(deletedIndex);
                                                       mAdapter.notifyDataSetChanged();

                                                       Toast.makeText(getApplicationContext(), "매칭 끊기 성공", Toast.LENGTH_LONG).show();
                                                    }

                                                    @Override
                                                    public void onFail()
                                                    {
                                                        Toast.makeText(getApplicationContext(), "매칭 끊기 실패", Toast.LENGTH_LONG).show();
                                                     }
                                                });
                                            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_left_out);
                                        }
                                    }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    mAdapter.notifyDataSetChanged();

                                }
                            }).show();
                            //mAdapter.notifyItemRemoved(viewHolder.getLayoutPosition());
                        }
                    };
                    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
                    itemTouchHelper.attachToRecyclerView(mRecyclerView);
                }
            }
            @Override
            public void onFail() {
            }
        });
    }


}
