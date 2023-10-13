package com.rkboss.mtk;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SpMotor extends AppCompatActivity {

    ArrayList<String> numberX = new ArrayList<>();
    private ImageView back;
    private Spinner type;
    private EditText number;
    private EditText amount;
    Button add;
    private RecyclerView recyclerview;
    private EditText totalamount;
    Button submit;
    TextView open_game, close_game;


    TextView title, balance, screenTitle;
    LinearLayout type_container, digit_header;

    String gametype;

    String value = "";
    String open_av = "0";

    SharedPreferences prefs;
    ArrayList<String> list;
    ArrayList<String> numbers = new ArrayList<>();
    adapterbetting adapterbetting;
    String market, game;
    ViewDialog progressDialog;
    String url;
    int total = 0;
    ArrayList<String> fillnumber = new ArrayList<>();
    ArrayList<String> fillamount = new ArrayList<>();
    ArrayList<String> fillmarket = new ArrayList<>();
    String numb, amou, types, timing = "";

    TextView date;
    // 0 - open, 1 - close
    int selectedType = 0;
    Boolean ischange = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_motor);
        initViews();
        open_av = getIntent().getStringExtra("open_av");
        url = constant.prefix + getString(R.string.bet);

        if (getIntent().hasExtra("timing")) {
            timing = getIntent().getStringExtra("timing");
            type_container.setVisibility(View.GONE);
        }
        prefs = getSharedPreferences(constant.prefs, MODE_PRIVATE);
        game = getIntent().getStringExtra("game");
        market = getIntent().getStringExtra("market");
        numbers = getIntent().getStringArrayListExtra("list");

//        title.setText(market.replace("_","").toUpperCase(Locale.ROOT)+", "+game.toUpperCase(Locale.ROOT));

        AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
        setAscendingDigitsWithLimit(myAutoCompleteTextView);

        if (game.equals("singlepatti")) {
            title.setText("Sp Motor");
        } else if (game.equals("doublepatti")) {
            title.setText("Dp Motor");
        }


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
//                    adapter_crossing adapterbetting = new adapter_crossing(SpMotor.this, numbers);
//                    recyclerview.setLayoutManager(new GridLayoutManager(SpMotor.this, 4));
//                    recyclerview.setAdapter(adapterbetting);
//                    adapterbetting.notifyDataSetChanged();
//
//                }
//            }
//        });

        if (!game.equals("jodi") && !getIntent().hasExtra("timing")) {
            ArrayList<String> typeof = new ArrayList<>();

            if (open_av.equals("1")) {
                typeof.add("OPEN");
            }
            typeof.add("CLOSE");

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SpMotor.this, R.layout.simple_list_item_2, typeof);
            type.setAdapter(arrayAdapter);
            type_container.setVisibility(View.GONE);

            if (open_av.equals("0")) {
                selectedType = 1;
                close_game.setTextColor(getResources().getColor(R.color.white));
                close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
                open_game.setVisibility(View.GONE);
            }

        } else {
            type.setVisibility(View.GONE);
        }

        open_game.setOnClickListener(v -> {
            selectedType = 0;
            open_game.setTextColor(getResources().getColor(R.color.white));
            open_game.setBackgroundColor(getResources().getColor(R.color.usedred));
            close_game.setTextColor(getResources().getColor(R.color.font));
            close_game.setBackgroundColor(getResources().getColor(R.color.white));

            if (!open_av.equals("1")) {
                fillnumber.clear();
                fillamount.clear();
                fillmarket.clear();
                AdapterSingleGames rc = new AdapterSingleGames(SpMotor.this, fillnumber, fillamount, fillmarket);
                recyclerview.setLayoutManager(new GridLayoutManager(SpMotor.this, 1));
                recyclerview.setAdapter(rc);
                rc.notifyDataSetChanged();

                if (fillmarket.size() > 0) {
                    digit_header.setVisibility(View.VISIBLE);
                } else {
                    digit_header.setVisibility(View.GONE);
                }
            }
        });

        close_game.setOnClickListener(v -> {
            selectedType = 1;
            close_game.setTextColor(getResources().getColor(R.color.white));
            close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
            open_game.setTextColor(getResources().getColor(R.color.font));
            open_game.setBackgroundColor(getResources().getColor(R.color.white));

        });

            // Assuming you have references to the "easy" and "special" views
