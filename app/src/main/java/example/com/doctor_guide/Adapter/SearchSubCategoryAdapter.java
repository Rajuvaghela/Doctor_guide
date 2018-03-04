package example.com.doctor_guide.Adapter;

/**
 * Created by Khodal on 04/08/2017.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import example.com.doctor_guide.Activity.RegisterActivity;
import example.com.doctor_guide.Model.SearchSubCategory;
import example.com.homepagefordoctor_guide.R;
import example.com.doctor_guide.util.RoundImageView;


public class SearchSubCategoryAdapter extends RecyclerView.Adapter<SearchSubCategoryAdapter.ViewHolder> {

    private Context context;
    String l_id;

    private List<SearchSubCategory> subcategoryList = new ArrayList<>();
    ArrayList<SearchSubCategory> arrayList;

    /*
        public ChildListAdapter(Response.Listener<String> context, List<DoctorName> doctornameList) {

            this.context = (Context) context;
            this.doctornameList = doctornameList;
        }*/
    public SearchSubCategoryAdapter (Context context, List<SearchSubCategory> list) {
        this.context = context;
        this.subcategoryList = list;
        this.arrayList=new ArrayList<SearchSubCategory>();
        this.arrayList.addAll(subcategoryList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategory_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textViewName.setText(subcategoryList.get(position).getSubname());
        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,RegisterActivity.class);
                intent.putExtra("value", subcategoryList.get(position).getSubname());
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return this.subcategoryList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public NetworkImageView imageView;
        public RoundImageView subcat_thumbnail;
        Button btnreadmore;
        public EditText edt_selectsubcategory;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.tvSubCategory);
            edt_selectsubcategory = (EditText)itemView.findViewById(R.id.edt_selectsubcategory);



        }


    }



    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        subcategoryList.clear();
        if (charText.length() == 0) {
            subcategoryList.addAll(arrayList);
        }
        else
        {
            for (SearchSubCategory wp : arrayList)
            {
                if (wp.getSubname().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    subcategoryList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
