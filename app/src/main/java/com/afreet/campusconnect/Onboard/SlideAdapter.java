package com.afreet.campusconnect.Onboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.afreet.campusconnect.R;
import com.nostra13.universalimageloader.utils.L;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context) {
        this.context = context;
    }

    // Arrays for the onboarding Slide
    public int[] slide_images = {
            R.drawable.tut_screen1,
            R.drawable.tut_screen2,
            R.drawable.tut_screen3,
            R.drawable.tut_screen4,
            R.drawable.tut_screen5


    };

    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_onboard_slide, container, false);

        ImageView slideImage = view.findViewById(R.id.slide_image);

        slideImage.setImageResource(slide_images[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout) object);
    }
}
