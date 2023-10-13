package com.rkboss.mtk;

import static android.content.ContentValues.TAG;

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
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class single_bet extends AppCompatActivity {
    private NumberAdapter adapter1;

    private List<String> itemList;
    private List<String> filteredList;
    private SearchView searchView;
    private List<String> dataList;

    LinearLayout single, jodi;
    private EditText totalamount1;
    private EditText totalamount;

    Button payLayout;

    //    private LinearLayout toLayout;
//    private EditText editTextNumber;
//    private TextView textViewNumber;
//    private CardView cardView;
//    private ArrayList<String> dataList;
//    private NumberAdapter adapter;
//    private List<String> numberList = new ArrayList<>();


    private RecyclerView recyclerView;


    private ImageView back;
    private Spinner type;
    private AutoCompleteTextView number;
    private EditText amount;
    Button add;
    private RecyclerView recyclerview;

    Button submit;
    TextView open_game, close_game;
    TextView title, balance, balance2, screenTitle;
    LinearLayout type_container, digit_header, container;
    String gametype;

    String open_av = "0";

    SharedPreferences prefs;
    ArrayList<String> list;
    ArrayList<String> numbers = new ArrayList<>();
    com.rkboss.mtk.adapterbetting adapterbetting;
    String market, game, timing = "";
    ViewDialog progressDialog;
    String url;
    int total = 0;
    ArrayList<String> fillnumber = new ArrayList<>();
    ArrayList<String> fillamount = new ArrayList<>();
    ArrayList<String> fillmarket = new ArrayList<>();

    String numb, amou, types;

    // 0 - open, 1 - close
    int selectedType = 0;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singe_bet);
        initViews();
        open_av = getIntent().getStringExtra("open_av");
        url = constant.prefix + getString(R.string.bet);
        if (getIntent().hasExtra("timing")) {
            timing = getIntent().getStringExtra("timing");
        }
        prefs = getSharedPreferences(constant.prefs, MODE_PRIVATE);
        game = getIntent().getStringExtra("game");
        Log.d("gggff", "" + open_av);
        market = getIntent().getStringExtra("market");
        numbers = getIntent().getStringArrayListExtra("list");


// Initialize RecyclerView and set its layout manager
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
// Create and set the adapter
        NumberAdapter adapter1 = new NumberAdapter(numbers, this);
        recyclerView.setAdapter(adapter1);

//        adapter1 = new NumberAdapter(numbers, this);
//
//        dataList = new ArrayList<>(); // Populate this list with your data
//        filteredList = new ArrayList<>();
//
//        NumberAdapter adapter1 = new NumberAdapter(dataList, this); // Initialize with dataList and context
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter1);



//        searchView = findViewById(R.id.search_view);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Not used in this example, you can implement search on submit if needed.
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterItems(newText);
//                return true;
//            }
//        });
//        title.setText(market.replace("_","").toUpperCase(Locale.ROOT)+", "+game.toUpperCase(Locale.ROOT));
//        title.setText(game.replace("_",""));

        if (game.equals("single")) {
            title.setText("Single Digit");
            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
            setDigitLimit(myAutoCompleteTextView, 1);
        } else if (game.equals("jodi")) {
            title.setText("Jodi");
            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
            setDigitLimit(myAutoCompleteTextView, 2);
        } else if (game.equals("singlepatti")) {
            title.setText("Single Patti");
            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
            setDigitLimit(myAutoCompleteTextView, 3);
        } else if (game.equals("doublepatti")) {
            title.setText("Double Patti");
            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
            setDigitLimit(myAutoCompleteTextView, 3);
        } else if (game.equals("triplepatti")) {
            title.setText("Triple Patti");
            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
            setDigitLimit(myAutoCompleteTextView, 3);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.simple_list_item_2, numbers);
        number.setAdapter(adapter);

        if (!game.equals("jodi") && !getIntent().hasExtra("timing")) {
            ArrayList<String> typeof = new ArrayList<>();

            if (open_av.equals("1")) {
                Log.d("numbgggggggg", "wwwwwwwwwwwww: " + open_av);

                typeof.add("OPEN");
            }
            typeof.add("CLOSE");

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(single_bet.this, R.layout.simple_list_item_2, typeof);
            type.setAdapter(arrayAdapter);
            type_container.setVisibility(View.GONE);

            //NEW CODE
            container.setVisibility(View.VISIBLE);
//NEW CODE ENDED

            if (open_av.equals("0")) {
                selectedType = 1;
                close_game.setTextColor(getResources().getColor(R.color.md_white_1000));
                close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
//                open_game.setTextColor(getResources().getColor(R.color.font));
//                open_game.setBackgroundColor(getResources().getColor(R.color.white));
                open_game.setVisibility(View.GONE);

            }

        } else {
            //    title.setVisibility(View.GONE);
            type.setVisibility(View.GONE);
            type_container.setVisibility(View.GONE);
        }

        open_game.setOnClickListener(v -> {
            selectedType = 0;
            open_game.setTextColor(getResources().getColor(R.color.md_white_1000));
            open_game.setBackgroundColor(getResources().getColor(R.color.usedred));
            close_game.setTextColor(getResources().getColor(R.color.font));
            close_game.setBackgroundColor(getResources().getColor(R.color.white));
//

            if (!open_av.equals("1")) {
                fillnumber.clear();
                fillamount.clear();
                fillmarket.clear();
                AdapterSingleGames rc = new AdapterSingleGames(single_bet.this, fillnumber, fillamount, fillmarket);
                recyclerview.setLayoutManager(new GridLayoutManager(single_bet.this, 1));
                recyclerview.setAdapter(rc);
                rc.notifyDataSetChanged();


                if (fillmarket.size() > 0) {
                    digit_header.setVisibility(View.VISIBLE);
                } else {
                    digit_header.setVisibility(View.GONE);
                }

                submit.setBackground(getResources().getDrawable(R.drawable.sub_ax));
                submit.setText("Bid close");

//
            }
        });

        close_game.setOnClickListener(v -> {
            selectedType = 1;
            close_game.setTextColor(getResources().getColor(R.color.md_white_1000));
            close_game.setBackgroundColor(getResources().getColor(R.color.usedred));
            open_game.setTextColor(getResources().getColor(R.color.font));
            open_game.setBackgroundColor(getResources().getColor(R.color.white));

        });


