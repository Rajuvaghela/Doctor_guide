package example.com.doctor_guide.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import example.com.homepagefordoctor_guide.R;

public class LoginDoctorActivity extends AppCompatActivity implements View.OnClickListener {
Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doctor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                startActivity(new Intent(getApplicationContext(),EditDocProfileActivity.class));
                break;
        }
    }
}
