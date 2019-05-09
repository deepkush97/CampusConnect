package com.afreet.campusconnect.Profile;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.afreet.campusconnect.R;
import com.afreet.campusconnect.Utils.AppUtils;
import com.afreet.campusconnect.Utils.UniversalImageLoader;

public class AboutFragment extends Fragment {

    private ImageView ivCollegeLogo, btnBack;
    private AppCompatButton btnOpenSource;
    private Context context = getActivity();

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        ivCollegeLogo = view.findViewById(R.id.iv_collegeLogo);
        btnBack = view.findViewById(R.id.btn_back);
        btnOpenSource = view.findViewById(R.id.btn_openSource);

        UniversalImageLoader.setImage(AppUtils.getCollegeLogo(), ivCollegeLogo, null, "");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        btnOpenSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog();
            }
        });


        return view;
    }

    private void initDialog() {
        String[] libraryList = {
                "com.android.support:appcompat-v7",
                "com.android.support:support-v4",
                "com.android.support:design",
                "de.hdodenhof:circleimageview",
                "com.nostra13.universalimageloader:universal-image-loader",
                "com.android.volley:volley",
        };

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_open_source_dialog);
        ListView listView = dialog.findViewById(R.id.listview_libraries);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, libraryList);

        listView.setAdapter(arrayAdapter);


        dialog.show();
    }


}
