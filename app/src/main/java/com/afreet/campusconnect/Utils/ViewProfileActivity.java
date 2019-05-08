package com.afreet.campusconnect.Utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afreet.campusconnect.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfileActivity extends AppCompatActivity {

    private String userId = null;
    private String userType = null;

    private CircleImageView profilePic;
    private TextView firstTitle;
    private TextView firstSubTitle;
    private TextView secondSubTitle;
    private ImageView backBtn;


    private CustomProgressDialog progressDialog = CustomProgressDialog.getInstance();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        Bundle args = getIntent().getExtras();

        if (args != null) {
            userId = args.getString("userId");
            userType = args.getString("userType");
        }

        profilePic = findViewById(R.id.profilePic);
        firstTitle = findViewById(R.id.firstTitle);
        firstSubTitle = findViewById(R.id.firstSubTitle);
        secondSubTitle = findViewById(R.id.secondSubTitle);
        backBtn = findViewById(R.id.btn_back);

        mQueue = Volley.newRequestQueue(this);

        if (userType.equals(getString(R.string.studentUserType))) {
            getStudentUser();
        } else if (userType.equals(getString(R.string.facultyUserType))) {
            getFacultyUser();
        } else {
            finish();
        }
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void getFacultyUser() {
        progressDialog.showProgress(this, "Loading...", false);

        String url = AppUtils.getCollegeHttpsURL() + "campusconnect/a/get_faculty_details.php?userid=" + userId;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int status = response.getInt("status");
                            if (status == 1) {
                                JSONObject message = response.getJSONObject("message");
                                String email = message.getString("email").trim();
                                String name = message.getString("name").trim();
                                String img = AppUtils.facultyImageUrl() + message.getString("img_url").trim();
                                String dept = message.getString("dept").trim();
                                String designation = message.getString("designation").trim();

                                UniversalImageLoader.setImage(img, profilePic, null, "");
                                firstTitle.setText(name);
                                firstSubTitle.setText(designation);
                                secondSubTitle.setText(AppUtils.deptConversion(dept));

                            }
                            progressDialog.hideProgress();
                        } catch (Exception e) {
                            progressDialog.hideProgress();
                            Log.d("Exception :", "onResponse: " + e.getMessage());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR: ", "onErrorResponse: " + error.toString());
                    }
                });
        mQueue.add(request);

    }


    private void getStudentUser() {


        progressDialog.showProgress(this, "Loading...", false);

        String url = AppUtils.getCollegeHttpsURL() + "campusconnect/a/get_stu_details.php?userid=" + userId;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int status = response.getInt("status");
                            if (status == 1) {
                                JSONObject message = response.getJSONObject("message");
                                String enroll = message.getString("enroll").trim();
                                String name = message.getString("name").trim();
                                String semester = message.getString("semester").trim();
                                String img = AppUtils.studentImageUrl() + message.getString("img").trim();
                                String dept = message.getString("dept").trim();
//                                String emailid = message.getString("emailid").trim();
//                                String mobileno = message.getString("mobileno").trim();
//                                String dob = message.getString("dob").trim();

                                UniversalImageLoader.setImage(img, profilePic, null, "");
//                                String sumUp = semester +"\n"+emailid +"\n" +mobileno +"\n" +dob;
                                firstTitle.setText(name);
                                firstSubTitle.setText(AppUtils.deptConversion(dept));
                                secondSubTitle.setText(AppUtils.semesterConversion(semester));
//                                tvAboutMe.setText(sumUp);
                            }
                            progressDialog.hideProgress();
                        } catch (Exception e) {
                            progressDialog.hideProgress();
                            Log.d("Exception :", "onResponse: " + e.getMessage());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR: ", "onErrorResponse: " + error.toString());
                    }
                });
        mQueue.add(request);
    }

}
