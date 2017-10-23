package com.cbnu.sweng.randombox.dictation_user.dictation_user.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.cbnu.sweng.randombox.dictation_user.dictation_user.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThanksToActivity extends AppCompatActivity {

    @BindView(R.id.tvImage) TextView tvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_to);

        ButterKnife.bind(this);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvImage.setText(Html.fromHtml(getString(R.string.HtmlCSS),Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvImage.setText(Html.fromHtml(getString(R.string.HtmlCSS)));
        }
    }
}
