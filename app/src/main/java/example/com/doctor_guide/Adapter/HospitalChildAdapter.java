package example.com.doctor_guide.Adapter;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import example.com.doctor_guide.Activity.Hospitals_details_CategoryActivity;
import example.com.doctor_guide.Activity.Hospitals_fulldetails_Activity;
import example.com.doctor_guide.Model.Doctor;
import example.com.doctor_guide.Model.DoctorName;
import example.com.doctor_guide.util.Constant;
import example.com.doctor_guide.util.RoundImageView;
import example.com.homepagefordoctor_guide.R;


public class HospitalChildAdapter extends RecyclerView.Adapter<HospitalChildAdapter.ViewHolder> {

    private Context context;
    String l_id;
    private ImageLoader imageLoader;

    private List<DoctorName> doctornameList = new ArrayList<>();
    ArrayList<DoctorName> arrayList;

    /*
        public ChildListAdapter(Response.Listener<String> context, List<DoctorName> doctornameList) {

            this.context = (Context) context;
            this.doctornameList = doctornameList;
        }*/
    public HospitalChildAdapter(Context context, List<DoctorName> list) {
        this.context = context;
        this.doctornameList = list;
        this.arrayList=new ArrayList<DoctorName>();
        this.arrayList.addAll(doctornameList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_name_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.e("position", "" + position);
        Log.e("getDoctor_l_id", "" + doctornameList.get(position).getDoctor_l_id());

        Constant.h_id = doctornameList.get(position).getDoctor_l_id();
        holder.textViewName.setText(doctornameList.get(position).getName());
        holder.textViewAddress.setText(doctornameList.get(position).getAddress());


        Glide.with(context)
                .load(doctornameList.get(position).getImageurl())
                .asBitmap()
                .placeholder(R.drawable.doctor_slider)
                .into(holder.subcat_thumbnail);
        holder.btnreadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Hospitals_fulldetails_Activity.class);
                Constant.h_id = doctornameList.get(position).getDoctor_l_id();

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.doctornameList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewAddress;
        public RoundImageView subcat_thumbnail;
        Button btnreadmore;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.name);
            textViewAddress = (TextView) itemView.findViewById(R.id.address);
            btnreadmore = (Button) itemView.findViewById(R.id.btnreadmore);
            subcat_thumbnail = (RoundImageView) itemView.findViewById(R.id.thumbnail);


        }
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        doctornameList.clear();
        if (charText.length() == 0) {
            doctornameList.addAll(arrayList);
        }
        else
        {
            for (DoctorName wp : arrayList)
            {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    doctornameList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}

