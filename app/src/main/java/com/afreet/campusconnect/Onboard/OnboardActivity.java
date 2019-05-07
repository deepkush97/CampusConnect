package com.afreet.campusconnect.Onboard;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afreet.campusconnect.R;

public class OnboardActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotsLayout;

    private TextView[] mDots;

    private SlideAdapter slideAdapter;

    private TextView btnPrev, btnNext;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        slideViewPager = findViewById(R.id.slideViewPager);
        dotsLayout = findViewById(R.id.dotsLayout);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);

        slideAdapter = new SlideAdapter(this);
        slideViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(currentPage + 1);
                if (currentPage == 4) {
                    finish();
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(currentPage - 1);
            }
        });

    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[5];
        dotsLayout.removeAllViews();

        for (int i = 0; i < 5; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.accent));

            dotsLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.primaryDark));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;

            if (i == 0) {
                btnNext.setClickable(true);
                btnPrev.setClickable(false);
                btnPrev.setVisibility(View.INVISIBLE);

                btnNext.setText("Next");
                btnPrev.setText("");
            } else if (i == mDots.length - 1) {
                btnNext.setClickable(true);
                btnPrev.setClickable(true);
                btnPrev.setVisibility(View.VISIBLE);

                btnNext.setText("FINISH");
                btnPrev.setText("Prev");
            } else {
                btnNext.setClickable(true);
                btnPrev.setClickable(true);
                btnPrev.setVisibility(View.VISIBLE);

                btnNext.setText("Next");
                btnPrev.setText("Prev");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
