package example.com.doctor_guide.Activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import example.com.doctor_guide.Adapter.DoctroProfileAddAwardsAdapter;
import example.com.doctor_guide.Adapter.DoctroProfileAddEducationAdapter;
import example.com.doctor_guide.Adapter.DoctroProfileAddExperienceAdapter;
import example.com.doctor_guide.Adapter.DoctroProfileAddMembershipHospitalAdapter;
import example.com.doctor_guide.Adapter.DoctroProfileAddSpelizationAdapter;
import example.com.doctor_guide.util.AndroidUtils;
import example.com.homepagefordoctor_guide.R;

public class EditDocProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Uri imageUri;
    private static final int PHOTO_PICK = 1, MY_READWRITE_REQUEST_CODE = 2, PHOTO_CAPTURE = 3, MY_CAPTURE_REQUEST_CODE = 4;
    String pictureUrl = "";

    private CircleImageView profileImage;
    private TextView textDname;
    private TextView textDegree;
    private Button btnEdtProfile;
    private TextView textHeader;
    private LinearLayout lvBasicInfo;
    private EditText edtFname;
    private EditText edtMname;
    private EditText edtLname;
    private EditText edtDob;
    private Spinner spnBloodGrp;
    private EditText edtDegree;
    private Button basicinfoNextBtn;


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

//    Education

    private LinearLayout lvEducationon;
    private RecyclerView recycleviewEducation;
    private Button btnAddMoreEducation;
    private Button btnEducationprevious;
    private Button btnEducationNext;
    DoctroProfileAddEducationAdapter doctroProfileAddEducationAdapter;
    List<String> EDTDATA = new ArrayList<>();

    // Specializations
    DoctroProfileAddSpelizationAdapter spelizationAdapter;
    private LinearLayout lvSpecializations;
    private RecyclerView recycleviewSpecializations;
    private Button btnAddMoreSpecializations;
    private Button btnSpecializationsprevious;
    private Button btnSpecializationsNext;
    List<String> Specializations = new ArrayList<>();

    //   VisitingHospitals
    DoctroProfileAddVisitingHospitalAdapter visitingHospitalAdapter;
    private LinearLayout lvVisitingHospitals;
    private RecyclerView recycleviewVisitingHospitals;
    private Button btnAddMoreVisitingHospitals;
    private Button btnVisitingHospitalsRevious;
    private Button btnVisitingHospitalsNext;
    List<String> VisitingHospitals = new ArrayList<>();

    //   Awards

    DoctroProfileAddAwardsAdapter awardsAdapter;
    private LinearLayout lvAwards;
    private RecyclerView recycleviewAwards;
    private Button btnAddMoreAwards;
    private Button btnAwardsPrevious;
    private Button btnAwardsNext;
    List<String> Awardss = new ArrayList<>();

//    Experience

    private LinearLayout lvExperience;
    private RecyclerView recycleviewExperience;
    private Button btnAddMoreExperience;
    private Button btnExperienceNext;
    List<String> Experience = new ArrayList<>();
    DoctroProfileAddExperienceAdapter experienceAdapter;


