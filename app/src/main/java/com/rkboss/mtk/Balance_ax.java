package com.rkboss.mtk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Balance_ax extends AppCompatActivity {

    ImageView okbtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_ax);


        okbtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Balance_ax.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
        }

//        @Override
//        public void onBackPressed() {
//            Intent in = new Intent(getApplicationContext(), MainActivity.class);
//            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(in);
//            finish();
//            super.onBackPressed();
//        }

        private void initView() {
//        text = (latonormal) findViewById(R.id.text);
            okbtn1 =  findViewById(R.id.okbtn1);
        }
    }
