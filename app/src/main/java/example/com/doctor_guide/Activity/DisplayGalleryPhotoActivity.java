package example.com.doctor_guide.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import example.com.doctor_guide.Adapter.DoctorDisplayPhotoAdapter;
import example.com.doctor_guide.Adapter.DoctorFullDetailAdapter;
import example.com.doctor_guide.Model.DetailsModel;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;

public class DisplayGalleryPhotoActivity extends AppCompatActivity {
    RecyclerView rv_doctor_gallery;

    ProgressDialog dialog;

    RecyclerView.Adapter listAdapter;
    RecyclerView.LayoutManager layoutManager2;
    private List<DetailsModel> galleryList = new ArrayList<>();
    String pos;
    ImageView iv_doctor_full_image;
    String image_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_gallery_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        pos = bundle.getString("pos");
        image_url = bundle.getString("img");
        rv_doctor_gallery = (RecyclerView) findViewById(R.id.rv_doctor_gallery);
        iv_doctor_full_image = (ImageView) findViewById(R.id.iv_doctor_full_image);
        Glide.with(getApplicationContext()).load(image_url).into(iv_doctor_full_image);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);

        Log.e("dis_pos", "" + pos);
        if(Constant.cat_id.equals("1")){
            DocPhotoGalleryRecyclerView();
        }else if(Constant.cat_id.equals("2")){

        }else if(Constant.cat_id.equals("3")){
            ExtraPhotoGalleryRecyclerView();
        }


    }

    void ExtraPhotoGalleryRecyclerView() {

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
                        if (responseString != null) {
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

                            rv_doctor_gallery.setHasFixedSize(true);
                            layoutManager2 = new LinearLayoutManager(DisplayGalleryPhotoActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            rv_doctor_gallery.setLayoutManager(layoutManager2);
                            listAdapter = new DoctorDisplayPhotoAdapter(DisplayGalleryPhotoActivity.this, galleryList);
                            rv_doctor_gallery.setAdapter(listAdapter);
                            rv_doctor_gallery.addOnItemTouchListener(new RecyclerItemClickListener(DisplayGalleryPhotoActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Glide.with(getApplicationContext()).load(galleryList.get(position).getDoctor_gallery_image()).into(iv_doctor_full_image);
                                }
                            }));

                        }
                        if (responseString.equals("[]")) {
                            //  ll_similar_product.setVisibility(View.GONE);
                        }
                    }
                });
            } else {
                Toast.makeText(DisplayGalleryPhotoActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
            }

    }

    public void DocPhotoGalleryRecyclerView() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();
            params.add("d_id", Constant.d_id);

            client.post(Constant.doctor_live_url + "doctor_gallery.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        galleryList.clear();

                        try {
                            Log.e("res_gallery", responseString);

                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONArray resultsarray = jsonObj.getJSONArray("response");
                            for (int i = 0; i < resultsarray.length(); i++) {
                                DetailsModel temp = new DetailsModel();
                                temp.setDoctor_gallery_image(Constant.Imageuri + resultsarray.getJSONObject(i).getString("dg_img"));
                                galleryList.add(temp);
                                dialog.dismiss();

                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }

                        rv_doctor_gallery.setHasFixedSize(true);
                        layoutManager2 = new LinearLayoutManager(DisplayGalleryPhotoActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv_doctor_gallery.setLayoutManager(layoutManager2);
                        listAdapter = new DoctorDisplayPhotoAdapter(DisplayGalleryPhotoActivity.this, galleryList);
                        rv_doctor_gallery.setAdapter(listAdapter);
                        rv_doctor_gallery.addOnItemTouchListener(new RecyclerItemClickListener(DisplayGalleryPhotoActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Glide.with(getApplicationContext()).load(galleryList.get(position).getDoctor_gallery_image()).into(iv_doctor_full_image);
                            }
                        }));

                    }
                    if (responseString.equals("[]")) {
                        //  ll_similar_product.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            Toast.makeText(DisplayGalleryPhotoActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
