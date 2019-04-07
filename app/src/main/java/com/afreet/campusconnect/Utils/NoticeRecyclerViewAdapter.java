package com.afreet.campusconnect.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afreet.campusconnect.Models.Notice;
import com.afreet.campusconnect.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * It is the RecyclerViewAdapter for the notice object in order to display the notice from the
 * noticeList passed in this adapter.
 */
public class NoticeRecyclerViewAdapter extends RecyclerView.Adapter<NoticeRecyclerViewAdapter.ViewHolder> {

    private List<Notice> noticeList;
    private Context mContext;

    public NoticeRecyclerViewAdapter(Context mContext, List<Notice> noticeList) {
        this.noticeList = noticeList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_notice_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.tvUserName.setText(noticeList.get(i).getFacultyName());
        viewHolder.noticeCaption.setText(noticeList.get(i).getCaption());
        viewHolder.noticeTime.setText(noticeList.get(i).getDate());
        if(!noticeList.get(i).getLink().equals("")){
            viewHolder.noticeLink.setVisibility(View.VISIBLE);
            viewHolder.noticeLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                   mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")));
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AppUtils.noticeFileUrl()+noticeList.get(i).getLink()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    mContext.startActivity(intent);
                }

            });
        }
        else {
            viewHolder.noticeLink.setVisibility(View.GONE);
        }
        UniversalImageLoader.setImage(AppUtils.facultyImageUrl()+noticeList.get(i).getFacultyImage(), viewHolder.userProfileImage,null, "");

    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }


    /**
     * ViewHolder class for RecyclerView adapter
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView userProfileImage;
        private TextView tvUserName;
        private TextView noticeTime;
        private TextView noticeCaption;
        private AppCompatButton noticeLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userProfileImage = (CircleImageView) itemView.findViewById(R.id.userProfilePic);
            tvUserName = (TextView) itemView.findViewById(R.id.tvUsername);
            noticeTime = (TextView) itemView.findViewById(R.id.noticeTime);
            noticeCaption = (TextView) itemView.findViewById(R.id.noticeCaption);
            noticeLink = (AppCompatButton) itemView.findViewById(R.id.noticeLink);

            noticeLink.setVisibility(View.GONE);
        }
    }
}
