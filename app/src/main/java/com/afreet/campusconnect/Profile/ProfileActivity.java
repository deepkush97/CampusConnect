package com.afreet.campusconnect.Profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class ProfileActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private static final int ACTIVITY_NUM = 4;
    private TabLayout tabLayout;
    private Context mContext = ProfileActivity.this;

    private String currentUserId;
    private SharedPreferenceConfig preferenceConfig;
    private TextView tvAboutMe;
    private CircleImageView profilePic;
    private TextView profileName;
    private TextView profileDept;
    private TextView profileErNo;
    private RelativeLayout rlAboutMe;
    private ImageView btnOptions;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView navProfileName;
    private CircleImageView navProfilePic;

    private CustomProgressDialog progressDialog = CustomProgressDialog.getInstance();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        currentUserId = preferenceConfig.readLoginUserId();
        if(!preferenceConfig.readLoginStatus()){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            finish();
            startActivity(intent);
        }
        tvAboutMe = (TextView) findViewById(R.id.aboutMe);
        profilePic = (CircleImageView) findViewById(R.id.profilePic);
        profileName = (TextView) findViewById(R.id.profileName);
        profileDept = (TextView) findViewById(R.id.profileDept);
        profileErNo = (TextView) findViewById(R.id.profileErNo);
        rlAboutMe = (RelativeLayout) findViewById(R.id.rl_aboutMe);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        rlAboutMe.setVisibility(View.GONE);
        btnOptions = (ImageView) findViewById(R.id.btn_options);
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });
        View headerView = navigationView.getHeaderView(0);
        navProfileName = (TextView) headerView.findViewById(R.id.nav_username);
        navProfilePic = (CircleImageView) headerView.findViewById(R.id.nav_profilePic);
        navProfilePic.setImageDrawable(getDrawable(R.drawable.ic_profile));
        navProfileName.setText("dfhsdkfj");

        mQueue = Volley.newRequestQueue(this);
        setupBottomNavigation();
        navigationView.setNavigationItemSelectedListener(this);
        getUser();
    }

    private void openDrawer() {
        //  Snackbar.make(getCurrentFocus(), "awesome",Snackbar.LENGTH_LONG).show();
        if(drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        }else
            drawer.openDrawer(GravityCompat.END);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
                                UniversalImageLoader.setImage(img, navProfilePic, null, "");
//                                String sumUp = semester +"\n"+emailid +"\n" +mobileno +"\n" +dob;
                                profileName.setText(name);
                                navProfileName.setText(name);
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



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Log.d("Hero", "onNavigationItemSelected: "+item.toString());

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent optionIntent = new Intent(getApplicationContext(), OptionsActivity.class);
        switch (id){
            case R.id.nav_account_management:
                optionIntent.putExtra(getString(R.string.fragment_name),getString(R.string.fragment_account_management));
                break;
            case R.id.nav_edit_category:
                optionIntent.putExtra(getString(R.string.fragment_name),getString(R.string.fragment_edit_categories));
                break;
            case R.id.nav_signout:
                optionIntent.putExtra(getString(R.string.fragment_name),getString(R.string.fragment_sign_out));
                break;
            case R.id.nav_feedback:
                optionIntent.putExtra(getString(R.string.fragment_name),getString(R.string.fragment_feedback));
                break;
            case R.id.nav_tutorials:
                optionIntent.putExtra(getString(R.string.fragment_name),getString(R.string.fragment_how_to_use));
                break;
            case R.id.nav_terms_conditions:
                optionIntent.putExtra(getString(R.string.fragment_name),getString(R.string.fragment_terms_and_condition));
                break;
            case R.id.nav_about:
                optionIntent.putExtra(getString(R.string.fragment_name),getString(R.string.fragment_about));
                break;
                default:
                    optionIntent.putExtra(getString(R.string.fragment_name),getString(R.string.fragment_about));
        }


        startActivity(optionIntent);
//        if(item.hasSubMenu()){
//            for(int i =0;i<item.getSubMenu().size();i++){
//                item.getSubMenu().getItem(i).setChecked(false);
//            }
//        }
//        else {
//            item.setChecked(false);
//        }
        drawer.closeDrawer(GravityCompat.END);
        return true;
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
