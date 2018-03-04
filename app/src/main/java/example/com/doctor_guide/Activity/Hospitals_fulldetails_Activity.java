package example.com.doctor_guide.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import example.com.doctor_guide.Adapter.AwardAdapter;
import example.com.doctor_guide.Adapter.DetailsAdapter;
import example.com.doctor_guide.Adapter.DoctorFullDetailAdapter;
import example.com.doctor_guide.Adapter.DoctorFullDetailVideoAdapter;
import example.com.doctor_guide.Adapter.ExtraDetailContactPersonAdapter;
import example.com.doctor_guide.Adapter.ViewLastPageRatingAdapter;
import example.com.doctor_guide.Model.DetailsModel;
import example.com.doctor_guide.Model.RatingModel;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;

public class Hospitals_fulldetails_Activity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_profile, iv_detail, iv_gallery;

    TextView tv_hos_name, tv_hos_contact, tv_hos_landline, tv_hos_email, tv_hos_website, tv_doc_address, tv_about_doc;
    ImageView hospital_image;
    ImageView calling_btn, share_btn, rate_btn, map_btn;
    Context context;
    LinearLayout ll_gallery_button, ll_profile, ll_detail, ll_gallaery, ll_like, include_profile;
    LinearLayout ll_hospital_gellary;
    RecyclerView rv_hospital_detail_gallery, rv_hospital_detail_video;
    List<String> rv_list = new ArrayList<>();
    RecyclerView.Adapter listAdapter;
    RecyclerView.LayoutManager layoutManager2;
    RecyclerView.LayoutManager layoutManager_extra_service;
    RecyclerView.LayoutManager layoutManager_extra_insurance;
    Button btn_gallery_photo, btn_gallery_video;
    String[] item;
    String[] award_acchiveitem;
    String[] award_dateitem;
    String[] award_fromitem;
    String[] memberitem;
    String[] member_from_item;
    String[] member_date_item;
    String[] e_person_name;
    String[] e_contact_no;
    String[] e_service;
    String[] e_insurance;

    String[] doctor_video;


    LinearLayout ln_gallery_button, lndummy, ln_profile;
    ProgressDialog dialog;
    private List<DetailsModel> specialList = new ArrayList<>();
    private List<DetailsModel> awardList = new ArrayList<>();
    private List<DetailsModel> memberList = new ArrayList<>();
    private List<DetailsModel> galleryList = new ArrayList<>();

    private RecyclerView sp_hos_recyclerView, award_hos_recyclerView, member_hos_recyclerview;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager1;
    private RecyclerView.LayoutManager layoutManager_contact_person;
    private RecyclerView.LayoutManager member_layoutManager;
    private RecyclerView.LayoutManager experience_layoutManager;
    private RecyclerView.LayoutManager education_layoutManager;

    private RecyclerView.Adapter adapter;

    LinearLayout ll_hospitals_video;
    private ImageView iv_like;
    private String call_number;
    String longitude, latitude;
    private String hospital_name;
    EditText review_mobno;
    EditText edt_text_review;
    TextView tv_hos_view_all_rating, tv_hos_number_of_rating, tv_hos__rate_in_number;
    int hos_number_of_rating;

    RecyclerView rv_view_doc_mainpage_rating;
    RecyclerView.Adapter listAdapter2;
    RecyclerView.LayoutManager mLayoutManager;
    List<RatingModel> listuser = new ArrayList<>();
    RatingBar ratingbar_hos_rating;
    float rating;
    EditText review_name_of_person;
    RecyclerView rv_contact_person;
    RecyclerView rv_extra_service;
    RecyclerView rv_extra_insurance;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoActionbarTheme);
        setContentView(R.layout.activity_hospitals_category);
        init();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);

        getData();

        DocPhotoGalleryRecyclerView();
        DocVideoGalleryRecyclerView();
        load_doc_rating();
    }

    void getData1() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();
            params.add("h_id", Constant.h_id);

            client.post(Constant.doctor_live_url + "hospital_gallery.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        try {
                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONArray resultsarray = jsonObj.getJSONArray("response");
                            for (int i = 0; i < resultsarray.length(); i++) {

                                DetailsModel doctor = new DetailsModel();
                                hospital_name = resultsarray.getJSONObject(i).getString("h_name");
                                tv_hos_name.setText(hospital_name);


                                String str = resultsarray.getJSONObject(i).getString("h_contact_person");
                                String[] contact = str.split(",");
                                call_number = contact[0];
                                Log.e("h_id", "" + resultsarray.getJSONObject(i).getString("h_id"));
                                Log.e("h_contact", "" + resultsarray.getJSONObject(i).getString("h_contact_person"));
                                tv_hos_contact.setText(resultsarray.getJSONObject(i).getString("h_contact_person"));
                                tv_hos_landline.setText(resultsarray.getJSONObject(i).getString("h_landline"));
                                tv_hos_email.setText(resultsarray.getJSONObject(i).getString("h_email"));
                                tv_hos_website.setText(resultsarray.getJSONObject(i).getString("h_website"));
                                tv_doc_address.setText(resultsarray.getJSONObject(i).getString("h_address"));
                                tv_about_doc.setText(resultsarray.getJSONObject(i).getString("h_aboutus"));

                                latitude = resultsarray.getJSONObject(i).getString("h_latitude");
                                longitude = resultsarray.getJSONObject(i).getString("h_latitude");


                                String e_person = resultsarray.getJSONObject(i).getString("h_person");
                                e_person_name = e_person.split(",");

                                String e_contact = resultsarray.getJSONObject(i).getString("h_contact_person");
                                e_contact_no = e_contact.split(",");

                                String service = resultsarray.getJSONObject(i).getString("h_services");
                                e_service = service.split(",");

                                String insurance = resultsarray.getJSONObject(i).getString("h_insurance");
                                e_insurance = insurance.split(",");



                                                      /*  For Details */


                                                       /*  For speciality */
                                doctor.setName(resultsarray.getJSONObject(i).getString("h_speciality"));
                                String spe = resultsarray.getJSONObject(i).getString("h_speciality");
                                Log.e("h_speciality", "" + resultsarray.getJSONObject(i).getString("h_speciality"));
                                item = spe.split(",");
                                specialList.add(doctor);

                                                           /*  For Award */


                                                       /*  For Award_acchive */

                                doctor.setAward_acchive(resultsarray.getJSONObject(i).getString("h_award_acchive"));
                                String award_acchive = resultsarray.getJSONObject(i).getString("h_award_acchive");
                                Log.e("h_award_acchive", "" + resultsarray.getJSONObject(i).getString("h_award_acchive"));
                                award_acchiveitem = award_acchive.split(",");
                                awardList.add(doctor);

                                                              /*  For Award_date */


                                doctor.setAward_date(resultsarray.getJSONObject(i).getString("h_award_date"));
                                String award_date = resultsarray.getJSONObject(i).getString("h_award_date");
                                Log.e("h_award_acchive", "" + resultsarray.getJSONObject(i).getString("h_award_acchive"));
                                Log.e("h_award_date", "" + resultsarray.getJSONObject(i).getString("h_award_date"));
                                award_dateitem = award_date.split(",");
                                awardList.add(doctor);


                                                              /*  For Award_from */

                                doctor.setAward_from(resultsarray.getJSONObject(i).getString("h_award_from"));
                                String award_from = resultsarray.getJSONObject(i).getString("h_award_from");
                                award_fromitem = award_from.split(",");
                                awardList.add(doctor);

                                                            /* MEMBER*/
                                                              /*For  MEMBER*/
                                doctor.setAward_acchive(resultsarray.getJSONObject(i).getString("h_member"));
                                String member = resultsarray.getJSONObject(i).getString("h_member");
                                memberitem = member.split(",");
                                memberList.add(doctor);
                                                            /*For  MEMBER_date*/
                                doctor.setAward_date(resultsarray.getJSONObject(i).getString("h_member_date"));
                                String member_date = resultsarray.getJSONObject(i).getString("h_member_date");
                                member_date_item = member_date.split(",");
                                memberList.add(doctor);
                                                             /*For  MEMBER_from*/
                                doctor.setAward_from(resultsarray.getJSONObject(i).getString("h_member_from"));
                                String member_from = resultsarray.getJSONObject(i).getString("h_member_from");
                                member_from_item = member_from.split(",");
                                memberList.add(doctor);


                                Glide.with(getApplication())
                                        .load(Constant.Imageuri + resultsarray.getJSONObject(i).getString("h_img"))
                                        .asBitmap()
                                        .placeholder(R.drawable.doctor_slider)
                                        .into(hospital_image);
                                dialog.dismiss();


                            }
                        } catch (JSONException e) {
                            Toast.makeText(Hospitals_fulldetails_Activity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            dialog.dismiss();

                        }

                        rv_contact_person.setHasFixedSize(false);
                        layoutManager_contact_person = new LinearLayoutManager(Hospitals_fulldetails_Activity.this, LinearLayoutManager.VERTICAL, false);
                        rv_contact_person.setLayoutManager(layoutManager_contact_person);
                        listAdapter = new ExtraDetailContactPersonAdapter(Hospitals_fulldetails_Activity.this, e_person_name, e_contact_no);
                        rv_contact_person.setAdapter(listAdapter);

                        rv_extra_service.setHasFixedSize(false);
                        layoutManager_extra_service = new LinearLayoutManager(Hospitals_fulldetails_Activity.this, LinearLayoutManager.VERTICAL, false);
                        rv_extra_service.setLayoutManager(layoutManager_extra_service);
                        listAdapter = new DetailsAdapter(Hospitals_fulldetails_Activity.this, e_service);
                        rv_extra_service.setAdapter(listAdapter);

                        rv_extra_insurance.setHasFixedSize(false);
                        layoutManager_extra_insurance = new LinearLayoutManager(Hospitals_fulldetails_Activity.this, LinearLayoutManager.VERTICAL, false);
                        rv_extra_insurance.setLayoutManager(layoutManager_extra_insurance);
                        listAdapter = new DetailsAdapter(Hospitals_fulldetails_Activity.this, e_insurance);
                        rv_extra_insurance.setAdapter(listAdapter);

                        sp_hos_recyclerView.setHasFixedSize(true);
                        adapter = new DetailsAdapter(Hospitals_fulldetails_Activity.this, item);
                        sp_hos_recyclerView.setLayoutManager(layoutManager);
                        sp_hos_recyclerView.setAdapter(adapter);

                        award_hos_recyclerView.setHasFixedSize(true);
                        adapter = new AwardAdapter(Hospitals_fulldetails_Activity.this, award_acchiveitem, award_dateitem, award_fromitem);
                        award_hos_recyclerView.setLayoutManager(layoutManager1);
                        award_hos_recyclerView.setAdapter(adapter);

//
                        member_hos_recyclerview.setHasFixedSize(true);
                        adapter = new AwardAdapter(Hospitals_fulldetails_Activity.this, memberitem, member_date_item, member_from_item);
                        member_hos_recyclerview.setLayoutManager(member_layoutManager);
                        member_hos_recyclerview.setAdapter(adapter);


                    }
                    if (responseString.equals("[]")) {
                        //  ll_similar_product.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            Toast.makeText(Hospitals_fulldetails_Activity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    public void init() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        rv_extra_service = (RecyclerView) findViewById(R.id.rv_extra_service);
        rv_contact_person = (RecyclerView) findViewById(R.id.rv_contact_person);
        tv_about_doc = (TextView) findViewById(R.id.tv_about_doc);
        tv_doc_address = (TextView) findViewById(R.id.tv_doc_address);
        ratingbar_hos_rating = (RatingBar) findViewById(R.id.ratingbar_hos_rating);
        tv_hos__rate_in_number = (TextView) findViewById(R.id.tv_hos_rate_in_number);
        tv_hos_view_all_rating = (TextView) findViewById(R.id.tv_hos_view_all_rating);
        tv_hos_number_of_rating = (TextView) findViewById(R.id.tv_hos_number_of_rating);
        tv_hos_name = (TextView) findViewById(R.id.tv_hos_name);
        tv_hos_contact = (TextView) findViewById(R.id.tv_hos_contact);
        tv_hos_landline = (TextView) findViewById(R.id.tv_hos_landline);
        tv_hos_email = (TextView) findViewById(R.id.tv_hos_email);
        tv_hos_website = (TextView) findViewById(R.id.tv_hos_website);
        btn_gallery_photo = (Button) findViewById(R.id.btn_gallery_photo);
        btn_gallery_video = (Button) findViewById(R.id.btn_gallery_video);

        ll_hospitals_video = (LinearLayout) findViewById(R.id.ll_doctor_video);
        rv_view_doc_mainpage_rating = (RecyclerView) findViewById(R.id.rv_view_hos_mainpage_rating);


        rv_hospital_detail_video = (RecyclerView) findViewById(R.id.rv_doctor_detail_video);
        rv_hospital_detail_gallery = (RecyclerView) findViewById(R.id.rv_doctor_detail_gallery);


        iv_like = (ImageView) findViewById(R.id.iv_like);
        ll_gallery_button = (LinearLayout) findViewById(R.id.ll_gallery_button);
        ll_profile = (LinearLayout) findViewById(R.id.ll_profile);
        ll_detail = (LinearLayout) findViewById(R.id.ll_detail);
        ll_gallaery = (LinearLayout) findViewById(R.id.ll_gallaery);
        ll_like = (LinearLayout) findViewById(R.id.ll_like);
        include_profile = (LinearLayout) findViewById(R.id.inclide_profile);
        ll_hospital_gellary = (LinearLayout) findViewById(R.id.ll_doctor_gellary);
        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        iv_detail = (ImageView) findViewById(R.id.iv_detail);
        iv_gallery = (ImageView) findViewById(R.id.iv_gallery);
        calling_btn = (ImageView) findViewById(R.id.calling_btn);
        share_btn = (ImageView) findViewById(R.id.share_btn);
        rate_btn = (ImageView) findViewById(R.id.rate_btn);
        map_btn = (ImageView) findViewById(R.id.map_btn);
        lndummy = (LinearLayout) findViewById(R.id.lndummy);
        lndummy.setOnClickListener(this);
        hospital_image = (ImageView) findViewById(R.id.doctor_image);

        rv_extra_insurance = (RecyclerView) findViewById(R.id.rv_extra_insurance);
        sp_hos_recyclerView = (RecyclerView) findViewById(R.id.sp_hos_recyclerview);
        award_hos_recyclerView = (RecyclerView) findViewById(R.id.award_hos_recyclerview);
        member_hos_recyclerview = (RecyclerView) findViewById(R.id.member_hos_recyclerview);


        layoutManager = new GridLayoutManager(this, 1);
        layoutManager1 = new LinearLayoutManager(this);
        member_layoutManager = new LinearLayoutManager(this);
        experience_layoutManager = new LinearLayoutManager(this);
        education_layoutManager = new LinearLayoutManager(this);

        sp_hos_recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        sp_hos_recyclerView.setLayoutManager(layoutManager);

        award_hos_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        award_hos_recyclerView.setLayoutManager(layoutManager1);

        member_hos_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        member_hos_recyclerview.setLayoutManager(member_layoutManager);


        iv_gallery.setOnClickListener(this);
        iv_profile.setOnClickListener(this);
        iv_detail.setOnClickListener(this);
        iv_like.setOnClickListener(this);
        share_btn.setOnClickListener(this);
        calling_btn.setOnClickListener(this);
        map_btn.setOnClickListener(this);
        iv_back.setOnClickListener(this);

        ll_profile.setOnClickListener(this);
        ll_detail.setOnClickListener(this);
        ll_gallaery.setOnClickListener(this);
        ll_like.setOnClickListener(this);
        rate_btn.setOnClickListener(this);
        tv_hos_view_all_rating.setOnClickListener(this);
        btn_gallery_photo.setOnClickListener(this);
        btn_gallery_video.setOnClickListener(this);

    }

    public void load_doc_rating() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();

            params.add("h_id", Constant.h_id);
            client.post(Constant.doctor_live_url + "hospital_rating_view.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        listuser.clear();

                        try {
                            Log.e("view_res", responseString);
                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONArray resultsarray = jsonObj.getJSONArray("response");
                            for (int i = 0; i < resultsarray.length(); i++) {
                                RatingModel temp = new RatingModel();
                                temp.setDr_id(resultsarray.getJSONObject(i).getString("h_id"));
                                temp.setD_id(resultsarray.getJSONObject(i).getString("h_id"));
                                temp.setDr_name(resultsarray.getJSONObject(i).getString("hr_name"));
                                temp.setDr_contact(resultsarray.getJSONObject(i).getString("hr_contact"));
                                temp.setDr_rating(resultsarray.getJSONObject(i).getString("hr_rating"));
                                temp.setDr_desc(resultsarray.getJSONObject(i).getString("hr_desc"));
                                Log.e("hr_rating", "" + resultsarray.getJSONObject(i).getString("hr_rating"));

                                rating = rating + Float.parseFloat(resultsarray.getJSONObject(i).getString("hr_rating"));
                                hos_number_of_rating = i + 1;
                                listuser.add(temp);
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        DecimalFormat df = new DecimalFormat("0.##");
                        String number_of_rate = String.valueOf(hos_number_of_rating);
                        tv_hos_number_of_rating.setText(number_of_rate + " Rating");
                        tv_hos__rate_in_number.setText(String.valueOf(df.format(rating / hos_number_of_rating)));
                        ratingbar_hos_rating.setRating(rating / hos_number_of_rating);
                        rv_view_doc_mainpage_rating.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(Hospitals_fulldetails_Activity.this, LinearLayoutManager.VERTICAL, false);
                        rv_view_doc_mainpage_rating.setLayoutManager(mLayoutManager);
                        listAdapter2 = new ViewLastPageRatingAdapter(Hospitals_fulldetails_Activity.this, listuser);
                        rv_view_doc_mainpage_rating.setAdapter(listAdapter2);

                    }

                }
            });
        } else {
            Toast.makeText(Hospitals_fulldetails_Activity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    /*  For Video */
    public void DocVideoGalleryRecyclerView() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();
            params.add("h_id", Constant.h_id);

            client.post(Constant.doctor_live_url + "hospital_video.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null && !responseString.equals("[]")) {

                        try {
                            Log.e("res_video", responseString);

                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONArray resultsarray = jsonObj.getJSONArray("response");
                            for (int i = 0; i < resultsarray.length(); i++) {

                                Log.e("hv_path", "" + resultsarray.getJSONObject(i).getString("hv_path"));
                                String str = resultsarray.getJSONObject(i).getString("hv_path");

                                doctor_video = str.split(",");
                                // galleryList.add(temp);
                                dialog.dismiss();

                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }

                        rv_hospital_detail_video.setHasFixedSize(true);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        //  layoutManager2 = new LinearLayoutManager(DoctorFullDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv_hospital_detail_video.setLayoutManager(gridLayoutManager);
                        listAdapter = new DoctorFullDetailVideoAdapter(Hospitals_fulldetails_Activity.this, doctor_video);
                        rv_hospital_detail_video.setAdapter(listAdapter);
                        rv_hospital_detail_video.addOnItemTouchListener(new RecyclerItemClickListener(Hospitals_fulldetails_Activity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getApplicationContext(), YoutubeVideoPlayActivity.class);
                                intent.putExtra("id", doctor_video[position]);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent); // start Intent


                            }
                        }));

                    }
                    if (responseString.equals("[]")) {
                        //  ll_similar_product.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            Toast.makeText(Hospitals_fulldetails_Activity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    /*  For Photo */
    public void DocPhotoGalleryRecyclerView() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();
            params.add("h_id", Constant.h_id);

            client.post(Constant.doctor_live_url + "hospital_gallery.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null && !responseString.equals("[]")) {
                        galleryList.clear();

                        try {
                            Log.e("res_gallery", responseString);

                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONArray resultsarray = jsonObj.getJSONArray("response");
                            for (int i = 0; i < resultsarray.length(); i++) {
                                DetailsModel temp = new DetailsModel();
                                temp.setDoctor_gallery_image(Constant.Imageuri + resultsarray.getJSONObject(i).getString("hg_img"));
                                galleryList.add(temp);
                                dialog.dismiss();

                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        rv_hospital_detail_gallery.setHasFixedSize(true);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        // layoutManager2 = new LinearLayoutManager(DoctorFullDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv_hospital_detail_gallery.setLayoutManager(gridLayoutManager);
                        listAdapter = new DoctorFullDetailAdapter(Hospitals_fulldetails_Activity.this, galleryList);
                        rv_hospital_detail_gallery.setAdapter(listAdapter);
                        rv_hospital_detail_gallery.addOnItemTouchListener(new RecyclerItemClickListener(Hospitals_fulldetails_Activity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getApplicationContext(), DisplayGalleryPhotoActivity.class);
                                int i = position;
                                String s = String.valueOf(i);
                                intent.putExtra("pos", s);
                                intent.putExtra("img", galleryList.get(position).getDoctor_gallery_image());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent); // start Intent


                            }
                        }));

                    }
                    if (responseString.equals("[]")) {
                        //  ll_similar_product.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            Toast.makeText(Hospitals_fulldetails_Activity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /*  For Profile */
    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(Hospitals_fulldetails_Activity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.hospitals_details_category_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        Log.e("res", responseString);
                        if (responseString != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(responseString);
                                JSONArray resultsarray = jsonObj.getJSONArray("response");
                                for (int i = 0; i < resultsarray.length(); i++) {

                                    DetailsModel doctor = new DetailsModel();
                                    hospital_name = resultsarray.getJSONObject(i).getString("h_name");
                                    tv_hos_name.setText(hospital_name);


                                    String str = resultsarray.getJSONObject(i).getString("h_contact_person");
                                    String[] contact = str.split(",");
                                    call_number = contact[0];
                                    Log.e("h_id", "" + resultsarray.getJSONObject(i).getString("h_id"));
                                    Log.e("h_contact", "" + resultsarray.getJSONObject(i).getString("h_contact_person"));
                                    tv_hos_contact.setText(resultsarray.getJSONObject(i).getString("h_contact_person"));
                                    tv_hos_landline.setText(resultsarray.getJSONObject(i).getString("h_landline"));
                                    tv_hos_email.setText(resultsarray.getJSONObject(i).getString("h_email"));
                                    tv_hos_website.setText(resultsarray.getJSONObject(i).getString("h_website"));
                                    tv_doc_address.setText(resultsarray.getJSONObject(i).getString("h_address"));
                                    tv_about_doc.setText(resultsarray.getJSONObject(i).getString("h_aboutus"));

                                    latitude = resultsarray.getJSONObject(i).getString("h_latitude");
                                    longitude = resultsarray.getJSONObject(i).getString("h_latitude");


                                    String e_person = resultsarray.getJSONObject(i).getString("h_person");
                                    e_person_name = e_person.split(",");

                                    String e_contact = resultsarray.getJSONObject(i).getString("h_contact_person");
                                    e_contact_no = e_contact.split(",");

                                    String service = resultsarray.getJSONObject(i).getString("h_services");
                                    e_service = service.split(",");

                                    String insurance = resultsarray.getJSONObject(i).getString("h_insurance");
                                    e_insurance = insurance.split(",");



                                                      /*  For Details */


                                                       /*  For speciality */
                                    doctor.setName(resultsarray.getJSONObject(i).getString("h_speciality"));
                                    String spe = resultsarray.getJSONObject(i).getString("h_speciality");
                                    Log.e("h_speciality", "" + resultsarray.getJSONObject(i).getString("h_speciality"));
                                    item = spe.split(",");
                                    specialList.add(doctor);

                                                           /*  For Award */


                                                       /*  For Award_acchive */

                                    doctor.setAward_acchive(resultsarray.getJSONObject(i).getString("h_award_acchive"));
                                    String award_acchive = resultsarray.getJSONObject(i).getString("h_award_acchive");
                                    Log.e("h_award_acchive", "" + resultsarray.getJSONObject(i).getString("h_award_acchive"));
                                    award_acchiveitem = award_acchive.split(",");
                                    awardList.add(doctor);

                                                              /*  For Award_date */


                                    doctor.setAward_date(resultsarray.getJSONObject(i).getString("h_award_date"));
                                    String award_date = resultsarray.getJSONObject(i).getString("h_award_date");
                                    Log.e("h_award_acchive", "" + resultsarray.getJSONObject(i).getString("h_award_acchive"));
                                    Log.e("h_award_date", "" + resultsarray.getJSONObject(i).getString("h_award_date"));
                                    award_dateitem = award_date.split(",");
                                    awardList.add(doctor);


                                                              /*  For Award_from */

                                    doctor.setAward_from(resultsarray.getJSONObject(i).getString("h_award_from"));
                                    String award_from = resultsarray.getJSONObject(i).getString("h_award_from");
                                    award_fromitem = award_from.split(",");
                                    awardList.add(doctor);

                                                            /* MEMBER*/
                                                              /*For  MEMBER*/
                                    doctor.setAward_acchive(resultsarray.getJSONObject(i).getString("h_member"));
                                    String member = resultsarray.getJSONObject(i).getString("h_member");
                                    memberitem = member.split(",");
                                    memberList.add(doctor);
                                                            /*For  MEMBER_date*/
                                    doctor.setAward_date(resultsarray.getJSONObject(i).getString("h_member_date"));
                                    String member_date = resultsarray.getJSONObject(i).getString("h_member_date");
                                    member_date_item = member_date.split(",");
                                    memberList.add(doctor);
                                                             /*For  MEMBER_from*/
                                    doctor.setAward_from(resultsarray.getJSONObject(i).getString("h_member_from"));
                                    String member_from = resultsarray.getJSONObject(i).getString("h_member_from");
                                    member_from_item = member_from.split(",");
                                    memberList.add(doctor);


                                    Glide.with(getApplication())
                                            .load(Constant.Imageuri + resultsarray.getJSONObject(i).getString("h_img"))
                                            .asBitmap()
                                            .placeholder(R.drawable.doctor_slider)
                                            .into(hospital_image);
                                    progressDialog.dismiss();


                                }
                            } catch (JSONException e) {
                                Toast.makeText(Hospitals_fulldetails_Activity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }

                            rv_contact_person.setHasFixedSize(false);
                            layoutManager_contact_person = new LinearLayoutManager(Hospitals_fulldetails_Activity.this, LinearLayoutManager.VERTICAL, false);
                            rv_contact_person.setLayoutManager(layoutManager_contact_person);
                            listAdapter = new ExtraDetailContactPersonAdapter(Hospitals_fulldetails_Activity.this, e_person_name, e_contact_no);
                            rv_contact_person.setAdapter(listAdapter);

                            rv_extra_service.setHasFixedSize(false);
                            layoutManager_extra_service = new LinearLayoutManager(Hospitals_fulldetails_Activity.this, LinearLayoutManager.VERTICAL, false);
                            rv_extra_service.setLayoutManager(layoutManager_extra_service);
                            listAdapter = new DetailsAdapter(Hospitals_fulldetails_Activity.this, e_service);
                            rv_extra_service.setAdapter(listAdapter);

                            rv_extra_insurance.setHasFixedSize(false);
                            layoutManager_extra_insurance = new LinearLayoutManager(Hospitals_fulldetails_Activity.this, LinearLayoutManager.VERTICAL, false);
                            rv_extra_insurance.setLayoutManager(layoutManager_extra_insurance);
                            listAdapter = new DetailsAdapter(Hospitals_fulldetails_Activity.this, e_insurance);
                            rv_extra_insurance.setAdapter(listAdapter);

                            sp_hos_recyclerView.setHasFixedSize(true);
                            adapter = new DetailsAdapter(Hospitals_fulldetails_Activity.this, item);
                            sp_hos_recyclerView.setLayoutManager(layoutManager);
                            sp_hos_recyclerView.setAdapter(adapter);

                            award_hos_recyclerView.setHasFixedSize(true);
                            adapter = new AwardAdapter(Hospitals_fulldetails_Activity.this, award_acchiveitem, award_dateitem, award_fromitem);
                            award_hos_recyclerView.setLayoutManager(layoutManager1);
                            award_hos_recyclerView.setAdapter(adapter);

//
                            member_hos_recyclerview.setHasFixedSize(true);
                            adapter = new AwardAdapter(Hospitals_fulldetails_Activity.this, memberitem, member_date_item, member_from_item);
                            member_hos_recyclerview.setLayoutManager(member_layoutManager);
                            member_hos_recyclerview.setAdapter(adapter);


                        }


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Hospitals_fulldetails_Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("h_id", Constant.h_id);


                return params;
            }

        };


        AppController.getInstance().addToRequestQueue(stringRequest);


    }

    /*For Share_btn*/
    public void sharebtn() {

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

    /*For Rate_btn*/
    public void ratebtn(View v) {

        AppRater.app_launched(this);


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    /* Button Click Event*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_gallery:
                ll_hospital_gellary.setVisibility(View.VISIBLE);
                include_profile.setVisibility(View.GONE);
                ll_gallery_button.setVisibility(View.VISIBLE);
                lndummy.setVisibility(View.GONE);
                break;
            case R.id.iv_profile:
                ll_hospital_gellary.setVisibility(View.GONE);
                include_profile.setVisibility(View.VISIBLE);
                ll_gallery_button.setVisibility(View.GONE);
                lndummy.setVisibility(View.GONE);
                break;
            case R.id.iv_detail:
                lndummy.setVisibility(View.VISIBLE);
                ll_hospital_gellary.setVisibility(View.GONE);
                include_profile.setVisibility(View.GONE);
                ll_gallery_button.setVisibility(View.GONE);
                break;
            case R.id.iv_like:
                startActivity(new Intent(getApplicationContext(), DoctorBrochuresListActivity.class));
                break;
            case R.id.share_btn:
                sharebtn();
                break;

            case R.id.ll_profile:
                break;
            case R.id.ll_detail:

                break;
            case R.id.ll_gallaery:
                break;

            case R.id.btn_gallery_photo:
                ll_hospital_gellary.setVisibility(View.VISIBLE);
                ll_hospitals_video.setVisibility(View.GONE);
                break;
            case R.id.btn_gallery_video:
                ll_hospitals_video.setVisibility(View.VISIBLE);
                ll_hospital_gellary.setVisibility(View.GONE);
                break;
            case R.id.map_btn:
                if (latitude.equals(null) && longitude.equals(null)) {
                } else {
                    Intent ii = new Intent(this, MapsActivity.class);
//                     ii.putExtra("pkgName", B2MAppsPKGName);
                    ii.putExtra("d_latitude", latitude);
                    ii.putExtra("d_longitude", longitude);
                    ii.putExtra("l_name", hospital_name);
                    ii.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(ii);
                }
                break;
            case R.id.calling_btn:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", call_number, null));
                startActivity(intent);
                break;
            case R.id.rate_btn:
                LayoutInflater li = LayoutInflater.from(Hospitals_fulldetails_Activity.this);
                View vi = li.inflate(R.layout.review_edittext, null);


                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(Hospitals_fulldetails_Activity.this);
                alertDialogBuilder.setView(vi);
                TextView review_name = (TextView) vi.findViewById(R.id.review_name);
                review_name.setText(hospital_name);
                review_mobno = (EditText) vi.findViewById(R.id.review_mobno);
                review_name_of_person = (EditText) vi.findViewById(R.id.review_name_of_person);
                edt_text_review = (EditText) vi.findViewById(R.id.edt_text_review);
                ratingbar_hos_rating = (RatingBar) vi.findViewById(R.id.doctor_ratingbar);
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RatingApi(review_name_of_person.getText().toString(), review_mobno.getText().toString(), edt_text_review.getText().toString(), String.valueOf(ratingbar_hos_rating.getRating()));


                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                Button theButton1 = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                theButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RatingApi(review_name_of_person.getText().toString(), review_mobno.getText().toString(), edt_text_review.getText().toString(), String.valueOf(ratingbar_hos_rating.getRating()));
                        alertDialog.dismiss();
                    }
                });
                break;
            case R.id.tv_hos_view_all_rating:
                startActivity(new Intent(getApplicationContext(), ViewAllDocRatingActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /*For Rating & Review*/
    private void RatingApi(String name, String mobile_no, String description, String ratting) {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();

            params.add("h_id", Constant.h_id);
            params.add("hr_name", name);
            params.add("hr_contact", mobile_no);
            params.add("hr_desc", description);
            params.add("hr_rating", ratting);


            client.post(Constant.doctor_live_url + "hospital_rating.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        String str = null;
                        String test = null;
                        JSONArray resultsarray = null;
                        Log.e("reg_res:", "" + responseString);


                        try {
                            JSONObject jsonObj = new JSONObject(responseString);
                            str = jsonObj.getString("success");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (str.equals("true")) {
                            Toast.makeText(getApplicationContext(), "Rating and reviews submit sucessfully !", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            return;
                        } else if (str.equals("false")) {

                            Toast.makeText(Hospitals_fulldetails_Activity.this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            return;
                        } else {
                            dialog.dismiss();
                            return;
                        }

                    } else {
                        Toast.makeText(Hospitals_fulldetails_Activity.this, "data is not available!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(Hospitals_fulldetails_Activity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }
}
