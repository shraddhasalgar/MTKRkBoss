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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mikepenz.materialize.color.Material;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RedFamilyJodi extends AppCompatActivity {

    LinearLayout single, jodi;
//    private EditText totalamount1;
    private EditText totalamount;

//    Button payLayout;

    //    ArrayList<String> number_dp = new ArrayList<>();
//    ArrayList<String> number_sp = new ArrayList<>();
//    ArrayList<String> number_tp = new ArrayList<>();
    HashMap<String, ArrayList<String>> cycles = new HashMap<>();
    ArrayList<String> numberX = new ArrayList<>();
    private ImageView back;
    private Spinner type;
    AutoCompleteTextView number;
    private EditText amount;
    Button add;
    private RecyclerView recyclerView;

    private RecyclerView recyclerview;
    Button submit;
    TextView open_game,close_game;
    TextView title,balance,screenTitle;
    LinearLayout type_container,digit_header, container;

    String value = "";
    String open_av = "0";

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
    ArrayList<String> fillgame = new ArrayList<>();
    String numb,amou,types,timing = "";

    // 0 - open, 1 - close
    int selectedType = 0;
    Boolean ischange = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_family_jodi);
        initViews();
        open_av = getIntent().getStringExtra("open_av");
        url = constant.prefix + getString(R.string.bet);

//
//        findViewById(R.id.money_block_1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int blockValue = 1;
//                if (amount.getText().toString().isEmpty()) {
//                    amount.setText(blockValue+"");
//                } else {
//                    amount.setText((Integer.parseInt(amount.getText().toString())+blockValue)+"");
//                }
//            }
//        });
//
//        findViewById(R.id.money_block_2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int blockValue = 5;
//                if (amount.getText().toString().isEmpty()) {
//                    amount.setText(blockValue+"");
//                } else {
//                    amount.setText((Integer.parseInt(amount.getText().toString())+blockValue)+"");
//                }
//            }
//        });
//
//        findViewById(R.id.money_block_3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int blockValue = 10;
//                if (amount.getText().toString().isEmpty()) {
//                    amount.setText(blockValue+"");
//                } else {
//                    amount.setText((Integer.parseInt(amount.getText().toString())+blockValue)+"");
//                }
//            }
//        });
//        singlepatti();
//        doublepatti();
//        triplepatti();


// Initialize RecyclerView and set its layout manager
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(layoutManager);
//// Create and set the adapter
//        NumberAdapter adapter1 = new NumberAdapter(numbers, this);
//        recyclerView.setAdapter(adapter1);



        if (getIntent().hasExtra("timing")){
            timing = getIntent().getStringExtra("timing");
            type_container.setVisibility(View.GONE);
        }
        prefs = getSharedPreferences(constant.prefs,MODE_PRIVATE);
        game = getIntent().getStringExtra("game");
        Log.d("gameeeeeee",""+getIntent().getStringExtra("game"));
        market = getIntent().getStringExtra("market");
        numbers = getIntent().getStringArrayListExtra("list");

        getCycles();
//        title.setText(market.replace("_","").toUpperCase(Locale.ROOT)+", "+game.toUpperCase(Locale.ROOT));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.simple_list_item_2,numbers);
        number.setAdapter(adapter);


