package com.afreet.campusconnect.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afreet.campusconnect.Login.LoginActivity;
import com.afreet.campusconnect.R;
import com.afreet.campusconnect.Utils.SharedPreferenceConfig;

public class TermsConditionFragment extends Fragment {


    private AppCompatButton btnDone;
    private ImageView btnBack;
    private CheckBox checkBoxAgree;
    private RelativeLayout relativeLayoutBottom;
    private String from;
    private boolean agree = true;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            from = getArguments().getString("from");
        }
        View view = inflater.inflate(R.layout.fragment_terms_condition, container, false);

        btnDone = view.findViewById(R.id.btn_done);
        checkBoxAgree = view.findViewById(R.id.checkbox_agree);
        relativeLayoutBottom = view.findViewById(R.id.relLayoutBottom);
        btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
//        if (from.equals("OA")){
//            relativeLayoutBottom.setVisibility(View.INVISIBLE);
//        }

        //OA will be changed into other activity code
        if (from.equals("OA")) {
            relativeLayoutBottom.setVisibility(View.VISIBLE);
            agree = false;
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxAgree.isChecked()) {
                    //Pass to the another activity
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Tick the checkbox and then move next", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