//    Memberships

    private LinearLayout lvMemberships;
    private RecyclerView recycleviewMemberships;
    private Button btnAddMoreMemberships;
    private Button btnMembershipsNext;
    List<String> Memberships = new ArrayList<>();
    DoctroProfileAddMembershipHospitalAdapter membershipHospitalAdapter;


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


    private TextView lvText1;
    private TextView lvText2;
    private TextView lvText3;
    private TextView lvText4;
    private TextView lvText5;
    private TextView lvText6;
    private TextView lvText7;
    private TextView lvText8;
    private TextView lvText9;
    private TextView lvText10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doc_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();

    }

    private void findViews() {

        lvText1 = (TextView) findViewById(R.id.lv_text1);
        lvText2 = (TextView) findViewById(R.id.lv_text2);
        lvText3 = (TextView) findViewById(R.id.lv_text3);
        lvText4 = (TextView) findViewById(R.id.lv_text4);
        lvText5 = (TextView) findViewById(R.id.lv_text5);
        lvText6 = (TextView) findViewById(R.id.lv_text6);
        lvText7 = (TextView) findViewById(R.id.lv_text7);
        lvText8 = (TextView) findViewById(R.id.lv_text8);
        lvText9 = (TextView) findViewById(R.id.lv_text9);
        lvText10 = (TextView) findViewById(R.id.lv_text10);

        lvText1.setOnClickListener(this);
        lvText2.setOnClickListener(this);
        lvText3.setOnClickListener(this);
        lvText4.setOnClickListener(this);
        lvText5.setOnClickListener(this);
        lvText6.setOnClickListener(this);
        lvText7.setOnClickListener(this);
        lvText8.setOnClickListener(this);
        lvText9.setOnClickListener(this);
        lvText10.setOnClickListener(this);


        profileImage = (CircleImageView) findViewById(R.id.profile_image);
        textDname = (TextView) findViewById(R.id.text_dname);
        textDegree = (TextView) findViewById(R.id.text_degree);
        btnEdtProfile = (Button) findViewById(R.id.btn_edt_profile);
        textHeader = (TextView) findViewById(R.id.text_header);
        lv_contact_info = (LinearLayout) findViewById(R.id.lv_contact_info);
        edtFname = (EditText) findViewById(R.id.edt_fname);
        edtMname = (EditText) findViewById(R.id.edt_mname);
        edtLname = (EditText) findViewById(R.id.edt_lname);
        edtDob = (EditText) findViewById(R.id.edt_dob);
        spnBloodGrp = (Spinner) findViewById(R.id.spn_blood_grp);
        edtDegree = (EditText) findViewById(R.id.edt_degree);
        basicinfoNextBtn = (Button) findViewById(R.id.basicinfo_next_btn);
        btnEdtProfile.setOnClickListener(this);
        basicinfoNextBtn.setOnClickListener(this);


//        Contact Information

        lvBasicInfo = (LinearLayout) findViewById(R.id.lv_basic_info);
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

//         Education

        lvEducationon = (LinearLayout) findViewById(R.id.lv_educationon);
        recycleviewEducation = (RecyclerView) findViewById(R.id.recycleview_education);
        btnAddMoreEducation = (Button) findViewById(R.id.btn_add_more_education);
        btnEducationprevious = (Button) findViewById(R.id.btn_educationprevious);
        btnEducationNext = (Button) findViewById(R.id.btn_education_next);
        btnAddMoreEducation.setOnClickListener(this);
        btnEducationprevious.setOnClickListener(this);
        btnEducationNext.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycleviewEducation.setLayoutManager(linearLayoutManager);
        EDTDATA.add("0");
        doctroProfileAddEducationAdapter = new DoctroProfileAddEducationAdapter(getApplicationContext(), EDTDATA);
        recycleviewEducation.setAdapter(doctroProfileAddEducationAdapter);


//        Specializations

        lvSpecializations = (LinearLayout) findViewById(R.id.lv_Specializations);
        recycleviewSpecializations = (RecyclerView) findViewById(R.id.recycleview_Specializations);
        btnAddMoreSpecializations = (Button) findViewById(R.id.btn_add_more_Specializations);
        btnSpecializationsprevious = (Button) findViewById(R.id.btn_Specializationsprevious);
        btnSpecializationsNext = (Button) findViewById(R.id.btn_Specializations_next);
        btnAddMoreSpecializations.setOnClickListener(this);
        btnSpecializationsprevious.setOnClickListener(this);
        btnSpecializationsNext.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycleviewSpecializations.setLayoutManager(linearLayoutManager2);

        Specializations.add("0");
        spelizationAdapter = new DoctroProfileAddSpelizationAdapter(getApplicationContext(), Specializations);
        recycleviewSpecializations.setAdapter(spelizationAdapter);


        //    VisitingHospitals
        lvVisitingHospitals = (LinearLayout) findViewById(R.id.lv_VisitingHospitals);
        recycleviewVisitingHospitals = (RecyclerView) findViewById(R.id.recycleview_VisitingHospitals);
        btnAddMoreVisitingHospitals = (Button) findViewById(R.id.btn_add_more_VisitingHospitals);
        btnVisitingHospitalsRevious = (Button) findViewById(R.id.btn_VisitingHospitals_revious);
        btnVisitingHospitalsNext = (Button) findViewById(R.id.btn_VisitingHospitals_next);
        btnAddMoreVisitingHospitals.setOnClickListener(this);
        btnVisitingHospitalsRevious.setOnClickListener(this);
        btnVisitingHospitalsNext.setOnClickListener(this);


        LinearLayoutManager linearLayoutManager22 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycleviewVisitingHospitals.setLayoutManager(linearLayoutManager22);

        VisitingHospitals.add("0");
        visitingHospitalAdapter = new DoctroProfileAddVisitingHospitalAdapter(getApplicationContext(), VisitingHospitals);
        recycleviewVisitingHospitals.setAdapter(visitingHospitalAdapter);

// Awards
        lvAwards = (LinearLayout) findViewById(R.id.lv_Awards);
        recycleviewAwards = (RecyclerView) findViewById(R.id.recycleview_Awards);
        btnAddMoreAwards = (Button) findViewById(R.id.btn_add_more_Awards);
        btnAwardsPrevious = (Button) findViewById(R.id.btn_Awards_previous);
        btnAwardsNext = (Button) findViewById(R.id.btn_Awards_next);

        btnAddMoreAwards.setOnClickListener(this);
        btnAwardsPrevious.setOnClickListener(this);
        btnAwardsNext.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager221 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycleviewAwards.setLayoutManager(linearLayoutManager221);

        Awardss.add("0");
        awardsAdapter = new DoctroProfileAddAwardsAdapter(getApplicationContext(), Awardss);
        recycleviewAwards.setAdapter(awardsAdapter);

//        Experience

        lvExperience = (LinearLayout) findViewById(R.id.lv_Experience);
        recycleviewExperience = (RecyclerView) findViewById(R.id.recycleview_Experience);
        btnAddMoreExperience = (Button) findViewById(R.id.btn_add_more_Experience);
        btnExperienceNext = (Button) findViewById(R.id.btn_Experience_next);
        btnAddMoreExperience.setOnClickListener(this);
        btnExperienceNext.setOnClickListener(this);

        LinearLayoutManager linearLayoutManagerE = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycleviewExperience.setLayoutManager(linearLayoutManagerE);
        Experience.add("0");
        experienceAdapter = new DoctroProfileAddExperienceAdapter(getApplicationContext(), Experience);
        recycleviewExperience.setAdapter(experienceAdapter);

//        Memberships

        lvMemberships = (LinearLayout) findViewById(R.id.lv_Memberships);
        recycleviewMemberships = (RecyclerView) findViewById(R.id.recycleview_Memberships);
        btnAddMoreMemberships = (Button) findViewById(R.id.btn_add_more_Memberships);
        btnMembershipsNext = (Button) findViewById(R.id.btn_Memberships_next);
        btnAddMoreMemberships.setOnClickListener(this);
        btnMembershipsNext.setOnClickListener(this);

        LinearLayoutManager linearLayoutManagerm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycleviewMemberships.setLayoutManager(linearLayoutManagerm);
        Memberships.add("0");
        membershipHospitalAdapter = new DoctroProfileAddMembershipHospitalAdapter(getApplicationContext(), Memberships);
        recycleviewMemberships.setAdapter(membershipHospitalAdapter);

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

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(EditDocProfileActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bloodgroup));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBloodGrp.setAdapter(dataAdapter);


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
                if (ContextCompat.checkSelfPermission(EditDocProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {

                    Intent intentPick = new Intent(Intent.ACTION_GET_CONTENT);
                    intentPick.setType("image/*");
                    startActivityForResult(Intent.createChooser(intentPick, "Select Action"), PHOTO_PICK);
                } else {
                    // Show rationale and request permission.
                    ActivityCompat.requestPermissions(EditDocProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_READWRITE_REQUEST_CODE);
                }

                break;
            case 1:
                if (ContextCompat.checkSelfPermission(EditDocProfileActivity.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {

                    Intent intentCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File photo = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                    intentCapture.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photo));
                    imageUri = Uri.fromFile(photo);
                    startActivityForResult(intentCapture, PHOTO_CAPTURE);

                } else {
                    // Show rationale and request permission.
                    ActivityCompat.requestPermissions(EditDocProfileActivity.this, new String[]{Manifest.permission.CAMERA}, MY_CAPTURE_REQUEST_CODE);
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
                Toast.makeText(EditDocProfileActivity.this, "Failed to load", Toast.LENGTH_SHORT)
                        .show();
                Log.e("msg", "Camera " + e.toString());
            }

        }

    }


    @Override
    public void onClick(View v) {

        if (v == lvText1) {

            lvBasicInfo.setVisibility(View.VISIBLE);
            lv_contact_info.setVisibility(View.GONE);
            lvEducationon.setVisibility(View.GONE);
            lvSpecializations.setVisibility(View.GONE);
            lvVisitingHospitals.setVisibility(View.GONE);
            lvAwards.setVisibility(View.GONE);
            lvExperience.setVisibility(View.GONE);
            lvMemberships.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText1.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        } else if (v == lvText2 || v == basicinfoNextBtn) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.VISIBLE);
            lvEducationon.setVisibility(View.GONE);
            lvSpecializations.setVisibility(View.GONE);
            lvVisitingHospitals.setVisibility(View.GONE);
            lvAwards.setVisibility(View.GONE);
            lvExperience.setVisibility(View.GONE);
            lvMemberships.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText2.setBackgroundColor(getResources().getColor(R.color.black));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (v == lvText3 || v == btnContactNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvEducationon.setVisibility(View.VISIBLE);
            lvSpecializations.setVisibility(View.GONE);
            lvVisitingHospitals.setVisibility(View.GONE);
            lvAwards.setVisibility(View.GONE);
            lvExperience.setVisibility(View.GONE);
            lvMemberships.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText3.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        } else if (v == lvText4 || v == btnEducationNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvEducationon.setVisibility(View.GONE);
            lvSpecializations.setVisibility(View.VISIBLE);
            lvVisitingHospitals.setVisibility(View.GONE);
            lvAwards.setVisibility(View.GONE);
            lvExperience.setVisibility(View.GONE);
            lvMemberships.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText4.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        } else if (v == lvText5 || v == btnSpecializationsNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvEducationon.setVisibility(View.GONE);
            lvSpecializations.setVisibility(View.GONE);
            lvVisitingHospitals.setVisibility(View.VISIBLE);
            lvAwards.setVisibility(View.GONE);
            lvExperience.setVisibility(View.GONE);
            lvMemberships.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText5.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        } else if (v == lvText6 || v == btnVisitingHospitalsNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvEducationon.setVisibility(View.GONE);
            lvSpecializations.setVisibility(View.GONE);
            lvVisitingHospitals.setVisibility(View.GONE);
            lvAwards.setVisibility(View.VISIBLE);
            lvExperience.setVisibility(View.GONE);
            lvMemberships.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText6.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        } else if (v == lvText7 || v == btnAwardsNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvEducationon.setVisibility(View.GONE);
            lvSpecializations.setVisibility(View.GONE);
            lvVisitingHospitals.setVisibility(View.GONE);
            lvAwards.setVisibility(View.GONE);
            lvExperience.setVisibility(View.VISIBLE);
            lvMemberships.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText7.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        } else if (v == lvText8 || v == btnExperienceNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvEducationon.setVisibility(View.GONE);
            lvSpecializations.setVisibility(View.GONE);
            lvVisitingHospitals.setVisibility(View.GONE);
            lvAwards.setVisibility(View.GONE);
            lvExperience.setVisibility(View.GONE);
            lvMemberships.setVisibility(View.VISIBLE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText8.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        } else if (v == lvText9 || v == btnMembershipsNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvEducationon.setVisibility(View.GONE);
            lvSpecializations.setVisibility(View.GONE);
            lvVisitingHospitals.setVisibility(View.GONE);
            lvAwards.setVisibility(View.GONE);
            lvExperience.setVisibility(View.GONE);
            lvMemberships.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.VISIBLE);
            lvMoreInformation.setVisibility(View.GONE);

            lvText9.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText10.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        } else if (v == lvText10 || v == btnSocialInformationNext) {
            lvBasicInfo.setVisibility(View.GONE);
            lv_contact_info.setVisibility(View.GONE);
            lvEducationon.setVisibility(View.GONE);
            lvSpecializations.setVisibility(View.GONE);
            lvVisitingHospitals.setVisibility(View.GONE);
            lvAwards.setVisibility(View.GONE);
            lvExperience.setVisibility(View.GONE);
            lvMemberships.setVisibility(View.GONE);
            lvSocialInformation.setVisibility(View.GONE);
            lvMoreInformation.setVisibility(View.VISIBLE);

            lvText10.setBackgroundColor(getResources().getColor(R.color.black));
            lvText2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText7.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText8.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText9.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            lvText1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        }


