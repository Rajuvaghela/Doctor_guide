package example.com.doctor_guide.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.com.homepagefordoctor_guide.R;

public class Hospitals_details_CategoryActivity extends AppCompatActivity {


/*
    Button bt1, bt2, bt3, calling_btn,share_btn,rate_btn,map_btn;
    LinearLayout lninfo,lndummy,lnhosinfo;
    RelativeLayout rl1,rl2,rl3;
    TextView tv_hos_name,tv_hos_contact,tv_hos_landline,tv_hos_email,
            tv_hos_website,tv_hos_speciality,tv_hos_award,tv_hos_services,tv_hos_segment,tv_hos_area,
            tv_hos_insurance,tv_hos_address,tv_hos_city,tv_hos_state,tv_hos_country,tv_hos_latitude,tv_hos_longitude;
    RoundImageView doctor_image;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        /*bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        bt3 = (Button)findViewById(R.id.bt3);
        calling_btn = (Button) findViewById(R.id.calling_btn);
        share_btn = (Button) findViewById(R.id.share_btn);
        rate_btn = (Button) findViewById(R.id.rate_btn);
        map_btn = (Button) findViewById(R.id.map_btn);
        lninfo  = (LinearLayout)findViewById(R.id.lninfo);
        lndummy  = (LinearLayout)findViewById(R.id.lndummy);
        rl1 = (RelativeLayout)findViewById(R.id.rl1);
        rl2 = (RelativeLayout)findViewById(R.id.rl2);
        rl3 = (RelativeLayout)findViewById(R.id.rl3);
        lnhosinfo = (LinearLayout)findViewById(R.id.lnhosinfo);



        tv_hos_name = (TextView)findViewById(R.id.txt_hos_name);
        tv_hos_contact = (TextView)findViewById(R.id.txt_hos_contact);
        tv_hos_landline= (TextView)findViewById(R.id.txt_hos_landline);
        tv_hos_email= (TextView)findViewById(R.id.txt_hos_email);
        tv_hos_website = (TextView)findViewById(R.id.txt_hos_website);
        tv_hos_speciality  = (TextView)findViewById(R.id.txt_hos_website);
        tv_hos_award  = (TextView)findViewById(R.id.txt_hos_award_acchive);
        tv_hos_services  = (TextView)findViewById(R.id.txt_hos_services);
        tv_hos_segment  = (TextView)findViewById(R.id.txt_hos_segment);
        tv_hos_area  = (TextView)findViewById(R.id.txt_hos_area);
        tv_hos_insurance  = (TextView)findViewById(R.id.txt_hos_insurance);
        tv_hos_address = (TextView)findViewById(R.id.txt_hos_address);
        tv_hos_city = (TextView)findViewById(R.id.txt_hos_city);
        tv_hos_state = (TextView)findViewById(R.id.txt_hos_state);
        tv_hos_country = (TextView)findViewById(R.id.txt_hos_country);
        tv_hos_latitude = (TextView)findViewById(R.id.txt_hos_latitude);
        tv_hos_longitude = (TextView)findViewById(R.id.txt_hos_longitude);
        doctor_image = (RoundImageView)findViewById(R.id.doctor_image);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bt1.setBackgroundDrawable( getResources().getDrawable(R.drawable.border));
        bt1.setTextColor(getResources().getColor(R.color.btntxtcolor));
        lnhosinfo.setVisibility(View.VISIBLE);
        getData();
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flipping);
        anim.setTarget(doctor_image);
        anim.setDuration(3000);
        anim.start();*/


    }



    /*public void Bt1(View v){
        bt1.setBackgroundDrawable( getResources().getDrawable(R.drawable.border));
        bt2.setTextColor(getResources().getColor(R.color.button1));
        bt3.setTextColor(getResources().getColor(R.color.button1));
        bt1.setTextColor(getResources().getColor(R.color.btntxtcolor));
        bt2.setBackgroundColor(getResources().getColor(R.color.button2));
        bt3.setBackgroundColor(getResources().getColor(R.color.button3));


        lninfo.setVisibility(View.GONE);
        lnhosinfo.setVisibility(View.VISIBLE);
        lndummy.setVisibility(View.GONE);

    }

    public void Bt2(View v){
        bt2.setTextColor(getResources().getColor(R.color.btntxtcolor));
        bt2.setBackgroundDrawable( getResources().getDrawable(R.drawable.border));

        bt1.setTextColor(getResources().getColor(R.color.button1));
        bt3.setTextColor(getResources().getColor(R.color.button1));
        bt1.setBackgroundColor(getResources().getColor(R.color.button2));
//        bt2.setBackgroundColor(getResources().getColor(R.color.button1));
        bt3.setBackgroundColor(getResources().getColor(R.color.button2));
//        rl2.setBackgroundDrawable( getResources().getDrawable(R.drawable.border));
//        rl1.setBackgroundDrawable( getResources().getDrawable(R.drawable.noborder));
//        rl3.setBackgroundDrawable( getResources().getDrawable(R.drawable.noborder));
        lnhosinfo.setVisibility(View.GONE);
        lndummy.setVisibility(View.VISIBLE);
        lninfo.setVisibility(View.GONE);
    }

    public void Bt3(View v){
        bt3.setTextColor(getResources().getColor(R.color.btntxtcolor));

        bt2.setTextColor(getResources().getColor(R.color.button1));
        bt1.setTextColor(getResources().getColor(R.color.button1));
        bt1.setBackgroundColor(getResources().getColor(R.color.button2));
        bt2.setBackgroundColor(getResources().getColor(R.color.button2));

        bt3.setBackgroundDrawable( getResources().getDrawable(R.drawable.border));


        lnhosinfo.setVisibility(View.GONE);
        lninfo.setVisibility(View.VISIBLE);
        lndummy.setVisibility(View.GONE);
    }


    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(Hospitals_details_CategoryActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.hospitals_details_category_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        Log.d("res", responseString);
                        if (responseString != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(responseString);
                                JSONArray resultsarray = jsonObj.getJSONArray("response");
                                for (int i = 0; i < resultsarray.length(); i++) {
                                    tv_hos_name.setText(resultsarray.getJSONObject(i).getString("h_name"));
                                    tv_hos_contact.setText(resultsarray.getJSONObject(i).getString("h_contact_person"));
                                    tv_hos_landline.setText(resultsarray.getJSONObject(i).getString("h_landline"));
                                    tv_hos_email.setText(resultsarray.getJSONObject(i).getString("h_email"));
                                    tv_hos_website.setText(resultsarray.getJSONObject(i).getString("h_website"));
                                    tv_hos_speciality.setText(resultsarray.getJSONObject(i).getString("h_speciality"));
                                    tv_hos_award.setText(resultsarray.getJSONObject(i).getString("h_award_acchive"));
                                    tv_hos_services.setText(resultsarray.getJSONObject(i).getString("h_services"));
//                                    tv_hos_segment.setText(resultsarray.getJSONObject(i).getString("h_segment"));
                                    tv_hos_area.setText(resultsarray.getJSONObject(i).getString("h_area"));
                                    tv_hos_insurance.setText(resultsarray.getJSONObject(i).getString("h_insurance"));
                                    tv_hos_address.setText(resultsarray.getJSONObject(i).getString("h_address"));
                                    tv_hos_city .setText(resultsarray.getJSONObject(i).getString("h_city"));
                                    tv_hos_state.setText(resultsarray.getJSONObject(i).getString("h_state"));
                                    tv_hos_country.setText(resultsarray.getJSONObject(i).getString("h_country"));
                                    tv_hos_latitude.setText(resultsarray.getJSONObject(i).getString("h_latitude"));
                                    tv_hos_longitude.setText(resultsarray.getJSONObject(i).getString("h_longitude"));
                                    final String latitude = resultsarray.getJSONObject(i).getString("h_latitude");
                                    final String longitude = resultsarray.getJSONObject(i).getString("h_longitude");
                                    final String no = resultsarray.getJSONObject(i).getString("h_contact_person");

                                    Glide.with(getApplication())
                                            .load(Constant.Imageuri + resultsarray.getJSONObject(i).getString("h_img"))
                                            .asBitmap()
                                            .placeholder(R.drawable.doctor_slider)
                                            .into(doctor_image);


                                    progressDialog.dismiss();
                                    calling_btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

//

                                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", no, null));
                                            startActivity(intent);
                                        }
                                    });


                                    map_btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude ;
                                            String uri = String.format(Locale.ENGLISH, "http://geo:%f,%f",  59.915494, 30.409456);
                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                                            startActivity(intent);




                                        }
                                    });


                                }
                            } catch (JSONException e) {
                                Toast.makeText(Hospitals_details_CategoryActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }

                        }







                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Hospitals_details_CategoryActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("h_id",Constant.h_id);


                return params;
            }

        };


        AppController.getInstance().addToRequestQueue(stringRequest);



    }
    public void sharebtn(View v) {

        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Doctor-Guide");
            String sAux = "\nLet me recommend you this application\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    public void ratebtn(View v) {

        AppRater.app_launched(this);


    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

*/






}

