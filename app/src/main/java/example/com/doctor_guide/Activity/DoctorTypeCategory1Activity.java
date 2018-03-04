package example.com.doctor_guide.Activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import example.com.doctor_guide.Model.Doctor;
import example.com.doctor_guide.Other.DividerItemDecoration;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;

public class DoctorTypeCategory1Activity extends AppCompatActivity {
    private static final String TAG = DoctorTypeCategory1Activity.class.getSimpleName();
    private final String url = Constant.sub_categoryurl;


    private List<Doctor> doctorList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    DoctorAdapter adapter;
    EditText edt_search_subcategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this));
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
    }

    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(DoctorTypeCategory1Activity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        Log.e("res_doc_type", responseString);
                        if (responseString != null) {
                            try {


                                JSONObject jsonObj = new JSONObject(responseString);
                                JSONArray resultsarray = jsonObj.getJSONArray("response");
                                for (int i = 0; i < resultsarray.length(); i++) {
                                    Doctor doctor = new Doctor();
                                    doctor.setTitle(resultsarray.getJSONObject(i).getString("sub_name"));
                                    doctor.setSub_id(resultsarray.getJSONObject(i).getString("sub_id"));
                                    doctor.setImageurl(Constant.Imageuri + resultsarray.getJSONObject(i).getString("sub_img"));
///
                                    Log.e("sub_id", "" + resultsarray.getJSONObject(i).getString("sub_id"));
                                    Log.e("sub_img", "" + resultsarray.getJSONObject(i).getString("sub_img"));

//                                        Log.e("tag",""+resultsarray.getJSONObject(i).getString("sub_name"));
                                    doctorList.add(doctor);
                                    progressDialog.dismiss();


                                }

                            } catch (JSONException e) {
                                Toast.makeText(DoctorTypeCategory1Activity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }


                        }


                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new DoctorAdapter(DoctorTypeCategory1Activity.this, doctorList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(DoctorTypeCategory1Activity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent it = new Intent(getApplicationContext(), DoctorTypeCategory2Activity.class);
//                                it.putExtra(sub_id,doctorList.get(position).getSub_id());
                                Log.e("position", "" + position);
                                Log.e("sub_id", "" + doctorList.get(position).getSub_id());
                                Constant.sub_id = doctorList.get(position).getSub_id();
                                Constant.doctor_type = doctorList.get(position).getTitle();

                                startActivity(it);
                            }
                        }));

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DoctorTypeCategory1Activity.this, error.toString(), Toast.LENGTH_LONG).show();
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





