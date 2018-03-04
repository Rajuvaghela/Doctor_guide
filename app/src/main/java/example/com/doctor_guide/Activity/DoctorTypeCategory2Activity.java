package example.com.doctor_guide.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import example.com.doctor_guide.Adapter.DoctorChildAdapter;
import example.com.doctor_guide.Model.DoctorName;
import example.com.doctor_guide.util.Constant;
import example.com.doctor_guide.Other.SimpleDividerItemDecoration;
import example.com.homepagefordoctor_guide.R;
import example.com.doctor_guide.app.AppController;

/**
 * Created by Khodal on 09/08/2017.
 */

public class DoctorTypeCategory2Activity extends AppCompatActivity {

    private List<DoctorName> doctornameList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DoctorChildAdapter adapter;
    private Button readmore;
    private RatingBar ratebar;
    private RecyclerView.LayoutManager layoutManager;
    private final String url = Constant.doctor_list_categoryurl ;
    EditText edt_search_subcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        readmore =(Button)findViewById(R.id.btnreadmore);
        ratebar =(RatingBar)findViewById(R.id.ratebar);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        edt_search_subcategory = (EditText)findViewById(R.id.edt_search_subcategory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edt_search_subcategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text =  edt_search_subcategory.getText().toString().toLowerCase(Locale.getDefault());
                if (text != null){
                    adapter.filter(text);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        getData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(DoctorTypeCategory2Activity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        doctornameList.clear();
                        Log.d("res", responseString);
                        if (responseString != null) {
                            try {


                                JSONObject jsonObj = new JSONObject(responseString);
                                JSONArray resultsarray = jsonObj.getJSONArray("response");
                                for (int i = 0; i < resultsarray.length(); i++) {
                                    DoctorName doctor = new DoctorName();
                                    doctor.setName("Dr." + resultsarray.getJSONObject(i).getString("d_firstname"));
                                    doctor.setMname(resultsarray.getJSONObject(i).getString("d_middelname"));
                                    doctor.setLname(resultsarray.getJSONObject(i).getString("d_lastname"));
                                    Log.e("d_city",""+ resultsarray .getJSONObject(i).getString("d_lastname"));
                                    doctor.setAddress(resultsarray.getJSONObject(i).getString("d_address"));
                                    doctor.setSub_id(resultsarray .getJSONObject(i).getString("sub_id"));
                                    doctor.setImageurl(Constant.Imageuri+resultsarray .getJSONObject(i).getString("d_img"));

                                    doctor.setDoctor_l_id(resultsarray .getJSONObject(i).getString("d_id"));
                                    Log.e("l_id",""+ resultsarray .getJSONObject(i).getString("d_id"));
                                    Log.e("l_img",""+ resultsarray .getJSONObject(i).getString("d_img"));
                                    Log.e("d_city",""+ resultsarray .getJSONObject(i).getString("d_city"));

                                    doctornameList.add(doctor);
                                    progressDialog.dismiss();


                                }
                            } catch (JSONException e) {
                                Toast.makeText(DoctorTypeCategory2Activity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }

                        }


                        recyclerView.setHasFixedSize(true);
//                        layoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new DoctorChildAdapter(DoctorTypeCategory2Activity.this, doctornameList);
                        recyclerView.setAdapter(adapter);




                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DoctorTypeCategory2Activity.this,error.toString(),Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("sub_id",Constant.sub_id);



                return params;
            }

        };

//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        AppController.getInstance().addToRequestQueue(stringRequest);
//        requestQueue.add(stringRequest);
//        doctorList.clear();
//        adapter = new DoctorAdapter(this,doctorList);
//        recyclerView.setAdapter(adapter);


    }


    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


//    public  void readmore(View v)
//    {
//
//
//      startActivity(new Intent(this,FourthActivity.class));
//
//
//    }






}


