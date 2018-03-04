package example.com.doctor_guide.Adapter;

/**
 * Created by Khodal on 19/08/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import example.com.doctor_guide.util.RoundImageView;
import example.com.doctor_guide.Model.Doctor;
import example.com.homepagefordoctor_guide.R;

/**
 * Created by Belal on 11/9/2015.
 */
public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    Context context;
    private ImageLoader imageLoader;

    private List<Doctor> doctorList = new ArrayList<>();
    ArrayList<Doctor> arrayList;


    public DoctorAdapter(Context context, List<Doctor> list) {
        this.context = context;
        this.doctorList = list;
        this.arrayList=new ArrayList<Doctor>();
        this.arrayList.addAll(doctorList);
    }
    public DoctorAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//Doctor doctor_slider = new Doctor();

        holder.textViewName.setText(doctorList.get(position).getTitle().toString());
        Glide.with(context)
                .load(doctorList.get(position).getImageurl())
                .asBitmap()
                .placeholder(R.drawable.doctor_slider)
                .into(holder.subcat_thumbnail);

//







    }

    @Override
    public int getItemCount() {
        return this.doctorList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public RoundImageView subcat_thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView)itemView.findViewById(R.id.title);
            subcat_thumbnail=(RoundImageView)itemView.findViewById(R.id.subcat_thumbnail);
        }
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        doctorList.clear();
        if (charText.length() == 0) {
            doctorList.addAll(arrayList);
        }
        else
        {
            for (Doctor wp : arrayList)
            {
                if (wp.getTitle().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    doctorList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
