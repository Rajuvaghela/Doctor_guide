package example.com.doctor_guide.Adapter;

/**
 * Created by Khodal on 03/08/2017.
 */


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import example.com.doctor_guide.Activity.MainActivity;
import example.com.homepagefordoctor_guide.R;


public class SliderAdapter extends PagerAdapter {




    MainActivity mContext;



    LayoutInflater mLayoutInflater;
    int[] mResources = {
            R.drawable.docttt,
//            R.drawable.labs,
            R.drawable.docttt,
            R.drawable.docttt,
            R.drawable.docttt,



    };

    public SliderAdapter(MainActivity context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);


        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }





    }
