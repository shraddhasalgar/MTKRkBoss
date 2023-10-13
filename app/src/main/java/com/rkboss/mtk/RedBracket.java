package com.rkboss.mtk;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class RedBracket extends AppCompatActivity {

    private ImageView back;
    private EditText amount;
    Button submit, half05, half16, half27, half38, half49, half50, half61, half72, half83, half94, full00, full11, full22, full33, full44, full55, full66, full77, full88, full99;
    TextView open_game, close_game;
    TextView title, balance, screenTitle;
    LinearLayout type_container, digit_header;
    LinearLayout half_bracket, full_bracket;
    TextView odd, even;
    String gametype;
    int Walletamount = 0;

    String open_av = "0";

    SharedPreferences prefs;
    ArrayList<String> list;
    ArrayList<String> numbers = new ArrayList<>();
    adapterbetting adapterbetting;
    String market, game;
    ViewDialog progressDialog;
    String url;
    int total;
    ArrayList<String> fillnumber = new ArrayList<>();
    ArrayList<String> fillamount = new ArrayList<>();
    ArrayList<String> fillmarket = new ArrayList<>();
    String numb, amou, types;
    TextView date;

    // 0 - open, 1 - close
    int selectedType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_bracket);
        initViews();
        open_av = getIntent().getStringExtra("open_av");
        url = constant.prefix + getString(R.string.bet);

        prefs = getSharedPreferences(constant.prefs, MODE_PRIVATE);
        game = getIntent().getStringExtra("game");
        market = getIntent().getStringExtra("market");
        numbers = getIntent().getStringArrayListExtra("list");

//        title.setText(market.replace("_","").toUpperCase(Locale.ROOT)+", "+game.toUpperCase(Locale.ROOT));
//        title.setText(game.replace("_", "").toUpperCase(Locale.ROOT));

        if (!game.equals("jodi")) {
            ArrayList<String> typeof = new ArrayList<>();

            if (open_av.equals("1")) {
                typeof.add("OPEN");
            }
            typeof.add("CLOSE");

            type_container.setVisibility(View.VISIBLE);

            if (open_av.equals("0")) {
                selectedType = 1;
                close_game.setTextColor(getResources().getColor(R.color.white));
                close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
                open_game.setVisibility(View.GONE);
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
//
            }
        });

        odd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                odd.setBackgroundColor(getResources().getColor(R.color.usedred));
                odd.setTextColor(getResources().getColor(R.color.white));
                even.setBackgroundColor(getResources().getColor(R.color.white));
                even.setTextColor(getResources().getColor(R.color.font));
                half_bracket.setVisibility(View.VISIBLE);
                full_bracket.setVisibility(View.GONE);
            }
        });


        even.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                even.setBackgroundColor(getResources().getColor(R.color.usedred));
                even.setTextColor(getResources().getColor(R.color.white));
                odd.setBackgroundColor(getResources().getColor(R.color.white));
                odd.setTextColor(getResources().getColor(R.color.font));
                full_bracket.setVisibility(View.VISIBLE);
                half_bracket.setVisibility(View.GONE);
            }
        });


//        half05.setOnClickListener(v -> changeDigit("0"));
//        half16.setOnClickListener(v -> changeDigit("1"));
//        half27.setOnClickListener(v -> changeDigit("2"));
//        half38.setOnClickListener(v -> changeDigit("3"));
//        half49.setOnClickListener(v -> changeDigit("4"));
//        half50.setOnClickListener(v -> changeDigit("5"));
//        half61.setOnClickListener(v -> changeDigit("6"));
//        half72.setOnClickListener(v -> changeDigit("7"));
//        half83.setOnClickListener(v -> changeDigit("8"));
//        half94.setOnClickListener(v -> changeDigit("9"));
//
//
//        full00.setOnClickListener(v -> changeDigit("00"));
//        full11.setOnClickListener(v -> changeDigit("11"));
//        full22.setOnClickListener(v -> changeDigit("22"));
//        full33.setOnClickListener(v -> changeDigit("33"));
//        full44.setOnClickListener(v -> changeDigit("44"));
//        full55.setOnClickListener(v -> changeDigit("55"));
//        full66.setOnClickListener(v -> changeDigit("66"));
//        full77.setOnClickListener(v -> changeDigit("77"));
//        full88.setOnClickListener(v -> changeDigit("88"));
//        full99.setOnClickListener(v -> changeDigit("99"));


        int maxLength = 5; //
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(maxLength);
        amount.setFilters(filters);

// Set range filter
        InputFilter rangeFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                try {
                    // Get the current text + the newly entered text
                    String input = dest.subSequence(0, dstart) + source.toString() +
                            dest.subSequence(dend, dest.length());

                    int value = Integer.parseInt(input);
                    if (value >= 1 && value <= 10000) {
                        // Input is within the range
                        return null;
                    }
                } catch (NumberFormatException ignored) {}

                // Input is not within the range, so return an empty string to prevent it from being entered
                return "";
            }
        };

        amount.setFilters(new InputFilter[] { rangeFilter });

        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

