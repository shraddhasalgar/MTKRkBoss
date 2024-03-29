package com.rkboss.mtk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OddEven extends AppCompatActivity {


    private ImageView back;
    private EditText amount;
    private latobold submit,odd1,odd3,odd5,odd7,odd9,even0,even2,even4,even6,even8;
    TextView open_game,close_game;
    TextView title,balance,screenTitle;
    LinearLayout type_container,digit_header;
    LinearLayout odd_row,even_row;
    TextView odd,even;

    String open_av = "0",timing = "";

    SharedPreferences prefs;
    ArrayList<String> list;
    ArrayList<String> numbers = new ArrayList<>();
    adapterbetting adapterbetting;
    String market,game;
    ViewDialog progressDialog;
    String url;
    int total = 0;
    ArrayList<String> fillnumber = new ArrayList<>();
    ArrayList<String> fillamount = new ArrayList<>();
    ArrayList<String> fillmarket = new ArrayList<>();
    String numb,amou,types;

    // 0 - open, 1 - close
    int selectedType = 0;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odd_even);
        initViews();
        open_av = getIntent().getStringExtra("open_av");
        url = constant.prefix + getString(R.string.bet);

        prefs = getSharedPreferences(constant.prefs,MODE_PRIVATE);
        game = getIntent().getStringExtra("game");
        market = getIntent().getStringExtra("market");
        numbers = getIntent().getStringArrayListExtra("list");
        if (getIntent().hasExtra("timing")){
            timing = getIntent().getStringExtra("timing");
            type_container.setVisibility(View.GONE);
        }
//        title.setText(market.replace("_","").toUpperCase(Locale.ROOT)+", "+game.toUpperCase(Locale.ROOT));
        title.setText(game.replace("_","").toUpperCase(Locale.ROOT));


        if (!game.equals("jodi") && !getIntent().hasExtra("timing")){
            ArrayList<String> typeof = new ArrayList<>();

            if (open_av.equals("1")){ typeof.add("OPEN");}
            typeof.add("CLOSE");

            type_container.setVisibility(View.VISIBLE);

            if (open_av.equals("0")){
                selectedType = 1;
                close_game.setTextColor(getResources().getColor(R.color.white));
                close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
                open_game.setTextColor(getResources().getColor(R.color.font));
                open_game.setBackgroundColor(getResources().getColor(R.color.white));
                submit.setBackground(getResources().getDrawable(R.drawable.sub_ax));
                submit.setText("Bid close");
            }

        }

        open_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedType = 0;
                open_game.setTextColor(getResources().getColor(R.color.white));
                open_game.setBackgroundColor(getResources().getColor(R.color.usedred));
                close_game.setTextColor(getResources().getColor(R.color.font));
                close_game.setBackgroundColor(getResources().getColor(R.color.white));
                submit.setBackground(getResources().getDrawable(R.drawable.sub_ax));
                submit.setText("Submit");



            }
        });

        close_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedType = 1;
                close_game.setTextColor(getResources().getColor(R.color.white));
                close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
                open_game.setTextColor(getResources().getColor(R.color.font));
                open_game.setBackgroundColor(getResources().getColor(R.color.white));
                submit.setBackground(getResources().getDrawable(R.drawable.sub_ax));
                submit.setText("Bid close");

            }
        });

        odd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odd.setBackgroundColor(getResources().getColor(R.color.usedred));
                odd.setTextColor(getResources().getColor(R.color.white));
                even.setBackgroundColor(getResources().getColor(R.color.white));
                even.setTextColor(getResources().getColor(R.color.font));
                odd_row.setVisibility(View.VISIBLE);
                even_row.setVisibility(View.GONE);
            }
        });


        even.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                even.setBackgroundColor(getResources().getColor(R.color.usedred));
                even.setTextColor(getResources().getColor(R.color.white));
                odd.setBackgroundColor(getResources().getColor(R.color.white));
                odd.setTextColor(getResources().getColor(R.color.font));
                even_row.setVisibility(View.VISIBLE);
                odd_row.setVisibility(View.GONE);
            }
        });

