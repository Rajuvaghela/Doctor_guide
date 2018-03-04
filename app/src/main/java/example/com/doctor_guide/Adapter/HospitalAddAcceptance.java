package example.com.doctor_guide.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

import example.com.homepagefordoctor_guide.R;

/**
 * Created by Khodal on 21/09/2017.
 */

public class HospitalAddAcceptance extends RecyclerView.Adapter<HospitalAddAcceptance.ViewHolder> {

    List<String> EDTDATA;
    Context context;
    View view1;

    HospitalAddAcceptance.ViewHolder viewHolder1;

    public HospitalAddAcceptance(Context context1, List<String> EDTDATA) {

        this.EDTDATA = EDTDATA;
        context = context1;


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public EditText edt_degreename;

        public ViewHolder(View v) {

            super(v);
            edt_degreename = (EditText) v.findViewById(R.id.edt_degreename);
        }
    }


    @Override
    public HospitalAddAcceptance.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.adt_doctro_add_spelization, parent, false);

        viewHolder1 = new HospitalAddAcceptance.ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(HospitalAddAcceptance.ViewHolder holder, int position) {

        holder.edt_degreename.setHint("Insurance");

    }

    @Override
    public int getItemCount() {
        return EDTDATA.size();
    }


}