package example.com.doctor_guide.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import example.com.homepagefordoctor_guide.R;

/**
 * Created by Khodal on 21/09/2017.
 */

public class DoctroProfileAddAwardsAdapter extends RecyclerView.Adapter<DoctroProfileAddAwardsAdapter.ViewHolder> {

    List<String> EDTDATA;
    Context context;
    View view1;

    DoctroProfileAddAwardsAdapter.ViewHolder viewHolder1;

    public DoctroProfileAddAwardsAdapter(Context context1, List<String> EDTDATA) {

        this.EDTDATA = EDTDATA;
        context = context1;


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View v) {

            super(v);


        }
    }

    @Override
    public DoctroProfileAddAwardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.adt_doctro_add_awards, parent, false);

        viewHolder1 = new DoctroProfileAddAwardsAdapter.ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(DoctroProfileAddAwardsAdapter.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return EDTDATA.size();
    }


}