//        odd1.setOnClickListener(v -> changeDigit("0"));
//        odd3.setOnClickListener(v -> changeDigit("1"));
//        odd5.setOnClickListener(v -> changeDigit("2"));
//        odd7.setOnClickListener(v -> changeDigit("3"));
//        odd9.setOnClickListener(v -> changeDigit("4"));
//
//
//        even0.setOnClickListener(v -> changeDigit("00"));
//        even2.setOnClickListener(v -> changeDigit("11"));
//        even4.setOnClickListener(v -> changeDigit("22"));
//        even6.setOnClickListener(v -> changeDigit("33"));
//        even8.setOnClickListener(v -> changeDigit("44"));



        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty() || s == null) {
                    // DO NOTHING FIELD IS EMPTY
                } else if (Integer.parseInt(s.toString()) > constant.max_single) {
                    amount.setText(constant.max_single+"");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String num = intent.getStringExtra("number");
                fillamount.remove(Integer.parseInt(num));
                fillnumber.remove(Integer.parseInt(num));
                fillmarket.remove(Integer.parseInt(num));

                if (fillmarket.size() > 0){
                    digit_header.setVisibility(View.VISIBLE);
                } else {
                    digit_header.setVisibility(View.GONE);
                }

                total = 0;
                for (int a = 0; a < fillamount.size(); a++) {
                    total = total+Integer.parseInt(fillamount.get(a));
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
        registerReceiver(mReceiver, intentFilter);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (total <= Integer.parseInt(amount.getText().toString())*5) {
                    if (odd_row.getVisibility() == View.VISIBLE) {
                        fillnumber.add("1");
                        fillnumber.add("3");
                        fillnumber.add("5");
                        fillnumber.add("7");
                        fillnumber.add("9");
                    } else {
                        fillnumber.add("0");
                        fillnumber.add("2");
                        fillnumber.add("4");
                        fillnumber.add("6");
                        fillnumber.add("8");
                    }

                    fillamount.add(amount.getText().toString());
                    fillamount.add(amount.getText().toString());
                    fillamount.add(amount.getText().toString());
                    fillamount.add(amount.getText().toString());
                    fillamount.add(amount.getText().toString());

                    if (selectedType == 0) {
                        fillmarket.add("OPEN");
                        fillmarket.add("OPEN");
                        fillmarket.add("OPEN");
                        fillmarket.add("OPEN");
                        fillmarket.add("OPEN");
                    } else {
                        fillmarket.add("CLOSE");
                        fillmarket.add("CLOSE");
                        fillmarket.add("CLOSE");
                        fillmarket.add("CLOSE");
                        fillmarket.add("CLOSE");
                    }

                    numb = TextUtils.join(",", fillnumber);
                    amou = TextUtils.join(",", fillamount);
                    types = TextUtils.join(",", fillmarket);


                    apicall();
                }
                else
                {
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(OddEven.this);
//                    builder1.setMessage("You don't have enough wallet balance to place this bet, Recharge your wallet to play");
//                    builder1.setCancelable(true);
//                    builder1.setNegativeButton(
//                            "Close",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dialog.cancel();
//                                }
//                            });
//
//                    AlertDialog alert11 = builder1.create();
//                    alert11.show();

                    Intent in = new Intent(getApplicationContext(), Balance_ax.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(in);
                    finish();


                }
            }
        });

    }

    @Override
    protected void onResume() {
        balance.setText(getSharedPreferences(constant.prefs,MODE_PRIVATE).getString("wallet","0"));
        super.onResume();
    }


