package com.afreet.campusconnect.Share;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.afreet.campusconnect.Home.HomeActivity;
import com.afreet.campusconnect.Notice.NoticeActivity;
import com.afreet.campusconnect.Offer.OfferActivity;
import com.afreet.campusconnect.Profile.ProfileActivity;
import com.afreet.campusconnect.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;
import java.util.List;

public class ShareActivity extends AppCompatActivity {

    private static final int ACTIVITY_NUM = 3;
    private static final int PICK_IMAGE = 2;
    private TabLayout tabLayout;
    private Context mContext = ShareActivity.this;

    private Spinner spinnerUserCategory;
    private EditText inputPostCaption, inputPostLink;
    private RelativeLayout btnSelectImage;
    private ImageView ivSelectedImage;
    private FloatingActionButton fabPublishPost;

    private int imageSelect = 0;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        spinnerUserCategory = (Spinner) findViewById(R.id.spinner_userCategory);
        inputPostCaption = (EditText) findViewById(R.id.input_postCaption);
        inputPostLink = (EditText) findViewById(R.id.input_postLink);
        btnSelectImage = (RelativeLayout) findViewById(R.id.btn_selectImage);
        ivSelectedImage = (ImageView) findViewById(R.id.iv_selectedImage);
        fabPublishPost = (FloatingActionButton) findViewById(R.id.fab_publishPost);
        setupBottomNavigation();
        initWidgets();

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"Select Image"),PICK_IMAGE);
            }
        });

        fabPublishPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyPost();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){

            imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                ivSelectedImage.setVisibility(View.VISIBLE);
                ivSelectedImage.setImageURI(imageUri);
                imageSelect = 1;
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void verifyPost() {
        if(spinnerUserCategory.getSelectedItem().toString().equals("Select")){
            Snackbar.make(getCurrentFocus(), "Select a Category.", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(inputPostCaption.getText().toString().isEmpty() || inputPostCaption.getText().toString().length()<=40){
            Snackbar.make(getCurrentFocus(), "Write a caption of post (minimum 40 characters).", Snackbar.LENGTH_SHORT).show();
            inputPostCaption.requestFocus();
            return;
        }
        if(!inputPostLink.getText().toString().isEmpty()) {
            if (!Patterns.WEB_URL.matcher(inputPostLink.getText().toString()).matches()) {
                Snackbar.make(getCurrentFocus(), "Enter a valid Link Url.", Snackbar.LENGTH_SHORT).show();
                return;
            }
        }

        publishPost();

    }

    private void publishPost() {
        Snackbar.make(getCurrentFocus(), "Validation Successful", Snackbar.LENGTH_SHORT).show();

    }

    private void initWidgets() {
        List<String> dummy = new ArrayList<>();
        dummy.add("Select");
        dummy.add("Cat 1");
        dummy.add("Cat 2");
        dummy.add("Cat 3");
        dummy.add("Cat 4");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dummy);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUserCategory.setAdapter(adapter);

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
