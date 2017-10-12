package com.cbnu.sweng.randombox.dictation_user.dictation_user.utils;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by Userinsight on 2017-10-12.
 */

public class TTSRequester extends AsyncTask<String, Void, Boolean> {
    private static final String clientId = "IzFoSpvKFOdTIJ9VkHCG";//애플리케이션 클라이언트 아이디값";
    private static final String clientSecret = "DFghKnA7uE";//애플리케이션 클라이언트 시크릿값";
    private static final String apiURL = "https://openapi.naver.com/v1/voice/tts.bin";
    private static final int SPEED = 2;
    //음성 재생 속도. -5에서 5 사이의 정수 값이며, -5이면 0.5배 빠른 속도이고 5이면 0.5배 느린 속도입니다. \
    //0이면 정상 속도의 목소리로 음성을 합성합니다.
    private static final String TAG = "TTSRequester.java";

    public MediaPlayer getMp() {
        return mp;
    }

    public void setMp(MediaPlayer mp) {
        this.mp = mp;
    }

    private MediaPlayer mp;

    // TODO: 2017-10-09 TTS 최적화 필요
    private void postTTS(String sentence) {
        try {
            String text = URLEncoder.encode(sentence, "UTF-8"); // 13자
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            // post request
            String postParams = "speaker=mijin&speed=" + SPEED + "&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                Log.d(TAG, "PostTTS: 정상호출");
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // 랜덤한 이름으로 mp3 파일 생성
                String tempName = Long.valueOf(new Date().getTime()).toString();
//                File f = new File(tempname + ".mp3");
                File convertedFile = File.createTempFile(tempName, ".mp3");
                Log.d(TAG, "PostTTS: createTempFile");

                OutputStream outputStream = new FileOutputStream(convertedFile);
                while ((read = is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                is.close();
                Log.d(TAG, "PostTTS: is.close()");
                FileInputStream fis = new FileInputStream(convertedFile);

                mp = new MediaPlayer();
                mp.setDataSource(fis.getFD());
                mp.prepare();
                mp.start();
                Log.d(TAG, "PostTTS: is.start()");
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected Boolean doInBackground(String... params) {
        if (params != null) {
            postTTS(params[0]);
        }

        return true;
    }
}