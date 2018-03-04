package example.com.doctor_guide.Adapter;

/**
 * Created by Khodal on 19/08/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.com.homepagefordoctor_guide.R;

/**
 * Created by Belal on 11/9/2015.
 */
public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    String[] movie;


    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;

    public DetailsAdapter(Context context1, String[] movie) {

        this.movie = movie;
        context = context1;


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_movies;

        public ViewHolder(View v) {

            super(v);
            tv_movies = (TextView) v.findViewById(R.id.txt_sp1);


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.specialization_design, parent, false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("value", "" + movie[position]);
        holder.tv_movies.setText(movie[position]);

    }

    @Override
    public int getItemCount() {

        return movie.length;
    }
}