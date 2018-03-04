package example.com.doctor_guide.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import example.com.doctor_guide.Adapter.DocBrocherAdapter;
import example.com.doctor_guide.Adapter.DoctorFullDetailAdapter;
import example.com.doctor_guide.Adapter.DoctorFullDetailVideoAdapter;
import example.com.doctor_guide.Adapter.ExtraDetailContactPersonAdapter;
import example.com.doctor_guide.Adapter.ViewLastPageRatingAdapter;
import example.com.doctor_guide.Model.DetailsModel;
import example.com.doctor_guide.Model.ExtraListModel;
import example.com.doctor_guide.Model.RatingModel;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;


import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import example.com.doctor_guide.Adapter.ViewAllDocRatingAdapter;
import example.com.doctor_guide.Adapter.ViewLastPageRatingAdapter;
import example.com.doctor_guide.Model.DetailsModel;
import example.com.doctor_guide.Model.DoctorFullDetailModel;
import example.com.doctor_guide.Model.RatingModel;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;

public class Extra_details_CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_profile, iv_detail, iv_gallery;
    /*    LinearLayout lninfo, lndummy;
        RelativeLayout rl1, rl2, rl3;*/
    TextView tv_fname, tv_contact, tv_email, tv_about_doc,/*tv_lname, tv_mname,*/
            tv_website, tv_doc_address;
    ImageView doctor_image;
    ImageView calling_btn, share_btn, rate_btn, map_btn;
    Context context;
    RecyclerView recyclerView;
    LinearLayout ll_gallery_button, ll_profile, ll_detail, ll_gallaery, ll_like, inclide_profile;
    LinearLayout ll_doctor_gellary;
    RecyclerView rv_doctor_detail_gallery, rv_doctor_detail_video;
    List<String> rv_list = new ArrayList<>();
    RecyclerView.Adapter listAdapter;

    Button btn_gallery_photo, btn_gallery_video;
    String[] item;
    String[] award_acchiveitem;
    String[] award_dateitem;
    String[] award_fromitem;
    String[] memberitem;
    String[] member_from_item;
    String[] member_date_item;
    String[] experience_item;
    String[] experience_from_item;
    String[] experience_start_date_item;
    String[] education_item;
    String[] education_start_date;
    String[] education_bord_uni;
    String[] doctor_video;
    String[] e_person_name;
    String[] e_contact_no;
    String[] e_service;


    LinearLayout ln_gallery_button, lndummy, ln_profile;
    ProgressDialog dialog;
    private List<DetailsModel> specialList = new ArrayList<>();
    private List<DetailsModel> awardList = new ArrayList<>();
    private List<DetailsModel> memberList = new ArrayList<>();
    private List<DetailsModel> experienceList = new ArrayList<>();
    private List<DetailsModel> educationeList = new ArrayList<>();
    private List<DetailsModel> galleryList = new ArrayList<>();

    private RecyclerView rv_contact_person, rv_extra_service;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager1;
    private RecyclerView.LayoutManager layoutManager2;


    private RecyclerView.Adapter adapter;
    LinearLayout ll_doctor_video;
    private ImageView iv_like;
    private String call_number;

    private String dr_name;
    EditText review_mobno;
    EditText edt_text_review;
    TextView tv_view_all_rating, tv_number_of_rating, tv_doc_rate_in_number;
    int number_of_rating;

    RecyclerView rv_view_doc_mainpage_rating;
    RecyclerView.Adapter listAdapter2;
    RecyclerView.LayoutManager mLayoutManager;
    List<RatingModel> listuser = new ArrayList<>();
    RatingBar doctor_ratingbar, ratingbar_doc_rating;
    float rating;
    EditText review_name_of_person;
    Bitmap bitmap;
    public static final int RequestPermissionCode = 1;
    ImageView iv_share;
    String d_latitude;
    String d_longitude;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoActionbarTheme);
        setContentView(R.layout.activity_extralist_detail);
        init();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        getData();
        DocPhotoGalleryRecyclerView();
        load_doc_rating();
        DocVideoGalleryRecyclerView();

    }

    public void init() {


        // btn_gallery_photo, btn_gallery_video;
        iv_back=(ImageView)findViewById(R.id.iv_back);
        tv_doc_address = (TextView) findViewById(R.id.tv_doc_address);
        ratingbar_doc_rating = (RatingBar) findViewById(R.id.ratingbar_doc_rating);
        tv_doc_rate_in_number = (TextView) findViewById(R.id.tv_doc_rate_in_number);
        tv_view_all_rating = (TextView) findViewById(R.id.tv_view_all_rating);
        tv_number_of_rating = (TextView) findViewById(R.id.tv_number_of_rating);
        btn_gallery_photo = (Button) findViewById(R.id.btn_gallery_photo);
        btn_gallery_video = (Button) findViewById(R.id.btn_gallery_video);

        ll_doctor_video = (LinearLayout) findViewById(R.id.ll_doctor_video);
        rv_view_doc_mainpage_rating = (RecyclerView) findViewById(R.id.rv_view_doc_mainpage_rating);


        rv_doctor_detail_video = (RecyclerView) findViewById(R.id.rv_doctor_detail_video);
        rv_doctor_detail_gallery = (RecyclerView) findViewById(R.id.rv_doctor_detail_gallery);


        iv_like = (ImageView) findViewById(R.id.iv_like);
        ll_gallery_button = (LinearLayout) findViewById(R.id.ll_gallery_button);
        ll_profile = (LinearLayout) findViewById(R.id.ll_profile);
        ll_detail = (LinearLayout) findViewById(R.id.ll_detail);
        ll_gallaery = (LinearLayout) findViewById(R.id.ll_gallaery);
        ll_like = (LinearLayout) findViewById(R.id.ll_like);
        inclide_profile = (LinearLayout) findViewById(R.id.inclide_profile);
        ll_doctor_gellary = (LinearLayout) findViewById(R.id.ll_doctor_gellary);

        iv_profile = (ImageView) findViewById(R.id.iv_profile);
        iv_detail = (ImageView) findViewById(R.id.iv_detail);
        iv_gallery = (ImageView) findViewById(R.id.iv_gallery);
        calling_btn = (ImageView) findViewById(R.id.calling_btn);
        share_btn = (ImageView) findViewById(R.id.share_btn);
        rate_btn = (ImageView) findViewById(R.id.rate_btn);
        map_btn = (ImageView) findViewById(R.id.map_btn);
        lndummy = (LinearLayout) findViewById(R.id.lndummy);
        lndummy.setOnClickListener(this);

        tv_fname = (TextView) findViewById(R.id.txt_fname);
        tv_about_doc = (TextView) findViewById(R.id.tv_about_doc);
        tv_contact = (TextView) findViewById(R.id.txt_contact);
        // tv_landline = (TextView) findViewById(R.id.txt_landline);
        tv_email = (TextView) findViewById(R.id.txt_email);
        tv_website = (TextView) findViewById(R.id.txt_website);
        //tv_degree = (TextView) findViewById(R.id.txt_degree);

        doctor_image = (ImageView) findViewById(R.id.doctor_image);

        rv_extra_service = (RecyclerView) findViewById(R.id.rv_extra_service);
        rv_contact_person = (RecyclerView) findViewById(R.id.rv_contact_person);

        layoutManager = new GridLayoutManager(this, 1);



   /*     rv_extra_service.setLayoutManager(new GridLayoutManager(this, 2));
        rv_extra_service.setLayoutManager(layoutManager);*/

/*        rv_contact_person.setLayoutManager(new LinearLayoutManager(this));
        rv_contact_person.setLayoutManager(layoutManager1);

        rv_extra_service.setLayoutManager(new LinearLayoutManager(this));
        rv_extra_service.setLayoutManager(layoutManager1);*/


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
        tv_view_all_rating.setOnClickListener(this);

        // btn_gallery_photo, btn_gallery_video;
        btn_gallery_photo.setOnClickListener(this);
        btn_gallery_video.setOnClickListener(this);

    }

    public void load_doc_rating() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();

            params.add("e_id", Constant.e_id);
            client.post(Constant.doctor_live_url + "extra_rating_view.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null && !responseString.equals("[]")) {
                        listuser.clear();

                        try {
                            Log.e("view_res", responseString);
                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONArray resultsarray = jsonObj.getJSONArray("response");
                            for (int i = 0; i < resultsarray.length(); i++) {
                                RatingModel temp = new RatingModel();
                                temp.setDr_id(resultsarray.getJSONObject(i).getString("er_id"));
                                temp.setD_id(resultsarray.getJSONObject(i).getString("e_id"));
                                temp.setDr_name(resultsarray.getJSONObject(i).getString("er_name"));

                                temp.setDr_contact(resultsarray.getJSONObject(i).getString("er_contact"));
                                temp.setDr_rating(resultsarray.getJSONObject(i).getString("er_rating"));
                                temp.setDr_desc(resultsarray.getJSONObject(i).getString("er_desc"));

                                rating = rating + Float.parseFloat(resultsarray.getJSONObject(i).getString("er_rating"));
                                number_of_rating = i + 1;
                                listuser.add(temp);
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        DecimalFormat df = new DecimalFormat("0.#");
                        String number_of_rate = String.valueOf(number_of_rating);
                        tv_number_of_rating.setText(number_of_rate + " Rating");
                        tv_doc_rate_in_number.setText(String.valueOf(df.format(rating / number_of_rating)));
                        ratingbar_doc_rating.setRating(rating / number_of_rating);
                        rv_view_doc_mainpage_rating.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(Extra_details_CategoryActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv_view_doc_mainpage_rating.setLayoutManager(mLayoutManager);
                        listAdapter2 = new ViewLastPageRatingAdapter(Extra_details_CategoryActivity.this, listuser);
                        rv_view_doc_mainpage_rating.setAdapter(listAdapter2);

                    }
                    dialog.dismiss();

                }
            });
        } else {
            Toast.makeText(Extra_details_CategoryActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void DocVideoGalleryRecyclerView() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();
            params.add("e_id", Constant.e_id);

            client.post(Constant.doctor_live_url + "extra_video.php", params, new TextHttpResponseHandler() {
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
                                //DetailsModel temp = new DetailsModel();
                                // temp.setDoctor_gallery_image(Constant.Imageuri + resultsarray.getJSONObject(i).getString("dg_img"));
                                Log.e("ev_path", "" + resultsarray.getJSONObject(i).getString("ev_path"));
                                String str = resultsarray.getJSONObject(i).getString("ev_path");

                                doctor_video = str.split(",");
                                // galleryList.add(temp);
                                dialog.dismiss();

                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }

                        rv_doctor_detail_video.setHasFixedSize(true);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        //  layoutManager2 = new LinearLayoutManager(DoctorFullDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv_doctor_detail_video.setLayoutManager(gridLayoutManager);
                        listAdapter = new DoctorFullDetailVideoAdapter(Extra_details_CategoryActivity.this, doctor_video);
                        rv_doctor_detail_video.setAdapter(listAdapter);
                        rv_doctor_detail_video.addOnItemTouchListener(new RecyclerItemClickListener(Extra_details_CategoryActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
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
                        dialog.dismiss();
                        //  ll_similar_product.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            Toast.makeText(Extra_details_CategoryActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void DocPhotoGalleryRecyclerView() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();
            params.add("e_id", Constant.e_id);

            client.post(Constant.doctor_live_url + "extra_gallery.php", params, new TextHttpResponseHandler() {
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
                                temp.setDoctor_gallery_image(Constant.Imageuri + resultsarray.getJSONObject(i).getString("eg_img"));
                                galleryList.add(temp);
                                dialog.dismiss();

                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        rv_doctor_detail_gallery.setHasFixedSize(true);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        // layoutManager2 = new LinearLayoutManager(DoctorFullDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv_doctor_detail_gallery.setLayoutManager(gridLayoutManager);
                        listAdapter = new DoctorFullDetailAdapter(Extra_details_CategoryActivity.this, galleryList);
                        rv_doctor_detail_gallery.setAdapter(listAdapter);
                        rv_doctor_detail_gallery.addOnItemTouchListener(new RecyclerItemClickListener(Extra_details_CategoryActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
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
                        dialog.dismiss();
                        //  ll_similar_product.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            Toast.makeText(Extra_details_CategoryActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(Extra_details_CategoryActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.extra_details_category_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        Log.e("extra_res", responseString);
                        if (responseString != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(responseString);
                                JSONArray resultsarray = jsonObj.getJSONArray("response");
                                for (int i = 0; i < resultsarray.length(); i++) {
                                    ExtraListModel temp = new ExtraListModel();
                                    tv_fname.setText(resultsarray.getJSONObject(i).getString("e_name"));
                                    dr_name = resultsarray.getJSONObject(i).getString("e_name");
                                    String str = resultsarray.getJSONObject(i).getString("e_landline");
                                    String[] contact = str.split(",");
                                    call_number = contact[0];
                                    tv_contact.setText(resultsarray.getJSONObject(i).getString("e_landline"));
                                    tv_email.setText(resultsarray.getJSONObject(i).getString("e_email"));
                                    tv_website.setText(resultsarray.getJSONObject(i).getString("e_website"));
                                    tv_about_doc.setText(resultsarray.getJSONObject(i).getString("e_aboutus"));
                                    tv_doc_address.setText(resultsarray.getJSONObject(i).getString("e_address"));
                                    d_latitude = resultsarray.getJSONObject(0).getString("e_latitude");
                                    d_longitude = resultsarray.getJSONObject(0).getString("e_longitude");

                                    // temp.setAward_from(resultsarray.getJSONObject(i).getString("d_member_from"));
                                    String e_person = resultsarray.getJSONObject(i).getString("e_person");
                                    e_person_name = e_person.split(",");

                                    String e_contact = resultsarray.getJSONObject(i).getString("e_contact_person");
                                    e_contact_no = e_contact.split(",");

                                    String service = resultsarray.getJSONObject(i).getString("e_services");
                                    e_service = service.split(",");


                                    Glide.with(getApplication())
                                            .load(Constant.Imageuri + resultsarray.getJSONObject(i).getString("e_img"))
                                            .asBitmap()
                                            .placeholder(R.drawable.doctor_slider)
                                            .into(doctor_image);
                                    progressDialog.dismiss();

                                    /*ExtraListModel doctor = new ExtraListModel();
                                    dr_name = "Dr." + resultsarray.getJSONObject(i).getString("d_firstname") + " " + resultsarray.getJSONObject(i).getString("d_middelname") + " " + resultsarray.getJSONObject(i).getString("d_lastname");
                                    tv_fname.setText(dr_name);
                                   *//* tv_mname.setText(resultsarray.getJSONObject(i).getString("d_middelname"));
                                    tv_lname.setText(resultsarray.getJSONObject(i).getString("d_lastname"));*//*

                                    String str = resultsarray.getJSONObject(i).getString("d_contact");
                                    String[] contact = str.split(",");
                                    call_number = contact[0];
                                    tv_contact.setText(resultsarray.getJSONObject(i).getString("d_contact"));
                                    Log.e("d_contact", "" + resultsarray.getJSONObject(i).getString("d_contact"));
                                    tv_landline.setText(resultsarray.getJSONObject(i).getString("d_landline"));
                                    tv_email.setText(resultsarray.getJSONObject(i).getString("e_email"));
                                    tv_website.setText(resultsarray.getJSONObject(i).getString("d_website"));
                                    tv_degree.setText(resultsarray.getJSONObject(i).getString("d_degree"));
                                    tv_about_doc.setText(resultsarray.getJSONObject(i).getString("e_aboutus"));
                                    doctor.setName(resultsarray.getJSONObject(i).getString("d_specilization"));
                                    latitude = resultsarray.getJSONObject(i).getString("d_latitude");
                                    longitude = resultsarray.getJSONObject(i).getString("d_latitude");
                                    String spe = resultsarray.getJSONObject(i).getString("d_specilization");
                                    item = spe.split(",");
                                    specialList.add(doctor);

                                    doctor.setAward_acchive(resultsarray.getJSONObject(i).getString("d_award_acchive"));

                                    String award_acchive = resultsarray.getJSONObject(i).getString("d_award_acchive");
                                    award_acchiveitem = award_acchive.split(",");
                                    awardList.add(doctor);
                                    doctor.setAward_date(resultsarray.getJSONObject(i).getString("d_award_date"));

                                    String award_date = resultsarray.getJSONObject(i).getString("d_award_date");
                                    award_dateitem = award_date.split(",");
                                    awardList.add(doctor);
                                    doctor.setAward_from(resultsarray.getJSONObject(i).getString("d_award_from"));
                                    String award_from = resultsarray.getJSONObject(i).getString("d_award_from");
                                    award_fromitem = award_from.split(",");
                                    awardList.add(doctor);

                                                            *//* MEMBER*//*
                                    doctor.setAward_acchive(resultsarray.getJSONObject(i).getString("d_member"));
                                    String member = resultsarray.getJSONObject(i).getString("d_member");
                                    memberitem = member.split(",");
                                    memberList.add(doctor);
*//*
                                    doctor.setAward_date(resultsarray.getJSONObject(i).getString("d_member_date"));
                                    String member_date = resultsarray.getJSONObject(i).getString("d_member_date");
                                    member_date_item = member_date.split(",");
                                    memberList.add(doctor);*//*

                                    doctor.setAward_from(resultsarray.getJSONObject(i).getString("d_member_from"));
                                    String member_from = resultsarray.getJSONObject(i).getString("d_member_from");
                                    member_from_item = member_from.split(",");
                                    memberList.add(doctor);


                                                        *//*  Experience*//*
                                    doctor.setAward_acchive(resultsarray.getJSONObject(i).getString("d_experience"));
                                    String experience = resultsarray.getJSONObject(i).getString("d_experience");
                                    experience_item = experience.split(",");
                                    experienceList.add(doctor);

                                    doctor.setAward_date(resultsarray.getJSONObject(i).getString("d_exp_start_date"));
                                    String experience_start_date = resultsarray.getJSONObject(i).getString("d_exp_start_date");
                                    experience_start_date_item = experience_start_date.split(",");
                                    experienceList.add(doctor);


                                    doctor.setAward_from(resultsarray.getJSONObject(i).getString("d_experience_from"));
                                    String experience_from = resultsarray.getJSONObject(i).getString("d_experience_from");
                                    experience_from_item = experience_from.split(",");
                                    experienceList.add(doctor);

                                                              *//*  Education*//*

                                    doctor.setAward_acchive(resultsarray.getJSONObject(i).getString("d_education"));
                                    String education = resultsarray.getJSONObject(i).getString("d_education");
                                    education_item = education.split(",");
                                    educationeList.add(doctor);

                                    doctor.setAward_date(resultsarray.getJSONObject(i).getString("d_edu_start_date"));
                                    String education_date = resultsarray.getJSONObject(i).getString("d_edu_start_date");
                                    education_start_date = education_date.split(",");
                                    educationeList.add(doctor);

                                    doctor.setAward_from(resultsarray.getJSONObject(i).getString("d_bord_uni"));
                                    String bord_uni = resultsarray.getJSONObject(i).getString("d_bord_uni");
                                    education_bord_uni = bord_uni.split(",");
                                    educationeList.add(doctor);


                                    Glide.with(getApplication())
                                            .load(Constant.Imageuri + resultsarray.getJSONObject(i).getString("d_img"))
                                            .asBitmap()
                                            .placeholder(R.drawable.doctor_slider)
                                            .into(doctor_image);
                                    progressDialog.dismiss();*/


                                }
                            } catch (JSONException e) {
                                Toast.makeText(Extra_details_CategoryActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }
                            rv_contact_person.setHasFixedSize(false);
                            layoutManager1 = new LinearLayoutManager(Extra_details_CategoryActivity.this, LinearLayoutManager.VERTICAL, false);
                            rv_contact_person.setLayoutManager(layoutManager1);
                            listAdapter = new ExtraDetailContactPersonAdapter(Extra_details_CategoryActivity.this, e_person_name, e_contact_no);
                            rv_contact_person.setAdapter(listAdapter);

                            rv_extra_service.setHasFixedSize(false);
                            layoutManager2 = new LinearLayoutManager(Extra_details_CategoryActivity.this, LinearLayoutManager.VERTICAL, false);
                            rv_extra_service.setLayoutManager(layoutManager2);
                            listAdapter = new DetailsAdapter(Extra_details_CategoryActivity.this, e_service);
                            rv_extra_service.setAdapter(listAdapter);
            /*                rv_contact_person.setHasFixedSize(true);
                            adapter = new ExtraDetailContactPersonAdapter(Extra_details_CategoryActivity.this, e_person_name, e_contact_no);
                            rv_contact_person.setLayoutManager(layoutManager);
                            rv_contact_person.setAdapter(adapter);

                            rv_extra_service.setHasFixedSize(true);
                            adapter = new DetailsAdapter(Extra_details_CategoryActivity.this, e_service);
                            rv_extra_service.setLayoutManager(layoutManager1);
                            rv_extra_service.setAdapter(adapter);*/

                            /*sp_recyclerView.setHasFixedSize(true);
//
                            adapter = new DetailsAdapter(Extra_details_CategoryActivity.this, item);
                            sp_recyclerView.setLayoutManager(layoutManager);
                            sp_recyclerView.setAdapter(adapter);

                            award_recyclerView.setHasFixedSize(true);
                            adapter = new AwardAdapter(Extra_details_CategoryActivity.this, award_acchiveitem, award_dateitem, award_fromitem);
                            award_recyclerView.setLayoutManager(layoutManager1);
                            award_recyclerView.setAdapter(adapter);

//
                            member_recyclerview.setHasFixedSize(true);
                            adapter = new AwardAdapter(Extra_details_CategoryActivity.this, memberitem, member_date_item, member_from_item);
                            member_recyclerview.setLayoutManager(member_layoutManager);
                            member_recyclerview.setAdapter(adapter);


                            experience_recyclerview.setHasFixedSize(true);
                            adapter = new AwardAdapter(Extra_details_CategoryActivity.this, experience_item, experience_start_date_item, experience_from_item);
                            experience_recyclerview.setLayoutManager(experience_layoutManager);
                            experience_recyclerview.setAdapter(adapter);


                            education_recyclerview.setHasFixedSize(true);
                            adapter = new AwardAdapter(Extra_details_CategoryActivity.this, education_item, education_start_date, education_bord_uni);
                            education_recyclerview.setLayoutManager(education_layoutManager);
                            education_recyclerview.setAdapter(adapter);*/
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Extra_details_CategoryActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("e_id", Constant.e_id);
                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void sharebtn() {

        if (checkPermission()) {
            bitmap = getBitmapFromView(doctor_image);
            //saveImage(bitmap);
            String pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
            Uri bmpUri = Uri.parse(pathofBmp);
            final Intent emailIntent1 = new Intent(Intent.ACTION_SEND);
            emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
            emailIntent1.setType("image/png");
           /* String upToNCharacters = textview_news_detail.getText().toString().substring(0, Math.min(textview_news_detail.getText().toString().length(), 160));
            //  fulldisplay_description.getText().toString() +"\n"+fulldisplay_address.getText().toString()
            emailIntent1.putExtra(Intent.EXTRA_TEXT,upToNCharacters+"\n"+ "Get more Description:\nhttp://rajkotcityguide.com");
           */
            startActivity(Intent.createChooser(emailIntent1, "Share via"));
        } else {
            requestPermission();
        }
    }

    public void ratebtn(View v) {

        AppRater.app_launched(this);


    }


//        share_btn.setDrawingCacheEnabled(true);
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        Bitmap bitmap = ((BitmapDrawable) i.getDrawable()).getBitmap();
//
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "deal_photo.jpg");
//        try {
//            file.createNewFile();
//            FileOutputStream fo = new FileOutputStream(file);
//            fo.write(bytes.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Intent share = new Intent(Intent.ACTION_SEND);
//        share.setType("image/*");
//        Uri uri = Uri.parse("file:///sdcard/temporary_file.jpg");
//        share.putExtra(Intent.EXTRA_STREAM, uri);
//        startActivity(Intent.createChooser(share, "Share image Using"));


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }



/*        iv_gallery.setOnClickListener(this);
        iv_profile.setOnClickListener(this);
        iv_detail.setOnClickListener(this);
        iv_like.setOnClickListener(this);*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_gallery:
                ll_doctor_gellary.setVisibility(View.VISIBLE);
                inclide_profile.setVisibility(View.GONE);
                ll_gallery_button.setVisibility(View.VISIBLE);
                lndummy.setVisibility(View.GONE);
                break;
            case R.id.iv_profile:
                ll_doctor_gellary.setVisibility(View.GONE);
                inclide_profile.setVisibility(View.VISIBLE);
                ll_gallery_button.setVisibility(View.GONE);
                lndummy.setVisibility(View.GONE);
                break;
            case R.id.iv_detail:
                lndummy.setVisibility(View.VISIBLE);
                ll_doctor_gellary.setVisibility(View.GONE);
                inclide_profile.setVisibility(View.GONE);
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
                ll_doctor_gellary.setVisibility(View.VISIBLE);
                ll_doctor_video.setVisibility(View.GONE);
                break;
            case R.id.btn_gallery_video:
                ll_doctor_video.setVisibility(View.VISIBLE);
                ll_doctor_gellary.setVisibility(View.GONE);
                break;
            case R.id.map_btn:
                if (d_latitude.equals(null) && d_longitude.equals(null)) {
                } else {

                    Intent intent = new Intent(Extra_details_CategoryActivity.this, MapsActivity.class);
                    intent.putExtra("d_latitude", d_latitude);
                    intent.putExtra("d_longitude", d_longitude);
                    intent.putExtra("l_name", dr_name);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
                break;
            case R.id.calling_btn:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", call_number, null));
                startActivity(intent);
                break;
            case R.id.rate_btn:
                LayoutInflater li = LayoutInflater.from(Extra_details_CategoryActivity.this);
                View vi = li.inflate(R.layout.review_edittext, null);


                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(Extra_details_CategoryActivity.this);
                alertDialogBuilder.setView(vi);
                TextView review_name = (TextView) vi.findViewById(R.id.review_name);
                review_name.setText(dr_name);
                review_mobno = (EditText) vi.findViewById(R.id.review_mobno);
                review_name_of_person = (EditText) vi.findViewById(R.id.review_name_of_person);
                edt_text_review = (EditText) vi.findViewById(R.id.edt_text_review);
                doctor_ratingbar = (RatingBar) vi.findViewById(R.id.doctor_ratingbar);
                final float f_rating = doctor_ratingbar.getRating();
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RatingApi(review_name_of_person.getText().toString(), review_mobno.getText().toString(), edt_text_review.getText().toString(), String.valueOf(f_rating));
                      /*  if (mvname != null) {
                            if (reviewvalidate()) {
                                reviewsubmit("Movies", mvname, review_mobno.getText().toString(), edt_text_review.getText().toString());
                            }
                        }*/

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
                        RatingApi(review_name_of_person.getText().toString(), review_mobno.getText().toString(), edt_text_review.getText().toString(), String.valueOf(doctor_ratingbar.getRating()));

            /*            if (mvname != null) {
                            if (reviewvalidate()) {
                                reviewsubmit("Movies", mvname, review_mobno.getText().toString(), edt_text_review.getText().toString());
                            }
                        }*/
                        alertDialog.dismiss();
                    }
                });

                /*LayoutInflater li = LayoutInflater.from(DoctorFullDetailActivity.this);
                View vi = li.inflate(R.layout.pop_pup, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DoctorFullDetailActivity.this);


                alertDialogBuilder.setView(vi);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.show();

                Button btn_submit = (Button) vi.findViewById(R.id.btn_submit);
                Button btn_cancle = (Button) vi.findViewById(R.id.btn_cancle);
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                TextView txt_rate_title = (TextView) vi.findViewById(R.id.txt_rate_title);
                final EditText edt_mobile = (EditText) vi.findViewById(R.id.edt_mobile);
                EditText et_name = (EditText) vi.findViewById(R.id.et_name);
                EditText edt_description = (EditText) vi.findViewById(R.id.edt_description);
                RatingBar doctor_ratingbar = (RatingBar) vi.findViewById(R.id.doctor_ratingbar);
                txt_rate_title.setText(dr_name);
                final String des = edt_description.getText().toString();
                final String doc_rating = String.valueOf(doctor_ratingbar.getRating());
                final String mob_no = edt_mobile.getText().toString();
                final String name = et_name.getText().toString();
                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((edt_mobile.getText().toString().trim().isEmpty())) {
                            edt_mobile.setError("Please Enter valid Mobile No");
                            Toast.makeText(DoctorFullDetailActivity.this, "Enter correct mobile no", Toast.LENGTH_SHORT).show();
                        } else {
                            //RatingApi(name, mob_no, des, doc_rating);
                            alertDialog.dismiss();

                        }

                    }
                });


                btn_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });*/
                break;
            case R.id.tv_view_all_rating:
                startActivity(new Intent(getApplicationContext(), ViewAllDocRatingActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    private void RatingApi(String name, String mobile_no, String description, String ratting) {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();

            params.add("e_id", Constant.e_id);
            params.add("er_name", name);
            params.add("er_contact", mobile_no);
            params.add("er_desc", description);
            params.add("er_rating", ratting);


            client.post(Constant.doctor_live_url + "extra_rating.php", params, new TextHttpResponseHandler() {
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

                            Toast.makeText(Extra_details_CategoryActivity.this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            return;
                        } else {
                            dialog.dismiss();
                            return;
                        }

                    } else {
                        Toast.makeText(Extra_details_CategoryActivity.this, "data is not available!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(Extra_details_CategoryActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        view.draw(canvas);
        return returnedBitmap;
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(Extra_details_CategoryActivity.this, new
                String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);
    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(Extra_details_CategoryActivity.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

}


