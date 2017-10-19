package com.cbnu.sweng.randombox.dictation_user.dictation_user.utils;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.model.Student;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by DH on 2017-08-21.
 */

public class FcmRequester {
    String TAG = "FcmRequester.java";
    
    private static final String SERVER_KEY = "AAAAJnyiypk:APA91bFoEQ5S3lUV3JocgTN_2G5uAQJ80YzdgkrASI_tcDmw_fQ7hg_DKfbFu9qqGKtAqRvphCreAbA-bBfRT40iCgFbRjHpcQ2BtxCoeAFcC0zp2sDCtbvhIxnqzqDBdgz6DJkG5DgB";
    public static final String FCM_TO_TEACHER = "TEACHER";
    private static final String FCM_BASE_URL = "https://fcm.googleapis.com/fcm/send";

    private static FcmRequester instance;

    private FcmRequester() {
    }

    public static FcmRequester getInstance() {
        if (instance == null) {
            instance = new FcmRequester();
        }
        return instance;
    }

    private OkHttpClient client = new OkHttpClient();

    public void notifyToTeacherSubscribe(String topic, Student me, boolean isSub) {
        String message;
        if (isSub) {
            message = "ready";
        } else {
            message = "cancel";
        }

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"to\": \"/topics/" + topic + FCM_TO_TEACHER +"\",\r\n  \"data\": \r\n  {\r\n    \"message\": \"" + message + "\",\r\n    \"name\": \"" + me.getName() + "\",\r\n    \"id\": \"" + me.getId() + "\"\r\n  }\r\n}");
        Request request = new Request.Builder()
                .url(FCM_BASE_URL)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "key=" + SERVER_KEY)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "557a25cc-6af7-6093-9e71-12f85a721517")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }
}
