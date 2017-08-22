package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import java.io.IOException;
import java.util.List;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.EndedQuiz;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Quiz;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizHistory;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.QuizResult;
import com.cbnu.sweng.randombox.dictation_user.dictation_user.service.DictationServerApi;
import com.google.gson.*;
import okhttp3.ResponseBody;
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
	
	private class CustomCallback<T> implements Callback<T>{

		UserCallback callback;
		
		public CustomCallback(UserCallback<T> _callback){
			callback = _callback;
		}
		
		@Override
		public void onResponse(Call<T> call, Response<T> response) {
			// TODO Auto-generated method stub
			if (response.isSuccessful()) {
				// tasks available
				callback.onSuccess(response.body());
			} else {
				// error response, no access to resource?
				System.out.println("서버 실패");
				callback.onFail();
			}
		}
		@Override
		public void onFailure(Call<T> call, Throwable t) {
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
	
	//quiz list를 리턴한다
	public void getTeachersQuizzes(UserCallback<List<Quiz>> userCallback) throws IOException{
		Call<List<Quiz>> call = dictationServerApi.getTeachersQuizzes();
		call.enqueue(new CustomCallback<List<Quiz>>(userCallback));
	}
	
	//quiz history를 리턴한다
	public void getQuizHistory(String id, UserCallback<QuizHistory> userCallback) throws IOException{
    	Call<QuizHistory> call = dictationServerApi.getQuizHistory(id);
    	call.enqueue(new CustomCallback<QuizHistory>(userCallback));
	}
	
	//선생님의 quiz history를 리턴한다
	public void getTeachersQuizHistories(String id, UserCallback<List<QuizHistory>> userCallback) throws IOException{
		Call<List<QuizHistory>> call = dictationServerApi.getTeachersQuizHistories(id);
		call.enqueue(new CustomCallback<List<QuizHistory>>(userCallback));
	}
	
	//시험을 시작하고 quiz history id를 리턴한다
	public String startQuiz(String teacherId, int quizNumber, UserCallback userCallback) throws JsonSyntaxException, IOException{
		Call<ResponseBody> call = dictationServerApi.startQuiz(teacherId, quizNumber);
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
		call.enqueue(new CustomCallback<QuizHistory>(userCallback));
	}
	
	//
}
