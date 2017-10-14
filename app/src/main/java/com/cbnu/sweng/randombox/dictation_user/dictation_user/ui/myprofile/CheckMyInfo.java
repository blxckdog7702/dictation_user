package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.myprofile;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.School;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.SelectExamOrPractice;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectPracticeTypeActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignInActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign.SignUpActivity;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.cbnu.sweng.randombox.dictation_user.dictation_user.R.array.strArrayCity;
import static com.cbnu.sweng.randombox.dictation_user.dictation_user.R.id.spCity;
import static com.cbnu.sweng.randombox.dictation_user.dictation_user.R.id.spState;

public class CheckMyInfo extends AppCompatActivity {

    SharedPreferences setting;
    SharedPreferences.Editor editor;


    Student student = new Student();
    ApiRequester apiRequester = new ApiRequester();

    private Handler mHandler;
    private Runnable mRunnable;

    private String myname; // 수정 API로 넘어가는 값
    private String myschool; // 수정 API로 넘어가는 값
    private String myinfo; // 수정 API로 넘어가는 값 ( 학년 + 반 + 번호 )
    private String id; // 수정 API로 넘어가는 값

    private String myclass; // 반
    private String mygrade; // 학년
    private int myStudentId; // 번호


    private Button schoolsearch; // 학교 검색 버튼
    private EditText schoolname; // 다이얼로그에 있는 학교이름 란
    private String selectedschool; // 학교검색 API 로 넘어가는 학교 값
    private String name[];
    int temp;

    Spinner spState;
    Spinner spCity;

    @BindArray(R.array.strArrayCity)
    String [] strArrayCity;
    @BindView(R.id.showname) TextView showname;
    @BindView(R.id.showschoolname) TextView showschoolname;
    @BindView(R.id.showinfo) TextView showinfo;
    @BindView(R.id.btEdit) Button btEdit;


    @OnClick(R.id.btEdit)
    void onClickBtEdit()
    {
        myname = showname.getText().toString();
        myschool = showschoolname.getText().toString();
        myinfo = showinfo.getText().toString();

        student.setName(myname);
        student.setSchool(myschool);
        student.setInfo(myinfo);

        Log.d("CHECK", student.getName());
        Log.d("CHECK", student.getSchool());
        Log.d("CHECK", student.getInfo());
        Log.d("CHECK", id);


        apiRequester.updateStudent(id, student, new ApiRequester.UserCallback<Student>() {
            @Override
            public void onSuccess(Student result) {
                Toast.makeText(getApplicationContext(), "수정이 완료되었습니다.", Toast.LENGTH_LONG).show();
                Log.d("FINISH", student.getName());
                Log.d("FINISH", student.getSchool());
                Log.d("FINISH", student.getInfo());
                Log.d("FINISH", id);

                editor.putString("studentname", student.getName());
                editor.putString("schoolname", student.getSchool());
                editor.putString("studentInfo", student.getInfo());
                editor.putString("id", id);

                editor.commit();

                Intent intent = new Intent(CheckMyInfo.this, SelectExamOrPractice.class);
                startActivity(intent);
            }

            @Override
            public void onFail() {
                Toast.makeText(getApplicationContext(), "서버와의 연결을 확인해주세요.", Toast.LENGTH_SHORT);

            }
        });
    }