//    public void changeDigit(String id) {
//
//        switch (id) {
//            case "0" :
//                unselectOthers();
//                odd1.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "1" :
//                unselectOthers();
//                odd3.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "2" :
//                unselectOthers();
//                odd5.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "3" :
//                unselectOthers();
//                odd7.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "4" :
//                unselectOthers();
//                odd9.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//
//
//            case "00" :
//                unselectOthers();
//                even0.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "11" :
//                unselectOthers();
//                even2.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "22" :
//                unselectOthers();
//                even4.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "33" :
//                unselectOthers();
//                even6.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "44" :
//                unselectOthers();
//                even8.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//
//        }
//
//    }
//
//    private void unselectOthers() {
//        odd1.setBackground(getResources().getDrawable(R.drawable.shadow));
//        odd3.setBackground(getResources().getDrawable(R.drawable.shadow));
//        odd5.setBackground(getResources().getDrawable(R.drawable.shadow));
//        odd7.setBackground(getResources().getDrawable(R.drawable.shadow));
//        odd9.setBackground(getResources().getDrawable(R.drawable.shadow));
//
//
//        even0.setBackground(getResources().getDrawable(R.drawable.shadow));
//        even2.setBackground(getResources().getDrawable(R.drawable.shadow));
//        even4.setBackground(getResources().getDrawable(R.drawable.shadow));
//        even6.setBackground(getResources().getDrawable(R.drawable.shadow));
//        even8.setBackground(getResources().getDrawable(R.drawable.shadow));
//
//
//    }
//



    private void apicall() {

        progressDialog = new ViewDialog(OddEven.this);
        progressDialog.showDialog();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String response = null;

        final StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("edsa", "efsdc" + response);
                        progressDialog.hideDialog();
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);

                            if (jsonObject1.getString("active").equals("0")) {
                                Toast.makeText(OddEven.this, "Your account temporarily disabled by admin", Toast.LENGTH_SHORT).show();

                                getSharedPreferences(constant.prefs, MODE_PRIVATE).edit().clear().apply();
                                Intent in = new Intent(getApplicationContext(), login.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                                finish();
                            }

                            if (!jsonObject1.getString("session").equals(getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null))) {
                                Toast.makeText(OddEven.this, "Session expired ! Please login again", Toast.LENGTH_SHORT).show();

                                getSharedPreferences(constant.prefs, MODE_PRIVATE).edit().clear().apply();
                                Intent in = new Intent(getApplicationContext(), login.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                                finish();
                            }

                            if (jsonObject1.getString("success").equalsIgnoreCase("1")) {

                                Intent in = new Intent(getApplicationContext(), thankyou.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.hideDialog();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        progressDialog.hideDialog();
                        Toast.makeText(OddEven.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("number",numb);
                params.put("amount",amou);
                params.put("bazar",market);
                params.put("total",total+"");
                params.put("game",game);
                params.put("mobile", prefs.getString("mobile",null));
                params.put("types",types);
                if (!timing.equals("")){
                    params.put("timing",timing);
                }
                params.put("session",getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null));
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    private void initViews() {
        back = findViewById(R.id.back);
        amount = findViewById(R.id.amount);
        submit = findViewById(R.id.submit);
        title = findViewById(R.id.title);
        balance = findViewById(R.id.balance);
        screenTitle = findViewById(R.id.title);
        open_game = findViewById(R.id.open_game);
        close_game = findViewById(R.id.close_game);
        type_container = findViewById(R.id.type_container);
        digit_header = findViewById(R.id.digit_header);
        odd_row = findViewById(R.id.odd_row);
        even_row = findViewById(R.id.even_row);
        odd = findViewById(R.id.odd);
        even = findViewById(R.id.even);

//        odd1 = findViewById(R.id.odd1);
//        odd3 = findViewById(R.id.odd3);
//        odd5 = findViewById(R.id.odd5);
//        odd7 = findViewById(R.id.odd7);
//        odd9 = findViewById(R.id.odd9);
//
//        even0 = findViewById(R.id.even0);
//        even2 = findViewById(R.id.even2);
//        even4 = findViewById(R.id.even4);
//        even8 = findViewById(R.id.even8);
//        even6 = findViewById(R.id.even6);


        date = findViewById(R.id.date);
        date.setText(new SimpleDateFormat("MMM, d\nyyyy", Locale.getDefault()).format(new Date()));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}