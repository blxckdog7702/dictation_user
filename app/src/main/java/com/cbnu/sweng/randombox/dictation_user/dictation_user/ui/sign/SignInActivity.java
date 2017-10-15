package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.sign;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.SelectExamOrPractice;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.ApiRequester;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.School;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.ui.practice.SelectPracticeTypeActivity;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.utils.Util;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    private Handler mHandler;
    private Runnable mRunnable;
    private CheckBox Auto_Login;

    private Button schoolsearch; // 다이얼로그 학교검색 버튼
    private EditText schoolname; // 다이얼로그에 있는 학교이름 란
    private String selectedschool; // 학교검색 API 로 넘어가는 학교 값
    private int temp;

    private String myname; // 실제 로그인 때 넘어가는 값들
    private String myschool; // 실제 로그인 때 넘어가는 값들
    private String myinfo; // 실제 로그인 때 넘어가는 값들
    private String myclass; // 실제 로그인 때 넘어가는 값들
    private String mygrade; // 실제 로그인 때 넘어가는 값들
    private String myteacher; // 실제 가입할 때 넘어가는 값들
    private int myStudentId; // 번호

    private String id;

    private String name[];

    Spinner spState;
    Spinner spCity;
    @BindArray(R.array.strArrayCity)
    String [] strArrayCity;
    @BindView(R.id.etSchoolNameIn) EditText etSchoolNameIn;
    @BindView(R.id.etStudentInfoIn) EditText etStudentInfoIn;
    @BindView(R.id.etStudentNameIn) EditText etStudentNameIn;
    @BindView(R.id.goSignUp) TextView goSignUp;
    @BindView(R.id.btSignIn) Button btSignIn;
    @BindView(R.id.nonsignperson) Button nonsignperson;

    @OnClick(R.id.nonsignperson)
    void onClickNonSignPerson()
    {
        setting = getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();// editor가져오기

        editor.clear();
        editor.commit(); // 파일에 최종 반영


        Intent i = new Intent(SignInActivity.this, SelectExamOrPractice.class);
        startActivity(i);
    }

    @OnClick(R.id.goSignUp)
    void onClickGoSignUp()
    {
        Intent t = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(t);
    }

    @OnClick(R.id.etSchoolNameIn)
    void onClickEtSchoolNameIn(){
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

                        ApiRequester.getInstance().searchSchools(strCity, strState, selectedschool, new ApiRequester.UserCallback<List<School>>() {

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

                                    for(School school : result){
                                        i++;
                                        System.out.println(school.getName());
                                        name[i] = school.getName();
                                    }

                                    AlertDialog.Builder builder3 =
                                            new AlertDialog.Builder(SignInActivity.this);
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
                etSchoolNameIn.setText(myschool);
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

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(SignInActivity.this,
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
    @OnClick(R.id.etStudentInfoIn)
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
                etStudentInfoIn.setText(Integer.toString(strGrade) + "학년 " + Integer.toString(strClass) + "반 " + Integer.toString(strAttendenceNum) + "번 "
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
    @OnClick(R.id.btSignIn)
    void onClickBtSignIn()
    {
        myname = etStudentNameIn.getText().toString(); // 기재한 이름을 변수에 담음

        if(TextUtils.isEmpty(myname)|| TextUtils.isEmpty(myschool) || TextUtils.isEmpty(mygrade)) {
            Toast.makeText(getApplicationContext(), "정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else {
            Student.getInstance().setName(myname);
            Student.getInstance().setSchool(myschool);
            Student.getInstance().setGrade(mygrade);
            Student.getInstance().setClass_(myclass);
            Student.getInstance().setStudentId(myStudentId);

            ApiRequester.getInstance().loginStudent(Student.getInstance(), new ApiRequester.UserCallback<Student>() {
                @Override
                public void onSuccess(Student result) {
                    if(result == null)
                    {
                        Toast.makeText(getApplicationContext(), "정보없어", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Student.getInstance().setStudent(result);
                        Toast.makeText(getApplicationContext(), "로그인 완료!", Toast.LENGTH_SHORT).show();

                        editor.putString("myname", myname);
                        editor.putString("myschool", myschool);
                        editor.putString("mygrade", mygrade);
                        editor.putString("myclass", myclass);
                        editor.putString("myStudentId", String.valueOf(myStudentId));
                        editor.putString("id", Student.getInstance().getId());
                        editor.commit();

                        Intent e = new Intent(SignInActivity.this, SelectExamOrPractice.class);
                        startActivity(e);
                    }
                }
                @Override
                public void onFail() {
                    Toast.makeText(getApplicationContext(), "서버와의 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        Auto_Login = (CheckBox) findViewById(R.id.cbAutoLogin);
        goSignUp.setPaintFlags(goSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        setting = getSharedPreferences("setting", 0);
        editor= setting.edit();

        Auto_Login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    editor.putBoolean("Auto_Login_enabled", true);
                    editor.commit();

                }else{
                    editor.clear();
                    editor.commit();
                }
            }
        });
        if(setting.getBoolean("Auto_Login_enabled", false)){
            Student.getInstance().setName(setting.getString("my_name", ""));
            Student.getInstance().setSchool(setting.getString("my_school", ""));
            Student.getInstance().setGrade(setting.getString("mygrade", ""));
            Student.getInstance().setClass_(setting.getString("myclass", ""));
            Student.getInstance().setStudentId(Integer.parseInt(setting.getString("myStudentId", "")));

            ApiRequester.getInstance().loginStudent(Student.getInstance(), new ApiRequester.UserCallback<Student>() {
                @Override
                public void onSuccess(Student result) {
                    if(result == null)
                    {
                        Toast.makeText(getApplicationContext(), "사용자 정보가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Student.getInstance().setStudent(result);
                        Util.getInstance().moveActivity(getApplicationContext(), SelectExamOrPractice.class);
                    }
                }
                @Override
                public void onFail() {
                    Toast.makeText(getApplicationContext(), "서버와의 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void setStateApdapter(int state){
        ArrayAdapter<String> StateAdapter = new ArrayAdapter<String>(SignInActivity.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(state));
        StateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spState.setAdapter(StateAdapter);
    }

}