//        if (v == btnEdtProfile) {
//            // Handle clicks for btnEdtProfile
//        }
//        else if (v == basicinfoNextBtn) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.VISIBLE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//
//        }
// else if (v == btnContactPrevious) {
//
//            lvBasicInfo.setVisibility(View.VISIBLE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//
//
//        } else if (v == btnContactNext) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.VISIBLE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//        }
        else if (v == btnAddMoreEducation) {

            EDTDATA.add("0");
            doctroProfileAddEducationAdapter.notifyItemInserted(EDTDATA.size() - 1);

        }
//    else if (v == btnEducationprevious) {
//
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.VISIBLE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//
//        } else if (v == btnEducationNext) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.VISIBLE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//        }
        else if (v == btnAddMoreSpecializations) {

            Specializations.add("0");
            spelizationAdapter.notifyItemInserted(Specializations.size() - 1);

        }
// else if (v == btnSpecializationsprevious) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.VISIBLE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//        } else if (v == btnSpecializationsNext) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.VISIBLE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//        }
        else if (v == btnAddMoreVisitingHospitals) {

            VisitingHospitals.add("0");
            visitingHospitalAdapter.notifyItemInserted(VisitingHospitals.size() - 1);

        }
// else if (v == btnVisitingHospitalsRevious) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.VISIBLE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//
//
//        } else if (v == btnVisitingHospitalsNext) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.VISIBLE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//
//
//        }
        else if (v == btnAddMoreAwards) {

            Awardss.add("0");
            awardsAdapter.notifyItemInserted(Awardss.size() - 1);


        }
