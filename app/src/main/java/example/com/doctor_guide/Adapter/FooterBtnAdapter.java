package example.com.doctor_guide.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import example.com.doctor_guide.Model.FooterBtn;
import example.com.doctor_guide.util.RoundImageView;
import example.com.homepagefordoctor_guide.R;

/**
 * Created by Khodal on 08/09/2017.
 */

public class FooterBtnAdapter extends RecyclerView.Adapter<FooterBtnAdapter.ViewHolder> {

    private Context context;


    private List<FooterBtn> footeritemList = new ArrayList<>();


    public FooterBtnAdapter(Context context, List<FooterBtn> list) {
        this.context = context;
        this.footeritemList = list;
    }

    @Override
    public FooterBtnAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.footer_btn_first_page, parent, false);
        FooterBtnAdapter.ViewHolder viewHolder = new FooterBtnAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FooterBtnAdapter.ViewHolder holder, final int position) {
//        Log.e("position", ""+position);
//        Log.e("getDoctor_l_id",""+footeritemList .get(position).getDoctor_l_id());

//        Constant.d_id = footeritemList.get(position).getDoctor_l_id();
        holder.textViewName.setText(footeritemList.get(position).getName());
        holder.textViewDescription.setText(footeritemList.get(position).getDescription());
        Glide.with(context)
                .load(footeritemList.get(position).getImageurl())
                .asBitmap()
                .placeholder(R.drawable.doctor_slider)
                .into(holder.footerimageview);
//        holder.btnmoredetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,DoctorFullDetailActivity.class);
////                Constant.d_id = footeritemList.get(position).getDoctor_l_id();
//                context.startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return this.footeritemList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName, textViewDescription;

        public RoundImageView footerimageview;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.footer_item_name);
            textViewDescription = (TextView) itemView.findViewById(R.id.footer_item_description);
            footerimageview = (RoundImageView) itemView.findViewById(R.id.footer_imageview);


        }
    }


}


