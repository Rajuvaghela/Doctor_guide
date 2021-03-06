package example.com.doctor_guide.Activity;

/**
 * Created by Khodal on 26/08/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import example.com.doctor_guide.Adapter.DoctorAdapter;
import example.com.doctor_guide.Adapter.HospitalChildAdapter;
import example.com.doctor_guide.Model.DoctorName;
import example.com.doctor_guide.Other.DividerItemDecorationForChildCatHospital;
import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;


public class Hospitals_list_category extends AppCompatActivity {

    private List<DoctorName> doctornameList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HospitalChildAdapter adapter;
    private Button readmore;
    private RecyclerView.LayoutManager layoutManager;
    private final String url = Constant.hospital_list_categoryurl ;

    EditText edt_search_subcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        readmore =(Button)findViewById(R.id.btnreadmore);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.addItemDecoration(new DividerItemDecorationForChildCatHospital(this));
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

        final ProgressDialog progressDialog = new ProgressDialog(Hospitals_list_category.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        Log.d("res", responseString);
                        if (responseString != null) {
                            try {


                                JSONObject jsonObj = new JSONObject(responseString);
                                JSONArray resultsarray = jsonObj.getJSONArray("response");
                                for (int i = 0; i < resultsarray.length(); i++) {
                                    DoctorName doctor = new DoctorName();
                                    doctor.setName(resultsarray.getJSONObject(i).getString("h_name"));
                                    doctor.setAddress(resultsarray.getJSONObject(i).getString("h_address"));
                                    doctor.setImageurl(Constant.Imageuri+resultsarray .getJSONObject(i).getString("h_img"));                                    doctor.setDoctor_l_id(resultsarray .getJSONObject(i).getString("h_id"));
                                    Log.e("tag", "" + resultsarray.getJSONObject(i).getString("h_name"));
                                    doctornameList.add(doctor);
                                    progressDialog.dismiss();


                                }
                            } catch (JSONException e) {
                                Toast.makeText(Hospitals_list_category.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }

                        }


                        recyclerView.setHasFixedSize(true);
//                        layoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new HospitalChildAdapter(Hospitals_list_category.this, doctornameList);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Hospitals_list_category.this,error.toString(),Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("sub_id", Constant.sub_id);


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
//        startActivity(new Intent(this,FourthActivity.class));
//    }




}