//        number.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s != null && s.length() > 0 && !value.equals(s.toString()))
//                {
//                    ischange = true;
//                    characterCount(s.toString());
//                }
//                else if (s.toString().equals(""))
//                {
//                    numbers.clear();
//                    adapter_crossing adapterbetting = new adapter_crossing(CyclePanna.this, numbers);
//                    recyclerview.setLayoutManager(new GridLayoutManager(CyclePanna.this, 4));
//                    recyclerview.setAdapter(adapterbetting);
//                    adapterbetting.notifyDataSetChanged();
//
//                }
//            }
//        });

        if (!game.equals("jodi") && !getIntent().hasExtra("timing")){
            ArrayList<String> typeof = new ArrayList<>();

            if (open_av.equals("1")){
                typeof.add("OPEN");
            }
            typeof.add("CLOSE");

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(RedFamilyJodi.this, R.layout.simple_list_item_2, typeof);
            type.setAdapter(arrayAdapter);
            type_container.setVisibility(View.GONE);
            container.setVisibility(View.VISIBLE);


            if (open_av.equals("0")){
                selectedType = 1;
                close_game.setTextColor(getResources().getColor(R.color.md_white_1000));
                close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
                open_game.setVisibility(View.GONE);
//                open_game.setTextColor(getResources().getColor(R.color.font));
//                open_game.setBackgroundColor(getResources().getColor(R.color.white));
//                submit.setBackground(getResources().getDrawable(R.drawable.sub_ax));

            }

        } else  {
            type.setVisibility(View.GONE);
        }

        open_game.setOnClickListener(v -> {
            selectedType = 0;
            open_game.setTextColor(getResources().getColor(R.color.md_white_1000));
            open_game.setBackgroundColor(getResources().getColor(R.color.usedred));
            close_game.setTextColor(getResources().getColor(R.color.font));
            close_game.setBackgroundColor(getResources().getColor(R.color.white));


            if (!open_av.equals("1")){
                fillnumber.clear();
                fillamount.clear();
                fillmarket.clear();
                AdapterSingleGames rc = new AdapterSingleGames(RedFamilyJodi.this,fillnumber,fillamount,fillmarket);
                recyclerview.setLayoutManager(new GridLayoutManager(RedFamilyJodi.this, 1));
                recyclerview.setAdapter(rc);
                rc.notifyDataSetChanged();

                if (fillmarket.size() > 0){
                    digit_header.setVisibility(View.VISIBLE);
                } else {
                    digit_header.setVisibility(View.GONE);
                }

            }
        });

        close_game.setOnClickListener(v -> {
            selectedType = 1;
            close_game.setTextColor(getResources().getColor(R.color.md_white_1000));
            close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
            open_game.setTextColor(getResources().getColor(R.color.font));
            open_game.setBackgroundColor(getResources().getColor(R.color.white));

        });


        View easyMode = findViewById(R.id.easy);
        View specialMode = findViewById(R.id.special);

        easyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to handle click on easy mode
                // For example, you can show the easybet layout and hide the bet layout
                LinearLayout easyBetLayout = findViewById(R.id.easybet);
                LinearLayout betLayout = findViewById(R.id.bet);
                Button addLayout = findViewById(R.id.add);
                Button submitLayout = findViewById(R.id.submit);
                Button payLayout = findViewById(R.id.pay);
                LinearLayout totalLayout = findViewById(R.id.total);
                LinearLayout total1Layout = findViewById(R.id.total1);

//                LinearLayout toLayout = findViewById(R.id.to);

