package example.com.doctor_guide.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import example.com.doctor_guide.Model.DetailsModel;
import example.com.homepagefordoctor_guide.R;

/**
 * Created by raju on 22-09-2017.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raju on 20-09-2017.
 */

import example.com.doctor_guide.Model.DetailsModel;
import example.com.doctor_guide.Model.DoctorFullDetailModel;
import example.com.homepagefordoctor_guide.R;


public class DoctorFullDetailVideoAdapter extends RecyclerView.Adapter<DoctorFullDetailVideoAdapter.MyViewHolder> {

    Context context;
    String[] video;

    public DoctorFullDetailVideoAdapter(Context context, String[] video) {
        this.context = context;
        this.video = video;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_video_layout_adapter, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.e("video_pos", "" + position);
        Glide.with(context).load(video[position]).placeholder(R.drawable.youtube_player).into(holder.YoutubeVideoView);

    }


    @Override
    public int getItemCount() {
        return video.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_item_name;
        ImageView YoutubeVideoView;

        public MyViewHolder(View itemView) {
            super(itemView);
            YoutubeVideoView = (ImageView) itemView.findViewById(R.id.YoutubeVideoView);
/*
            tv_item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            iv_subcategory1_image = (ImageView) itemView.findViewById(R.id.iv_subcategory1_image);
*/


        }
    }
}