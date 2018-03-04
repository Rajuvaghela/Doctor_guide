package example.com.doctor_guide.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import example.com.doctor_guide.Adapter.SearchSubCategoryAdapter;
import example.com.doctor_guide.Model.SearchSubCategory;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;


public class SelectSubCategoryActivity extends AppCompatActivity {
    private static final String TAG = SelectSubCategoryActivity.class.getSimpleName();
    String url = Constant.sub_categoryurl ;


    private List<SearchSubCategory> subCategoryList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    SearchSubCategoryAdapter adapter;
    private EditText edt_selectsubcategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategoryregister);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.subcategory_recycleview);
        edt_selectsubcategory = (EditText)findViewById(R.id.edt_selectsubcategory);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        edt_selectsubcategory.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text = edt_selectsubcategory.getText().toString().toLowerCase(Locale.getDefault());
                if (text != null){
                    adapter.filter(text);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });


        getData();
    }

    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(SelectSubCategoryActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.sub_categoryurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        Log.d("res", responseString);
                        if (responseString != null) {
                            try {


                                JSONObject jsonObj = new JSONObject(responseString);
                                JSONArray resultsarray = jsonObj.getJSONArray("response");
                                for (int i = 0; i < resultsarray.length(); i++) {
                                    SearchSubCategory subcatecory = new SearchSubCategory();
                                    subcatecory.setSubname(resultsarray.getJSONObject(i).getString("sub_name"));
                                    subcatecory.setSub_id(resultsarray.getJSONObject(i).getString("sub_id"));

                                    Log.e("sub_name", "" + resultsarray.getJSONObject(i).getString("sub_name"));
                                    Log.e("sub_id", "" + resultsarray.getJSONObject(i).getString("sub_id"));


                                    subCategoryList.add(subcatecory);
                                    progressDialog.dismiss();


                                }

                            } catch (JSONException e) {
                                Toast.makeText(SelectSubCategoryActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }


                        }


                        recyclerView.setHasFixedSize(true);
//                        layoutManager = new LinearLayoutManager(this);
                        recyclerView.setLayoutManager(layoutManager);
                        adapter = new SearchSubCategoryAdapter(SelectSubCategoryActivity.this, subCategoryList);

                        recyclerView.setAdapter(adapter);
                        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(SelectSubCategoryActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
//
                                Constant.subcategory = subCategoryList.get(position).getSubname();
                                Constant.rsub_id = subCategoryList.get(position).getSub_id();

                                SelectSubCategoryActivity.this.finish();


                            }
                        }));
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SelectSubCategoryActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.d("error",error.toString());
                        progressDialog.dismiss();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("cat_id",Constant.cat_id);

                return params;
            }

        };

//
        AppController.getInstance().addToRequestQueue(stringRequest);
//


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





