package example.com.doctor_guide.Adapter;

/**
 * Created by Khodal on 04/08/2017.
 */


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
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import example.com.doctor_guide.Activity.DoctorFullDetailActivity;
import example.com.doctor_guide.Model.DoctorName;
import example.com.homepagefordoctor_guide.R;
import example.com.doctor_guide.util.Constant;
import example.com.doctor_guide.util.RoundImageView;


public class DoctorChildAdapter extends RecyclerView.Adapter<DoctorChildAdapter.ViewHolder> {

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
    public DoctorChildAdapter(Context context, List<DoctorName> list) {
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

        Constant.d_id = doctornameList.get(position).getDoctor_l_id();
        holder.textViewName.setText(doctornameList.get(position).getName());
        holder.textViewAddress.setText(doctornameList.get(position).getAddress());
        holder.textViewMName.setText(doctornameList.get(position).getMname());
        holder.textViewLName.setText(doctornameList.get(position).getLname());
        Glide.with(context)
                .load(doctornameList.get(position).getImageurl())
                .asBitmap()
                .placeholder(R.drawable.doctor_slider)
                .into(holder.subcat_thumbnail);
        holder.btnreadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DoctorFullDetailActivity.class);
                Constant.d_id = doctornameList.get(position).getDoctor_l_id();

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.doctornameList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName, textViewAddress, textViewMName, textViewLName;
        public NetworkImageView imageView;
        public RoundImageView subcat_thumbnail;
        Button btnreadmore;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.name);
            textViewMName = (TextView) itemView.findViewById(R.id.mname);
            textViewLName = (TextView) itemView.findViewById(R.id.lname);
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
