package com.rkboss.mtk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class signup extends AppCompatActivity {

    EditText mobile,referalcode;
    EditText name;
    EditText email;
    EditText password;
    EditText pinCode;
    TextView bcklogin;
    Button submit;
    ViewDialog progressDialog;
    String url;
    String strFcmId,strDeviceId;
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    String hash = "";

    ActivityResultLauncher<Intent> mStartForResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_signup);
        initView();
        url = constant.prefix + getString(R.string.register);
        hash = getRandomString(30);

//        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

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


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobile.getText().toString().isEmpty()) {
                    mobile.setError("Enter valid mobile number");
                } else if (name.getText().toString().isEmpty()) {
                    name.setError("Enter name");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Enter valid password");
//                } else if (pinCode.getText().toString().isEmpty()) {
//                    pinCode.setError("Enter valid 4 digit pin number");
                } else {
                    apicall();
                }

            }
        });


        bcklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this, login.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });

    }

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    private void apicall() {

        progressDialog = new ViewDialog(signup.this);
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

                                Log.d("issignup","");

                                SharedPreferences.Editor editor = getSharedPreferences(constant.prefs, MODE_PRIVATE).edit();
                                editor.putString("mobile", mobile.getText().toString()).apply();
                                editor.putString("login", "true").apply();
                                editor.putString("name", name.getText().toString()).apply();
//                                editor.putString("email", email.getText().toString()).apply();
//                                editor.putString("pin", pinCode.getText().toString()).apply();
                                editor.putString("session", hash).apply();
                                Toast.makeText(signup.this, "Account created successfully, Please login now", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(in);
                                finish();


//
//                                Intent in = new Intent(getApplicationContext(), login.class);
//                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(in);
//                                finish();

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
                        Toast.makeText(signup.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                        Log.d("mess","");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("mobile", mobile.getText().toString());
                params.put("name", name.getText().toString());
//                params.put("email", email.getText().toString());
                params.put("pass", password.getText().toString());
                params.put("refcode", referalcode.getText().toString());
                params.put("fcm_id", strFcmId);
                params.put("device_id", strDeviceId);
                Log.d("Fcm",""+strFcmId);
                Log.d("device_id",""+strDeviceId);
                params.put("session", hash);


                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    private void initView() {
        mobile = findViewById(R.id.mobile);
        name =  findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        pinCode = findViewById(R.id.pinCode);
        submit =  findViewById(R.id.submit);
        bcklogin =  findViewById(R.id.bcklogin);
        referalcode =  findViewById(R.id.referalcode);


        mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent == null) return;
                        if (intent.hasExtra("verification") && intent.getStringExtra("verification").equals("success")) {
                            apicall();
                        }
                    }
                });
    }
}