    @OnClick(R.id.showschoolname)
    void onClickShowSchoolName(){
        View mView = getLayoutInflater().inflate(R.layout.dialog_select_school_name, null);
        spCity = ButterKnife.findById(mView, R.id.spCity);
        spState = ButterKnife.findById(mView, R.id.spState);
        schoolsearch = ButterKnife.findById(mView, R.id.btSearchShool);
        schoolname = ButterKnife.findById(mView, R.id.etSchoolName);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setTitle("학교를 검색합니다.");

        schoolsearch.setOnClickListener( // 검색하기 버튼 누르는 부분
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        String strCity = spCity.getSelectedItem().toString();
                        if(strCity.equals("시/도"))
                        {
                            strCity = null;
                        }

                        String strState = spState.getSelectedItem().toString();
                        if(strState.equals("시/군/구"))
                        {
                            strState = null;
                        }

                        selectedschool = schoolname.getText().toString();
                        Log.d("TAG", selectedschool);

                        apiRequester.searchSchools(strCity, strState, selectedschool, new ApiRequester.UserCallback<List<School>>() {
                            @Override
                            public void onSuccess(List<School> result)
                            {
                                if(result==null)
                                {
                                    Toast.makeText(getApplicationContext(), "지역을 다시 입력해주세요", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    int size = result.size();
                                    name = new String[size];
                                    int i = -1;

                                    Log.d("TAG", "성공");

                                    for(School school : result){
                                        i++;
                                        System.out.println(school.getName());
                                        name[i] = school.getName();
                                    }

                                    AlertDialog.Builder builder3 =
                                            new AlertDialog.Builder(CheckMyInfo.this);
                                    builder3.setTitle("학교를 선택해 주세요.")
                                            .setPositiveButton("선택완료", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(getApplicationContext(),
                                                            name[temp] + "를 선택했습니다.",
                                                            Toast.LENGTH_SHORT).show();
                                                    schoolname.setText(name[temp]);
                                                    myschool = name[temp];
                                                }
                                            })
                                            .setSingleChoiceItems
                                                    ( name,// 리스트배열 목록
                                                            -1, // 기본 설정값
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog,
                                                                                    int which) {
                                                                    temp = which;
                                                                }
                                                            }).setNegativeButton("취소", null);    // 리스너

                                    AlertDialog dialog = builder3.create();
                                    dialog.show();
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

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                showschoolname.setText(myschool);
//                myschoolname = showschoolname.getText().toString();
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

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(CheckMyInfo.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.strArrayCity));
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(cityAdapter);
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                if(spCity.getSelectedItem().toString().equals("시/도")){
                    spState.setClickable(false);
                    spState.setSelection(0);
                }
                else if(spCity.getSelectedItem().toString().equals(strArrayCity[1])){
                    setStateApdapter(R.array.strArraySeoulState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[2])) {
                    setStateApdapter(R.array.strArrayBusanState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[3])) {
                    setStateApdapter(R.array.strArrayDagueState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[4])) {
                    setStateApdapter(R.array.strArrayIncheonState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[5])) {
                    setStateApdapter(R.array.strArrayGwangJuState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[6])) {
                    setStateApdapter(R.array.strArrayDaJeonState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[7])) {
                    setStateApdapter(R.array.strArrayUlsanState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[8])) {
                    setStateApdapter(R.array.strArraySeJongState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[9])) {
                    setStateApdapter(R.array.strArrayGyeonGgiDo);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[10])) {
                    setStateApdapter(R.array.strArrayGangWonDoState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[11])) {
                    setStateApdapter(R.array.strArrayChungCheongBukDoState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[12])) {
                    setStateApdapter(R.array.strArrayChungCheongNamDoState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[13])) {
                    setStateApdapter(R.array.strArrayJeolLaBukDoState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[14])) {
                    setStateApdapter(R.array.strArrayJeolLaNamDoState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[15])) {
                    setStateApdapter(R.array.strArrayGyeongSangBukDoState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[16])) {
                    setStateApdapter(R.array.strArrayGyeongSangNamDoState);
                } else if (spCity.getSelectedItem().toString().equals(strArrayCity[17])) {
                    setStateApdapter(R.array.strArrayJeJuState);
                }
                spState.setClickable(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // onNothingSelected logic
            }
        });

        setStateApdapter(R.array.strArraySeoulState);
        spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextColor(Color.BLACK);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // onNothingSelected logic
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @OnClick(R.id.showinfo)
    void onClickShowInfo(){
        View mView = getLayoutInflater().inflate(R.layout.dialog_select_student_info, null);
        final NumberPicker npGrade = ButterKnife.findById(mView, R.id.npGrade);
        final NumberPicker npClass = ButterKnife.findById(mView, R.id.npClass);
        final NumberPicker npAttendenceNum = ButterKnife.findById(mView, R.id.npAttendenceNum);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        builder.setTitle("학년 / 반 / 이름을 선택해주세요.");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                int strGrade = npGrade.getValue();
                int strClass = npClass.getValue();
                int strAttendenceNum = npAttendenceNum.getValue();
                myStudentId = strAttendenceNum;
                showinfo.setText(Integer.toString(strGrade) + "학년 " + Integer.toString(strClass) + "반 " + Integer.toString(strAttendenceNum) + "번 "
                );
                myinfo = Integer.toString(strGrade) + "학년 " + Integer.toString(strClass) + "반 " + Integer.toString(strAttendenceNum) + "번 ";
                mygrade = Integer.toString(strGrade);
                myclass = Integer.toString(strClass);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // negative button logic
            }
        });
        builder.setView(mView);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @BindView(R.id.deletebt) Button deletebt;
    @OnClick(R.id.deletebt)
    void onClickDeleteBt()
    {
        Toast.makeText(getApplicationContext(), "탈퇴할꺼라우", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_info);
        ButterKnife.bind(this);

        setting = getSharedPreferences("setting", MODE_PRIVATE);
        editor= setting.edit();

        showname.setText(setting.getString("studentname", ""));
            showschoolname.setText(setting.getString("schoolname", ""));
            showinfo.setText(setting.getString("studentInfo", ""));
            id = setting.getString("id", "");
        }





    private void setStateApdapter(int state){
        ArrayAdapter<String> StateAdapter = new ArrayAdapter<String>(CheckMyInfo.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(state));
        StateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spState.setAdapter(StateAdapter);
    }



}