package com.afreet.campusconnect.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afreet.campusconnect.R;

import java.util.ArrayList;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> categories = new ArrayList<>();
    private Context mContext;

    public CategoryRecyclerViewAdapter(Context mContext, ArrayList<String> categories) {
        this.categories = categories;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cat_item, viewGroup, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textCat.setText(categories.get(i));

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textCat;
        CardView chip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textCat = itemView.findViewById(R.id.textCat);
            this.chip = itemView.findViewById(R.id.chip);

        }
    }

}