//            View easyMode = findViewById(R.id.easy);
//            View specialMode = findViewById(R.id.special);

//            easyMode.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Code to handle click on easy mode
//                    // For example, you can show the easybet layout and hide the bet layout
//                    LinearLayout easyBetLayout = findViewById(R.id.easybet);
//                    LinearLayout betLayout = findViewById(R.id.bet);
//
//                    easyBetLayout.setVisibility(View.VISIBLE);
//                    betLayout.setVisibility(View.GONE);
//
//
//                    easyMode.setBackgroundColor(getResources().getColor(R.color.usedred));
//                    specialMode.setBackgroundColor(getResources().getColor(android.R.color.white));
//
//                 }
//            });
//
//            specialMode.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Code to handle click on special mode
//                    // For example, you can show the bet layout and hide the easybet layout
//                    LinearLayout easyBetLayout = findViewById(R.id.easybet);
//                    LinearLayout betLayout = findViewById(R.id.bet);
//
//                    easyBetLayout.setVisibility(View.GONE);
//                    betLayout.setVisibility(View.VISIBLE);
//
//
//                    // Change background color
//                    specialMode.setBackgroundColor(getResources().getColor(R.color.usedred));
//                    easyMode.setBackgroundColor(getResources().getColor(android.R.color.white));
//                }
//            });





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
                } catch (NumberFormatException ignored) {
                }

                // Input is not within the range, so return an empty string to prevent it from being entered
                return "";
            }
        };

        amount.setFilters(new InputFilter[]{rangeFilter});

        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String num = intent.getStringExtra("number");
                fillamount.remove(Integer.parseInt(num));
                fillnumber.remove(Integer.parseInt(num));
                fillmarket.remove(Integer.parseInt(num));

                AdapterSingleGames rc = new AdapterSingleGames(SpMotor.this, fillnumber, fillamount, fillmarket);
                recyclerview.setLayoutManager(new GridLayoutManager(SpMotor.this, 1));
                recyclerview.setAdapter(rc);
                rc.notifyDataSetChanged();

                if (fillmarket.size() > 0) {
                    digit_header.setVisibility(View.VISIBLE);
                } else {
                    digit_header.setVisibility(View.GONE);
                }

                total = 0;
                for (int a = 0; a < fillamount.size(); a++) {
                    total = total + Integer.parseInt(fillamount.get(a));
                }
                totalamount.setText(total + "");
            }
        };

        IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
        registerReceiver(mReceiver, intentFilter);

        add.setOnClickListener(v -> {
            if (amount.getText().toString().isEmpty() || Integer.parseInt(amount.getText().toString()) < constant.min_single) {
                amount.setError("Enter amount between " + constant.min_single + " - " + constant.max_single);
            } else {

                characterCount(number.getText().toString());

            }

            number.setText("");
            amount.setText("");

        });

        submit.setOnClickListener(v -> {

            if (fillnumber.size() > 0) {


                if (total < Integer.parseInt(prefs.getString("wallet", null))) {


                    Log.d("wallet", "" + Integer.parseInt(prefs.getString("wallet", null)));
                    numb = "";
                    amou = "";
                    types = "";

                    numb = TextUtils.join(",", fillnumber);
                    amou = TextUtils.join(",", fillamount);
                    types = TextUtils.join(",", fillmarket);

                    total = 0;
                    for (int a = 0; a < fillamount.size(); a++) {
                        total = total + Integer.parseInt(fillamount.get(a));
                    }

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SpMotor.this);
                    builder1.setMessage("Your total bet point\t" + total + "\twill be debited from wallet..\n\nAre you sure to submit bet ?\n");
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
                } else {
//
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(SpMotor.this);
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

                    Log.d("balancee", "");

//                        showDialogBalance();

                }
            }


        });

    }


    public void characterCount(String inputString) {
        StringBuilder data = new StringBuilder();
        HashMap<Character, Integer> charCountMap
                = new HashMap<Character, Integer>();
        char[] strArray = inputString.toCharArray();
        for (char c : strArray) {
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {
                charCountMap.put(c, 1);
            }
        }


        for (Map.Entry entry : charCountMap.entrySet()) {
            data.append(entry.getKey().toString());
        }

        value = data.toString();

        for (int a = 0; a < value.length(); a++) {
            for (int i = 0; i < value.length(); i++) {
                for (int ia = 0; ia < value.length(); ia++) {
                    String nd = value.charAt(a) + "" + value.charAt(i) + "" + value.charAt(ia) + "";
                    Log.e("nd", nd);
                    if (numbers.contains(nd)) {
                        fillamount.add(amount.getText().toString());
                        fillnumber.add(nd);
                        if (selectedType == 0) {
                            fillmarket.add("OPEN");
                        } else {
                            fillmarket.add("CLOSE");
                        }
                    }
                }
            }
        }

        AdapterSingleGames rc = new AdapterSingleGames(SpMotor.this, fillnumber, fillamount, fillmarket);
        recyclerview.setLayoutManager(new GridLayoutManager(SpMotor.this, 1));
        recyclerview.setAdapter(rc);
        number.setText(value);

        if (!amount.getText().toString().isEmpty()) {
            totalamount.setText("" + (Integer.parseInt(amount.getText().toString()) * (numbers.size())));
        }
    }


    @Override
    protected void onResume() {
        balance.setText(getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("wallet", "0"));
        super.onResume();
    }


    public void setAscendingDigitsOnly(final AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        StringBuilder filteredStringBuilder = new StringBuilder();
                        for (int i = start; i < end; i++) {
                            char currentChar = source.charAt(i);
                            if (Character.isDigit(currentChar) && isAscendingDigits(dest.toString() + currentChar)) {
                                filteredStringBuilder.append(currentChar);
                            }
                        }
                        return filteredStringBuilder.toString();
                    }
                }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed
            }
        });
    }

    public void setAscendingDigitsWithLimit(final AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(10),
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        StringBuilder filteredStringBuilder = new StringBuilder();
                        for (int i = start; i < end; i++) {
                            char currentChar = source.charAt(i);
                            if (Character.isDigit(currentChar) && isAscendingDigits(dest.toString() + currentChar)) {
                                filteredStringBuilder.append(currentChar);
                            }
                        }
                        return filteredStringBuilder.toString();
                    }
                }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed
            }
        });
    }

    // Check if the input string consists of ascending digits
    private boolean isAscendingDigits(String input) {
        if (TextUtils.isEmpty(input)) {
            return true;
        }

        int previousDigit = -1;
        for (int i = 0; i < input.length(); i++) {
            int currentDigit = Character.getNumericValue(input.charAt(i));
            if (currentDigit <= previousDigit) {
                return false;
            }
            previousDigit = currentDigit;
        }
        return true;
    }

    private void apicall() {

        progressDialog = new ViewDialog(SpMotor.this);
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
                            Toast.makeText(SpMotor.this, "Your account temporarily disabled by admin", Toast.LENGTH_SHORT).show();

                            getSharedPreferences(constant.prefs, MODE_PRIVATE).edit().clear().apply();
                            Intent in = new Intent(getApplicationContext(), login.class);
                            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                            finish();
                        }

                        if (!jsonObject1.getString("session").equals(getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null))) {
                            Toast.makeText(SpMotor.this, "Session expired ! Please login again", Toast.LENGTH_SHORT).show();

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
                            Log.d("CLOSEE", "");

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
                    Toast.makeText(SpMotor.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("number", numb);
                params.put("amount", amou);
                params.put("bazar", market);
                params.put("total", total + "");
                Log.d("TOTAL", "" + total);
//                params.put("game",game);

                if (game.equals("singlepatti")) {
                    gametype = "Sp Motor";
                    params.put("game_type", gametype);
                } else if (game.equals("doublepatti")) {
                    gametype = "Dp Motor";
                    params.put("game_type", gametype);
                }

                Log.d("gametype", "" + params.put("game_type", gametype));
                params.put("game", game);
                params.put("mobile", prefs.getString("mobile", null));
                params.put("types", types);
                if (!timing.equals("")) {
                    params.put("timing", timing);
                }
                params.put("session", getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null));
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }


    public ArrayList<String> singlepatti() {
        ArrayList<String> number = new ArrayList<>();
        number.add("128");
        number.add("137");
        number.add("146");
        number.add("236");
        number.add("245");
        number.add("290");
        number.add("380");
        number.add("470");
        number.add("489");
        number.add("560");
        number.add("678");
        number.add("579");
        number.add("589");
        number.add("129");
        number.add("138");
        number.add("147");
        number.add("156");
        number.add("237");
        number.add("246");
        number.add("345");
        number.add("390");
        number.add("480");
        number.add("570");
        number.add("679");
        number.add("120");
        number.add("139");
        number.add("148");
        number.add("157");
        number.add("238");
        number.add("247");
        number.add("256");
        number.add("346");
        number.add("490");
        number.add("580");
        number.add("670");
        number.add("689");
        number.add("130");
        number.add("149");
        number.add("158");
        number.add("167");
        number.add("239");
        number.add("248");
        number.add("257");
        number.add("347");
        number.add("356");
        number.add("590");
        number.add("680");
        number.add("789");
        number.add("140");
        number.add("159");
        number.add("168");
        number.add("230");
        number.add("249");
        number.add("258");
        number.add("267");
        number.add("348");
        number.add("357");
        number.add("456");
        number.add("690");
        number.add("780");
        number.add("123");
        number.add("150");
        number.add("169");
        number.add("178");
        number.add("240");
        number.add("259");
        number.add("268");
        number.add("349");
        number.add("358");
        number.add("457");
        number.add("367");
        number.add("790");
        number.add("124");
        number.add("160");
        number.add("179");
        number.add("250");
        number.add("269");
        number.add("278");
        number.add("340");
        number.add("359");
        number.add("368");
        number.add("458");
        number.add("467");
        number.add("890");
        number.add("125");
        number.add("134");
        number.add("170");
        number.add("189");
        number.add("260");
        number.add("279");
        number.add("350");
        number.add("369");
        number.add("378");
        number.add("459");
        number.add("567");
        number.add("468");
        number.add("126");
        number.add("135");
        number.add("180");
        number.add("234");
        number.add("270");
        number.add("289");
        number.add("360");
        number.add("379");
        number.add("450");
        number.add("469");
        number.add("478");
        number.add("568");
        number.add("127");
        number.add("136");
        number.add("145");
        number.add("190");
        number.add("235");
        number.add("280");
        number.add("370");
        number.add("479");
        number.add("460");
        number.add("569");
        number.add("389");
        number.add("578");

        return number;
    }


    private void initViews() {
        back = findViewById(R.id.back);
        type = findViewById(R.id.type);
        number = findViewById(R.id.number);
        amount = findViewById(R.id.amount);
        add = findViewById(R.id.add);
        recyclerview = findViewById(R.id.recyclerview);
        totalamount = findViewById(R.id.totalamount);
        submit = findViewById(R.id.submit);
        title = findViewById(R.id.title);
        balance = findViewById(R.id.balance);
        screenTitle = findViewById(R.id.title);
        open_game = findViewById(R.id.open_game);
        close_game = findViewById(R.id.close_game);
        type_container = findViewById(R.id.type_container);
        digit_header = findViewById(R.id.digit_header);
        date = findViewById(R.id.date);
        date.setText(new SimpleDateFormat("MMM, d\nyyyy", Locale.getDefault()).format(new Date()));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showDialogBalance() {

        Log.d("balancee2", "");
        BalanceDialog alertDialog1 = new BalanceDialog();
        alertDialog1.show(SpMotor.this.getSupportFragmentManager(), "BalanceDialog");

    }

    public void showDialogBet() {
        BidPlaceDialog alertDialog = new BidPlaceDialog();
        alertDialog.show(SpMotor.this.getSupportFragmentManager(), "BidPlaceDialog");

    }

    public void showDialog2() {
        FragmentDialogBox2 alertDialog = new FragmentDialogBox2();
        alertDialog.show(SpMotor.this.getSupportFragmentManager(), "FragmentDialogBox2");

    }


}