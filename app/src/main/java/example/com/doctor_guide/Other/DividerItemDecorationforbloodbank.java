package example.com.doctor_guide.Other;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import example.com.doctor_guide.Activity.Bloodbank_And_Stemcell_list_CategoryActivity;
import example.com.homepagefordoctor_guide.R;


/**
 * Created by Khodal on 04/08/2017.
 */

public class DividerItemDecorationforbloodbank extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public DividerItemDecorationforbloodbank(Bloodbank_And_Stemcell_list_CategoryActivity context) {
        mDivider = context.getResources().getDrawable(R.drawable.line_divider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}