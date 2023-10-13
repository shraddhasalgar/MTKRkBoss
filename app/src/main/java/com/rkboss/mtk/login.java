package com.rkboss.mtk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rkboss.mtk.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class login extends AppCompatActivity {

Button changeMylang;
    private String currentLanguage = "en";
    protected EditText mobile;
    protected EditText password;
    Button submit;
    TextView create;
//    protected TextView forgot;

    ViewDialog progressDialog;
    String url;

    String strFcmId, strDeviceId;

    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";


    String hash = "";
    ActivityResultLauncher<Intent> mStartForResult;

    String name = "", email = "";
//    private eczarbold logo;
//    private latobold whatsapp;
//    private latobold otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        super.setContentView(R.layout.activity_login);
        initViews();
        initView();
        url = constant.prefix + getString(R.string.login);
        hash = getRandomString(30);

        Button changeLang = findViewById(R.id.changeMylang);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showChangeLanguageDialog();

            }
        });





        FirebaseApp.initializeApp(this);


        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String token) {
                        strFcmId = token;
                        Log.e("Token", strFcmId);
                    }
                });

        strDeviceId = android.provider.Settings.Secure.getString(
                getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID
        );

        Log.e("DeviceId", strDeviceId);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, signup.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

//        forgot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = constant.getWhatsapp(getApplicationContext());
//
//                Uri uri = Uri.parse(url);
//                Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(sendIntent);
//            }
//        });
//        whatsapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = constant.getWhatsapp(getApplicationContext());
//
//                Uri uri = Uri.parse(url);
//                Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(sendIntent);
//            }
//        });
//        otp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(login.this, ForgotPassword.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//            }
//        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobile.getText().toString().isEmpty()) {
                    mobile.setError("Enter mobile number");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Enter password");
                } else {

                    SharedPreferences.Editor editor = getSharedPreferences(constant.prefs, MODE_PRIVATE).edit();
                    editor.putString("session", hash).apply();

                    apicall();
                }
            }
        });
    }

    private void showChangeLanguageDialog() {

        final String[] listItems = {"English", "हिंदी", "मराठी", "ગુજરાતી", "தமிழ்"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(login.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocale("en");
                    recreate();
                } else if (i == 1) {
                    setLocale("hi");

                    recreate();
                } else if (i == 2) {
                    setLocale("mr");

                    recreate();
                } else if (i == 3) {
                    setLocale("gu");

                    recreate();
                } else if (i == 4) {
                    setLocale("ta");

                    recreate();
                }

                //dismiss alert dailog
                dialogInterface.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale= locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings",MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

    private static String getRandomString(final int sizeOfRandomString) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    private void apicall() {

        progressDialog = new ViewDialog(login.this);
        progressDialog.showDialog();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        final StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hideDialog();
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            if (jsonObject1.getString("success").equalsIgnoreCase("1")) {
                                name = jsonObject1.getString("name");
                                email = jsonObject1.getString("email");

                                // mStartForResult.launch(new Intent(login.this, OTPVerification.class).putExtra("mobile", mobile.getText().toString()));
                                SharedPreferences.Editor editor = getSharedPreferences(constant.prefs, MODE_PRIVATE).edit();
                                editor.putString("mobile", mobile.getText().toString()).apply();
                                editor.putString("login", "true").apply();
                                editor.putString("name", name).apply();
                                editor.putString("email", email).apply();

                                Intent in = new Intent(getApplicationContext(), MainActivity.class);
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
                        Toast.makeText(login.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("mobile", mobile.getText().toString());
                params.put("pass", password.getText().toString());
                params.put("fcm_id", strFcmId);
                params.put("device_id", strDeviceId);
                Log.d("Fcm", "" + strFcmId);
                Log.d("device_id", "" + strDeviceId);
                params.put("session", hash);
                Log.d("sess", "" + hash);

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }


    private void initView() {

        mobile = (EditText) findViewById(R.id.mobile);
        password = (EditText) findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        create = findViewById(R.id.create);
//        forgot = (TextView) findViewById(R.id.forgot);
        mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent == null) return;
                        if (intent.hasExtra("verification") && intent.getStringExtra("verification").equals("success")) {
                            SharedPreferences.Editor editor = getSharedPreferences(constant.prefs, MODE_PRIVATE).edit();
                            editor.putString("mobile", mobile.getText().toString()).apply();
                            editor.putString("login", "true").apply();
                            editor.putString("name", name).apply();
                            editor.putString("email", email).apply();

                            Intent in = new Intent(getApplicationContext(), MainActivity.class);
                            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(in);
                            finish();
                        }
                    }
                });
    }

    private void initViews() {
//        logo = findViewById(R.id.logo);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        create = findViewById(R.id.create);
//        forgot = findViewById(R.id.forgot);
//        whatsapp = findViewById(R.id.whatsapp);
//        otp = findViewById(R.id.otp2);
    }
}
