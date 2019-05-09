package com.afreet.campusconnect.Profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.afreet.campusconnect.R;

public class FeedbackFragment extends Fragment {


    private RadioGroup rgOptionSelected;
    private TextInputEditText etTitle, etMessage;
    private AppCompatButton btnSubmit;
    private ImageView backBtn;
    private String optionSelected;
    private String title;
    private String message;


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        rgOptionSelected = view.findViewById(R.id.rg_optionSelected);
        etTitle = view.findViewById(R.id.et_title);
        etMessage = view.findViewById(R.id.et_message);
        btnSubmit = view.findViewById(R.id.btn_submit);
        backBtn = view.findViewById(R.id.btn_back);

        rgOptionSelected.clearCheck();
        RadioButton rb = rgOptionSelected.findViewById(R.id.feedbackSelect);
        rb.setChecked(true);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyInputs();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        return view;
    }

    private void verifyInputs() {

        int selected = rgOptionSelected.getCheckedRadioButtonId();

        switch (selected) {
            case R.id.feedbackSelect:
                optionSelected = getString(R.string.feedbackOptionSelected);
                break;
            case R.id.complaintSelect:
                optionSelected = getString(R.string.complaintOptionSelected);
                break;
        }

        title = etTitle.getText().toString();
        if (title.isEmpty()) {
            return;
        }

        message = etMessage.getText().toString();
        if (message.isEmpty()) {
            return;
        }

        Toast.makeText(getContext(), optionSelected + " : " + title + " : " + message, Toast.LENGTH_SHORT).show();
    }


}