//                String num = intent.getStringExtra("number");
//                fillamount.remove(Integer.parseInt(num));
//                fillnumber.remove(Integer.parseInt(num));
//                fillmarket.remove(Integer.parseInt(num));
//
//
//                Log.d("Filamt","" +fillamount);
//                if (fillmarket.size() > 0) {
//                    digit_header.setVisibility(View.VISIBLE);
//                } else {
//                    digit_header.setVisibility(View.GONE);
//                }
//
//
//                total =  Integer.parseInt(amount.getText().toString()) * 10;


                String num = intent.getStringExtra("number");
                fillamount.remove(Integer.parseInt(num));
                fillnumber.remove(Integer.parseInt(num));
                fillmarket.remove(Integer.parseInt(num));


                if (fillmarket.size() > 0){
                    digit_header.setVisibility(View.VISIBLE);
                } else {
                    digit_header.setVisibility(View.GONE);
                }

                total =  Integer.parseInt(amount.getText().toString()) * 10;
                amount.setText("");

            }
        };

        IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
        registerReceiver(mReceiver, intentFilter);


        submit.setOnClickListener(v -> {

            if (open_av.equals("1")) {

            total =  Integer.parseInt(amount.getText().toString()) * 10;

            if (total <= Integer.parseInt(prefs.getString("wallet",null))) {
                if (half_bracket.getVisibility() == View.VISIBLE) {
                    fillnumber.add("05");
                    fillnumber.add("16");
                    fillnumber.add("27");
                    fillnumber.add("38");
                    fillnumber.add("49");
                    fillnumber.add("50");
                    fillnumber.add("61");
                    fillnumber.add("72");
                    fillnumber.add("83");
                    fillnumber.add("94");
                } else {
                    fillnumber.add("00");
                    fillnumber.add("11");
                    fillnumber.add("22");
                    fillnumber.add("33");
                    fillnumber.add("44");
                    fillnumber.add("55");
                    fillnumber.add("66");
                    fillnumber.add("77");
                    fillnumber.add("88");
                    fillnumber.add("99");
                }

                fillamount.add(amount.getText().toString());
                fillamount.add(amount.getText().toString());
                fillamount.add(amount.getText().toString());
                fillamount.add(amount.getText().toString());
                fillamount.add(amount.getText().toString());
                fillamount.add(amount.getText().toString());
                fillamount.add(amount.getText().toString());
                fillamount.add(amount.getText().toString());
                fillamount.add(amount.getText().toString());
                fillamount.add(amount.getText().toString());

                fillmarket.add("");
                fillmarket.add("");
                fillmarket.add("");
                fillmarket.add("");
                fillmarket.add("");
                fillmarket.add("");
                fillmarket.add("");
                fillmarket.add("");
                fillmarket.add("");
                fillmarket.add("");

                numb = TextUtils.join(",", fillnumber);
                amou = TextUtils.join(",", fillamount);
                types = TextUtils.join(",", fillmarket);
                total =  Integer.parseInt(amount.getText().toString()) * 10;

                AlertDialog.Builder builder1 = new AlertDialog.Builder(RedBracket.this);
                builder1.setMessage("Your total bet point\t"+total+"\twill be debited from wallet..\n\nAre you sure to submit bet ?\n");
                builder1.setCancelable(true);

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                builder1.setPositiveButton("Confirm", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                    apicall();
                    amount.setText("");

                });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            } else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(RedBracket.this);
                builder1.setMessage("You don't have enough wallet balance to place this bet, Recharge your wallet to play");
                builder1.setCancelable(true);
                builder1.setNegativeButton(
                        "Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

//                showDialogBalance();
                amount.setText("");
            }


        } else {

            Log.d("CLOSE","");

            FragmentDialogBox2 alertDialog = new FragmentDialogBox2();
            alertDialog.show(RedBracket.this.getSupportFragmentManager(),"FragmentDialogBox2");



        }

        });



    }

    @Override
    protected void onResume() {
        balance.setText(getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("wallet", "0"));
        super.onResume();
    }


