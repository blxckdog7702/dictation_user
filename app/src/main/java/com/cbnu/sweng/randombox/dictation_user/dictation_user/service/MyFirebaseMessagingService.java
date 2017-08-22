package com.cbnu.sweng.randombox.dictation_user.dictation_user.service;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by DH on 2017-08-16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    String keyword;
    String quizHistoryId;
    public static final String START_INTENT = "START_INTENT";
    public static final String QUIZ_CONTROL_INTENT = "QUIZ_CONTROL_INTENT";


    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getData());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (remoteMessage.getData().get("message") != null) {
                keyword = remoteMessage.getData().get("message");
            }

            if (remoteMessage.getData().get("quizHistoryId") != null) {
                quizHistoryId = remoteMessage.getData().get("quizHistoryId");
            } else {
                quizHistoryId = "뀨뀨까까";
            }

            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            keyword + quizHistoryId,
                            Toast.LENGTH_LONG).show();
                }
            });

            if (keyword.equals("start")) {
                Intent intent = new Intent(START_INTENT);
                sendBroadcast(intent);
            } else if (keyword.equals("next")) {
                Intent intent = new Intent(QUIZ_CONTROL_INTENT);
                intent.putExtra("keyword", "next");
                sendBroadcast(intent);
            } else if (keyword.equals("previous")) {
                Intent intent = new Intent(QUIZ_CONTROL_INTENT);
                intent.putExtra("keyword", "previous");
                sendBroadcast(intent);
            }

//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
//            } else {
//                // Handle message within 10 seconds
//                handleNow();
//            }

        }

//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }
}