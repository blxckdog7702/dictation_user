package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign;

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


import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.School;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectPracticeTypeActivity;

import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    ApiRequester apiRequester = new ApiRequester();
    Student student = new Student();
    private Handler mHandler;
    private Runnable mRunnable;

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    Spinner spState;
    Spinner spCity;
    Button schoolsearch;
    EditText schoolname;
    String selectedschool;
    int temp;
    String myschool;

    String myname; // 실제 가입할 때 넘어가는 값들

    String myinfo; // 실제 가입할 때 넘어가는 값들
    String myclass;
    String mygrade;
    int myStudentId;

    String myschoolname; // 실제 가입할 때 넘어가는 값들
    String myteacher; // 실제 가입할 때 넘어가는 값들
    String name[];

    @BindArray(R.array.strArrayCity)
    String [] strArrayCity;
    @BindView(R.id.etSchoolNameUp) EditText etSchoolNameUp;
    @BindView(R.id.etStudentInfoUp) EditText etStudentInfoUp;
    @BindView(R.id.etStudentNameUp) EditText etStudentNameUp;
    @BindView(R.id.etTeacherNameUp) EditText etTeacherNameUp;
    @BindView(R.id.btSignUp) Button btSignUp;


    @OnClick(R.id.etSchoolNameUp)
    void onClickEtSchoolNameUp(){
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
                                            new AlertDialog.Builder(SignUpActivity.this);
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
                etSchoolNameUp.setText(myschool);
                myschoolname = etSchoolNameUp.getText().toString();
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

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(SignUpActivity.this,
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
    @OnClick(R.id.etStudentInfoUp)
    void onClickEtStudentInfoUp(){
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
                etStudentInfoUp.setText(Integer.toString(strGrade) + "학년 " + Integer.toString(strClass) + "반 " + Integer.toString(strAttendenceNum) + "번 "
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

    @OnClick(R.id.btSignUp)
    void onClickBtSignUp()
    {
        myname = etStudentNameUp.getText().toString(); // 기재한 이름을 변수에 담음
        myteacher = etTeacherNameUp.getText().toString(); // 기재한 선생님ID를 변수에 담음

        if(myname.equals("") || myschool.equals("") || mygrade.equals(""))
        {
            Toast.makeText(getApplicationContext(), "정보를 입력해주세요..", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Log.d("TAG", myname); // 이름
            Log.d("TAG", myschoolname); // 학교 명
            Log.d("TAG", mygrade); // 학년
            Log.d("TAG", myclass); // 반
            Log.d("TAG", String.valueOf(myStudentId)); // 번호

            student.setSchool(myschoolname);
            student.setGrade(mygrade);
            student.setClass_(myclass);
            student.setStudentId(myStudentId);

            apiRequester.checkDuplicateStudent(student, new ApiRequester.UserCallback<Boolean>() {
                @Override
                public void onSuccess(Boolean result) {
                    if(result==true)
                    {
                        Toast.makeText(getApplicationContext(), "중복 되는 정보가 있습니다.", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "회원 가입 진행중..", Toast.LENGTH_LONG).show();

                        student.setName(myname);
                        student.setSchool(myschoolname);
                        student.setGrade(mygrade);
                        student.setClass_(myclass);

                        apiRequester.signUpStudent(student, new ApiRequester.UserCallback<Student>() {
                            @Override
                            public void onSuccess(Student result) {

                                String studentname = etStudentNameUp.getText().toString();
                                String schoolname = etSchoolNameUp.getText().toString();
                                String studentInfo = etStudentInfoUp.getText().toString();

                                editor.putString("studentname", studentname);
                                editor.putString("schoolname", schoolname);
                                editor.putString("studentInfo", studentInfo);

                                editor.commit();

                                Toast.makeText(getApplicationContext(), "회원가입이 완료 되었습니다.", Toast.LENGTH_LONG).show();
                                mRunnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent e = new Intent(SignUpActivity.this, SelectPracticeTypeActivity.class);

                                        startActivity(e);
                                    }
                                };
                                mHandler = new Handler();
                                mHandler.postDelayed(mRunnable, 5000);
                            }
                            @Override
                            public void onFail() {
                                Toast.makeText(getApplicationContext(), "서버와의 연결을 확인해주세요.", Toast.LENGTH_SHORT);
                            }
                        });
                    }
                }

                @Override
                public void onFail() {
                    Toast.makeText(getApplicationContext(), "서버와의 연결을 확인해주세요.", Toast.LENGTH_SHORT);
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setting = getSharedPreferences("setting", 0);
        editor= setting.edit();

        ButterKnife.bind(this);
    }

    private void setStateApdapter(int state){
        ArrayAdapter<String> StateAdapter = new ArrayAdapter<String>(SignUpActivity.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(state));
        StateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spState.setAdapter(StateAdapter);
    }

}