//                toLayout.setVisibility(View.VISIBLE);
                submitLayout.setVisibility(View.GONE);
                easyBetLayout.setVisibility(View.VISIBLE);
                betLayout.setVisibility(View.GONE);
                addLayout.setVisibility(View.GONE);
                payLayout.setVisibility(View.VISIBLE);
                totalLayout.setVisibility(View.GONE);
                total1Layout.setVisibility(View.VISIBLE);
                digit_header.setVisibility(View.GONE);
                recyclerview.setVisibility(View.GONE);


                easyMode.setBackgroundColor(getResources().getColor(R.color.usedred));
                specialMode.setBackgroundColor(getResources().getColor(android.R.color.white));

            }
        });

        specialMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to handle click on special mode
                // For example, you can show the bet layout and hide the easybet layout
                LinearLayout easyBetLayout = findViewById(R.id.easybet);
                LinearLayout betLayout = findViewById(R.id.bet);
                Button addLayout = findViewById(R.id.add);
                Button submitLayout = findViewById(R.id.submit);
                Button payLayout = findViewById(R.id.pay);
                LinearLayout totalLayout = findViewById(R.id.total);
                LinearLayout total1Layout = findViewById(R.id.total1);


                totalLayout.setVisibility(View.VISIBLE);
                total1Layout.setVisibility(View.GONE);
                easyBetLayout.setVisibility(View.GONE);
                betLayout.setVisibility(View.VISIBLE);
                addLayout.setVisibility(View.VISIBLE);
                submitLayout.setVisibility(View.VISIBLE);
                payLayout.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);


                // Change background color
                specialMode.setBackgroundColor(getResources().getColor(R.color.usedred));
                easyMode.setBackgroundColor(getResources().getColor(android.R.color.white));
            }
        });



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

                String num = intent.getStringExtra("number");
                fillamount.remove(Integer.parseInt(num));
                fillnumber.remove(Integer.parseInt(num));
                fillmarket.remove(Integer.parseInt(num));
                fillgame.remove(Integer.parseInt(num));

                AdapterSingleGames rc = new AdapterSingleGames(RedFamilyJodi.this,fillnumber,fillamount,fillmarket);
                recyclerview.setLayoutManager(new GridLayoutManager(RedFamilyJodi.this, 1));
                recyclerview.setAdapter(rc);
                rc.notifyDataSetChanged();

                if (fillmarket.size() > 0){
                    digit_header.setVisibility(View.VISIBLE);
                } else {
                    digit_header.setVisibility(View.GONE);
                }

                total = 0;
                for (int a = 0; a < fillamount.size(); a++) {
                    total = total+Integer.parseInt(fillamount.get(a));
                }
                totalamount.setText(total+"");
            }
        };

        IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
        registerReceiver(mReceiver, intentFilter);

        add.setOnClickListener(v -> {
            if (amount.getText().toString().isEmpty() || Integer.parseInt(amount.getText().toString()) < constant.min_single){
                amount.setError("Enter points between "+constant.min_single+" - "+constant.max_single);
            } else {

                characterCount(number.getText().toString());

            }
        });

        submit.setOnClickListener(v -> {


            if (fillnumber.size() > 0){
                if (total <= Integer.parseInt(prefs.getString("wallet",null))) {
                    numb = "";
                    amou = "";
                    types = "";
//                    game = "";

                    numb = TextUtils.join(",", fillnumber);
                    amou = TextUtils.join(",", fillamount);
                    types = TextUtils.join(",", fillmarket);
//                    game = TextUtils.join(",", fillgame);


                    AlertDialog.Builder builder1 = new AlertDialog.Builder(RedFamilyJodi.this);
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
                    });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else
                {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(RedFamilyJodi.this);
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

//                    showDialogBalance();
                }
            }



        });

