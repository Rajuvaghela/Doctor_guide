package example.com.doctor_guide.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import example.com.doctor_guide.Adapter.ViewAllDocRatingAdapter;
import example.com.doctor_guide.Model.RatingModel;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;

public class ViewAllDocRatingActivity extends AppCompatActivity {
    RecyclerView rv_view_doc_rating;
    RecyclerView.Adapter listAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ProgressDialog dialog;
    List<RatingModel> listuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_doc_rating);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        rv_view_doc_rating = (RecyclerView) findViewById(R.id.rv_view_doc_rating);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        listuser = new ArrayList<>();
        if (Constant.cat_id.equals("1")) {
            load_doc_rating();
        } else if (Constant.cat_id.equals("2")) {
            load_hospital_rating();
        } else {
            load_extra_rating();
        }

    }

    void load_hospital_rating() {
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
                                temp.setDr_id(resultsarray.getJSONObject(i).getString("hr_id"));
                                temp.setD_id(resultsarray.getJSONObject(i).getString("h_id"));
                                temp.setDr_name(resultsarray.getJSONObject(i).getString("hr_name"));
                                temp.setDr_contact(resultsarray.getJSONObject(i).getString("hr_contact"));
                                temp.setDr_rating(resultsarray.getJSONObject(i).getString("hr_rating"));
                                temp.setDr_desc(resultsarray.getJSONObject(i).getString("hr_desc"));
                                listuser.add(temp);
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        rv_view_doc_rating.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(ViewAllDocRatingActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv_view_doc_rating.setLayoutManager(mLayoutManager);
                        listAdapter = new ViewAllDocRatingAdapter(ViewAllDocRatingActivity.this, listuser);
                        rv_view_doc_rating.setAdapter(listAdapter);

                    }


                }
            });
        } else {
            Toast.makeText(ViewAllDocRatingActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    void load_extra_rating() {
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
                    if (responseString != null) {
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
                                listuser.add(temp);
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        rv_view_doc_rating.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(ViewAllDocRatingActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv_view_doc_rating.setLayoutManager(mLayoutManager);
                        listAdapter = new ViewAllDocRatingAdapter(ViewAllDocRatingActivity.this, listuser);
                        rv_view_doc_rating.setAdapter(listAdapter);

                    }


                }
            });
        } else {
            Toast.makeText(ViewAllDocRatingActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void load_doc_rating() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();

            params.add("d_id", Constant.d_id);
            client.post(Constant.doctor_live_url + "doctor_rating_view.php", params, new TextHttpResponseHandler() {
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
                                temp.setDr_id(resultsarray.getJSONObject(i).getString("dr_id"));
                                temp.setD_id(resultsarray.getJSONObject(i).getString("d_id"));
                                temp.setDr_name(resultsarray.getJSONObject(i).getString("dr_name"));
                                temp.setDr_contact(resultsarray.getJSONObject(i).getString("dr_contact"));
                                temp.setDr_rating(resultsarray.getJSONObject(i).getString("dr_rating"));
                                temp.setDr_desc(resultsarray.getJSONObject(i).getString("dr_desc"));
                                listuser.add(temp);
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        rv_view_doc_rating.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(ViewAllDocRatingActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv_view_doc_rating.setLayoutManager(mLayoutManager);
                        listAdapter = new ViewAllDocRatingAdapter(ViewAllDocRatingActivity.this, listuser);
                        rv_view_doc_rating.setAdapter(listAdapter);

                    }


                }
            });
        } else {
            Toast.makeText(ViewAllDocRatingActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