//NEW CODE

        // Assuming you have references to the "easy" and "special" views
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

//NEW CODE ENDED

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

                AdapterSingleGames rc = new AdapterSingleGames(single_bet.this, fillnumber, fillamount, fillmarket);
                recyclerview.setLayoutManager(new GridLayoutManager(single_bet.this, 1));
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

                //NEW CODE
//                totalamount1.setText(total + "");
                //NEW CODE ENDED
            }
        };

        IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
        registerReceiver(mReceiver, intentFilter);

        add.setOnClickListener(v -> {

            Log.d("Debug", "number: " + number.getText().toString());
            Log.d("Debug", "amount: " + amount.getText().toString());
            if (number.getText().toString().isEmpty() || !numbers.contains(number.getText().toString())) {
                Log.d("abcddd", "shradhhhhaaa: " + number.getText().toString());

                number.setError("Enter valid number");
            } else if (amount.getText().toString().isEmpty() || Integer.parseInt(amount.getText().toString()) < constant.min_single) {
                Log.d("shrajjjjjj", "aaaaaaaa: " + number.getText().toString());

                amount.setError("Enter points between " + constant.min_single + " - " + constant.max_single);
            } else {
                Log.d("cdcdcc", "yggfyguyyg: " + number.getText().toString());

                fillnumber.add(number.getText().toString());
                fillamount.add(amount.getText().toString());
                if (game.equals("jodi")) {
                    fillmarket.add("");
                } else {
                    if (open_av.equals("1")) {
                        Log.d("abchhhhhh", "salgar: " + open_av);

                        fillmarket.add("OPEN");

                    } else {
                        fillmarket.add("CLOSE");
                    }
                }

                AdapterSingleGames rc = new AdapterSingleGames(single_bet.this, fillnumber, fillamount, fillmarket);
                recyclerview.setLayoutManager(new GridLayoutManager(single_bet.this, 1));
                recyclerview.setAdapter(rc);

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

                number.setText("");
                amount.setText("");

            }
        });


        submit.setOnClickListener(v -> {

            Log.e("wallet-heck", total + "<=" + prefs.getString("wallet", null));

            if (fillnumber.size() > 0) {
                if (total <= Integer.parseInt(prefs.getString("wallet", null))) {
                    numb = "";
                    amou = "";
                    types = "";

                    numb = TextUtils.join(",", fillnumber);
                    amou = TextUtils.join(",", fillamount);
                    types = TextUtils.join(",", fillmarket);

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(single_bet.this);
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
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(single_bet.this);
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

        payLayout.setOnClickListener(v -> {

            Log.d("fbfzdgdgg",""+previousPos);
            Log.d("fbvccxbcbvfg",""+previousVal);

//            if(!previousVal.isEmpty()) {
//
//                if (total <= Integer.parseInt(prefs.getString("wallet", null))) {
//                    numb = "";
//                    amou = "";
//                    types = "";
//
//                    numb = String.valueOf(previousPos);
//                    amou = String.valueOf(previousVal);
//                    types = TextUtils.join(",", fillmarket);
//
//                    Log.d("Position", "New Position: " + numb);
//                    Log.d("Value", "New Value: " + amou);
//                    Log.d("total", "New Total: " + total);
//
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(single_bet.this);
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
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(single_bet.this);
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
            List<String> newArrayList = new ArrayList<>();

            String market_av;

            if (open_av.equalsIgnoreCase("1")){

                market_av="OPEN";

                for (int i = 0; i < previousPos.size(); i++) {
                    newArrayList.add(market_av);
                }

            }else{

                market_av="CLOSE";

                for (int i = 0; i < previousPos.size(); i++) {
                    newArrayList.add(market_av);
                }

                System.out.println("marketlist"+newArrayList);

            }


            if (previousVal.size() > 0) {

                if (total <= Integer.parseInt(prefs.getString("wallet", null))) {
                    numb = "";
                    amou = "";
                    types = "";

                    numb = TextUtils.join(",", previousPos);
                    amou = TextUtils.join(",", previousVal);
                    types = TextUtils.join(",", newArrayList);

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(single_bet.this);
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
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(single_bet.this);
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

    }


//        private void filterItems(String query) {
//            filteredList.clear();
//            for (String item : dataList) {
//                if (item.contains(query)) {
//                    filteredList.add(item);
//                }
//            }
//            adapter1.notifyDataSetChanged();
//        }
//


//
//    public void updateTotal() {
//        int total = adapter.calculateTotal();
//        totalamount.setText(String.valueOf(total));
//    }

//    private void calculateAndDisplayTotal() {
//        List<Integer> editTextValues = adapter.getEditTextValues();
//        int total = 0;
//        for (int value : editTextValues) {
//            total += value;
//        }
//
//        totalAmountEditText.setText(String.valueOf(total));
//        Log.d("TotalAmount", "Total: " + total); // Add this line for debugging
//    }


//    public void updateTotal(int newTotal) {
//        int total1 = newTotal;
//        adapter.notifyDataSetChanged(); // Refresh the adapter to reflect the updated total
//    }


    @Override
    protected void onResume() {
        balance.setText(getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("wallet", "0"));
        balance2.setText("Balance : ₹" + getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("wallet", "0"));
        super.onResume();
    }


    public void setDigitLimit(final AutoCompleteTextView autoCompleteTextView, final int digitLimit) {
        // Set the InputFilter to limit the number of digits
        InputFilter lengthFilter = new InputFilter.LengthFilter(digitLimit);
        autoCompleteTextView.setFilters(new InputFilter[]{lengthFilter});

        // Set the TextWatcher to check for exceeding digit limit
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
                if (!TextUtils.isEmpty(s) && s.length() > digitLimit) {
                    String trimmedText = s.toString().substring(0, digitLimit);
                    autoCompleteTextView.setText(trimmedText);
                    autoCompleteTextView.setSelection(trimmedText.length());
                }
            }
        });

    }

    private void apicall() {

        progressDialog = new ViewDialog(single_bet.this);
        progressDialog.showDialog();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String response = null;
        Log.e("edsa1", "sssss");
        final StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response1 -> {
                    Log.e("edsa", "sssss" + response1);
                    progressDialog.hideDialog();
                    try {
                        JSONObject jsonObject1 = new JSONObject(response1);

                        if (jsonObject1.getString("active").equals("0")) {
                            Toast.makeText(single_bet.this, "Your account temporarily disabled by admin", Toast.LENGTH_SHORT).show();

                            getSharedPreferences(constant.prefs, MODE_PRIVATE).edit().clear().apply();
                            Intent in = new Intent(getApplicationContext(), login.class);
                            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                            finish();
                        }

                        if (!jsonObject1.getString("session").equals(getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null))) {
                            Toast.makeText(single_bet.this, "Session expired ! Please login again", Toast.LENGTH_SHORT).show();

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
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        progressDialog.hideDialog();
                        Toast.makeText(single_bet.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("number", numb);
                params.put("amount", amou);
                params.put("bazar", market);
                params.put("total", total + "");

                Log.d(TAG, "Original number: " + numb);
                Log.d(TAG, "Original amount: " + amou);
                Log.d(TAG, "Market: " + market);
                Log.d(TAG, "Market12345: " + market);
                Log.d(TAG, "Original total: " + total);

                if (game.equals("single")) {
                    gametype = "single digit";
                    params.put("game_type", gametype);

                } else if (game.equals("jodi")) {
                    gametype = "Jodi";
                    params.put("Jodi", gametype);
                    Log.d(TAG, "Original total: " + total);

                } else if (game.equals("singlepatti")) {
                    gametype = "Single Patti";
                    params.put("game_type", gametype);
                    Log.d(TAG, "Single Patti" + total);

                } else if (game.equals("doublepatti")) {
                    title.setText("Double Patti");
                    gametype = "double patti";
                    params.put("game_type", gametype);
                    Log.d(TAG, "double patti" + total);
                } else if (game.equals("triplepatti")) {
                    gametype = "Tripple Patti";
                    params.put("game_type", gametype);
                    Log.d(TAG, "Tripple Patti" + total);
                }

                params.put("game", game);
                Log.d(TAG, "game" + game);
                params.put("mobile", prefs.getString("mobile", null));
                Log.d(TAG, "mobile" + prefs.getString("mobile", null));
                params.put("types", types);
                Log.d(TAG, "types" + types);
                if (!timing.equals("")) {
                    params.put("timing", timing);
                }
                params.put("session", getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null));
                Log.d(TAG, "session" + getSharedPreferences(constant.prefs, MODE_PRIVATE).getString("session", null));
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    private void initViews() {

        single = findViewById(R.id.single);
        jodi = findViewById(R.id.jodi);
        back = findViewById(R.id.back);
        type = findViewById(R.id.type);
        number = findViewById(R.id.number);
        amount = findViewById(R.id.amount);



        add = findViewById(R.id.add);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerView = findViewById(R.id.recycler_view);
        totalamount = findViewById(R.id.totalamount);
        totalamount1 = findViewById(R.id.totalamount1);
        payLayout = findViewById(R.id.pay);
        submit = findViewById(R.id.submit);
        title = findViewById(R.id.title);
        balance = findViewById(R.id.balance);
        screenTitle = findViewById(R.id.title);
        open_game = findViewById(R.id.open_game);
        close_game = findViewById(R.id.close_game);
        type_container = findViewById(R.id.type_container);
        container = findViewById(R.id.container);
        digit_header = findViewById(R.id.digit_header);
        balance2 = findViewById(R.id.balance2);

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
        BalanceDialog alertDialog1 = new BalanceDialog();
        alertDialog1.show(single_bet.this.getSupportFragmentManager(), "BalanceDialog");

    }

    public void showDialogBet() {
        BidPlaceDialog alertDialog = new BidPlaceDialog();
        alertDialog.show(single_bet.this.getSupportFragmentManager(), "BidPlaceDialog");

    }


    public void showDialog2() {
        FragmentDialogBox2 alertDialog = new FragmentDialogBox2();
        alertDialog.show(single_bet.this.getSupportFragmentManager(), "FragmentDialogBox2");

    }


    public void updateTotal(int total) {
        TextView totalamount12 = findViewById(R.id.totalamount1);
        totalamount12.setText(String.valueOf(total));
    }

    // Define member variables to store the previous position and values
    private List<Integer> previousPos;
    private List<Integer> previousVal;
    public void updatedvalues(int val, int pos) {
        // Check if the position has changed
            // Position has changed, update the values


            // Now you can work with the new values
            // Example:
            // Perform any actions you need with the new values
        }

    public void updatedValues(List<Integer> positions, List<Integer> values) {


        ArrayList<Integer> one = new ArrayList<>(positions);
        ArrayList<Integer> two = new ArrayList<>(values);

        Iterator<Integer> iteratorOne = one.iterator();
        Iterator<Integer> iteratorTwo = two.iterator();

        while (iteratorOne.hasNext() && iteratorTwo.hasNext()) {
            int valueOne = iteratorOne.next();
            int valueTwo = iteratorTwo.next();

            if (valueTwo == 0) {
                // Remove elements at the same index when valueTwo is 0
                iteratorOne.remove();
                iteratorTwo.remove();
            }
        }

//        ArrayList<Integer> resultOne = new ArrayList<>();
//        ArrayList<Integer> resultTwo = new ArrayList<>();
//
//        for (int i = 0; i < two.size(); i++) {
//            if (two.get(i) != 0) {
//                resultOne.add(one.get(i));
//                resultTwo.add(two.get(i));
//            }
//        }

        Log.d("fhfhfh",""+one);
        Log.d("fghfhfhf",""+two);

        previousPos = one;
        previousVal = two;

    }

}




