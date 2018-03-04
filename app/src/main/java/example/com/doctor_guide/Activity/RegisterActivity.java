package example.com.doctor_guide.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import cz.msebera.android.httpclient.Header;
import example.com.doctor_guide.app.AppController;
import example.com.doctor_guide.util.RoundImageView;
import example.com.homepagefordoctor_guide.R;
import example.com.doctor_guide.util.Constant;

import static android.R.attr.bitmap;
import static example.com.doctor_guide.util.Constant.l_id;

public class RegisterActivity extends AppCompatActivity {

    String citywindow;
    String dir;
    File file;
    ProgressDialog dialog;
    private Calendar calendar;
    private int year, month, day;
    Uri uri1;


    EditText edt_fname, edt_mname, edt_lname, edt_dob, edt_bloodgroup, edt_mobile1, edt_mobile2, edt_landline1,
            edt_landline2, edt_email, edt_website, edt_username, edt_password,edt_confim_password, edt_area, edt_address, edt_city, edt_pincode, edt_state, edt_country, edt_latitude, edt_longtitude,


    edt_hos_bname, edt_hos_person_name, edt_hos_contact_person, edt_hos_landlineno1, edt_hos_landlineno2, edt_hos_email, edt_hos_website,
            edt_hos_username, edt_hos_password, edt_hos_area, edt_hos_address, edt_hos_city, edt_hos_pincode,
            edt_hos_state, edt_hos_country, edt_hos_latitude, edt_hos_longtitude,edt_hos_confim_password,


    edt_ext_bname, edt_ext_person_name, edt_ext_contact_person, edt_ext_landlineno1, edt_ext_landlineno2, edt_ext_mobile, edt_ext_email, edt_ext_website,
            edt_ext_username, edt_ext_password, edt_ext_area, edt_ext_address, edt_ext_city, edt_ext_pincode,
            edt_ext_state, edt_ext_country, edt_ext_latitude, edt_ext_longtitude,edt_ext_confim_password,


    edt_bld_bname, edt_bld_person_name, edt_bld_contact_person, edt_bld_landlineno1, edt_bld_landlineno2, edt_bld_mobile, edt_bld_email, edt_bld_website,
            edt_bld_username, edt_bld_password, edt_bld_area, edt_bld_address, edt_bld_city, edt_bld_pincode,
            edt_bld_state, edt_bld_country, edt_bld_latitude, edt_bld_longtitude,edt_bld_confim_password;


    Button doctor_register, hospital_register, extra_Categort_register, bloodbank_register;
    Spinner spcategory, spin_bloodgroup;
    LinearLayout lndoctor, lnhospital, lnextra, lnbld;
    TextView subcat;
    RoundImageView img_btn;
    String value,bgvalue;
    Bitmap photo;

    Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_register_form);

        spcategory = (Spinner) findViewById(R.id.spincatagory);
        lndoctor = (LinearLayout) findViewById(R.id.lndoctor);
        lnhospital = (LinearLayout) findViewById(R.id.lnhospital);
        lnextra = (LinearLayout) findViewById(R.id.lnextra);
        lnbld = (LinearLayout) findViewById(R.id.lnbld);
        subcat = (TextView) findViewById(R.id.subcat);
        img_btn = (RoundImageView) findViewById(R.id.img_upload_btn);


        doctor_register = (Button) findViewById(R.id.doctor_submit);
        hospital_register = (Button) findViewById(R.id.hos_btn_submit);
        extra_Categort_register = (Button) findViewById(R.id.ext_btn_submit);
        bloodbank_register = (Button) findViewById(R.id.bld_btn_submit);

                      /* For Doctor */
        edt_fname = (EditText) findViewById(R.id.edt_fname);
        edt_lname = (EditText) findViewById(R.id.edt_lname);
        edt_mname = (EditText) findViewById(R.id.edt_mname);
        edt_dob = (EditText) findViewById(R.id.edt_dob);
