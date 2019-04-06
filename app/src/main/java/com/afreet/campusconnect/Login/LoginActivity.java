package com.afreet.campusconnect.Login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afreet.campusconnect.Home.HomeActivity;
import com.afreet.campusconnect.R;
import com.afreet.campusconnect.Utils.AppUtils;
import com.afreet.campusconnect.Utils.CustomProgressDialog;
import com.afreet.campusconnect.Utils.SharedPreferenceConfig;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * LoginActivity being the activity which will be called whenever their is no user logged in
 * and when user is signed Out.
 */
public class LoginActivity extends AppCompatActivity {

    private SharedPreferenceConfig preferenceConfig;
    private TextInputEditText inputUserId, inputUserPassword;
    private AppCompatButton btnLoginUser;

    private CustomProgressDialog progressDialog = CustomProgressDialog.getInstance();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUserId = (TextInputEditText) findViewById(R.id.input_userId);
        inputUserPassword = (TextInputEditText) findViewById(R.id.input_userPassword);

        //If user hasn't logged in, the login activity will be called
        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        if(preferenceConfig.readLoginStatus()){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        mQueue = Volley.newRequestQueue(this);

        btnLoginUser = (AppCompatButton) findViewById(R.id.btn_loginUser);
        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }

    /**
     * For validating the details for logging in and loggin in.
     */
    private void loginUser() {


        progressDialog.showProgress(this, "Loading...", false);
        final String userId = inputUserId.getText().toString();
        final String userPassword = inputUserPassword.getText().toString();

        String url = AppUtils.getCollegeHttpsURL()+ "campusconnect/a/login_user.php?username="+userId+"&password="+userPassword;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            int status = 0;
                            status = response.getInt("status");
                            String message = response.getString("message");

                            if(status ==1) {
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                preferenceConfig.writeLoginStatus(true, userId);
                                finish();
                            }
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                        }catch (Exception e){
                            Log.d("Exception :", "onResponse: "+ e.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR: ", "onErrorResponse: "+error.toString() );
                    }
                });
        mQueue.add(request);
        progressDialog.hideProgress();
    }
}
