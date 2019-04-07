package com.afreet.campusconnect.Notice;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.afreet.campusconnect.Home.HomeActivity;
import com.afreet.campusconnect.Models.Notice;
import com.afreet.campusconnect.Offer.OfferActivity;
import com.afreet.campusconnect.Profile.ProfileActivity;
import com.afreet.campusconnect.R;
import com.afreet.campusconnect.Share.ShareActivity;
import com.afreet.campusconnect.Utils.AppUtils;
import com.afreet.campusconnect.Utils.CustomProgressDialog;
import com.afreet.campusconnect.Utils.NoticeRecyclerViewAdapter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 0;
    private TabLayout tabLayout;
    private Context mContext = NoticeActivity.this;

    private RequestQueue mQueue;
    private List<Notice> noticeList;

    private Spinner deptSpinner;
    private List<String> deptList;
    private List<String> strDeptList;
    private String currentDeptSelected;
    private int page;
    private boolean prevClickable, nextClickable;

    private FloatingActionButton fabPrev, fabNext;


    private RecyclerView noticeRecyclerView;
    private NoticeRecyclerViewAdapter noticeRecyclerViewAdapter;
    private CustomProgressDialog progressDialog = CustomProgressDialog.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        setupBottomNavigation();

        page =1;
        noticeList = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);

        deptSpinner = (Spinner) findViewById(R.id.deptSpinner);
        noticeRecyclerView = (RecyclerView) findViewById(R.id.noticeRecyclerView);
        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        fabPrev = (FloatingActionButton) findViewById(R.id.fabPrev);


        initDeptSpinner();
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                page = 0;
                fabPrev.setImageDrawable(getDrawable(R.drawable.ic_prev_disabled));
                fabNext.setImageDrawable(getDrawable(R.drawable.ic_next));
                prevClickable = false;
                nextClickable = true;
                currentDeptSelected = deptList.get(i);
                jsonParse(currentDeptSelected, page);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fabPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page==0 && prevClickable){
                    prevClickable = false;
                    fabPrev.setImageDrawable(getDrawable(R.drawable.ic_prev_disabled));
                }
                if(prevClickable && page>0){
                    fabNext.setImageDrawable(getDrawable(R.drawable.ic_next));
                    nextClickable = true;
                    page--;
                    jsonParse(currentDeptSelected,page);
                    Log.d("fabPrev", "onClick: "+ prevClickable+" "+ page);

                }
                else {

                    Log.d("fabPrev", "onClick: "+ prevClickable+" "+ page);
                    fabPrev.setImageDrawable(getDrawable(R.drawable.ic_prev_disabled));
                    prevClickable = false;
                }
            }
        });
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nextClickable){
                    page++;
                    prevClickable = true;
                    fabPrev.setImageDrawable(getDrawable(R.drawable.ic_prev));
                    jsonParse(currentDeptSelected, page);

                    Log.d("fabNext", "onClick: "+ nextClickable+" "+ page);
                }
                else {
                    fabNext.setImageDrawable(getDrawable(R.drawable.ic_next_disabled));
                }
            }
        });

    }


    private void jsonParse(String dept, int page) {

        progressDialog.showProgress(this, "Loading...", false);
        String url = AppUtils.getCollegeHttpsURL()+ "campusconnect/a/get_notices.php?dept="+dept+"&page="+page;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        noticeList.clear();
                        if(response.length()==0){
                            Snackbar.make(getCurrentFocus(), "No More Data Available",Snackbar.LENGTH_LONG).show();
                            nextClickable = false;
                            fabNext.setImageDrawable(getDrawable(R.drawable.ic_next_disabled));
                            progressDialog.hideProgress();
                        }
                        else {

                            try {

                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonNotice = response.getJSONObject(i);
                                    Notice newNotice = new Notice();

                                    newNotice.setNoticeId(jsonNotice.getString("noticeid"));
                                    newNotice.setFacultyName(jsonNotice.getString("name"));
                                    newNotice.setFacultyImage(jsonNotice.getString("img_url"));
                                    newNotice.setUserId(jsonNotice.getString("userid"));
                                    newNotice.setCaption(jsonNotice.getString("caption"));
                                    if (jsonNotice.getString("link") != null && !jsonNotice.getString("link").isEmpty()) {
                                        newNotice.setLink(jsonNotice.getString("link"));
                                    } else {
                                        newNotice.setLink("");
                                    }
                                    newNotice.setDept(jsonNotice.getString("dept"));
                                    newNotice.setDate(jsonNotice.getString("date"));
                                    noticeList.add(newNotice);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        initRecyclerView();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        mQueue.add(request);
    }

    private void initRecyclerView(){

        try {
            Collections.sort(noticeList, new Comparator<Notice>() {
                @Override
                public int compare(Notice o1, Notice o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

        noticeRecyclerViewAdapter = new NoticeRecyclerViewAdapter(this, noticeList);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        noticeRecyclerView.setLayoutManager(linearLayoutManager);
        noticeRecyclerView.setAdapter(noticeRecyclerViewAdapter);

        progressDialog.hideProgress();

    }


    private void initDeptSpinner() {
        deptList = AppUtils.getDepartments();
        strDeptList = new ArrayList<>();

        for (int i = 0; i<deptList.size();i++){
            strDeptList.add(AppUtils.deptConversion(deptList.get(i)));
        }

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, strDeptList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deptSpinner.setAdapter(aa);
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
