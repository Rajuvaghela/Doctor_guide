package example.com.doctor_guide.Activity;

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
import android.view.MenuItem;
import android.view.View;
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

import example.com.doctor_guide.Adapter.BloodBankChildAdapter;
import example.com.doctor_guide.Model.DoctorName;
import example.com.doctor_guide.Other.DividerItemDecorationforbloodbank;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;

public class Bloodbank_And_Stemcell_list_CategoryActivity extends AppCompatActivity {
    private static final String TAG = Bloodbank_And_Stemcell_list_CategoryActivity.class.getSimpleName();
    private final String url = Constant.bloodbank_category_url;


    private List<DoctorName> doctornameList = new ArrayList<>();
    private BloodBankChildAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    BloodBankChildAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        adapter = new DoctorAdapter(this,doctorList);
//
////        recyclerView.setAdapter(adapter);
//
//
        recyclerView.addItemDecoration(new DividerItemDecorationforbloodbank(this));
        final EditText edt_search_listcategory = (EditText)findViewById(R.id.edt_search_subcategory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edt_search_listcategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text =  edt_search_listcategory.getText().toString().toLowerCase(Locale.getDefault());
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
////        recyclerView.addOnItemTouchListener(
//                new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//
//
//
////                        Intent it = new Intent(getApplication(), Doctor_listActivity.class);
////                        it.putExtra(sub_id, doctorList.get(position).getSub_id());
////                        Constant.sub_id = doctorList.get(position).getSub_id();
////                        startActivity(it);
//
//                          }
//
//                    @Override
//                    public void onLongItemClick(View view, int position) {
//                        // do whatever
//                    }
//                }
//
//                )
//        );


        getData();
    }

    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(Bloodbank_And_Stemcell_list_CategoryActivity.this);
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
                                    doctor.setName(resultsarray.getJSONObject(i).getString("e_name"));
                                    doctor.setAddress(resultsarray.getJSONObject(i).getString("e_address"));
                                    doctor.setSub_id(resultsarray.getJSONObject(i).getString("sub_id"));
                                    doctor.setDoctor_l_id(resultsarray.getJSONObject(i).getString("e_id"));
                                    doctor.setImageurl(Constant.Imageuri + resultsarray.getJSONObject(i).getString("e_img"));
                                    Log.e("sub_id", "" + resultsarray.getJSONObject(i).getString("sub_id"));
                                    String id = doctor.getSub_id();
                                    Log.e("tag", "" + resultsarray.getJSONObject(i).getString("e_name"));
                                    doctornameList.add(doctor);
                                    progressDialog.dismiss();


                                }

                            } catch (JSONException e) {
                                Toast.makeText(Bloodbank_And_Stemcell_list_CategoryActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }


                        }


                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new BloodBankChildAdapter(Bloodbank_And_Stemcell_list_CategoryActivity.this, doctornameList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(Bloodbank_And_Stemcell_list_CategoryActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getApplicationContext(), Extra_details_CategoryActivity.class);
                                Constant.e_id = doctornameList.get(position).getDoctor_l_id();
                                startActivity(intent);
                            }
                        }));

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Bloodbank_And_Stemcell_list_CategoryActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("cat_id", Constant.cat_id);

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
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}






