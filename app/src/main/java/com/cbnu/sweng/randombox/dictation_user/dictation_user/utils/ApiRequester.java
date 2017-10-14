package com.cbnu.sweng.randombox.dictation_user.dictation_user.utils;

import android.util.Log;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.EndedQuiz;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Quiz;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizHistory;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.School;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Teacher;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.DictationServerApi;
import com.google.gson.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRequester {

	DictationServerApi dictationServerApi;
	Gson gson;
	JsonParser parser;
	Callback callback;

	public interface UserCallback<T>{
		void onSuccess(T result);
		void onFail();
	}

	private class ObjectCallback<T> implements Callback<T>{

		UserCallback callback;

		public ObjectCallback(UserCallback<T> _callback){
			callback = _callback;
		}

		@Override
		public void onResponse(Call<T> call, Response<T> response) {
			// TODO Auto-generated method stub
			if (response.isSuccessful()) {
				// tasks available
				callback.onSuccess(response.body());
			} else {
				int status = response.code();
				System.out.println(response.message());
				if( status == 404 ){
					callback.onSuccess(null);
				}
				else {
					System.out.println("서버 실패");
					callback.onFail();
				}
			}
		}
		@Override
		public void onFailure(Call<T> call, Throwable t) {
			// TODO Auto-generated method stub

			System.out.println(t.getMessage());
			callback.onFail();
		}
	}

	private class ResultCallback implements Callback<okhttp3.ResponseBody>{

		UserCallback callback;

		public ResultCallback(UserCallback _callback){
			callback = _callback;
		}

		@Override
		public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {
			// TODO Auto-generated method stub
			if (response.isSuccessful()) {
				// tasks available
				JsonObject object;
				try {
					object = new JsonParser().parse(response.body().string()).getAsJsonObject();
					callback.onSuccess(object.get("result").getAsBoolean());
				} catch (JsonSyntaxException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				// error response, no access to resource?
				int status = response.code();
				System.out.println(response.message());
				if( status == 404 ){
					callback.onSuccess(false);
				}
				else {
					System.out.println("서버 실패");
					callback.onFail();
				}
			}
		}
		@Override
		public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {
			// TODO Auto-generated method stub
			System.out.println(t.getMessage());
			callback.onFail();
		}
	}

	public ApiRequester() {
		parser = new JsonParser();
		gson = new Gson();
		dictationServerApi = DictationServerApi.retrofit.create(DictationServerApi.class);
	}

	//선생님의 quiz list를 리턴한다
	public void getTeachersQuizzes(String teacherID,UserCallback<List<Quiz>> userCallback) throws IOException{
		Call<List<Quiz>> call = dictationServerApi.getTeachersQuizzes(teacherID);
		call.enqueue(new ObjectCallback<List<Quiz>>(userCallback));
	}

	//quiz history를 리턴한다
	public void getQuizHistory(String quizHistoryId, UserCallback<QuizHistory> userCallback) throws IOException{
    	Call<QuizHistory> call = dictationServerApi.getQuizHistory(quizHistoryId);
    	call.enqueue(new ObjectCallback<QuizHistory>(userCallback));
	}

	//선생님의 quiz history를 리턴한다
	public void getTeachersQuizHistories(String teacherId, UserCallback<List<QuizHistory>> userCallback) throws IOException{
		Call<List<QuizHistory>> call = dictationServerApi.getTeachersQuizHistories(teacherId);
		call.enqueue(new ObjectCallback<List<QuizHistory>>(userCallback));
	}

	//시험을 시작하고 quiz history id를 리턴한다
	public String startQuiz(String teacherId, int quizNumber) throws JsonSyntaxException, IOException{
		Call<okhttp3.ResponseBody> call = dictationServerApi.startQuiz(teacherId, quizNumber);
		JsonObject object = new JsonParser().parse(call.execute().body().string()).getAsJsonObject();
		return object.get("quiz_history_id").getAsString();
	}

	//시험을 끝내고 quiz result를 전송한다
	public void endQuiz(String quizHistoryId, String studentId, QuizResult quizResult, UserCallback<QuizHistory> userCallback) throws JsonSyntaxException, IOException{

		EndedQuiz endedQuiz = new EndedQuiz();
		endedQuiz.setQuizHistoryId(quizHistoryId);
		endedQuiz.setQuizResult(quizResult);
		endedQuiz.setStudentId(studentId);

		System.out.println(gson.toJson(endedQuiz).toString());

		Call<QuizHistory> call = dictationServerApi.endQuiz(parser.parse(gson.toJson(endedQuiz)).getAsJsonObject());
		call.enqueue(new ObjectCallback<QuizHistory>(userCallback));
	}

	//login_id로 선생님의 가입여부를 판단한다
	public void checkDuplicateTeacher(String loginId, UserCallback<Boolean> userCallback){
		Call<okhttp3.ResponseBody> call = dictationServerApi.checkDuplicateTeacher(loginId);
		call.enqueue(new ResultCallback(userCallback));
	}
	//학교,반,번호,이름으로 학생의 가입여부를 판단한다
	public void checkDuplicateStudent(Student student, UserCallback<Boolean> userCallback){
		Call<okhttp3.ResponseBody> call = dictationServerApi.checkDuplicateStudent(	student.getSchool(),
																					student.getGrade(),
																					student.getClass_(),
																					student.getStudentId());
		call.enqueue(new ResultCallback(userCallback));
	}

	//학생 회원 가입
	public void signUpStudent(Student student, UserCallback<Student> userCallback){
		Call<Student> call = dictationServerApi.signUpStudent(parser.parse(gson.toJson(student)).getAsJsonObject());
		call.enqueue(new ObjectCallback<Student>(userCallback));
	}

	//선생님 회원 가입
	public void signUpTeacher(Teacher teacher, UserCallback<Teacher> userCallback){
		Call<Teacher> call = dictationServerApi.signUpTeacher(parser.parse(gson.toJson(teacher)).getAsJsonObject());
		call.enqueue(new ObjectCallback<Teacher>(userCallback));
	}

	//선생님 로그인 아이디로 검색
	public void searchTeacherByLoginID(String loginID, UserCallback<Teacher> userCallback){
		Call<Teacher> call = dictationServerApi.searchTeacherByLoginID(loginID);
		call.enqueue(new ObjectCallback<Teacher>(userCallback));
	}

	//선생님 로그인
	public void loginTeacher(String loginID, String password, UserCallback<Teacher> userCallback){
		Call<Teacher> call = dictationServerApi.login(loginID, password, "teacher");
		call.enqueue(new ObjectCallback<Teacher>(userCallback));
	}

	//학생 로그인
	public void loginStudent(Student student, UserCallback<Student> userCallback) {
		Call<Student> call = dictationServerApi.loginStudent(   "student",
															student.getSchool(),
															student.getGrade(),
															student.getClass_(),
															student.getStudentId(),
															student.getName());
		call.enqueue(new ObjectCallback<Student>(userCallback));
	}

	//매칭 신청하기
	public void applyMatching(String teacherLoginID,String studentID, UserCallback<Boolean> userCallback){
		Call<okhttp3.ResponseBody> call = dictationServerApi.applyMatching(teacherLoginID, studentID);
		call.enqueue(new ResultCallback(userCallback));
	}
	//매칭 수락하기
	public void acceptMatching(String teacherLoginID, String studentID, UserCallback<Boolean> userCallback){
		Call<okhttp3.ResponseBody> call = dictationServerApi.acceptMatching(teacherLoginID, studentID);
		call.enqueue(new ResultCallback(userCallback));
	}
	//매칭 삭제하기
	public void cancelMatching(String teacherLoginID, String studentID, UserCallback<Boolean> userCallback){
		Call<okhttp3.ResponseBody> call = dictationServerApi.cancelMatching(teacherLoginID, studentID);
		call.enqueue(new ResultCallback(userCallback));
	}
	//매칭 목록보기
	public void getTeachersApplicants(String teacherLoginID, UserCallback<List<Student>> userCallback){
		Call<List<Student>> call = dictationServerApi.getTeachersApplicants(teacherLoginID);
		call.enqueue(new ObjectCallback<>(userCallback));
	}

	//학교 목록보기
	public void getSchools(UserCallback<List<School>> userCallback){
		Call<List<School>> call = dictationServerApi.getSchools();
		call.enqueue(new ObjectCallback<>(userCallback));
}
	//학교 검색하기
	public void searchSchools(String region1, String region2, String name, UserCallback<List<School>> userCallback){
		Call<List<School>> call = dictationServerApi.searchSchool(region1, region2, name);
		call.enqueue(new ObjectCallback<>(userCallback));
	}

	//등록된 선생님 목록보기
	public void getStudentsTeachers(String studentID, UserCallback<List<Teacher>> userCallback){
		Call<List<Teacher>> call = dictationServerApi.getStudentsTeachers(studentID);
		call.enqueue(new ObjectCallback<>(userCallback));
	}
	//매칭 끊기
	public void unConnectedMatching(String studentID, String teacherID, UserCallback<Boolean> userCallback){
		Call<okhttp3.ResponseBody> call = dictationServerApi.unConnectedMatching(studentID, teacherID);
		call.enqueue(new ResultCallback(userCallback));
	}
	//학생 정보 가져오기
	public void getStudent(String studentID, UserCallback<Student> userCallback){
		Call<Student> call = dictationServerApi.getStudent(studentID);
		call.enqueue(new ObjectCallback<>(userCallback));
	}

	//학생 정보 수정하기
	public void updateStudent(String studentID, Student student, UserCallback<Student> userCallback){
		student.setId(null);
		Call<Student> call = dictationServerApi.updateStudent(studentID, parser.parse(gson.toJson(student)).getAsJsonObject());
		call.enqueue(new ObjectCallback<>(userCallback));
	}

}
