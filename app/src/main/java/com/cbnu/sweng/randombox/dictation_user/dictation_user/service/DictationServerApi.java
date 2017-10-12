package com.cbnu.sweng.randombox.dictation_user.dictation_user.service;

import java.util.List;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Quiz;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizHistory;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.School;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.google.gson.JsonObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by user on 2017-08-14.
 */

public interface DictationServerApi {
	//등록된 선생님 목록보기
	@GET("/students/{student_id}/teachers")
	Call<List<Teacher>> getStudentsTeachers(@Path("student_id") String studentID);
	//매칭 끊기 - 이거 사용
	@DELETE("/matching/teacher_id/{teacher_id}/student_id/{student_id}")
	Call<ResponseBody> unConnectedMatching(@Path("student_id") String studentID, @Path("teacher_id") String teacherID);
	//학교 목록보기
	@GET("/schools")
	Call<List<School>> getSchools();
	//학교 검색하기
	@GET("/schools/search")
	Call<List<School>> searchSchool(@Query("region1") String region1, @Query("region2") String region2, @Query("name") String name);
	//매칭 신청하기
	@FormUrlEncoded
	@POST("/matching/apply")
	Call<ResponseBody> applyMatching(@Field("teacher_login_id") String teacherLoginID, @Field("student_id") String studentID);
	//매칭 수락하기
	@FormUrlEncoded
	@POST("/matching/accept")
	Call<ResponseBody> acceptMatching(@Field("teacher_login_id") String teacherLoginID, @Field("student_id") String studentID);
	//매칭 삭제하기
	@FormUrlEncoded
	@POST("/matching/cancel")
	Call<ResponseBody> cancelMatching(@Field("teacher_login_id") String teacherLoginID, @Field("student_id") String studentID);
	//매칭 목록보기
	@GET("/matching/list/{teacher_login_id}")
	Call<List<Student>> getTeachersApplicants(@Path("teacher_login_id") String teacherLoginID);

	//학생 중복 검사
	@GET("/students/check_duplicate")
	Call<ResponseBody> checkDuplicateStudent(	@Query("school") String school,
												@Query("grade") String grade,
												@Query("class") String _class,
												@Query("student_id") int studentId);
	//선생님 중복 검사
	@GET("/teachers/check_duplicate")
	Call<ResponseBody> checkDuplicateTeacher( @Query("login_id") String loginId);
	//학생 가입
	@POST("/students")
	Call<Student> signUpStudent(@Body JsonObject student);
	//선생님 가입
	@POST("/teachers")
	Call<Teacher> signUpTeacher(@Body JsonObject teacher);
    //학생 수정
    @GET("/quizzes")
    Call<List<Quiz>> getTeachersQuizzes();
    //선생님 로그인 아이디로 검색
    @GET("/teachers/login_id/{login_id}")
    Call<Teacher> searchTeacherByLoginID(@Path("login_id") String loginID);
    //선생님 로그인
    @FormUrlEncoded
    @POST("/auth/login")
    Call<Teacher> login(@Field("login_id") String loginID, @Field("password") String password, @Field("type") String type);
		//학생 로그인
    @FormUrlEncoded
    @POST("/auth/login")
    Call<Student> loginStudent(	@Field("type") String type,
    							@Field("school") String school,
    							@Field("grade") String grade,
    							@Field("class") String _class,
    							@Field("student_id") int studentId,
    							@Field("name") String name);
    @GET("/teachers/{id}/quiz_histories")
    Call<List<QuizHistory>> getTeachersQuizHistories(@Path("id") String id);

    @GET("/quiz_histories/{id}")
    Call<QuizHistory> getQuizHistory(@Path("id") String id);

    @FormUrlEncoded
    @POST("/quiz/start")
    Call<ResponseBody> startQuiz(@Field("teacher_id") String teacherId, @Field("quiz_number") int quizNumber);

    @Headers("Content-Type: application/json")
    @POST("/quiz/end")
    Call<QuizHistory> endQuiz(@Body JsonObject endedQuiz);

//    //test2
//    public static final Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://dev-dictation-server.herokuapp.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();

    //test1
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://dev-dictation-server.herokuapp.com")
            //.baseUrl("https://dictation-server-minung.c9users.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
