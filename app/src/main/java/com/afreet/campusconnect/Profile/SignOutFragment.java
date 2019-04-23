package com.afreet.campusconnect.Profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afreet.campusconnect.Login.LoginActivity;
import com.afreet.campusconnect.R;
import com.afreet.campusconnect.Utils.SharedPreferenceConfig;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignOutFragment extends Fragment {

    private SharedPreferenceConfig preferenceConfig;
    private AppCompatButton btnConfirm, btnCancel;


    public SignOutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        preferenceConfig = new SharedPreferenceConfig(getActivity().getApplicationContext());

        View view = inflater.inflate(R.layout.fragment_sign_out, container, false);

        btnCancel = (AppCompatButton) view.findViewById(R.id.cancelBtn);
        btnConfirm = (AppCompatButton) view.findViewById(R.id.confirmBtn);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        return view;
    }

    private void signOut() {

        preferenceConfig.writeLoginStatus(false, getResources().getString(R.string.user_id_placeholder));
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getActivity().finish();
        startActivity(intent);
    }


}
