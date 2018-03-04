package example.com.doctor_guide.Activity;

import android.app.ActivityOptions;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import example.com.homepagefordoctor_guide.R;
import example.com.doctor_guide.Adapter.SliderAdapter;
import example.com.doctor_guide.util.Constant;

import static example.com.doctor_guide.util.Constant.cat_id;
import static example.com.doctor_guide.util.Constant.l_id;

public class MainActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Button doctor, hospitals, wellness, pharmacies, labs, fitness, bloodbanks, clinics, stemcell;
    private ImageView imgdoctor, imghospitals, imgwellness, imgpharmacies, imglabs, imgfitness,
            imgbloodbanks, imgclinics, imgstemcell, btnshop, btnfestival, btnevent, btndeal;
    private static TextView mDotsText[];
    TextView tvdeal, tvevent, tvfestival, tvshop;
    private static final int NUM_PAGES = 7;
    private LinearLayout mDotsLayout;
    private int mDotsCount;
    int currentPage = 1;
    Timer timer;

    int page = 1;
    private ViewPager viewPager;
    CoordinatorLayout cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        doctor = (Button) findViewById(R.id.doctorbtn);
        hospitals = (Button) findViewById(R.id.hospitalbtn);
        wellness = (Button) findViewById(R.id.wellnessbtn);
        pharmacies = (Button) findViewById(R.id.pharmaciesbtn);
        labs = (Button) findViewById(R.id.labsbtn);
        fitness = (Button) findViewById(R.id.fitnessbtn);
        bloodbanks = (Button) findViewById(R.id.bloodbanksbtn);
        clinics = (Button) findViewById(R.id.clinicsbtn);
        stemcell = (Button) findViewById(R.id.stemcellbtn);

        imgdoctor = (ImageView) findViewById(R.id.doctor);
        imghospitals = (ImageView) findViewById(R.id.hospital);
        imghospitals = (ImageView) findViewById(R.id.hospital);
        imgwellness = (ImageView) findViewById(R.id.wellness);
        imgpharmacies = (ImageView) findViewById(R.id.pharmacies);
        imglabs = (ImageView) findViewById(R.id.labs);
        imgfitness = (ImageView) findViewById(R.id.fitness);
        imgbloodbanks = (ImageView) findViewById(R.id.bloodbanks);
        imgclinics = (ImageView) findViewById(R.id.clinics);
        imgstemcell = (ImageView) findViewById(R.id.stemcell);
        btnshop = (ImageView) findViewById(R.id.btn_shop);
        btnfestival = (ImageView) findViewById(R.id.btn_festival);
        btnevent = (ImageView) findViewById(R.id.btn_event);
        btndeal = (ImageView) findViewById(R.id.btn_deal);
        tvdeal = (TextView) findViewById(R.id.tv_deal);
        tvfestival = (TextView) findViewById(R.id.tv_festival);
        tvshop = (TextView) findViewById(R.id.tv_shop);
        tvevent = (TextView) findViewById(R.id.tv_event);
        cd = (CoordinatorLayout) findViewById(R.id.app_bar);


        SliderAdapter imageAdapter = new SliderAdapter(this);
        viewPager.setAdapter(imageAdapter);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES - 1) {
                    currentPage = 1;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 300, 3000);

        mDotsLayout = (LinearLayout) findViewById(R.id.image_count);
        mDotsCount = viewPager.getAdapter().getCount();

        mDotsText = new TextView[mDotsCount];
        for (int i = 0; i < mDotsCount; i++) {
            mDotsText[i] = new TextView(this);
            mDotsText[i].setText(".");
            mDotsText[i].setTextSize(45);
            mDotsText[i].setTypeface(null, Typeface.BOLD);
            mDotsText[i].setTextColor(Color.RED);
            mDotsLayout.addView(mDotsText[i]);


        }
        mDotsText[0].setTextColor(Color.DKGRAY);
        mDotsCount = viewPager.getAdapter().getCount();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                for (int i = 0; i < mDotsCount; i++) {
                    mDotsText[i].setTextColor(Color.DKGRAY);

                }

                mDotsText[position].setTextColor(Color.RED);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        Button btn = (Button) menu.findItem(R.id.register).getActionView();

