package example.com.doctor_guide.Activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import java.util.Map;

import example.com.doctor_guide.Adapter.FooterBtnAdapter;
import example.com.doctor_guide.Model.FooterBtn;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.Other.SimpleDividerItemDecorationForfooterbtn;
import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;

public class Footer_Btn_Activity extends AppCompatActivity {
    private static final String TAG = Footer_Btn_Activity.class.getSimpleName();
    private final String url = Constant.sub_categoryurl;


    private List<FooterBtn> footeritemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer_btn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.footer_btn_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        adapter = new DoctorAdapter(this,doctorList);
//
////        recyclerView.setAdapter(adapter);
//
//
        recyclerView.addItemDecoration(new SimpleDividerItemDecorationForfooterbtn(this));


        getData();
    }

    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(Footer_Btn_Activity.this);
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
                                    FooterBtn footerbtn = new FooterBtn();
                                    footerbtn.setName(resultsarray.getJSONObject(i).getString("sub_name"));
                                    footerbtn.setDescription(resultsarray.getJSONObject(i).getString("sub_name"));
                                    footerbtn.setSub_id(resultsarray.getJSONObject(i).getString("sub_id"));
                                    footerbtn.setImageurl(Constant.Imageuri + resultsarray.getJSONObject(i).getString("sub_img"));
                                    Log.e("sub_id", "" + resultsarray.getJSONObject(i).getString("sub_id"));
                                    String id = footerbtn.getSub_id();
                                    Log.e("tag", "" + resultsarray.getJSONObject(i).getString("sub_name"));
                                    footeritemList.add(footerbtn);
                                    progressDialog.dismiss();


                                }

                            } catch (JSONException e) {
                                Toast.makeText(Footer_Btn_Activity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }


                        }


                        recyclerView.setHasFixedSize(true);
//                        layoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new FooterBtnAdapter(Footer_Btn_Activity.this, footeritemList);

                        recyclerView.setAdapter(adapter);
                        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(Footer_Btn_Activity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent it = new Intent(getApplicationContext(), Footer_btn_description_Activity.class);
//                                it.putExtra(sub_id,doctorList.get(position).getSub_id());
                                Log.e("position", "" + position);
                                Log.e("sub_id", "" + footeritemList.get(position).getSub_id());
                                Constant.sub_id = footeritemList.get(position).getSub_id();

                                startActivity(it);
                            }
                        }));

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Footer_Btn_Activity.this, error.toString(), Toast.LENGTH_LONG).show();
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






