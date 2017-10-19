package com.cbnu.sweng.randombox.dictation_user.dictation_user.utils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class ProgressBarHandler extends AsyncTask<Integer, Void ,Void>{
    private ProgressBar mProgressBar;
    private Context mContext;

    public volatile static ProgressBarHandler progressBarHandler;
    private ProgressBarHandler(){}

    public static ProgressBarHandler getInstance() {
        if (progressBarHandler == null) {
            synchronized (ProgressBarHandler.class) {
                if (progressBarHandler == null) {
                    progressBarHandler = new ProgressBarHandler();
                }
            }
        }
        return progressBarHandler;
    }

    public ProgressBarHandler(Context context) {
        mContext = context;

        ViewGroup layout = (ViewGroup) ((Activity) context).findViewById(android.R.id.content).getRootView();

        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        mProgressBar.setIndeterminate(true);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        RelativeLayout rl = new RelativeLayout(context);

        rl.setGravity(Gravity.CENTER);
        rl.addView(mProgressBar);

        layout.addView(rl, params);

        hide();
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(Integer... params) {
        if(params[0] == 0){
            hide();
        }
        if(params[1] == 1){
            show();
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void ... values) {

    }
    @Override
    protected void onPostExecute(Void result) {

    }
    @Override
    protected void onCancelled() {

    }

    public void show() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hide() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

}