//        this.menu = menu;  // this will copy menu values to upper defined menu so that we can change icon later akash

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_doctor) {
            Intent i = new Intent(this, DoctorTypeCategory1Activity.class);
            i.putExtra("cat_id", 1);
            Constant.cat_id = "1";
            startActivity(i);


        } else if (id == R.id.nav_hospitals) {
            Intent i = new Intent(this, Hospitals_subCategory.class);
            i.putExtra(cat_id, 2);
            Constant.cat_id = "2";
            startActivity(i);


        } else if (id == R.id.nav_wellness) {
            Intent i = new Intent(this, Extra_SubcategoryActivity.class);
            i.putExtra(cat_id, 3);
            Constant.cat_id = "3";
            startActivity(i);

        } else if (id == R.id.nav_pharamacies) {

            Intent i = new Intent(this, Extra_SubcategoryActivity.class);
            i.putExtra(cat_id, 4);
            Constant.cat_id = "4";
            startActivity(i);
        } else if (id == R.id.nav_labs) {
            Intent i = new Intent(this, Extra_SubcategoryActivity.class);
            i.putExtra(cat_id, 5);
            Constant.cat_id = "5";
            startActivity(i);
        } else if (id == R.id.nav_fitness) {
            Intent i = new Intent(this, Extra_SubcategoryActivity.class);
            i.putExtra(cat_id, 6);
            Constant.cat_id = "6";
            startActivity(i);
        } else if (id == R.id.nav_bloodbanks) {
            Intent i = new Intent(this, Extra_SubcategoryActivity.class);
            i.putExtra(cat_id, 8);
            Constant.cat_id = "8";
            startActivity(i);
           /* Intent i = new Intent(this, Bloodbank_And_Stemcell_list_CategoryActivity.class);
            i.putExtra(cat_id, 8);
            Constant.cat_id = "8";
            startActivity(i);
*/
        } else if (id == R.id.nav_clinics) {
            Intent i = new Intent(this, Extra_SubcategoryActivity.class);
            i.putExtra(cat_id, 7);
            Constant.cat_id = "7";
            startActivity(i);

        } else if (id == R.id.nav_stemcell) {
            Intent i = new Intent(this, Extra_SubcategoryActivity.class);
            i.putExtra(cat_id, 9);
            Constant.cat_id = "9";
            startActivity(i);
           /* Intent i = new Intent(this, Bloodbank_And_Stemcell_list_CategoryActivity.class);
            i.putExtra(l_id, 9);
            Constant.l_id = "9";
            startActivity(i);*/

        } else if (id == R.id.nav_doc_login) {
            Intent i = new Intent(this, LoginDoctorActivity.class);
            startActivity(i);
        }  else if (id == R.id.nav_doctor_profile) {

            Intent i = new Intent(this, EditDocProfileActivity.class);
            startActivity(i);
        }

        else if (id == R.id.nav_wellness_profile) {
            Intent i = new Intent(this, WellnessProfileActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_hospital_profile) {
            Intent i = new Intent(this, HospitalProfileActivity.class);
            startActivity(i);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void doctor(View v) {
        Intent i = new Intent(this, DoctorTypeCategory1Activity.class);
        i.putExtra("cat_id", 1);

        Constant.cat_id = "1";
        startActivity(i);
    }


    public void hospitals(View v) {

        Intent i = new Intent(this, Hospitals_subCategory.class);
        i.putExtra(cat_id, 2);
        Constant.cat_id = "2";
        startActivity(i);
    }

    public void wellness(View v) {

        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 3);
        Constant.cat_id = "3";
        startActivity(i);

    }

    public void pharamacies(View v) {
        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 4);
        Constant.cat_id = "4";
        startActivity(i);
    }

    public void labs(View v) {
        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 5);

        Constant.cat_id = "5";
        startActivity(i);
    }

    public void fitness(View v) {
        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 6);

        Constant.cat_id = "6";
        startActivity(i);
    }

    public void bloodbanks(View v) {
        Intent i = new Intent(this, Bloodbank_And_Stemcell_list_CategoryActivity.class);
        i.putExtra(cat_id, 8);
        Constant.cat_id = "8";
        startActivity(i);

    }

    public void clinics(View v) {
        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 7);

        Constant.cat_id = "7";
        startActivity(i);
    }

    public void stemcell(View v) {
        Intent i = new Intent(this, Bloodbank_And_Stemcell_list_CategoryActivity.class);
        i.putExtra(cat_id, 9);
        Constant.cat_id = "9";
        startActivity(i);
    }

    public void doctorbtn(View v) {
        Intent i = new Intent(this, DoctorTypeCategory1Activity.class);
        i.putExtra(cat_id, 1);
        Constant.cat_id = "1";
        startActivity(i);
    }

    public void hospitalbtn(View v) {
        Intent i = new Intent(this, Hospitals_subCategory.class);
        i.putExtra(cat_id, 2);

        Constant.cat_id = "2";
        startActivity(i);
    }

    public void wellnessbtn(View v) {

        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 3);

        Constant.cat_id = "3";
        startActivity(i);
    }

    public void pharamaciesbtn(View v) {
        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 4);

        Constant.cat_id = "4";
        startActivity(i);
    }

    public void labsbtn(View v) {
        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 5);

        Constant.cat_id = "5";
        startActivity(i);
    }

    public void fitnessbtn(View v) {
        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 6);

        Constant.cat_id = "6";
        startActivity(i);
    }

    public void clinicsbtn(View v) {
        Intent i = new Intent(this, Extra_SubcategoryActivity.class);
        i.putExtra(cat_id, 7);

        Constant.cat_id = "7";
        startActivity(i);
    }

    public void bloodbanksbtn(View v) {
        Intent i = new Intent(this, Bloodbank_And_Stemcell_list_CategoryActivity.class);
        i.putExtra(cat_id, 8);

        Constant.cat_id = "8";
        startActivity(i);
    }


    public void stemcellbtn(View v) {
        Intent i = new Intent(this, Bloodbank_And_Stemcell_list_CategoryActivity.class);
        i.putExtra(cat_id, 9);

        Constant.cat_id = "9";
        startActivity(i);
    }


    public boolean register(MenuItem item) {
        startActivity(new Intent(this, RegisterActivity.class));
        return true;
    }

    public void btnshop(View v) {
        Intent i = new Intent(this, Footer_Btn_Activity.class);
//        i.putExtra(cat_id, 9);
        Constant.cat_id = "1";

        startActivity(i);
    }


    public void btnfestival(View v) {
        Intent i = new Intent(this, Footer_Btn_Activity.class);
//        i.putExtra(cat_id, 9);
        Constant.cat_id = "1";

        startActivity(i);
    }


    public void btnevent(View v) {
        Intent i = new Intent(this, Footer_Btn_Activity.class);
//        i.putExtra(cat_id, 9);
        Constant.cat_id = "1";

        startActivity(i);
    }


    public void btndeal(View v) {
        Intent i = new Intent(this, Footer_Btn_Activity.class);
//        i.putExtra(cat_id, 9);
        Constant.cat_id = "1";
        startActivity(i);
    }

    public void tvdeal(View v) {
        Intent i = new Intent(this, Footer_Btn_Activity.class);
//        i.putExtra(cat_id, 9);
        Constant.cat_id = "1";

        startActivity(i);
    }

    public void tvevent(View v) {
        Intent i = new Intent(this, Footer_Btn_Activity.class);
//        i.putExtra(cat_id, 9);
        Constant.cat_id = "1";
        startActivity(i);
    }


    public void tvfestival(View v) {
        Intent i = new Intent(this, Footer_Btn_Activity.class);
//        i.putExtra(cat_id, 9);
        Constant.cat_id = "1";

        startActivity(i);
    }


    public void tvshop(View v) {
        Intent i = new Intent(this, Footer_Btn_Activity.class);
//        i.putExtra(cat_id, 9);
        Constant.cat_id = "1";

        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        Constant.subcategory = "";

    }
}
