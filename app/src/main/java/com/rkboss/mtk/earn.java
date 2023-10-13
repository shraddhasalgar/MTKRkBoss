package com.rkboss.mtk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

public class earn extends AppCompatActivity {

    protected latobold code;
    protected latobold share;
    String code1;
    protected NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_earn);
        initView();

        code1 = getIntent().getStringExtra("code");
        Log.d("CodeChecking",""+code1);
        code.setText(code1);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent booking = new Intent(earn.this, MainActivity.class);
                startActivityForResult(booking, 1);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Use this referral code "+code1+" at signup, Download "+getString(R.string.app_name)+" and earn money at home, Download link - "+ constant.link);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    private void initView() {
        code = (latobold) findViewById(R.id.code);
        share = (latobold) findViewById(R.id.share);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
    }
}
