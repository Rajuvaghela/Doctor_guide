package example.com.doctor_guide.Adapter;

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


public class ViewAllDocRatingAdapter extends RecyclerView.Adapter<ViewAllDocRatingAdapter.MyViewHolder> {

    Context context;
    List<RatingModel> listuser = new ArrayList<>();

    public ViewAllDocRatingAdapter(Context context, List<RatingModel> listuser) {
        this.context = context;
        this.listuser = listuser;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rating, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.tv_rating_name.setText(listuser.get(position).getDr_name());
        holder.tv_mobile_no.setText(listuser.get(position).getDr_contact());
        float rating = Float.parseFloat(listuser.get(position).getDr_rating());
        holder.ratingbar_doctor.setRating(rating);
        holder.tv_rating_description.setText(listuser.get(position).getDr_desc());
        holder.tv_rating_in_number.setText(listuser.get(position).getDr_rating());
        //holder.RIV_rating_image.setText(listuser.get(position).getDr_name());

        //Glide.with(context).load()).into(holder.iv_subcategory1_image);
/*        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, YummyFoodsMoreDetailsActivity.class);
                context.startActivity(intent);
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return listuser.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingbar_doctor;
        TextView tv_mobile_no, tv_rating_name, tv_rating_description, tv_rating_in_number;
        RoundImageView RIV_rating_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_mobile_no = (TextView) itemView.findViewById(R.id.tv_mobile_no);
            tv_rating_name = (TextView) itemView.findViewById(R.id.tv_rating_name);
            RIV_rating_image = (RoundImageView) itemView.findViewById(R.id.RIV_rating_image);
            ratingbar_doctor = (RatingBar) itemView.findViewById(R.id.ratingbar_doctor);
            tv_rating_description = (TextView) itemView.findViewById(R.id.tv_rating_description);
            tv_rating_in_number = (TextView) itemView.findViewById(R.id.tv_rating_in_number);


        }
    }
}