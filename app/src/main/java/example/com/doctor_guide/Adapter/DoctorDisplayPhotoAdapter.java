package example.com.doctor_guide.Adapter;

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

import example.com.doctor_guide.Model.DetailsModel;
import example.com.homepagefordoctor_guide.R;

/**
 * Created by raju on 23-09-2017.
 */



public class DoctorDisplayPhotoAdapter extends RecyclerView.Adapter<DoctorDisplayPhotoAdapter.MyViewHolder> {

    Context context;
    List<DetailsModel> listuser = new ArrayList<>();

    public DoctorDisplayPhotoAdapter(Context context, List<DetailsModel> listuser) {
        this.context = context;
        this.listuser = listuser;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_doc_photo, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.e("pos", "" + position);
        Glide.with(context).load(listuser.get(position).getDoctor_gallery_image()).into(holder.iv_doctor_gallery_image);
/*

        holder.tv_item_name.setText(listuser.get(position).getSubname());
        Glide.with(context).load(listuser.get(position).getImg()).into(holder.iv_doctor_gallery_image);
*/

    }


    @Override
    public int getItemCount() {
        return listuser.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_item_name;
        ImageView iv_doctor_gallery_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_doctor_gallery_image = (ImageView) itemView.findViewById(R.id.iv_doctor_gallery_image);
/*
            tv_item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            iv_subcategory1_image = (ImageView) itemView.findViewById(R.id.iv_subcategory1_image);
*/


        }
    }
}