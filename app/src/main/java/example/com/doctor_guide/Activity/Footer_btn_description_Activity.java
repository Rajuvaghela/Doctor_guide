package example.com.doctor_guide.Activity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.Constant;
import example.com.doctor_guide.util.RoundImageView;
import example.com.homepagefordoctor_guide.R;

/**
 * Created by Khodal on 09/09/2017.
 */

public class Footer_btn_description_Activity extends AppCompatActivity implements View.OnClickListener {
    Button footer_share, footer_download;
    TextView footer_item_title, footer_item_description;
    ImageView footer_image;

    ImageView imageView_new_detail;
    TextView textview_news_detail, textview_news_title, textview_news_date;

    public static final int RequestPermissionCode = 1;
    ImageView iv_share;
    //    File file;
    Drawable drawable;
    //    Bitmap bitmap;
    String ImagePath;
    Uri URI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer_description);
        footer_share = (Button) findViewById(R.id.footer_share_btn);
        footer_download = (Button) findViewById(R.id.footer_download_btn);
        footer_item_title = (TextView) findViewById(R.id.footer_title);
        footer_item_description = (TextView) findViewById(R.id.footer_description);
        footer_image = (ImageView) findViewById(R.id.footer_image);
        footer_download.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getData();

    }

    private void getData() {

        final ProgressDialog progressDialog = new ProgressDialog(Footer_btn_description_Activity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.doctor_details_category_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseString) {
                        Log.d("res", responseString);
                        if (responseString != null) {
                            try {
                                JSONObject jsonObj = new JSONObject(responseString);
                                JSONArray resultsarray = jsonObj.getJSONArray("response");
                                for (int i = 0; i < resultsarray.length(); i++) {
                                    footer_item_title.setText(resultsarray.getJSONObject(i).getString("d_firstname"));
                                    footer_item_description.setText(resultsarray.getJSONObject(i).getString("d_firstname"));

                                    Glide.with(getApplication())
                                            .load(Constant.Imageuri + resultsarray.getJSONObject(i).getString("d_img"))
                                            .asBitmap()
                                            .placeholder(R.drawable.doctor_slider)
                                            .into(footer_image);


                                    progressDialog.dismiss();


                                }
                            } catch (JSONException e) {
                                Toast.makeText(Footer_btn_description_Activity.this, "No Data Available", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                                progressDialog.dismiss();

                            }

                        }


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Footer_btn_description_Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("d_id", "1");


                return params;
            }

        };


        AppController.getInstance().addToRequestQueue(stringRequest);


    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    public void btnshre(View v) {


        footer_share.setDrawingCacheEnabled(true);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable) footer_image.getDrawable()).getBitmap();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "deal_photo.jpg");
        try {
            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        Uri uri = Uri.parse("file:///sdcard/temporary_file.jpg");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share image Using"));


    }

//           footer_download.setOnClickListener(new View.OnClickListener() {


//    public void btndownload(View v)  {
//
//       footer_image.buildDrawingCache();
//        Bitmap bm = footer_image.getDrawingCache();
//
//        OutputStream fOut = null;
//        Uri outputFileUri;
//        try {
//            File root = new File(Environment.getExternalStorageDirectory()
//                    + File.separator + "folder_name" + File.separator);
//            root.mkdirs();
//            File sdImageMainDirectory = new File(root, "myPicName.jpg");
//            outputFileUri = Uri.fromFile(sdImageMainDirectory);
//            fOut = new FileOutputStream(sdImageMainDirectory);
//        } catch (Exception e) {
//            Toast.makeText(this, "Error occured. Please try again later.",
//                    Toast.LENGTH_SHORT).show();
//        }
//        try {
//            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//            fOut.flush();
//            fOut.close();
//        } catch (Exception e) {
//        }
//    }
//        BitmapDrawable draw = (BitmapDrawable) footer_image.getDrawable();
//        Bitmap bitmap = draw.getBitmap();
//
//        FileOutputStream outStream = null;
//        File sdCard = Environment.getExternalStorageDirectory();
//        File dir = new File(sdCard.getAbsolutePath() + "/YourFolderName");
//        dir.mkdirs();
//        String fileName = String.format("%d.jpg", System.currentTimeMillis());
//        File outFile = new File(dir, fileName);
//        outStream = new FileOutputStream(outFile);
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
//        outStream.flush();
//        outStream.close();
//
//        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        intent.setData(Uri.fromFile(file));
//        sendBroadcast(intent);
//    }


//        switch (v.getId()){
//            case R.id.footer_share_btn:
//                if (checkPermission()) {
//                    bitmap = getBitmapFromView(footer_image);
//                    //saveImage(bitmap);
//                    String pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
//                    Uri bmpUri = Uri.parse(pathofBmp);
//                    final Intent emailIntent1 = new Intent(Intent.ACTION_SEND);
//                    emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
//                    emailIntent1.setType("image");
//                      //  fulldisplay_description.getText().toString() +"\n"+fulldisplay_address.getText().toString()
//                      /*  emailIntent1.putExtra(Intent.EXTRA_TEXT,
//                            "Get more stickers on Dayro app:\nhttps://play.google.com/store/apps/details?id=com.gradlesol.dayro");*/
//                    startActivity(Intent.createChooser(emailIntent1, "Share via"));
//                }else {
//                    requestPermission();
//                }
//        }
//    }

//    public Bitmap getBitmapFromView(View view) {
//        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(returnedBitmap);
//        Drawable bgDrawable = view.getBackground();
//        if (bgDrawable != null)
//            bgDrawable.draw(canvas);
//        view.draw(canvas);
//        return returnedBitmap;


    private void requestPermission() {
        ActivityCompat.requestPermissions(Footer_btn_description_Activity.this, new
                String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);
    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(Footer_btn_description_Activity.this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        footer_image.buildDrawingCache();
        Bitmap bm = footer_image.getDrawingCache();


        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/Doctor-Guide Images");
        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        File outFile = new File(dir, fileName);
        try {
            outStream = new FileOutputStream(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        try {
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(outFile));
        sendBroadcast(intent);
        Toast.makeText(Footer_btn_description_Activity.this, "Image Saved Sucessfully", Toast.LENGTH_LONG).show();


    }

//
}



