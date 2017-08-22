package com.cbnu.sweng.randombox.dictation_user.dictation_user;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by user on 2017-08-20.
 */

public class NaverSpellChecker extends AsyncTask<String, Void, ArrayList<String[]>>{

    @Override
    protected ArrayList<String[]> doInBackground(String... params) {
        ArrayList<String[]> result = parser(params[0]);
        return result;
    }

    public ArrayList<String[]> parser(String text){
        String parseData = null;
        ArrayList<String[]> result = new ArrayList<String[]>();

        try{
            String param = encodeURIComponent(text);
            String line;
            URL url = new URL("https://m.search.naver.com/p/csearch/dcontent/spellchecker.nhn?_callback="
                                + "window.__jindo2_callback._spellingCheck_0&q="
                                + param);
            BufferedReader bin = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            while ((line = bin.readLine()) != null){
                parseData += line;
            }
            bin.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("Net", "NETWORK ERROR");
        }

        parseData = parseData.substring(parseData.indexOf("\"html\":")+8, parseData.length()-6);
        String[] temps = parseData.split("</span>");
        for(String temp : temps){
            temp = temp.trim();
            String[] colorWord = new String[2];
            String[] blackWord = temp.split("(<span class='re_green'>)|(<span class='re_red'>)|(<span class='re_purple'>)");

            if(!blackWord[0].equals("")){
                result.add(new String[] {"black", blackWord[0]});
            }
            if(temp.contains("re_green")){
                colorWord[0] = "green";
                colorWord[1] = temp.split(">")[1].trim();
            }
            else if(temp.contains("re_red")){
                colorWord[0] = "red";
                colorWord[1] = temp.split(">")[1].trim();
            }
            else if(temp.contains("re_purple")){
                colorWord[0] = "purple";
                colorWord[1] = temp.split(">")[1].trim();
            }
            if(colorWord[0] != null){
                result.add(colorWord);
            }
        }
        return result;
    }

    public static String encodeURIComponent(String s){
        String result;
        try{
            result = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!")
                                                    .replaceAll("\\%27", "'").replaceAll("\\%28", "(")
                                                    .replaceAll("\\%29", ")") .replaceAll("\\%7E", "~");
        }
        catch(UnsupportedEncodingException e){
            result = s;
        }

        return result;
    }

}
