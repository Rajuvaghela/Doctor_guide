package example.com.doctor_guide.Activity;

import android.app.ActivityOptions;
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

import example.com.doctor_guide.Adapter.DoctorAdapter;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.util.Constant;
import example.com.doctor_guide.Model.Doctor;
import example.com.doctor_guide.Other.DividerItemDecorationForHospital;
import example.com.homepagefordoctor_guide.R;
import example.com.doctor_guide.app.AppController;

public class Hospitals_subCategory extends AppCompatActivity {
    private static final String TAG = Hospitals_subCategory.class.getSimpleName();
    private final String url = Constant.sub_categoryurl;


    private List<Doctor> doctorList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    DoctorAdapter  adapter;
    EditText edt_search_subcategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        adapter = new DoctorAdapter(this,doctorList);
//
////        recyclerView.setAdapter(adapter);
//
//
        recyclerView.addItemDecoration(new DividerItemDecorationForHospital(this));
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

        final ProgressDialog progressDialog = new ProgressDialog(Hospitals_subCategory.this);
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
                                    Doctor doctor = new Doctor();
                                    doctor.setTitle(resultsarray.getJSONObject(i).getString("sub_name"));
                                    doctor.setSub_id(resultsarray.getJSONObject(i).getString("sub_id"));
                                    doctor.setImageurl(Constant.Imageuri + resultsarray.getJSONObject(i).getString("sub_img"));
                                    Log.e("sub_id", "" + resultsarray.getJSONObject(i).getString("sub_id"));
                                    String id = doctor.getSub_id();
                                    Log.e("tag", "" + resultsarray.getJSONObject(i).getString("sub_name"));
                                    doctorList.add(doctor);
                                    progressDialog.dismiss();


                                }

                            } catch (JSONException e) {
                                Toast.makeText(Hospitals_subCategory.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }


                        }


                        recyclerView.setHasFixedSize(true);
//                        layoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new DoctorAdapter(Hospitals_subCategory.this, doctorList);

                        recyclerView.setAdapter(adapter);
                        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(Hospitals_subCategory.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent it = new Intent(getApplicationContext(), Hospitals_list_category.class);
//                                it.putExtra(sub_id,doctorList.get(position).getSub_id());
                                Log.e("position", "" + position);
                                Log.e("sub_id", "" + doctorList.get(position).getSub_id());
                                Constant.sub_id = doctorList.get(position).getSub_id();
                                startActivity(it);
                            }
                        }));

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Hospitals_subCategory.this, error.toString(), Toast.LENGTH_LONG).show();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}