//        payLayout.setOnClickListener(v -> {
//
//            Log.e("sssssssss", total + "<=" + prefs.getString("wallet", null));
//
//            if (fillnumber.size() > 0) {
//
//                Log.e("kkkk", total + "<=" + prefs.getString("wallet", null));
//
//                if (total <= Integer.parseInt(prefs.getString("wallet", null))) {
//                    numb = "";
//                    amou = "";
//                    types = "";
//
//                    numb = TextUtils.join(",", fillnumber);
//                    amou = TextUtils.join(",", fillamount);
//                    types = TextUtils.join(",", fillmarket);
//
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(RedFamilyJodi.this);
//                    builder1.setMessage("Your total bet point\t" + total + "\twill be debited from wallet..\n\nAre you sure to submit bet ?\n");
//                    builder1.setCancelable(true);
//
//                    builder1.setNegativeButton(
//                            "Cancel",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dialog.cancel();
//                                }
//                            });
//
//                    builder1.setPositiveButton("Confirm", (DialogInterface.OnClickListener) (dialog, which) -> {
//                        dialog.cancel();
//                        apicall();
//
//                    });
//
//                    AlertDialog alert11 = builder1.create();
//                    alert11.show();
//
//                } else {
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(RedFamilyJodi.this);
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
//
////                    showDialogBalance();
//
//                }
//            }
//
//        });



    }


    public void characterCount(String inputString)
    {

        if (cycles.get(inputString) != null){
            for (int ab = 0; ab < cycles.get(inputString).size(); ab++)
            {
                String number = cycles.get(inputString).get(ab);
                fillnumber.add(number);
                fillamount.add(amount.getText().toString());
                if (selectedType == 0){
                    fillmarket.add("OPEN");
                } else {
                    fillmarket.add("CLOSE");
                }
//                if (number_sp.contains(number)){
//                    fillgame.add("singlepatti");
//                } else if (number_dp.contains(number)){
//                    fillgame.add("doublepatti");
//                } else if (number_tp.contains(number)){
//                    fillgame.add("triplepatti");
//                }
            }
        } else {
            number.setError("Input don't have any red family jodi");
        }

        AdapterSingleGames rc = new AdapterSingleGames(RedFamilyJodi.this,fillnumber,fillamount,fillmarket);
        recyclerview.setLayoutManager(new GridLayoutManager(RedFamilyJodi.this, 1));
        recyclerview.setAdapter(rc);
        number.setText(value);

        if (fillmarket.size() > 0) {
            digit_header.setVisibility(View.VISIBLE);
        } else {
            digit_header.setVisibility(View.GONE);
        }

        total = 0;
        for (int a = 0; a < fillamount.size(); a++) {
            total = total+Integer.parseInt(fillamount.get(a));
        }
        totalamount.setText(total+"");

        number.setText("");
        amount.setText("");
    }


    @Override
    protected void onResume() {
        balance.setText(getSharedPreferences(constant.prefs,MODE_PRIVATE).getString("wallet","0"));
        super.onResume();
    }

    private void apicall() {

        progressDialog = new ViewDialog(RedFamilyJodi.this);
        progressDialog.showDialog();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String response = null;

        final StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response1 -> {
                    Log.e("edsa", "efsdc" + response1);
                    progressDialog.hideDialog();
                    try {
                        JSONObject jsonObject1 = new JSONObject(response1);

                        if (jsonObject1.getString("active").equals("0")) {
                            Toast.makeText(RedFamilyJodi.this, "Your account temporarily disabled by admin", Toast.LENGTH_SHORT).show();

                            getSharedPreferences(constant.prefs, MODE_PRIVATE).edit().clear().apply();
                            Intent in = new Intent(getApplicationContext(), login.class);
                            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                            finish();
                        }

                        if (!jsonObject1.getString("session").equals(getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null))) {
                            Toast.makeText(RedFamilyJodi.this, "Session expired ! Please login again", Toast.LENGTH_SHORT).show();

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
//                            showDialogBet();

                        } else {
                            Log.d("CLOSEE","");

                            selectedType = 1;
                            close_game.setTextColor(getResources().getColor(R.color.md_white_1000));
                            close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
                            open_game.setVisibility(View.GONE);

                            Toast.makeText(getApplicationContext(), jsonObject1.getString("msg"), Toast.LENGTH_SHORT).show();

//                            showDialog2();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.hideDialog();
                    }
                },
                error -> {
                    error.printStackTrace();
                    progressDialog.hideDialog();
                    Toast.makeText(RedFamilyJodi.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("number",numb);
                Log.d("number",""+numb);
                params.put("amount",amou);
                Log.d("amount",""+amou);
                params.put("bazar",market);
                Log.d("bazar",""+market);
                params.put("total",total+"");
                Log.d("total",""+total);
                params.put("game_type",game);
                Log.d("game_type",""+game);
                params.put("game",game);
                Log.d("game",""+game);
                params.put("mobile", prefs.getString("mobile",null));
                Log.d("mobile",""+prefs.getString("mobile",null));
                params.put("types",types);
                Log.d("types",""+types);
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



    public void getCycles(){

        cycles.put("00",new ArrayList<String>(Arrays.asList("00, 05, 50, 55".split(", "))));
        cycles.put("11",new ArrayList<String>(Arrays.asList("11, 16, 61, 66".split(", "))));
        cycles.put("22",new ArrayList<String>(Arrays.asList("22, 27, 72, 77".split(", "))));
        cycles.put("33",new ArrayList<String>(Arrays.asList("33, 38, 83, 88".split(", "))));
        cycles.put("44",new ArrayList<String>(Arrays.asList("44, 49, 94, 99".split(", "))));



    }

//    public void triplepatti() {
//        number_tp.add("000");
//        number_tp.add("111");
//        number_tp.add("222");
//        number_tp.add("333");
//        number_tp.add("444");
//        number_tp.add("555");
//        number_tp.add("666");
//        number_tp.add("777");
//        number_tp.add("888");
//        number_tp.add("999");
//    }
//
//    public void singlepatti() {
//        number_sp.add("128");
//        number_sp.add("137");
//        number_sp.add("146");
//        number_sp.add("236");
//        number_sp.add("245");
//        number_sp.add("290");
//        number_sp.add("380");
//        number_sp.add("470");
//        number_sp.add("489");
//        number_sp.add("560");
//        number_sp.add("678");
//        number_sp.add("579");
//        number_sp.add("129");
//        number_sp.add("138");
//        number_sp.add("147");
//        number_sp.add("156");
//        number_sp.add("237");
//        number_sp.add("246");
//        number_sp.add("345");
//        number_sp.add("390");
//        number_sp.add("480");
//        number_sp.add("570");
//        number_sp.add("679");
//        number_sp.add("120");
//        number_sp.add("139");
//        number_sp.add("148");
//        number_sp.add("157");
//        number_sp.add("238");
//        number_sp.add("247");
//        number_sp.add("256");
//        number_sp.add("346");
//        number_sp.add("490");
//        number_sp.add("580");
//        number_sp.add("670");
//        number_sp.add("689");
//        number_sp.add("130");
//        number_sp.add("149");
//        number_sp.add("158");
//        number_sp.add("167");
//        number_sp.add("239");
//        number_sp.add("248");
//        number_sp.add("257");
//        number_sp.add("347");
//        number_sp.add("356");
//        number_sp.add("590");
//        number_sp.add("680");
//        number_sp.add("789");
//        number_sp.add("140");
//        number_sp.add("159");
//        number_sp.add("168");
//        number_sp.add("230");
//        number_sp.add("249");
//        number_sp.add("258");
//        number_sp.add("267");
//        number_sp.add("348");
//        number_sp.add("357");
//        number_sp.add("456");
//        number_sp.add("690");
//        number_sp.add("780");
//        number_sp.add("123");
//        number_sp.add("150");
//        number_sp.add("169");
//        number_sp.add("178");
//        number_sp.add("240");
//        number_sp.add("259");
//        number_sp.add("268");
//        number_sp.add("349");
//        number_sp.add("358");
//        number_sp.add("457");
//        number_sp.add("367");
//        number_sp.add("790");
//        number_sp.add("124");
//        number_sp.add("160");
//        number_sp.add("179");
//        number_sp.add("250");
//        number_sp.add("269");
//        number_sp.add("278");
//        number_sp.add("340");
//        number_sp.add("359");
//        number_sp.add("368");
//        number_sp.add("458");
//        number_sp.add("467");
//        number_sp.add("890");
//        number_sp.add("125");
//        number_sp.add("134");
//        number_sp.add("170");
//        number_sp.add("189");
//        number_sp.add("260");
//        number_sp.add("279");
//        number_sp.add("350");
//        number_sp.add("369");
//        number_sp.add("378");
//        number_sp.add("459");
//        number_sp.add("567");
//        number_sp.add("468");
//        number_sp.add("126");
//        number_sp.add("135");
//        number_sp.add("180");
//        number_sp.add("234");
//        number_sp.add("270");
//        number_sp.add("289");
//        number_sp.add("360");
//        number_sp.add("379");
//        number_sp.add("450");
//        number_sp.add("469");
//        number_sp.add("478");
//        number_sp.add("568");
//        number_sp.add("127");
//        number_sp.add("136");
//        number_sp.add("145");
//        number_sp.add("190");
//        number_sp.add("235");
//        number_sp.add("280");
//        number_sp.add("370");
//        number_sp.add("479");
//        number_sp.add("460");
//        number_sp.add("569");
//        number_sp.add("389");
//        number_sp.add("578");
//        number_sp.add("589");
//    }
//
//    public void doublepatti() {
//        number_dp.add("100");
//        number_dp.add("119");
//        number_dp.add("155");
//        number_dp.add("227");
//        number_dp.add("335");
//        number_dp.add("344");
//        number_dp.add("399");
//        number_dp.add("588");
//        number_dp.add("669");
//        number_dp.add("200");
//        number_dp.add("110");
//        number_dp.add("228");
//        number_dp.add("255");
//        number_dp.add("336");
//        number_dp.add("499");
//        number_dp.add("660");
//        number_dp.add("688");
//        number_dp.add("778");
//        number_dp.add("300");
//        number_dp.add("166");
//        number_dp.add("229");
//        number_dp.add("337");
//        number_dp.add("355");
//        number_dp.add("445");
//        number_dp.add("599");
//        number_dp.add("779");
//        number_dp.add("788");
//        number_dp.add("400");
//        number_dp.add("112");
//        number_dp.add("220");
//        number_dp.add("266");
//        number_dp.add("338");
//        number_dp.add("446");
//        number_dp.add("455");
//        number_dp.add("699");
//        number_dp.add("770");
//        number_dp.add("500");
//        number_dp.add("113");
//        number_dp.add("122");
//        number_dp.add("177");
//        number_dp.add("339");
//        number_dp.add("366");
//        number_dp.add("447");
//        number_dp.add("799");
//        number_dp.add("889");
//        number_dp.add("600");
//        number_dp.add("114");
//        number_dp.add("277");
//        number_dp.add("330");
//        number_dp.add("448");
//        number_dp.add("466");
//        number_dp.add("556");
//        number_dp.add("880");
//        number_dp.add("899");
//        number_dp.add("700");
//        number_dp.add("115");
//        number_dp.add("133");
//        number_dp.add("188");
//        number_dp.add("223");
//        number_dp.add("377");
//        number_dp.add("449");
//        number_dp.add("557");
//        number_dp.add("566");
//        number_dp.add("800");
//        number_dp.add("116");
//        number_dp.add("224");
//        number_dp.add("233");
//        number_dp.add("288");
//        number_dp.add("440");
//        number_dp.add("477");
//        number_dp.add("558");
//        number_dp.add("990");
//        number_dp.add("900");
//        number_dp.add("117");
//        number_dp.add("144");
//        number_dp.add("199");
//        number_dp.add("225");
//        number_dp.add("388");
//        number_dp.add("559");
//        number_dp.add("577");
//        number_dp.add("667");
//        number_dp.add("550");
//        number_dp.add("668");
//        number_dp.add("244");
//        number_dp.add("299");
//        number_dp.add("226");
//        number_dp.add("488");
//        number_dp.add("677");
//        number_dp.add("118");
//        number_dp.add("334");
//    }


    private void initViews() {
        back = findViewById(R.id.back);
        type = findViewById(R.id.type);
        number = findViewById(R.id.number);
        amount = findViewById(R.id.amount);
        add = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recycler_view);
//        totalamount1 = findViewById(R.id.totalamount1);

        recyclerview = findViewById(R.id.recyclerview);
        totalamount = findViewById(R.id.totalamount);
        submit = findViewById(R.id.submit);
//        payLayout = findViewById(R.id.pay);
        title = findViewById(R.id.title);
        balance = findViewById(R.id.balance);
        screenTitle = findViewById(R.id.title);
        open_game = findViewById(R.id.open_game);
        close_game = findViewById(R.id.close_game);
        container = findViewById(R.id.container);
        type_container = findViewById(R.id.type_container);
        digit_header = findViewById(R.id.digit_header);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void showDialogBalance(){
        BalanceDialog alertDialog1 = new BalanceDialog();
        alertDialog1.show(RedFamilyJodi.this.getSupportFragmentManager(),"BalanceDialog");

    }

    public void showDialogBet(){
        BidPlaceDialog alertDialog = new BidPlaceDialog();
        alertDialog.show(RedFamilyJodi.this.getSupportFragmentManager(),"BidPlaceDialog");

    }

    public void showDialog2(){
        FragmentDialogBox2 alertDialog = new FragmentDialogBox2();
        alertDialog.show(RedFamilyJodi.this.getSupportFragmentManager(),"FragmentDialogBox2");

    }

//    public void updateTotal(int total) {
//        TextView totalamount12 = findViewById(R.id.totalamount1);
//        totalamount12.setText(String.valueOf(total));
//    }

    public interface RecyclerViewItemClickListener {
        void onItemClicked(String itemType); // Define the method signature
    }


}