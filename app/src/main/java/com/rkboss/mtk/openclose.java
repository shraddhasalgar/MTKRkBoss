package com.rkboss.mtk;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class openclose extends AppCompatActivity {
//    String gametype;
//
//    TextView title;
//    String numb, amou, types;
//    Button submit;
//    ViewDialog progressDialog;
//    private RecyclerView recyclerview;
//    private Spinner type;
//    int selectedType = 0;
    ImageView closeImageView;
//    SharedPreferences prefs;
//    ArrayList<String> fillnumber = new ArrayList<>();
//    ArrayList<String> fillamount = new ArrayList<>();
//    ArrayList<String> fillmarket = new ArrayList<>();
    TextView openTextView, closeTextView;
//    String open_av = "0";
//    String url;
//    LinearLayout type_container, digit_header, container;
//    String market, game, timing = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openclose_dialog_box);
        initViews();


//        open_av = getIntent().getStringExtra("open_av");
//        url = constant.prefix + getString(R.string.bet);
//        if (getIntent().hasExtra("timing")) {
//            timing = getIntent().getStringExtra("timing");
//        }
//        if (game.equals("single")) {
//            title.setText("Single Digit");
//            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
//            setDigitLimit(myAutoCompleteTextView, 1);
//        } else if (game.equals("jodi")) {
//            title.setText("Jodi");
//            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
//            setDigitLimit(myAutoCompleteTextView, 2);
//        } else if (game.equals("singlepatti")) {
//            title.setText("Single Patti");
//            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
//            setDigitLimit(myAutoCompleteTextView, 3);
//        } else if (game.equals("doublepatti")) {
//            title.setText("Double Patti");
//            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
//            setDigitLimit(myAutoCompleteTextView, 3);
//        } else if (game.equals("triplepatti")) {
//            title.setText("Triple Patti");
//            AutoCompleteTextView myAutoCompleteTextView = findViewById(R.id.number);
//            setDigitLimit(myAutoCompleteTextView, 3);
//        }
//
//        if (!game.equals("jodi") && !getIntent().hasExtra("timing")) {
//            ArrayList<String> typeof = new ArrayList<>();
//
//            if (open_av.equals("1")) {
//                typeof.add("OPEN");
//            }
//            typeof.add("CLOSE");
//
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(openclose.this, R.layout.simple_list_item_2, typeof);
//            type.setAdapter(arrayAdapter);
//            type_container.setVisibility(View.GONE);
//
//            //NEW CODE
//            container.setVisibility(View.VISIBLE);
////NEW CODE ENDED
//
//            if (open_av.equals("0")) {
//                selectedType = 1;
//                closeTextView.setTextColor(getResources().getColor(R.color.md_white_1000));
//                closeTextView.setBackgroundColor(getResources().getColor(R.color.usedred));
////                open_game.setTextColor(getResources().getColor(R.color.font));
////                open_game.setBackgroundColor(getResources().getColor(R.color.white));
//                openTextView.setVisibility(View.GONE);
//
//            }
//
//        } else {
//            //    title.setVisibility(View.GONE);
//            type.setVisibility(View.GONE);
//            type_container.setVisibility(View.GONE);
//        }
//
//        openTextView.setOnClickListener(v -> {
//            selectedType = 0;
//            openTextView.setTextColor(getResources().getColor(R.color.md_white_1000));
//            openTextView.setBackgroundColor(getResources().getColor(R.color.usedred));
//            closeTextView.setTextColor(getResources().getColor(R.color.font));
//            closeTextView.setBackgroundColor(getResources().getColor(R.color.white));
////
//
//            if (!open_av.equals("1")) {
//                fillnumber.clear();
//                fillamount.clear();
//                fillmarket.clear();
//                AdapterSingleGames rc = new AdapterSingleGames(openclose.this, fillnumber, fillamount, fillmarket);
//                recyclerview.setLayoutManager(new GridLayoutManager(openclose.this, 1));
//                recyclerview.setAdapter(rc);
//                rc.notifyDataSetChanged();
//
//
//                if (fillmarket.size() > 0) {
//                    digit_header.setVisibility(View.VISIBLE);
//                } else {
//                    digit_header.setVisibility(View.GONE);
//                }
//
//                submit.setBackground(getResources().getDrawable(R.drawable.sub_ax));
//                submit.setText("Bid close");
//
////
//            }
//        });
//
//        closeTextView.setOnClickListener(v -> {
//            selectedType = 1;
//            closeTextView.setTextColor(getResources().getColor(R.color.md_white_1000));
//            closeTextView.setBackgroundColor(getResources().getColor(R.color.usedred));
//            openTextView.setTextColor(getResources().getColor(R.color.font));
//            openTextView.setBackgroundColor(getResources().getColor(R.color.white));
//
//        });




        // Find views
        TextView openTextView = findViewById(R.id.openTextView);
        TextView closeTextView = findViewById(R.id.closeTextView);

        // Set click listeners
        openTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your code for "OPEN" click action here
                // For example, you can perform some action or dismiss the dialog
                finish(); // Finish the activity to close the dialog
            }
        });

       closeImageView = findViewById(R.id.cross);

        // Set an OnClickListener for the 'X' ImageView
        closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the dialog
                finish();
            }
        });


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

    private void initViews() {
        openTextView = findViewById(R.id.openTextView);
        closeTextView = findViewById(R.id.closeTextView);
    }
}
