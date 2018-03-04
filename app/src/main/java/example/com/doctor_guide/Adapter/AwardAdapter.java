package example.com.doctor_guide.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import example.com.homepagefordoctor_guide.R;

/**
 * Created by Khodal on 21/09/2017.
 */

public class AwardAdapter extends RecyclerView.Adapter<AwardAdapter.ViewHolder> {

    String[] award_acchive;
    String[] award_date;
    String[] award_from;


    Context context;
    View view1;
    AwardAdapter.ViewHolder viewHolder1;

    public AwardAdapter(Context context1, String[] award_acchive, String[] award_date, String[] award_from) {

        this.award_acchive = award_acchive;
        this.award_date = award_date;
        this.award_from = award_from;
        context = context1;


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView award_acchive, award_date, award_from;

        public ViewHolder(View v) {

            super(v);
            award_acchive = (TextView) v.findViewById(R.id.txt_award_acchive);
            award_date = (TextView) v.findViewById(R.id.txt_award_date);
            award_from = (TextView) v.findViewById(R.id.txt_award_from);


        }
    }

    @Override
    public AwardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.award_and_other_design, parent, false);

        viewHolder1 = new AwardAdapter.ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(AwardAdapter.ViewHolder holder, int position) {
        Log.e("value", "" + award_acchive[position]);
        holder.award_acchive.setText(award_acchive[position]);
        // holder.award_date.setText(award_date[position]);
        //  holder.award_date.setText(parseDateToddMMyyyy(award_date[position]));
        holder.award_date.setText(award_date[position]);
        holder.award_from.setText(award_from[position]);

    }

    @Override
    public int getItemCount() {
        return award_acchive.length;
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}