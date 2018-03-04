package example.com.doctor_guide.Adapter;

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

/**
 * Created by raju on 27-09-2017.
 */


/**
 * Created by raju on 31-07-2017.
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


public class ExtraDetailContactPersonAdapter extends RecyclerView.Adapter<ExtraDetailContactPersonAdapter.MyViewHolder> {

    Context context;
    String[] person_name;
    String[] contact_no;

    public ExtraDetailContactPersonAdapter(Context context, String[] name, String[] no) {
        this.context = context;
        person_name = name;
        contact_no = no;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_extra_contact_person, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.tv_contact_person.setText(person_name[position]);
        holder.tv_contact_no.setText(contact_no[position]);
    }


    @Override
    public int getItemCount() {
        return person_name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_contact_person, tv_contact_no;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_contact_no = (TextView) itemView.findViewById(R.id.tv_contact_no);
            tv_contact_person = (TextView) itemView.findViewById(R.id.tv_contact_person);

        }
    }
}