//    public void changeDigit(String id) {
//
//
//
//
//        switch (id) {
//            case "0" :
//                unselectOthers();
//                half05.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "1" :
//                unselectOthers();
//                half16.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "2" :
//                unselectOthers();
//                half27.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "3" :
//                unselectOthers();
//                half38.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "4" :
//                unselectOthers();
//                half49.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "5" :
//                unselectOthers();
//                half50.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "6" :
//                unselectOthers();
//                half61.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "7" :
//                unselectOthers();
//                half72.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "8" :
//                unselectOthers();
//                half83.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "9" :
//                unselectOthers();
//                half94.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//
//
//            case "00" :
//                unselectOthers();
//                full00.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "11" :
//                unselectOthers();
//                full11.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "22" :
//                unselectOthers();
//                full22.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "33" :
//                unselectOthers();
//                full33.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "44" :
//                unselectOthers();
//                full44.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "55" :
//                unselectOthers();
//                full55.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "66" :
//                unselectOthers();
//                full66.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "77" :
//                unselectOthers();
//                full77.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "88" :
//                unselectOthers();
//                full88.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//            case "99" :
//                unselectOthers();
//                full99.setBackground(getResources().getDrawable(R.drawable.shadow22));
//                break;
//
//        }
//
//    }
//
//    private void unselectOthers() {
//        half05.setBackground(getResources().getDrawable(R.drawable.shadow));
//        half16.setBackground(getResources().getDrawable(R.drawable.shadow));
//        half27.setBackground(getResources().getDrawable(R.drawable.shadow));
//        half38.setBackground(getResources().getDrawable(R.drawable.shadow));
//        half49.setBackground(getResources().getDrawable(R.drawable.shadow));
//        half50.setBackground(getResources().getDrawable(R.drawable.shadow));
//        half61.setBackground(getResources().getDrawable(R.drawable.shadow));
//        half72.setBackground(getResources().getDrawable(R.drawable.shadow));
//        half83.setBackground(getResources().getDrawable(R.drawable.shadow));
//        half94.setBackground(getResources().getDrawable(R.drawable.shadow));
//
//
//        full00.setBackground(getResources().getDrawable(R.drawable.shadow));
//        full11.setBackground(getResources().getDrawable(R.drawable.shadow));
//        full22.setBackground(getResources().getDrawable(R.drawable.shadow));
//        full33.setBackground(getResources().getDrawable(R.drawable.shadow));
//        full44.setBackground(getResources().getDrawable(R.drawable.shadow));
//        full55.setBackground(getResources().getDrawable(R.drawable.shadow));
//        full66.setBackground(getResources().getDrawable(R.drawable.shadow));
//        full77.setBackground(getResources().getDrawable(R.drawable.shadow));
//        full88.setBackground(getResources().getDrawable(R.drawable.shadow));
//        full99.setBackground(getResources().getDrawable(R.drawable.shadow));
//
//    }


    private void apicall() {

        progressDialog = new ViewDialog(RedBracket.this);
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
                                Toast.makeText(RedBracket.this, "Your account temporarily disabled by admin", Toast.LENGTH_SHORT).show();

                                getSharedPreferences(constant.prefs, MODE_PRIVATE).edit().clear().apply();
                                Intent in = new Intent(getApplicationContext(), login.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                                finish();
                            }

                            if (!jsonObject1.getString("session").equals(getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null))) {
                                Toast.makeText(RedBracket.this, "Session expired ! Please login again", Toast.LENGTH_SHORT).show();

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
                                Log.d("CLOSEE","");

                                selectedType = 1;
                                close_game.setTextColor(getResources().getColor(R.color.md_white_1000));
                                close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
                                open_game.setVisibility(View.GONE);

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
                        Toast.makeText(RedBracket.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("number", numb);

                params.put("amount", amou);
                Log.d("AMOUNT", "" + amou);
                params.put("bazar", market);
                params.put("total", total + "");
                Log.d("TOTAL", "" + total);
//                params.put("game", game);
                if(game.equals("jodi")) {
                    gametype = "Red Bracket";
                    params.put("game_type", gametype);
                }

                params.put("game",game);
                params.put("mobile", prefs.getString("mobile", null));
                params.put("types", types);

                params.put("session", getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null));
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
        half_bracket = findViewById(R.id.half_bracket);
        full_bracket = findViewById(R.id.full_bracket);
        odd = findViewById(R.id.odd);
        even = findViewById(R.id.even);



//        full00 = findViewById(R.id.full00);
//        full11 = findViewById(R.id.full11);
//        full22 = findViewById(R.id.full22);
//        full33 = findViewById(R.id.full33);
//        full44 = findViewById(R.id.full44);
//        full55 = findViewById(R.id.full55);
//        full66 = findViewById(R.id.full66);
//        full77 = findViewById(R.id.full77);
//        full88 = findViewById(R.id.full88);
//        full99 = findViewById(R.id.full99);
//
//
//        half05 = findViewById(R.id.half05);
//        half16 = findViewById(R.id.half16);
//        half27 = findViewById(R.id.half27);
//        half38 = findViewById(R.id.half38);
//        half49 = findViewById(R.id.half49);
//        half50 = findViewById(R.id.half50);
//        half61 = findViewById(R.id.half61);
//        half72 = findViewById(R.id.half72);
//        half83 = findViewById(R.id.half83);
//        half94 = findViewById(R.id.half94);
//

        date = findViewById(R.id.date);
        date.setText(new SimpleDateFormat("MMM, d\nyyyy", Locale.getDefault()).format(new Date()));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public void showDialogBalance(){
        BalanceDialog alertDialog1 = new BalanceDialog();
        alertDialog1.show(RedBracket.this.getSupportFragmentManager(),"BalanceDialog");

    }

    public void showDialogBet(){
        BidPlaceDialog alertDialog = new BidPlaceDialog();
        alertDialog.show(RedBracket.this.getSupportFragmentManager(),"BidPlaceDialog");

    }

    public void showDialog2(){
        FragmentDialogBox2 alertDialog = new FragmentDialogBox2();
        alertDialog.show(RedBracket.this.getSupportFragmentManager(),"FragmentDialogBox2");

    }

}