//        edt_bloodgroup = (EditText) findViewById(R.id.edt_bloodgroup);
        edt_mobile1 = (EditText) findViewById(R.id.edt_mobile1);
        edt_mobile2 = (EditText) findViewById(R.id.edt_mobile2);
        edt_landline1 = (EditText) findViewById(R.id.edt_landline1);
        edt_landline2 = (EditText) findViewById(R.id.edt_landline2);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_website = (EditText) findViewById(R.id.edt_website);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_confim_password = (EditText) findViewById(R.id.edt_confim_password);
        edt_area = (EditText) findViewById(R.id.edt_area);
        edt_address = (EditText) findViewById(R.id.edt_address);
        edt_city = (EditText) findViewById(R.id.edt_city);
        edt_pincode = (EditText) findViewById(R.id.edt_pincode);
        edt_state = (EditText) findViewById(R.id.edt_state);
        edt_country = (EditText) findViewById(R.id.edt_country);
        edt_latitude = (EditText) findViewById(R.id.edt_latitude);
        edt_longtitude = (EditText) findViewById(R.id.edt_longitude);
        spin_bloodgroup = (Spinner) findViewById(R.id.spin_bloodgroup);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edt_dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });







            /* For Hospital*/
        edt_hos_bname = (EditText) findViewById(R.id.edt_hos_bname);
        edt_hos_person_name = (EditText) findViewById(R.id.edt_hos_person_name);
        edt_hos_contact_person = (EditText) findViewById(R.id.edt_hos_contact_person);
        edt_hos_landlineno1 = (EditText) findViewById(R.id.edt_hos_landline1);
        edt_hos_landlineno2 = (EditText) findViewById(R.id.edt_hos_landline2);
        edt_hos_email = (EditText) findViewById(R.id.edt_hos_email);
        edt_hos_website = (EditText) findViewById(R.id.edt_hos_website);
        edt_hos_username = (EditText) findViewById(R.id.edt_hos_username);
        edt_hos_password = (EditText) findViewById(R.id.edt_hos_password);
        edt_hos_confim_password = (EditText) findViewById(R.id.edt_hos_confim_password);
        edt_hos_area = (EditText) findViewById(R.id.edt_hos_area);
        edt_hos_address = (EditText) findViewById(R.id.edt_hos_address);
        edt_hos_city = (EditText) findViewById(R.id.edt_hos_city);
        edt_hos_state = (EditText) findViewById(R.id.edt_hos_state);
        edt_hos_country = (EditText) findViewById(R.id.edt_hos_country);
        edt_hos_latitude = (EditText) findViewById(R.id.edt_hos_latitude);
        edt_hos_longtitude = (EditText) findViewById(R.id.edt_hos_longitude);
        edt_hos_pincode = (EditText) findViewById(R.id.edt_hos_pincode);

                        /* For Extra_Category*/
        edt_ext_bname = (EditText) findViewById(R.id.edt_ext_bname);
        edt_ext_person_name = (EditText) findViewById(R.id.edt_ext_person_name);
        edt_ext_contact_person = (EditText) findViewById(R.id.edt_ext_contact_person);
        edt_ext_landlineno1 = (EditText) findViewById(R.id.edt_ext_landline1);
        edt_ext_landlineno2 = (EditText) findViewById(R.id.edt_ext_landline2);
        edt_ext_email = (EditText) findViewById(R.id.edt_ext_email);
        edt_ext_website = (EditText) findViewById(R.id.edt_ext_website);
        edt_ext_username = (EditText) findViewById(R.id.edt_ext_username);
        edt_ext_password = (EditText) findViewById(R.id.edt_ext_password);
        edt_ext_confim_password = (EditText) findViewById(R.id.edt_ext_confim_password);
        edt_ext_area = (EditText) findViewById(R.id.edt_ext_area);
        edt_ext_address = (EditText) findViewById(R.id.edt_ext_address);
        edt_ext_city = (EditText) findViewById(R.id.edt_ext_city);
        edt_ext_state = (EditText) findViewById(R.id.edt_ext_state);
        edt_ext_country = (EditText) findViewById(R.id.edt_ext_country);
        edt_ext_latitude = (EditText) findViewById(R.id.edt_ext_latitude);
        edt_ext_longtitude = (EditText) findViewById(R.id.edt_ext_longitude);
        edt_ext_pincode = (EditText) findViewById(R.id.edt_ext_pincode);




                         /* For BlooadBank_Category*/

        edt_bld_bname = (EditText) findViewById(R.id.edt_bld_bname);
        edt_bld_person_name = (EditText) findViewById(R.id.edt_bld_person_name);
        edt_bld_contact_person = (EditText) findViewById(R.id.edt_bld_contact_person);
        edt_bld_landlineno1 = (EditText) findViewById(R.id.edt_bld_landline1);
        edt_bld_landlineno2 = (EditText) findViewById(R.id.edt_bld_landline2);
        edt_bld_email = (EditText) findViewById(R.id.edt_bld_email);
        edt_bld_website = (EditText) findViewById(R.id.edt_bld_website);
        edt_bld_username = (EditText) findViewById(R.id.edt_bld_username);
        edt_bld_password = (EditText) findViewById(R.id.edt_bld_password);
        edt_bld_confim_password = (EditText) findViewById(R.id.edt_bld_confim_password);
        edt_bld_area = (EditText) findViewById(R.id.edt_bld_area);
        edt_bld_address = (EditText) findViewById(R.id.edt_bld_address);
        edt_bld_city = (EditText) findViewById(R.id.edt_bld_city);
        edt_bld_state = (EditText) findViewById(R.id.edt_bld_state);
        edt_bld_country = (EditText) findViewById(R.id.edt_bld_country);
        edt_bld_latitude = (EditText) findViewById(R.id.edt_bld_latitude);
        edt_bld_longtitude = (EditText) findViewById(R.id.edt_bld_longitude);
        edt_bld_pincode = (EditText) findViewById(R.id.edt_bld_pincode);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        dialog.setCancelable(false);





        /*  Api Functions*/
        doctor_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Valid()) {
                    doctor_Category_registration();
                }

            }
        });


        hospital_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HospitalValid()) {
                    hospital_Category_registration();
                }
            }
        });

        extra_Categort_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ExtraValid()) {
                    extra_Category_registration();
                }
            }
        });


        bloodbank_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BldValid()) {
                    bloodbank_Category_registration();
                }
            }
        });


        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload_image();
            }
        });




            /*<--------------------------- For Spinner----------------------------->*/

        String[] categories = {"Select Category", "Doctor", "Hospitals", "Wellness",
                "Pharmacies", "Labs", "Fitness",
                "Clinics", "BloodBanks", "Stemcell"};

        String[] bloodgroup = {"Select Blood Group", "A+", "A-", "B+", "B-", "O+", "O-",
                "AB+", "AB-"};

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> bgAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloodgroup);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spcategory.setAdapter(dataAdapter);
        spin_bloodgroup.setAdapter(bgAdapter);
        spcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int position, long row_id) {
//
                value = spcategory.getSelectedItem().toString();

                if (value.equals("Select Category ")) {
                    Constant.cat_id = "0";
                    lndoctor.setVisibility(View.VISIBLE);
                    lnhospital.setVisibility(View.GONE);
                    lnextra.setVisibility(View.GONE);
                    lnbld.setVisibility(View.GONE);
                    spcategory.setSelection(0);
                    Toast.makeText(RegisterActivity.this, "Plz Select category", Toast.LENGTH_SHORT).show();

                } else if (value.equals("Doctor")) {
                    Constant.cat_id = "1";
                    lndoctor.setVisibility(View.VISIBLE);
                    lnhospital.setVisibility(View.GONE);
                    lnextra.setVisibility(View.GONE);
                    lnbld.setVisibility(View.GONE);

                    spcategory.setSelection(1);

                } else if (value.equals("Hospitals")) {
                    Constant.cat_id = "2";
                    lndoctor.setVisibility(View.GONE);
                    lnextra.setVisibility(View.GONE);
                    lnhospital.setVisibility(View.VISIBLE);
                    lnbld.setVisibility(View.GONE);

                    spcategory.setSelection(2);

                } else if (value.equals("Wellness")) {
                    Constant.cat_id = "3";
                    lndoctor.setVisibility(View.GONE);
                    lnhospital.setVisibility(View.GONE);
                    lnextra.setVisibility(View.VISIBLE);
                    lnbld.setVisibility(View.GONE);

                    spcategory.setSelection(3);

                } else if (value.equals("Pharmacies")) {
                    Constant.cat_id = "4";
                    lndoctor.setVisibility(View.GONE);
                    lnhospital.setVisibility(View.GONE);
                    lnextra.setVisibility(View.VISIBLE);
                    lnbld.setVisibility(View.GONE);

                    spcategory.setSelection(4);

                } else if (value.equals("Labs")) {
                    Constant.cat_id = "5";
                    lndoctor.setVisibility(View.GONE);
                    lnhospital.setVisibility(View.GONE);
                    lnextra.setVisibility(View.VISIBLE);
                    lnbld.setVisibility(View.GONE);

                    spcategory.setSelection(5);

                } else if (value.equals("Fitness")) {
                    Constant.cat_id = "6";
                    lndoctor.setVisibility(View.GONE);
                    lnhospital.setVisibility(View.GONE);
                    lnextra.setVisibility(View.VISIBLE);
                    lnbld.setVisibility(View.GONE);

                    spcategory.setSelection(6);

                } else if (value.equals("Clinics")) {
                    Constant.cat_id = "7";
                    lndoctor.setVisibility(View.GONE);
                    lnhospital.setVisibility(View.GONE);
                    lnextra.setVisibility(View.VISIBLE);
                    lnbld.setVisibility(View.GONE);

                    spcategory.setSelection(7);

                } else if (value.equals("BloodBanks")) {
                    Constant.cat_id = "8";
                    lndoctor.setVisibility(View.GONE);
                    lnhospital.setVisibility(View.GONE);
                    lnextra.setVisibility(View.GONE);
                    lnbld.setVisibility(View.VISIBLE);

                    subcat.setVisibility(View.GONE);
                    spcategory.setSelection(8);

                } else if (value.equals("Stemcell")) {
                    Constant.cat_id = "9";
                    lndoctor.setVisibility(View.GONE);
                    lnhospital.setVisibility(View.GONE);
                    lnextra.setVisibility(View.GONE);
                    lnbld.setVisibility(View.VISIBLE);

                    subcat.setVisibility(View.GONE);
                    spcategory.setSelection(9);

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(RegisterActivity.this, "Plz Select category", Toast.LENGTH_SHORT).show();
            }

        });

        for (int i = 0; i < dataAdapter.getCount(); i++) {
            if (categories.toString().trim().equals(dataAdapter.getItem(i).toString())) {
                spcategory.setSelection(i);
                break;
            }
        }
        subcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectsubcat(v);
            }
        });

        spin_bloodgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                bgvalue = spin_bloodgroup.getSelectedItem().toString();

                if (bgvalue.equals("Select Category ")) {

                    spin_bloodgroup.setSelection(0);
                    Toast.makeText(RegisterActivity.this, "Plz Select Blood Group", Toast.LENGTH_SHORT).show();

                } else if (bgvalue.equals("A+")) {

                    spin_bloodgroup.setSelection(1);

                } else if (bgvalue.equals("A-")) {

                    spin_bloodgroup.setSelection(2);

                } else if (bgvalue.equals("B+")) {

                    spin_bloodgroup.setSelection(3);

                } else if (bgvalue.equals("B-")) {


                    spin_bloodgroup.setSelection(4);

                } else if (bgvalue.equals("O+")) {


                    spin_bloodgroup.setSelection(5);

                } else if (bgvalue.equals("O-")) {


                    spin_bloodgroup.setSelection(6);

                } else if (bgvalue.equals("AB+")) {

                    spin_bloodgroup.setSelection(7);

                } else if (bgvalue.equals("AB-")) {

                    spin_bloodgroup.setSelection(8);

                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        }

    private void updateLabel() {
        String myFormat = "yyyy/dd/MM"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edt_dob.setText(sdf.format(myCalendar.getTime()));
    }


    public void selectsubcat(View v) {
        if (value.equals("Select Category")) {
            Toast.makeText(this, "Select Category", Toast.LENGTH_SHORT).show();
        } else {
            Intent it = new Intent(RegisterActivity.this, SelectSubCategoryActivity.class);
            it.putExtra("cat_id", Constant.cat_id);
            startActivity(it);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constant.subcategory != null && !Constant.subcategory.equals("")) {
            subcat.setText(Constant.subcategory);

        }

    }


                        /* Validation Method*/

    private boolean Valid() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";




        if (subcat.getText().toString().trim().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please Choose SubCategory", Toast.LENGTH_SHORT).show();
            return false;

        } else if (edt_fname.getText().toString().trim().isEmpty()) {
            edt_fname.setError("Please Enter valid Name");
            Toast.makeText(RegisterActivity.this, "Please Enter valid Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (edt_lname.getText().toString().trim().isEmpty()) {
                edt_lname.setError("Please Enter valid LastName");
                Toast.makeText(RegisterActivity.this, "Please Enter valid LastName", Toast.LENGTH_SHORT).show();
                return false;

            } else {
                if (edt_mname.getText().toString().trim().isEmpty()) {
                    edt_mname.setError("Please Enter valid MiddleName");
                    Toast.makeText(RegisterActivity.this, "Please Enter valid MiddleName", Toast.LENGTH_SHORT).show();
                    return false;

                } else {
                    if ((edt_mobile1.getText().toString().trim().isEmpty())) {
                        edt_mobile1.setError("Please Enter valid Mobile No");
                        Toast.makeText(RegisterActivity.this, "Please Enter valid Mobile No", Toast.LENGTH_SHORT).show();
                        return false;


                    } else {
                        if (edt_landline1.getText().toString().trim().isEmpty()) {
                            edt_landline1.setError("Please Enter valid LandLine No");
                            Toast.makeText(RegisterActivity.this, "Please Enter valid LandLine No", Toast.LENGTH_SHORT).show();
                            return false;

                        } else {
                            if (edt_dob.getText().toString().trim().isEmpty()) {
                                edt_dob.setError("Please Enter valid Date of Birth");
                                Toast.makeText(RegisterActivity.this, "Please Enter valid Date of Birth", Toast.LENGTH_SHORT).show();
                                return false;

                            } else {
                                if (edt_email.getText().toString().trim().isEmpty()) {
                                    edt_email.setError("Please Enter Valid Email Address");
                                    Toast.makeText(RegisterActivity.this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                                    return false;


                                } else {
                                    if (edt_website.getText().toString().trim().isEmpty()) {
                                        edt_website.setError("Please Enter valid Website");
                                        Toast.makeText(RegisterActivity.this, "Please Enter valid Website", Toast.LENGTH_SHORT).show();
                                        return false;

                                    }  else {
                                            if (edt_username.getText().toString().trim().isEmpty()) {
                                                edt_username.setError("Please Enter UserName");
                                                Toast.makeText(RegisterActivity.this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
                                                return false;

                                            } else {
                                                if (edt_password.getText().toString().trim().isEmpty()) {
                                                    edt_password.setError("Please Enter Password ");
                                                    Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                                                    return false;
                                                } else {
                                                    if (!edt_confim_password.getText().toString().equalsIgnoreCase(edt_password.getText().toString())) {
                                                        edt_confim_password.setError("Password Not Match ");
                                                        Toast.makeText(RegisterActivity.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                                                        return false;

                                                } else {
                                                    if (edt_area.getText().toString().trim().isEmpty()) {
                                                        edt_area.setError("Please Enter valid Area");
                                                        Toast.makeText(RegisterActivity.this, "Please Enter valid Area", Toast.LENGTH_SHORT).show();
                                                        return false;

                                                    } else {
                                                        if (edt_address.getText().toString().trim().isEmpty()) {
                                                            edt_address.setError("Please Enter valid Address");
                                                            Toast.makeText(RegisterActivity.this, "Please Enter valid Address", Toast.LENGTH_SHORT).show();
                                                            return false;

                                                        } else {
                                                            if (edt_city.getText().toString().trim().isEmpty()) {
                                                                edt_city.setError("Please Enter valid City");
                                                                Toast.makeText(RegisterActivity.this, "Please Enter valid City", Toast.LENGTH_SHORT).show();
                                                                return false;

                                                            } else {
                                                                if (edt_pincode.getText().toString().trim().isEmpty()) {
                                                                    edt_pincode.setError("Please Enter valid Pincode");
                                                                    Toast.makeText(RegisterActivity.this, "Please Enter valid Pincode", Toast.LENGTH_SHORT).show();


                                                                    return false;

                                                                } else {
                                                                    if (edt_state.getText().toString().trim().isEmpty()) {
                                                                        edt_state.setError("Please Enter valid State");
                                                                        Toast.makeText(RegisterActivity.this, "Please Enter valid State", Toast.LENGTH_SHORT).show();

                                                                        return false;

                                                                    } else {
                                                                        if (edt_country.getText().toString().trim().isEmpty()) {
                                                                            edt_country.setError("Please Enter valid Country");
                                                                            Toast.makeText(RegisterActivity.this, "Please Enter valid Country", Toast.LENGTH_SHORT).show();

                                                                            return false;

                                                                        } else {
                                                                            if (edt_latitude.getText().toString().trim().isEmpty()) {
                                                                                edt_latitude.setError("Please Enter valid Latitude");
                                                                                Toast.makeText(RegisterActivity.this, "Please Enter valid Latitude", Toast.LENGTH_SHORT).show();
                                                                                return false;

                                                                            } else {
                                                                                if (edt_longtitude.getText().toString().trim().isEmpty()) {
                                                                                    edt_longtitude.setError("Please Enter valid Longtitude");
                                                                                    Toast.makeText(RegisterActivity.this, "Please Enter valid Longtitude", Toast.LENGTH_SHORT).show();
                                                                                    return false;


                                                                                } else {
                                                                                    return true;
                                                                                }

                                                                            }

                                                                        }     }

                                                                    }

                                                                }

                                                            }

                                                        }

                                                    }
                                                }

                                            }

                                        }

                                    }

                                }
                            }
                        }
                    }

                }
            }

        }





    private boolean HospitalValid() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";

        String pincode = "^[1-9][0-9]{5}$\n";

        if (subcat.getText().toString().trim().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please Choose SubCategory", Toast.LENGTH_SHORT).show();
            return false;

        } else if (edt_hos_bname.getText().toString().trim().isEmpty()) {
            edt_hos_bname.setError("Please Enter valid Name");
            Toast.makeText(RegisterActivity.this, "Please Enter valid Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (edt_hos_person_name.getText().toString().trim().isEmpty()) {
                edt_hos_person_name.setError("Please Enter valid Person Name");
                Toast.makeText(RegisterActivity.this, "Please Enter valid Person Name", Toast.LENGTH_SHORT).show();
                return false;


            } else {
                if ((edt_hos_contact_person.getText().toString().trim().isEmpty())) {
                    edt_hos_contact_person.setError("Please Enter valid Mobile No");
                    Toast.makeText(RegisterActivity.this, "Please Enter valid Mobile No", Toast.LENGTH_SHORT).show();
                    return false;


                } else {
                    if (edt_hos_landlineno1.getText().toString().trim().isEmpty()) {
                        edt_hos_landlineno1.setError("Please Enter valid LandLine No");
                        Toast.makeText(RegisterActivity.this, "Please Enter valid LandLine No", Toast.LENGTH_SHORT).show();
                        return false;


                    } else {
                        if (edt_hos_email.getText().toString().trim().isEmpty()) {
                            edt_hos_email.setError("Please Enter Valid Email Address");
                            Toast.makeText(RegisterActivity.this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                            return false;


                        } else {
                            if (edt_hos_website.getText().toString().trim().isEmpty()) {
                                edt_hos_website.setError("Please Enter valid Website");
                                Toast.makeText(RegisterActivity.this, "Please Enter valid Website", Toast.LENGTH_SHORT).show();
                                return false;


                            } else {
                                if (edt_hos_username.getText().toString().trim().isEmpty()) {
                                    edt_hos_username.setError("Please Enter UserName");
                                    Toast.makeText(RegisterActivity.this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
                                    return false;

                                } else {
                                    if (edt_hos_password.getText().toString().trim().isEmpty()) {
                                        edt_hos_password.setError("Please Enter Password ");
                                        Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                                        return false;
                                    } else {
                                        if (!edt_hos_confim_password.getText().toString().equalsIgnoreCase(edt_hos_password.getText().toString())) {
                                            edt_hos_confim_password.setError("Password Not Match ");
                                            Toast.makeText(RegisterActivity.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                                            return false;


                                        } else {
                                            if (edt_hos_area.getText().toString().trim().isEmpty()) {
                                                edt_hos_area.setError("Please Enter valid Area");
                                                Toast.makeText(RegisterActivity.this, "Please Enter valid Area", Toast.LENGTH_SHORT).show();
                                                return false;

                                            } else {
                                                if (edt_hos_address.getText().toString().trim().isEmpty()) {
                                                    edt_hos_address.setError("Please Enter valid Address");
                                                    Toast.makeText(RegisterActivity.this, "Please Enter valid Address", Toast.LENGTH_SHORT).show();
                                                    return false;

                                                } else {
                                                    if (edt_hos_city.getText().toString().trim().isEmpty()) {
                                                        edt_hos_city.setError("Please Enter valid City");
                                                        Toast.makeText(RegisterActivity.this, "Please Enter valid City", Toast.LENGTH_SHORT).show();
                                                        return false;

                                                    } else {
                                                        if (edt_hos_pincode.getText().toString().trim().isEmpty()) {
                                                            edt_hos_pincode.setError("Please Enter valid Pincode");
                                                            Toast.makeText(RegisterActivity.this, "Please Enter valid Pincode", Toast.LENGTH_SHORT).show();
                                                            return false;

                                                        } else {
                                                            if (edt_hos_state.getText().toString().trim().isEmpty()) {
                                                                edt_hos_state.setError("Please Enter valid State");
                                                                Toast.makeText(RegisterActivity.this, "Please Enter valid State", Toast.LENGTH_SHORT).show();
                                                                return false;

                                                            } else {
                                                                if (edt_hos_country.getText().toString().trim().isEmpty()) {
                                                                    edt_hos_country.setError("Please Enter valid Country");
                                                                    Toast.makeText(RegisterActivity.this, "Please Enter valid Country", Toast.LENGTH_SHORT).show();
                                                                    return false;

                                                                } else {
                                                                    if (edt_hos_latitude.getText().toString().trim().isEmpty()) {
                                                                        edt_hos_latitude.setError("Please Enter valid Latitude");
                                                                        Toast.makeText(RegisterActivity.this, "Please Enter valid Latitude", Toast.LENGTH_SHORT).show();
                                                                        return false;

                                                                    } else {
                                                                        if (edt_hos_longtitude.getText().toString().trim().isEmpty()) {
                                                                            edt_hos_longtitude.setError("Please Enter valid Longtitude");
                                                                            Toast.makeText(RegisterActivity.this, "Please Enter valid Longtitude", Toast.LENGTH_SHORT).show();
                                                                            return false;


                                                                        } else {
                                                                            return true;
                                                                        }


                                                                    }

                                                                }

                                                            }

                                                        }

                                                    }

                                                }
                                            }

                                        }

                                    }

                                }

                            }
                        }
                    }
                }


            }
        }
        }





    private boolean ExtraValid() {


        if (subcat.getText().toString().trim().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please Choose SubCategory", Toast.LENGTH_SHORT).show();
            return false;

        } else if (edt_ext_bname.getText().toString().trim().isEmpty()) {
            edt_ext_bname.setError("Please Enter valid Name");
            Toast.makeText(RegisterActivity.this, "Please Enter valid Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (edt_ext_person_name.getText().toString().trim().isEmpty()) {
                edt_ext_person_name.setError("Please Enter valid Person Name");
                Toast.makeText(RegisterActivity.this, "Please Enter valid Name", Toast.LENGTH_SHORT).show();
                return false;

            } else {
                if ((edt_ext_contact_person.getText().toString().trim().isEmpty())) {
                    edt_ext_contact_person.setError("Please Enter valid Mobile No");
                    Toast.makeText(RegisterActivity.this, "Please Enter valid Mobile No", Toast.LENGTH_SHORT).show();
                    return false;


                } else {
                    if (edt_ext_landlineno1.getText().toString().trim().isEmpty()) {
                        edt_ext_landlineno1.setError("Please Enter valid LandLine No");
                        Toast.makeText(RegisterActivity.this, "Please Enter valid LandLine No", Toast.LENGTH_SHORT).show();
                        return false;


                    } else {
                        if (edt_ext_email.getText().toString().trim().isEmpty()) {
                            edt_ext_email.setError("Please Enter Valid Email Address");
                            Toast.makeText(RegisterActivity.this, "Please Enter valid Email Address", Toast.LENGTH_SHORT).show();
                            return false;


                        } else {
                            if (edt_ext_website.getText().toString().trim().isEmpty()) {
                                edt_ext_website.setError("Please Enter valid Website");
                                Toast.makeText(RegisterActivity.this, "Please Enter valid Website", Toast.LENGTH_SHORT).show();
                                return false;


                            } else {
                                if (edt_ext_username.getText().toString().trim().isEmpty()) {
                                    edt_ext_username.setError("Please Enter UserName");
                                    Toast.makeText(RegisterActivity.this, "Please Enter valid UserName", Toast.LENGTH_SHORT).show();
                                    return false;

                                } else {
                                    if (edt_ext_password.getText().toString().trim().isEmpty()) {
                                        edt_ext_password.setError("Please Enter Password ");
                                        Toast.makeText(RegisterActivity.this, "Please Enter valid Password", Toast.LENGTH_SHORT).show();
                                        return false;
                                    }else{
                                            if (!edt_ext_confim_password.getText().toString().equalsIgnoreCase(edt_ext_password.getText().toString())) {
                                                edt_ext_confim_password.setError("Password Not Match ");
                                                Toast.makeText(RegisterActivity.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                                                return false;

                                            } else {
                                                if (edt_ext_area.getText().toString().trim().isEmpty()) {
                                                    edt_ext_area.setError("Please Enter valid Area");
                                                    Toast.makeText(RegisterActivity.this, "Please Enter valid Area", Toast.LENGTH_SHORT).show();
                                                    return false;

                                                } else {
                                                    if (edt_ext_address.getText().toString().trim().isEmpty()) {
                                                        edt_ext_address.setError("Please Enter valid Address");
                                                        Toast.makeText(RegisterActivity.this, "Please Enter valid Address", Toast.LENGTH_SHORT).show();
                                                        return false;

                                                    } else {
                                                        if (edt_ext_city.getText().toString().trim().isEmpty()) {
                                                            edt_ext_city.setError("Please Enter valid City");
                                                            Toast.makeText(RegisterActivity.this, "Please Enter valid City", Toast.LENGTH_SHORT).show();
                                                            return false;

                                                        } else {
                                                            if (edt_ext_pincode.getText().toString().trim().isEmpty()) {
                                                                edt_ext_pincode.setError("Please Enter valid Pincode");
                                                                Toast.makeText(RegisterActivity.this, "Please Enter valid Pincode", Toast.LENGTH_SHORT).show();
                                                                return false;

                                                            } else {
                                                                if (edt_ext_state.getText().toString().trim().isEmpty()) {
                                                                    edt_ext_state.setError("Please Enter valid State");
                                                                    Toast.makeText(RegisterActivity.this, "Please Enter valid State", Toast.LENGTH_SHORT).show();
                                                                    return false;

                                                                } else {
                                                                    if (edt_ext_country.getText().toString().trim().isEmpty()) {
                                                                        edt_ext_country.setError("Please Enter valid Country");
                                                                        Toast.makeText(RegisterActivity.this, "Please Enter valid Country", Toast.LENGTH_SHORT).show();
                                                                        return false;

                                                                    } else {
                                                                        if (edt_ext_latitude.getText().toString().trim().isEmpty()) {
                                                                            edt_ext_latitude.setError("Please Enter valid Latitude");
                                                                            Toast.makeText(RegisterActivity.this, "Please Enter valid Latitude", Toast.LENGTH_SHORT).show();
                                                                            return false;

                                                                        } else {
                                                                            if (edt_ext_longtitude.getText().toString().trim().isEmpty()) {
                                                                                edt_ext_longtitude.setError("Please Enter valid Longtitude");
                                                                                Toast.makeText(RegisterActivity.this, "Please Enter valid Longtitude", Toast.LENGTH_SHORT).show();
                                                                                return false;


                                                                            } else {
                                                                                return true;
                                                                            }

                                                                        }

                                                                    }

                                                                }

                                                            }

                                                        }

                                                    }
                                                }

                                            }

                                        }


                                    }
                                }
                            }
                        }


                    }

                }
            }


    }

    private boolean BldValid() {


        if (edt_bld_bname.getText().toString().trim().isEmpty()) {
            edt_hos_bname.setError("Please Enter valid Business Name");
            Toast.makeText(RegisterActivity.this, "Please Enter valid Business Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (edt_bld_person_name.getText().toString().trim().isEmpty()) {
                edt_bld_person_name.setError("Please Enter valid Person Name");
                Toast.makeText(RegisterActivity.this, "Please Enter valid Person Name", Toast.LENGTH_SHORT).show();
                return false;

            } else {
                if ((edt_bld_contact_person.getText().toString().trim().isEmpty())) {
                    edt_bld_contact_person.setError("Please Enter valid Mobile No");
                    Toast.makeText(RegisterActivity.this, "Please Enter valid Mobile No", Toast.LENGTH_SHORT).show();
                    return false;




                    } else {
                        if (edt_bld_landlineno1.getText().toString().trim().isEmpty()) {
                            edt_bld_landlineno1.setError("Please Enter valid LandLine No");
                            Toast.makeText(RegisterActivity.this, "Please Enter valid LandLine No", Toast.LENGTH_SHORT).show();
                            return false;


                        } else {
                            if (edt_bld_email.getText().toString().trim().isEmpty()) {
                                edt_bld_email.setError("Please Enter Valid Email Address");
                                Toast.makeText(RegisterActivity.this, "Please Enter valid Email Address", Toast.LENGTH_SHORT).show();
                                return false;


                            } else {
                                if (edt_bld_website.getText().toString().trim().isEmpty()) {
                                    edt_bld_website.setError("Please Enter valid Website");
                                    Toast.makeText(RegisterActivity.this, "Please Enter valid Website", Toast.LENGTH_SHORT).show();
                                    return false;


                                } else {
                                    if (edt_bld_username.getText().toString().trim().isEmpty()) {
                                        edt_bld_username.setError("Please Enter UserName");
                                        Toast.makeText(RegisterActivity.this, "Please Enter valid UserName", Toast.LENGTH_SHORT).show();
                                        return false;

                                    } else {
                                        if (edt_bld_password.getText().toString().trim().isEmpty()) {
                                            edt_bld_password.setError("Please Enter Password ");
                                            Toast.makeText(RegisterActivity.this, "Please Enter valid Password", Toast.LENGTH_SHORT).show();
                                            return false;
                                        } else {
                                            if (!edt_bld_confim_password.getText().toString().equalsIgnoreCase(edt_bld_password.getText().toString())){
                                                edt_bld_confim_password.setError("Password Not Match ");
                                                Toast.makeText(RegisterActivity.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                                                return false;

                                        } else {
                                            if (edt_bld_area.getText().toString().trim().isEmpty()) {
                                                edt_bld_area.setError("Please Enter valid Area");
                                                Toast.makeText(RegisterActivity.this, "Please Enter valid Area", Toast.LENGTH_SHORT).show();
                                                return false;

                                            } else {
                                                if (edt_bld_address.getText().toString().trim().isEmpty()) {
                                                    edt_bld_address.setError("Please Enter valid Address");
                                                    Toast.makeText(RegisterActivity.this, "Please Enter valid Address", Toast.LENGTH_SHORT).show();
                                                    return false;

                                                } else {
                                                    if (edt_bld_city.getText().toString().trim().isEmpty()) {
                                                        edt_bld_city.setError("Please Enter valid City");
                                                        Toast.makeText(RegisterActivity.this, "Please Enter valid City", Toast.LENGTH_SHORT).show();
                                                        return false;

                                                    } else {
                                                        if (edt_bld_pincode.getText().toString().trim().isEmpty()) {
                                                            edt_bld_pincode.setError("Please Enter valid Pincode");
                                                            Toast.makeText(RegisterActivity.this, "Please Enter valid Pincode", Toast.LENGTH_SHORT).show();
                                                            return false;

                                                        } else {
                                                            if (edt_bld_state.getText().toString().trim().isEmpty()) {
                                                                edt_hos_state.setError("Please Enter valid State");
                                                                Toast.makeText(RegisterActivity.this, "Please Enter valid State", Toast.LENGTH_SHORT).show();
                                                                return false;

                                                            } else {
                                                                if (edt_bld_country.getText().toString().trim().isEmpty()) {
                                                                    edt_bld_country.setError("Please Enter valid Country");
                                                                    Toast.makeText(RegisterActivity.this, "Please Enter valid Country", Toast.LENGTH_SHORT).show();
                                                                    return false;

                                                                } else {
                                                                    if (edt_bld_latitude.getText().toString().trim().isEmpty()) {
                                                                        edt_bld_latitude.setError("Please Enter valid Latitude");
                                                                        Toast.makeText(RegisterActivity.this, "Please Enter valid Latitude", Toast.LENGTH_SHORT).show();
                                                                        return false;

                                                                    } else {
                                                                        if (edt_bld_longtitude.getText().toString().trim().isEmpty()) {
                                                                            edt_bld_longtitude.setError("Please Enter valid Longtitude");
                                                                            Toast.makeText(RegisterActivity.this, "Please Enter valid Longtitude", Toast.LENGTH_SHORT).show();
                                                                            return false;


                                                                        } else {
                                                                            return true;
                                                                        }


                                                                    }
                                                                    }

                                                                }

                                                            }



                                                    }

                                                }
                                            }

                                        }

                                    }

                                }

                            }
                        }
                    }
                }


            }

        }



    }

    private void upload_image() {
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    Uri uri1 = data.getData();
                    img_btn.setImageURI(uri1);
                    try {
                        photo = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                        img_btn.setImageBitmap(photo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    saveImage(photo);
                    break;
            }
        }

    }

    void saveImage(Bitmap img) {
        dir = Environment.getExternalStorageDirectory()
                + File.separator + "doctor_slider-guide";
        File myDir = new File(dir);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        citywindow = "Image-" + n + ".jpg";
        file = new File(myDir, citywindow);
        long length = file.length();
        Log.e("length", "" + length);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            img.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
//            Constant.rsub_id = " ";
            subcat.setText("");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void bloodbank_Category_registration() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();


            params.add("cat_id", Constant.cat_id);
            params.add("img", String.valueOf(file));
            params.add("e_name", edt_bld_bname.getText().toString());
            params.add("e_person", edt_bld_person_name.getText().toString());
            params.add("e_contact_person", edt_bld_contact_person.getText().toString());
            params.add("e_landline", edt_bld_landlineno1.getText().toString());
            params.add("e_landline", edt_bld_landlineno1.getText().toString());
            params.add("e_email", edt_bld_email.getText().toString());
            params.add("e_username", edt_bld_username.getText().toString());
            params.add("e_password", edt_bld_password.getText().toString());
            params.add("e_website", edt_bld_website.getText().toString());
            params.add("e_area", edt_bld_area.getText().toString());
            params.add("e_address", edt_bld_address.getText().toString());
            params.add("e_city", edt_bld_city.getText().toString());
            params.add("e_state", edt_bld_state.getText().toString());
            params.add("e_country ", edt_bld_country.getText().toString());
            params.add("e_latitude", edt_bld_latitude.getText().toString());
            params.add("e_longitude ", edt_bld_longtitude.getText().toString());

            try {
                params.put("img", file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            client.post(Constant.bldCategory_register_category_url, params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        //rv_list.clear();
                        String str = null;
                        String test = null;
                        JSONArray resultsarray = null;
                        Log.e("reg_res:", "" + responseString);


                        try {
                            JSONObject jsonObj = new JSONObject(responseString);
                            str = jsonObj.getString("success");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (str.equals("true")) {
                            //Constants.user_mob = et_Register_Mobile.getText().toString();
                            Toast.makeText(getApplicationContext(), "Sucessfully Register", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(GetRegistrationPhotoActivity.this, HomemenuDisplayActivity.class));
                            dialog.dismiss();
                            finish();
                        } else if (str.equals("false")) {
                            dialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "data is not available!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(RegisterActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void hospital_Category_registration() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();


            params.add("cat_id", Constant.cat_id);
            params.add("sub_id", Constant.rsub_id);
            params.add("h_name", edt_hos_bname.getText().toString());
            params.add("h_person", edt_hos_person_name.getText().toString());
            params.add("h_contact_person", edt_hos_contact_person.getText().toString());
            params.add("h_landline", edt_hos_landlineno1.getText().toString());
            params.add("d_landline", edt_hos_landlineno1.getText().toString());
            params.add("h_email", edt_hos_email.getText().toString());
            params.add("h_username", edt_hos_username.getText().toString());
            params.add("h_password", edt_hos_password.getText().toString());
            params.add("h_website", edt_hos_website.getText().toString());
            params.add("h_area", edt_hos_area.getText().toString());
            params.add("h_address", edt_hos_address.getText().toString());
            params.add("h_city", edt_hos_city.getText().toString());
            params.add("h_state", edt_hos_state.getText().toString());
            params.add("h_country ", edt_hos_country.getText().toString());
            params.add("h_latitude", edt_hos_latitude.getText().toString());
            params.add("h_longitude ", edt_hos_longtitude.getText().toString());
            try {
                params.put("img", file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            client.post(Constant.hospital_register_category_url, params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        String str = null;
                        String test = null;
                        JSONArray resultsarray = null;
                        Log.e("reg_res:", "" + responseString);


                        try {
                            JSONObject jsonObj = new JSONObject(responseString);
                            str = jsonObj.getString("success");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (str.equals("true")) {
                            Toast.makeText(getApplicationContext(), "Sucessfully Register", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            finish();
                        } else if (str.equals("false")) {
                            dialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "data is not available!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(RegisterActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void doctor_Category_registration() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();
            params.add("cat_id", Constant.cat_id);
            params.add("sub_id", Constant.rsub_id);
            params.add("d_firstname", edt_fname.getText().toString());
            params.add("d_middelname", edt_mname.getText().toString());
            params.add("d_lastname", edt_lname.getText().toString());
            params.add("d_dob", edt_dob.getText().toString());
            params.add("d_bloodgroup", bgvalue);
            Log.e("d_bloodgroup",""+ bgvalue);
            params.add("d_contact", edt_mobile1.getText().toString());
            params.add("d_contact", edt_mobile2.getText().toString());
            params.add("d_landline", edt_landline1.getText().toString());
            params.add("d_landline", edt_landline2.getText().toString());
            params.add("d_email", edt_email.getText().toString());
            params.add("d_username", edt_username.getText().toString());
            params.add("d_password", edt_password.getText().toString());
            params.add("d_website", edt_website.getText().toString());
            params.add("d_area", edt_area.getText().toString());
            params.add("d_address", edt_address.getText().toString());
            params.add("d_city", edt_city.getText().toString());
            params.add("d_pincode", edt_pincode.getText().toString());
            params.add("d_state", edt_state.getText().toString());
            params.add("d_country ", edt_country.getText().toString());
            params.add("d_latitude", edt_latitude.getText().toString());
            params.add("d_longitude ", edt_longtitude.getText().toString());
            try {
                params.put("img", file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            client.post(Constant.doctor_register_category_url, params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
                {
                    Toast.makeText(RegisterActivity.this, responseString , Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        //rv_list.clear();
                        String str = null;
                        String test = null;
                        JSONArray resultsarray = null;
                        Log.e("reg_res:", "" + responseString);


                        try {
                            JSONObject jsonObj = new JSONObject(responseString);
                            str = jsonObj.getString("success");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (str.equals("true")) {
                            //Constants.user_mob = et_Register_Mobile.getText().toString();
                            Toast.makeText(getApplicationContext(), "Sucessfully Register", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(GetRegistrationPhotoActivity.this, HomemenuDisplayActivity.class));
                            dialog.dismiss();
                            finish();
                        } else if (str.equals("false")) {
                            dialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "data is not available!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(RegisterActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void extra_Category_registration() {
        if (isNetworkAvailable()) {
            dialog.show();
            AsyncHttpClient client = new AsyncHttpClient();
            client.setTimeout(800000);
            RequestParams params = new RequestParams();

            params.add("cat_id", Constant.cat_id);
            params.add("sub_id", Constant.rsub_id);
            params.add("e_name", edt_ext_bname.getText().toString());
            params.add("e_person", edt_ext_person_name.getText().toString());
            params.add("e_contact_person", edt_ext_contact_person.getText().toString());
            params.add("e_landline", edt_ext_landlineno1.getText().toString());
            params.add("e_landline", edt_ext_landlineno1.getText().toString());
            params.add("e_email", edt_ext_email.getText().toString());
            params.add("e_username", edt_ext_username.getText().toString());
            params.add("e_password", edt_ext_password.getText().toString());
            params.add("e_website", edt_ext_website.getText().toString());
            params.add("e_area", edt_ext_area.getText().toString());
            params.add("e_address", edt_ext_address.getText().toString());
            params.add("e_city", edt_ext_city.getText().toString());
            params.add("e_state", edt_ext_state.getText().toString());
            params.add("e_country ", edt_ext_country.getText().toString());
            params.add("e_latitude", edt_ext_latitude.getText().toString());
            params.add("e_longitude ", edt_ext_longtitude.getText().toString());

            try {
                params.put("img", file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            client.post(Constant.extraCategory_register_category_url, params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    dialog.dismiss();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (responseString != null) {
                        //rv_list.clear();
                        String str = null;
                        String test = null;
                        JSONArray resultsarray = null;
                        Log.e("reg_res:", "" + responseString);


                        try {
                            JSONObject jsonObj = new JSONObject(responseString);
                            str = jsonObj.getString("success");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if (str.equals("true")) {
                            //Constants.user_mob = et_Register_Mobile.getText().toString();
                            Toast.makeText(getApplicationContext(), "Sucessfully Register", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(GetRegistrationPhotoActivity.this, HomemenuDisplayActivity.class));
                            dialog.dismiss();
                            finish();
                        } else if (str.equals("false")) {
                            dialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.dismiss();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "data is not available!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(RegisterActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        subcat.setText("");
        finish();
        Constant.rsub_id = "";
        Constant.subcategory = "";


    }

}


