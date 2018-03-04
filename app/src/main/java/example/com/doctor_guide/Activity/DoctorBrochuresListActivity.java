package example.com.doctor_guide.Activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import example.com.doctor_guide.Adapter.DocBrocherAdapter;
import example.com.doctor_guide.Adapter.ViewAllDocRatingAdapter;
import example.com.doctor_guide.Model.RatingModel;
import example.com.doctor_guide.Other.RecyclerItemClickListener;
import example.com.doctor_guide.util.Constant;
import example.com.homepagefordoctor_guide.R;

public class DoctorBrochuresListActivity extends AppCompatActivity {
    RecyclerView rv_view_brocher_list;
    RecyclerView.Adapter listAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ProgressDialog dialog;
    List<RatingModel> listuser;
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_brochures_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv_view_brocher_list = (RecyclerView) findViewById(R.id.rv_view_brocher_list);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);
        listuser = new ArrayList<>();

        if (Constant.cat_id.equals("1")) {
            load_doc_brocher();
        } else if (Constant.cat_id.equals("2")) {
            load_hospital_brocher();
        } else {
            load_extra_brocher();
        }

    }

    void load_hospital_brocher() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            final RequestParams params = new RequestParams();

            params.add("h_id", Constant.h_id);
            client.post(Constant.doctor_live_url + "hospital_brochures.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        listuser.clear();

                        try {
                            Log.e("brocher_res", responseString);
                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONArray resultsarray = jsonObj.getJSONArray("response");
                            for (int i = 0; i < resultsarray.length(); i++) {
                                RatingModel temp = new RatingModel();
                                temp.setBrocher_path(Constant.Imageuri + resultsarray.getJSONObject(i).getString("hb_path"));
                                String path = resultsarray.getJSONObject(i).getString("hb_path");
                                String filename = path.substring(path.lastIndexOf("/") + 1);
                                temp.setBrocher_pdf_name(filename);
                                listuser.add(temp);
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        rv_view_brocher_list.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(DoctorBrochuresListActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv_view_brocher_list.setLayoutManager(mLayoutManager);
                        listAdapter = new DocBrocherAdapter(DoctorBrochuresListActivity.this, listuser);
                        rv_view_brocher_list.setAdapter(listAdapter);
                        rv_view_brocher_list.addOnItemTouchListener(new RecyclerItemClickListener(DoctorBrochuresListActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                path = listuser.get(position).getBrocher_pdf_name();
                                String file_url = listuser.get(position).getBrocher_path().toString();

                                if (checkPermission()) {
                                    new DownloadFileFromURL().execute(file_url);
                                } else {
                                    requestPermission();
                                }

                            }
                        }));

                    } else if (responseString.equals("[]")) {
                        Toast.makeText(DoctorBrochuresListActivity.this, "Brochures not available", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }


                }
            });
        } else {
            Toast.makeText(DoctorBrochuresListActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    void load_extra_brocher() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            final RequestParams params = new RequestParams();

            params.add("e_id", Constant.e_id);
            client.post(Constant.doctor_live_url + "extra_brochures.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        listuser.clear();

                        try {
                            Log.e("brocher_res", responseString);
                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONArray resultsarray = jsonObj.getJSONArray("response");
                            for (int i = 0; i < resultsarray.length(); i++) {
                                RatingModel temp = new RatingModel();
                                temp.setBrocher_path(Constant.Imageuri + resultsarray.getJSONObject(i).getString("eb_path"));
                                String path = resultsarray.getJSONObject(i).getString("eb_path");
                                String filename = path.substring(path.lastIndexOf("/") + 1);
                                temp.setBrocher_pdf_name(filename);
                                listuser.add(temp);
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        rv_view_brocher_list.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(DoctorBrochuresListActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv_view_brocher_list.setLayoutManager(mLayoutManager);
                        listAdapter = new DocBrocherAdapter(DoctorBrochuresListActivity.this, listuser);
                        rv_view_brocher_list.setAdapter(listAdapter);
                        rv_view_brocher_list.addOnItemTouchListener(new RecyclerItemClickListener(DoctorBrochuresListActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                path = listuser.get(position).getBrocher_pdf_name();
                                String file_url = listuser.get(position).getBrocher_path().toString();

                                if (checkPermission()) {
                                    new DownloadFileFromURL().execute(file_url);
                                } else {
                                    requestPermission();
                                }

                            }
                        }));

                    } else if (responseString.equals("[]")) {
                        Toast.makeText(DoctorBrochuresListActivity.this, "Brochures not available", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }


                }
            });
        } else {
            Toast.makeText(DoctorBrochuresListActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void load_doc_brocher() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            final RequestParams params = new RequestParams();

            params.add("d_id", Constant.d_id);
            client.post(Constant.doctor_live_url + "doctor_brochures.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null && !responseString.equals("[]")) {
                        listuser.clear();
                        try {
                            Log.e("brocher_res", responseString);
                            JSONObject jsonObj = new JSONObject(responseString);
                            JSONArray resultsarray = jsonObj.getJSONArray("response");
                            for (int i = 0; i < resultsarray.length(); i++) {
                                RatingModel temp = new RatingModel();
                                temp.setBrocher_path(Constant.Imageuri + resultsarray.getJSONObject(i).getString("db_path"));
                                String path = resultsarray.getJSONObject(i).getString("db_path");
                                String filename = path.substring(path.lastIndexOf("/") + 1);
                                temp.setBrocher_pdf_name(filename);
                                listuser.add(temp);
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            dialog.dismiss();
                            e.printStackTrace();
                        }
                        rv_view_brocher_list.setHasFixedSize(true);
                        mLayoutManager = new LinearLayoutManager(DoctorBrochuresListActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv_view_brocher_list.setLayoutManager(mLayoutManager);
                        listAdapter = new DocBrocherAdapter(DoctorBrochuresListActivity.this, listuser);
                        rv_view_brocher_list.setAdapter(listAdapter);
                        rv_view_brocher_list.addOnItemTouchListener(new RecyclerItemClickListener(DoctorBrochuresListActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                path = listuser.get(position).getBrocher_pdf_name();
                                String file_url = listuser.get(position).getBrocher_path().toString();

                                if (checkPermission()) {
                                    new DownloadFileFromURL().execute(file_url);
                                } else {
                                    requestPermission();
                                }

                            }
                        }));

                    } else if (responseString.equals("[]")) {
                        Toast.makeText(DoctorBrochuresListActivity.this, "Brochures not available", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });
        } else {
            Toast.makeText(DoctorBrochuresListActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(false);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    /**
     * Background Async Task to download file
     */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                OutputStream output = new FileOutputStream("/sdcard/" + path);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            // Displaying downloaded image into image view
            // Reading image path from sdcard
            //   String imagePath = Environment.getExternalStorageDirectory().toString() + "/menu.pdf";
            view();
            // setting downloaded into image view
            //  my_image.setImageDrawable(Drawable.createFromPath(imagePath));
            // startActivity(new Intent(getApplicationContext(),SecondActivity.class));
        }

    }

    public void view() {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/" + path);  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(DoctorBrochuresListActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(DoctorBrochuresListActivity.this, new
                String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(DoctorBrochuresListActivity.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
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
