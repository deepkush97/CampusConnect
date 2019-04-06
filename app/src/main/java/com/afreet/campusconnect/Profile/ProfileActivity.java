package com.afreet.campusconnect.Profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afreet.campusconnect.Home.HomeActivity;
import com.afreet.campusconnect.Login.LoginActivity;
import com.afreet.campusconnect.Notice.NoticeActivity;
import com.afreet.campusconnect.Offer.OfferActivity;
import com.afreet.campusconnect.R;
import com.afreet.campusconnect.Share.ShareActivity;
import com.afreet.campusconnect.Utils.AppUtils;
import com.afreet.campusconnect.Utils.CustomProgressDialog;
import com.afreet.campusconnect.Utils.SharedPreferenceConfig;
import com.afreet.campusconnect.Utils.UniversalImageLoader;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 4;
    private TabLayout tabLayout;
    private Context mContext = ProfileActivity.this;

    private String currentUserId;
    private SharedPreferenceConfig preferenceConfig;
    private AppCompatButton btnSignOut;
    private TextView tvAboutMe;
    private CircleImageView profilePic;
    private TextView profileName;
    private TextView profileDept;
    private TextView profileErNo;
    private RelativeLayout rlAboutMe;

    private CustomProgressDialog progressDialog = CustomProgressDialog.getInstance();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        currentUserId = preferenceConfig.readLoginUserId();
        tvAboutMe = (TextView) findViewById(R.id.aboutMe);
        profilePic = (CircleImageView) findViewById(R.id.profilePic);
        profileName = (TextView) findViewById(R.id.profileName);
        profileDept = (TextView) findViewById(R.id.profileDept);
        profileErNo = (TextView) findViewById(R.id.profileErNo);
        rlAboutMe = (RelativeLayout) findViewById(R.id.rl_aboutMe);
        rlAboutMe.setVisibility(View.GONE);
        btnSignOut = (AppCompatButton) findViewById(R.id.btn_signOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        mQueue = Volley.newRequestQueue(this);
        setupBottomNavigation();

        getUser();
    }


    private void getUser() {


        progressDialog.showProgress(this, "Loading...", false);

        String url = AppUtils.getCollegeHttpsURL()+"campusconnect/a/get_stu_details.php?userid="+currentUserId;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            int status = response.getInt("status");
                            if(status == 1){
                                JSONObject message = response.getJSONObject("message");
                                String enroll = message.getString("enroll").trim();
                                String name = message.getString("name").trim();
//                                String semester = message.getString("semester").trim();
                                String img = AppUtils.getCollegeHttpsURL()+"studentpics/" + message.getString("img").trim();
                                String dept = message.getString("dept").trim();
//                                String emailid = message.getString("emailid").trim();
//                                String mobileno = message.getString("mobileno").trim();
//                                String dob = message.getString("dob").trim();

                                UniversalImageLoader.setImage(img, profilePic, null, "");
//                                String sumUp = semester +"\n"+emailid +"\n" +mobileno +"\n" +dob;
                                profileName.setText(name);
                                profileDept.setText(AppUtils.deptConversion(dept));
                                profileErNo.setText(enroll);
//                                tvAboutMe.setText(sumUp);
                            }
                            progressDialog.hideProgress();
                        }catch (Exception e){
                            progressDialog.hideProgress();
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

    }

    private void signOut() {

        preferenceConfig.writeLoginStatus(false, getResources().getString(R.string.user_id_placeholder));
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    //Function for the making the bottom navigation Bar in each main Activities.
    private void setupBottomNavigation() {
        tabLayout = (TabLayout) findViewById(R.id.bottom_navigation_bar);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_notices),0);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_offers),1);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_feeds),2);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_share),3);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_profile),4);

        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.accent), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.accent), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.accent), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(getResources().getColor(R.color.accent), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(4).getIcon().setColorFilter(getResources().getColor(R.color.accent), PorterDuff.Mode.SRC_IN);

        tabLayout.getTabAt(ACTIVITY_NUM).select();
        tabLayout.getTabAt(ACTIVITY_NUM).getIcon().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Intent intent;
                switch (tab.getPosition()){
                    case 0:
                        intent = new Intent(mContext, NoticeActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(mContext, OfferActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(mContext, HomeActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(mContext, ShareActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(mContext, ProfileActivity.class);
                        startActivity(intent);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
