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

import static com.cbnu.sweng.randombox.dictation_user.dictation_user.R.array.strArrayCity;
import static com.cbnu.sweng.randombox.dictation_user.dictation_user.R.id.spCity;
import static com.cbnu.sweng.randombox.dictation_user.dictation_user.R.id.spState;

public class SignInActivity extends AppCompatActivity {

    ApiRequester apiRequester = new ApiRequester();
    Student student = new Student();
    private Handler mHandler;
    private Runnable mRunnable;
    SharedPreferences setting;
    SharedPreferences.Editor editor;
    CheckBox Auto_Login;

    Spinner spState;
    Spinner spCity;
    Button schoolsearch;
    EditText schoolname; // 두번째 다이얼로그에 띄워지는 선택한 학교 이름
    String selectedschool;
    int temp;
    String myschool;


    String myname; // 실제 가입할 때 넘어가는 값들

    String myinfo; // 실제 가입할 때 넘어가는 값들
    String myclass;
    String mygrade;
    int myStudentId;

    String myschoolname; // 실제 가입할 때 넘어가는 값들(변수에 담겨있음)
    //String myteacher; // 실제 가입할 때 넘어가는 값들

    String name[];

    @BindArray(R.array.strArrayCity)
    String [] strArrayCity;
    @BindView(R.id.etSchoolNameIn) EditText etSchoolNameIn;
    @BindView(R.id.etStudentInfoIn) EditText etStudentInfoIn;
    @BindView(R.id.etStudentNameIn) EditText etStudentNameIn;
    @BindView(R.id.goSignUp) TextView goSignUp;

    //@BindView(R.id.etTeacherNameUp) EditText etTeacherNameUp;

    @BindView(R.id.btSignIn) Button btSignIn;
    @BindView(R.id.nonsignperson) Button nonsignperson;

    @OnClick(R.id.nonsignperson)
    void onClickNonSignPerson()
    {
        Intent i = new Intent(SignInActivity.this, SelectPracticeTypeActivity.class);
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
                myschoolname = etSchoolNameIn.getText().toString();
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
                //myStudentId = Integer.toString(strAttendenceNum);
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
        //myteacher = etTeacherNameUp.getText().toString(); // 기재한 선생님ID를 변수에 담음

        if(myname==null || myschoolname==null || mygrade==null)
        {
            Toast.makeText(getApplicationContext(), "정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(myname.length()==0 || myschoolname.length()==0 || mygrade.length()==0)
        {
            Toast.makeText(getApplicationContext(), "정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
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
                    if (result == true) {
                        Toast.makeText(getApplicationContext(), "로그인중..", Toast.LENGTH_SHORT).show();

                        mRunnable = new Runnable() {
                            @Override
                            public void run() {
                                Intent e = new Intent(SignInActivity.this, SelectPracticeTypeActivity.class);
                                startActivity(e);
                            }
                        };
                        mHandler = new Handler();
                        mHandler.postDelayed(mRunnable, 3000);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "입력한 정보가 없습니다.", Toast.LENGTH_LONG).show();
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
                    String studentname = etStudentNameIn.getText().toString();
                    String schoolname = etSchoolNameIn.getText().toString();
                    String studentInfo = etStudentInfoIn.getText().toString();

                    editor.putString("studentname", studentname);
                    editor.putString("schoolname", schoolname);
                    editor.putString("studentInfo", studentInfo);

                    editor.putBoolean("Auto_Login_enabled", true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }
            }

        });

        if(setting.getBoolean("Auto_Login_enabled", false)){
            Auto_Login.setChecked(true);
            Intent intent = new Intent(SignInActivity.this, SelectPracticeTypeActivity.class);
            startActivity(intent);
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