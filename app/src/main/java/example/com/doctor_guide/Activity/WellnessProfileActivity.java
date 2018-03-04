package example.com.doctor_guide.Activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import example.com.doctor_guide.Adapter.WeelnessConperInfo;
import example.com.doctor_guide.util.AndroidUtils;
import example.com.homepagefordoctor_guide.R;

public class WellnessProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Uri imageUri;
    private static final int PHOTO_PICK = 1, MY_READWRITE_REQUEST_CODE = 2, PHOTO_CAPTURE = 3, MY_CAPTURE_REQUEST_CODE = 4;
    String pictureUrl = "";

    //     Basic Info
    private CircleImageView profileImage;
    private TextView textWname;
    private TextView textWelness;
    private Button btnEdtProfile;

    private TextView lvText1;
    private TextView lvText2;
    private TextView lvText3;
    private TextView lvText4;
    private TextView lvText5;

    private LinearLayout lvBasicInfo;
    private TextView textHeader;
    private EditText edtFname;
    private EditText edtMname;
    Button basicinfo_next_btn;


    // cninfo
    List<String> cninfo = new ArrayList<>();
    WeelnessConperInfo weelnessConperInfo;
    private LinearLayout lvCninfo;
    private RecyclerView recycleviewCninfo;
    private Button btnAddMoreCnInfo;
    Button btn_next_cnper;

    //    Contact Information
    private LinearLayout lv_contact_info;
    private EditText edtArea;
    private EditText edtAddress;
    private EditText edtCity;
    private EditText edtPincode;
    private EditText edtState;
    private EditText edtCountry;
    private EditText edtMno1;
    private EditText edtMno2;
    private EditText edtLanno1;
    private EditText edtLanno2;
    private EditText edtWebsite;
    private EditText edtEmail;
    private Button btnContactPrevious;
    private Button btnContactNext;


    //    SocialInformation

    private LinearLayout lvSocialInformation;
    private EditText edtFblink;
    private EditText edtTwitterlink;
    private EditText edtLinkdinlink;
    private EditText edtGooglelink;
    private EditText edtPinterestLink;
    private EditText edtYoutubrLink;
    private Button btnSocialInformationNext;


    //    	MoreInformation
    private LinearLayout lvMoreInformation;
    private EditText edtMoreinfo;
    private Button btnUpdateProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();
    }


    private void findViews() {

        profileImage = (CircleImageView) findViewById(R.id.profile_image);
        textWname = (TextView) findViewById(R.id.text_wname);
        textWelness = (TextView) findViewById(R.id.text_welness);
        btnEdtProfile = (Button) findViewById(R.id.btn_edt_profile);
        lvText1 = (TextView) findViewById(R.id.lv_text1);
        lvText2 = (TextView) findViewById(R.id.lv_text2);
        lvText3 = (TextView) findViewById(R.id.lv_text3);
        lvText4 = (TextView) findViewById(R.id.lv_text4);
        lvText5 = (TextView) findViewById(R.id.lv_text5);
        basicinfo_next_btn = (Button)findViewById(R.id.basicinfo_next_btn);
        basicinfo_next_btn.setOnClickListener(this);

        lvText1.setOnClickListener(this);
        lvText2.setOnClickListener(this);
        lvText3.setOnClickListener(this);
        lvText4.setOnClickListener(this);
        lvText5.setOnClickListener(this);

        lvBasicInfo = (LinearLayout) findViewById(R.id.lv_basic_info);
        textHeader = (TextView) findViewById(R.id.text_header);
        edtFname = (EditText) findViewById(R.id.edt_fname);
        edtMname = (EditText) findViewById(R.id.edt_mname);
        btnEdtProfile.setOnClickListener(this);

//         cninfo
        lvCninfo = (LinearLayout) findViewById(R.id.lv_cninfo);
        recycleviewCninfo = (RecyclerView) findViewById(R.id.recycleview_cninfo);
        btnAddMoreCnInfo = (Button) findViewById(R.id.btn_add_more_cn_info);
        btnAddMoreCnInfo.setOnClickListener(this);
        btn_next_cnper= (Button)findViewById(R.id.btn_next_cnper);
        btn_next_cnper.setOnClickListener(this);

        LinearLayoutManager linearLayoutManagerE = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycleviewCninfo.setLayoutManager(linearLayoutManagerE);
        cninfo.add("0");
        weelnessConperInfo = new WeelnessConperInfo(getApplicationContext(), cninfo);
        recycleviewCninfo.setAdapter(weelnessConperInfo);


        //        Contact Information
        lv_contact_info = (LinearLayout) findViewById(R.id.lv_contact_info);
        edtArea = (EditText) findViewById(R.id.edt_area);
        edtAddress = (EditText) findViewById(R.id.edt_address);
        edtCity = (EditText) findViewById(R.id.edt_city);
        edtPincode = (EditText) findViewById(R.id.edt_pincode);
        edtState = (EditText) findViewById(R.id.edt_State);
        edtCountry = (EditText) findViewById(R.id.edt_country);
        edtMno1 = (EditText) findViewById(R.id.edt_mno1);
        edtMno2 = (EditText) findViewById(R.id.edt_mno2);
        edtLanno1 = (EditText) findViewById(R.id.edt_lanno1);
        edtLanno2 = (EditText) findViewById(R.id.edt_lanno2);
        edtWebsite = (EditText) findViewById(R.id.edt_website);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnContactPrevious = (Button) findViewById(R.id.btn_contact_previous);
        btnContactNext = (Button) findViewById(R.id.btn_contact_next);

        btnContactPrevious.setOnClickListener(this);
        btnContactNext.setOnClickListener(this);


        //        SocialInformation

        lvSocialInformation = (LinearLayout) findViewById(R.id.lv_SocialInformation);
        edtFblink = (EditText) findViewById(R.id.edt_fblink);
        edtTwitterlink = (EditText) findViewById(R.id.edt_twitterlink);
        edtLinkdinlink = (EditText) findViewById(R.id.edt_linkdinlink);
        edtGooglelink = (EditText) findViewById(R.id.edt_googlelink);
        edtPinterestLink = (EditText) findViewById(R.id.edt_pinterest_link);
        edtYoutubrLink = (EditText) findViewById(R.id.edt_youtubr_link);
        btnSocialInformationNext = (Button) findViewById(R.id.btn_SocialInformation_next);
        btnSocialInformationNext.setOnClickListener(this);

//       MoreInformation

        lvMoreInformation = (LinearLayout) findViewById(R.id.lv_MoreInformation);
        edtMoreinfo = (EditText) findViewById(R.id.edt_moreinfo);
        btnUpdateProfile = (Button) findViewById(R.id.btn_update_profile);
        btnUpdateProfile.setOnClickListener(this);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerForContextMenu(profileImage);
                openContextMenu(profileImage);

            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Action");
        menu.add(0, 0, 0, "Pick Image");//groupId, itemId, order, title
        menu.add(0, 1, 0, "Capture Image");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                if (ContextCompat.checkSelfPermission(WellnessProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {

                    Intent intentPick = new Intent(Intent.ACTION_GET_CONTENT);
                    intentPick.setType("image/*");
                    startActivityForResult(Intent.createChooser(intentPick, "Select Action"), PHOTO_PICK);
                } else {
                    // Show rationale and request permission.
                    ActivityCompat.requestPermissions(WellnessProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_READWRITE_REQUEST_CODE);
                }

                break;
            case 1:
                if (ContextCompat.checkSelfPermission(WellnessProfileActivity.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {

                    Intent intentCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File photo = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                    intentCapture.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photo));
                    imageUri = Uri.fromFile(photo);
                    startActivityForResult(intentCapture, PHOTO_CAPTURE);

                } else {
                    // Show rationale and request permission.
                    ActivityCompat.requestPermissions(WellnessProfileActivity.this, new String[]{Manifest.permission.CAMERA}, MY_CAPTURE_REQUEST_CODE);
                }

                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHOTO_PICK) {

            Bitmap bm = null;
            if (data != null) {
                pictureUrl = data.getData().getPath();
                try {
                    bm = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    if (bm != null) {

                        Bitmap mBitmap = AndroidUtils.getResizedBitmap(bm, 400);
                        profileImage.setImageBitmap(mBitmap);

//                        pictureUrl = AndroidUtils.encodeTobase64(mBitmap);
                        Log.d("msg", "pic " + pictureUrl);
                    } else {
                        Log.d("msg", "BImtap Null");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("msg", "" + e.getMessage());
                }
            }

        } else if (requestCode == PHOTO_CAPTURE) {

            Uri selectedImage = imageUri;

            pictureUrl = imageUri.toString();

            getContentResolver().notifyChange(selectedImage, null);
            ContentResolver cr = getContentResolver();
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media
                        .getBitmap(cr, selectedImage);

                if (bitmap != null) {

                    Bitmap mBitmap = AndroidUtils.getResizedBitmap(bitmap, 400);
                    profileImage.setImageBitmap(mBitmap);
//                    pictureUrl = AndroidUtils.encodeTobase64(mBitmap);
                } else {
                    Log.d("msg", "Bitmap Null");
                }

            } catch (Exception e) {
                Toast.makeText(WellnessProfileActivity.this, "Failed to load", Toast.LENGTH_SHORT)
                        .show();
                Log.e("msg", "Camera " + e.toString());
            }

        }

    }

    @Override
    public void onClick(View v) {
        if (v == btnAddMoreCnInfo) {


            cninfo.add("0");
            weelnessConperInfo.notifyItemInserted(cninfo.size() - 1);

        } else if (v == btnEdtProfile) {
            // Handle clicks for btnEdtProfile
        } else if (v == lvText1) {

            lvBasicInfo.setVisibility(View.VISIBLE);
            lvCninfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText1.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        } else if (v == lvText2 || v == basicinfo_next_btn) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvCninfo.setVisibility(View.VISIBLE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText2.setBackgroundColor(getResources().getColor(R.color.black));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        } else if (v == lvText3 || v == btn_next_cnper) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.VISIBLE);
            lvCninfo.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText3.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        } else if (v == lvText4 || v == btnContactNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.VISIBLE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText4.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        } else if (v == lvText5 || v == btnSocialInformationNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.VISIBLE);

            lvText5.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_CAPTURE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intentCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photo = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                intentCapture.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);
                startActivityForResult(intentCapture, PHOTO_CAPTURE);
            } else {

                Toast.makeText(WellnessProfileActivity.this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == MY_READWRITE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intentPick = new Intent(Intent.ACTION_GET_CONTENT);
                intentPick.setType("image/*");
                startActivityForResult(Intent.createChooser(intentPick, "Select Action"), PHOTO_PICK);

            } else {

                Toast.makeText(WellnessProfileActivity.this, "Read/Write permission denied", Toast.LENGTH_LONG).show();
            }
        }
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
