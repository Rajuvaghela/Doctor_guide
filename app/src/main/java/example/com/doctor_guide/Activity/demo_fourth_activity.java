package example.com.doctor_guide.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.com.homepagefordoctor_guide.R;



public class demo_fourth_activity  extends AppCompatActivity {
    /*Button bt1,bt2,bt3;
    LinearLayout lninfo,lndummy;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_category);
       /* bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        bt3 = (Button)findViewById(R.id.bt3);
        lninfo  = (LinearLayout)findViewById(R.id.lninfo);
        lndummy  = (LinearLayout)findViewById(R.id.lndummy);*/
    }

    /*public void Bt1(View v){
        bt2.setTextColor(getResources().getColor(R.color.button1));
        bt3.setTextColor(getResources().getColor(R.color.button1));
        bt1.setTextColor(getResources().getColor(R.color.btntxtcolor));
        bt1.setBackgroundColor(getResources().getColor(R.color.button1));
        bt2.setBackgroundColor(getResources().getColor(R.color.button2));
        bt3.setBackgroundColor(getResources().getColor(R.color.button3));
        lninfo.setVisibility(View.VISIBLE);
        lndummy.setVisibility(View.GONE);

    }

    public void Bt2(View v){
        bt2.setTextColor(getResources().getColor(R.color.btntxtcolor));

        bt1.setTextColor(getResources().getColor(R.color.button1));
        bt3.setTextColor(getResources().getColor(R.color.button1));
        bt1.setBackgroundColor(getResources().getColor(R.color.button2));
        bt2.setBackgroundColor(getResources().getColor(R.color.button1));
        bt3.setBackgroundColor(getResources().getColor(R.color.button2));
        lndummy.setVisibility(View.VISIBLE);
        lninfo.setVisibility(View.GONE);
    }

    public void Bt3(View v){
        bt3.setTextColor(getResources().getColor(R.color.btntxtcolor));

        bt2.setTextColor(getResources().getColor(R.color.button1));
        bt1.setTextColor(getResources().getColor(R.color.button1));
        bt1.setBackgroundColor(getResources().getColor(R.color.button2));
        bt2.setBackgroundColor(getResources().getColor(R.color.button2));
        bt3.setBackgroundColor(getResources().getColor(R.color.button1));
        lninfo.setVisibility(View.VISIBLE);
        lndummy.setVisibility(View.GONE);
    }*/

}
