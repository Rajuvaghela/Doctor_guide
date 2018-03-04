package example.com.doctor_guide.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.doctor_guide.Model.RatingModel;
import example.com.doctor_guide.util.RoundImageView;
import example.com.homepagefordoctor_guide.R;

/**
 * Created by raju on 26-09-2017.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.doctor_guide.Model.RatingModel;
import example.com.doctor_guide.util.RoundImageView;
import example.com.homepagefordoctor_guide.R;


public class DocBrocherAdapter extends RecyclerView.Adapter<DocBrocherAdapter.MyViewHolder> {

    Context context;
    List<RatingModel> listuser = new ArrayList<>();

    public DocBrocherAdapter(Context context, List<RatingModel> listuser) {
        this.context = context;
        this.listuser = listuser;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doc_brocher, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_doc_brocher_name.setText(listuser.get(position).getBrocher_pdf_name());
        //holder.RIV_rating_image.setText(listuser.get(position).getDr_name());
    }


    @Override
    public int getItemCount() {
        return listuser.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_doc_brocher_name;
        ImageView iv_download_brocher;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_doc_brocher_name = (TextView) itemView.findViewById(R.id.tv_doc_brocher_name);
            iv_download_brocher = (ImageView) itemView.findViewById(R.id.iv_download_brocher);


        }
    }
}