// else if (v == btnAwardsPrevious) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.VISIBLE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//
//        } else if (v == btnAwardsNext) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.VISIBLE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//
//
//        }
        else if (v == btnAddMoreExperience) {

            Experience.add("0");
            experienceAdapter.notifyItemInserted(Experience.size() - 1);

        }
// else if (v == btnExperienceNext) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.VISIBLE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//        }
        else if (v == btnAddMoreMemberships) {

            Memberships.add("0");
            membershipHospitalAdapter.notifyItemInserted(Memberships.size() - 1);

        }
// else if (v == btnMembershipsNext) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.VISIBLE);
//            lvMoreInformation.setVisibility(View.GONE);
//
//        } else if (v == btnSocialInformationNext) {
//
//            lvBasicInfo.setVisibility(View.GONE);
//            lv_contact_info.setVisibility(View.GONE);
//            lvEducationon.setVisibility(View.GONE);
//            lvSpecializations.setVisibility(View.GONE);
//            lvVisitingHospitals.setVisibility(View.GONE);
//            lvAwards.setVisibility(View.GONE);
//            lvExperience.setVisibility(View.GONE);
//            lvMemberships.setVisibility(View.GONE);
//            lvSocialInformation.setVisibility(View.GONE);
//            lvMoreInformation.setVisibility(View.VISIBLE);
//
//        } else if (v == btnUpdateProfile) {
//
//
//        }


    }

    public class DoctroProfileAddVisitingHospitalAdapter extends RecyclerView.Adapter<DoctroProfileAddVisitingHospitalAdapter.ViewHolder> {

        List<String> EDTDATA;
        Context context;
        View view1;
        DoctroProfileAddVisitingHospitalAdapter.ViewHolder viewHolder1;
        Calendar dateSelected = Calendar.getInstance();
        private DatePickerDialog datePickerDialog;


        public DoctroProfileAddVisitingHospitalAdapter(Context context1, List<String> EDTDATA) {

            this.EDTDATA = EDTDATA;
            context = context1;


        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView edt_startdate;

            public ViewHolder(View v) {

                super(v);
                edt_startdate = (TextView) v.findViewById(R.id.edt_startdate);


            }
        }

        @Override
        public DoctroProfileAddVisitingHospitalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view1 = LayoutInflater.from(context).inflate(R.layout.adt_doctro_add_visiting_hospital, parent, false);
            viewHolder1 = new DoctroProfileAddVisitingHospitalAdapter.ViewHolder(view1);
            return viewHolder1;
        }

        @Override
        public void onBindViewHolder(final DoctroProfileAddVisitingHospitalAdapter.ViewHolder holder, int position) {


        }

        @Override
        public int getItemCount() {
            return EDTDATA.size();
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

                Toast.makeText(EditDocProfileActivity.this, "Camera permission denied", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == MY_READWRITE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intentPick = new Intent(Intent.ACTION_GET_CONTENT);
                intentPick.setType("image/*");
                startActivityForResult(Intent.createChooser(intentPick, "Select Action"), PHOTO_PICK);

            } else {

                Toast.makeText(EditDocProfileActivity.this, "Read/Write permission denied", Toast.LENGTH_LONG).show();
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









